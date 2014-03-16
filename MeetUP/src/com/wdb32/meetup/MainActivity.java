package com.wdb32.meetup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity {
	public String rootCause = "Main Activity";
	public double lat, lon;
	public ListView view;
	public GroupJson groupJson = new GroupJson();
	public AccountJson accountJson = new AccountJson();
	public EventJson eventJson;
	public ArrayList<String> text = new ArrayList<String>();
	public ArrayList<Account> accounts = new ArrayList<Account>();
	public ArrayList<Event> events = new ArrayList<Event>();
	public ArrayList<Group> groups = new ArrayList<Group>();
	public Button updateGPSD, createEvent, editEvent, createGroup, addToGroup,
			checkin;
	private ArrayAdapter<String> adapter;
	String myPhoneNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			doThis();
		} catch (Exception e) {
			Log.i(rootCause, e.toString());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void doThis() throws Exception {
		getPhoneNumber();

		accountJson.execute(myPhoneNumber).get();// check account
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, text);
		view = (ListView) findViewById(R.id.list);
		view.setAdapter(adapter);
		view.invalidate();
		linkButtons();
	}

	public void getPhoneNumber() {
		TelephonyManager tMgr = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		myPhoneNumber = tMgr.getLine1Number();
	}

	public void linkButtons() {
		updateGPSD = (Button) findViewById(R.id.gpsButton);
		createEvent = (Button) findViewById(R.id.createEvent);
		editEvent = (Button) findViewById(R.id.editEvent);
		createGroup = (Button) findViewById(R.id.createGroup);
		addToGroup = (Button) findViewById(R.id.addToGroup);
		checkin = (Button) findViewById(R.id.CheckIn);
		buttonsOnClickSet();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			doThis();
		} catch (Exception e) {
		}
		eventJson = new EventJson();
		if (requestCode == 1) {// createEvent
			if (resultCode == RESULT_OK) {
				try {
					String date = data.getStringExtra("date");
					String time = data.getStringExtra("time");
					String id = data.getStringExtra("id");
					eventJson = new EventJson();
					// groupID,modnumber,date,time,lat,lon
					events = eventJson.execute("CreateEvent", id,
							myPhoneNumber, date, time).get();
				} catch (Exception e) {
					Log.i("Failed on Return from Create Event Screen",
							e.toString());
				}
			}
		}
		if (requestCode == 2) {// edit Event
			if (resultCode == RESULT_OK) {
				try {
					String date = data.getStringExtra("date");
					String time = data.getStringExtra("time");
					String id = data.getStringExtra("id");
					eventJson = new EventJson();
					// EventID,modnumber,date,time,lat,lon
					events = eventJson.execute("EditEvent", id, myPhoneNumber,
							date, time).get();
				} catch (Exception e) {
					Log.i("Failed on Return from Edit Event Screen",
							e.toString());
				}
			}
		}
		if (requestCode == 3) {// add To Group
			if (resultCode == RESULT_OK) {
				try {
					groupJson = new GroupJson();
					String id = data.getStringExtra("id");
					eventJson = new EventJson();
					// myPhoneNumber,GroupID
					events = eventJson.execute("addToGroup", myPhoneNumber, id)
							.get();
				} catch (Exception e) {
					Log.i("Failed on Return from addToGroup Screen",
							e.toString());
				}
			}
		}
	}

	private void buttonsOnClickSet() {
		updateGPSD.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {// getting location in button click
				updateGPS(); // then update display
				text.clear();
				text.add("lat:" + lat);
				text.add("lon:" + lon);
				adapter.notifyDataSetChanged();
			}
		});
		createEvent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent createInfo = new Intent(MainActivity.this,
						CreateEventInfo.class);
				startActivityForResult(createInfo, 1);
			}
		});
		editEvent.setOnClickListener(new View.OnClickListener() {
			@Override
			//
			public void onClick(View v) {
				Intent createInfo = new Intent(MainActivity.this,
						CreateEventInfo.class);
				startActivityForResult(createInfo, 2);
			}
		});
		createGroup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				try {
					groups = groupJson.execute("createGroup").get();
					text.clear();
					text.add("Group Created");
					adapter.notifyDataSetChanged();
				} catch (Exception e) {
					Log.i("Failure in create Group", e.toString());
				}
			}
		});
		addToGroup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent createInfo = new Intent(MainActivity.this,
						AddToGroup.class);
				startActivityForResult(createInfo, 3);
			}
		});
		checkin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				eventJson = new EventJson();
				updateGPS();
				// phone,eventid,date,time,lat,lon
				Calendar calendar = Calendar.getInstance();

				SimpleDateFormat dateFormat = new SimpleDateFormat(
						"dd:MMM:yyyy");
				String date = dateFormat.format(calendar.getTime());
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
				String time = timeFormat.format(calendar.getTime());
				eventJson.execute("checkIn", myPhoneNumber, "id", date, time,
						lat + "", lon + "");
				text.clear();
				text.add("Checked In for Event");

				adapter.notifyDataSetChanged();
			}

		});
	}

	public void printToScreen(Object[] arrayList) {
		text.clear();
		for (int x = 0; x < arrayList.length; x++) {
			text.add(arrayList[x].toString());
		}

		adapter.notifyDataSetChanged();
	}

	public double calculateMiles(double latitude, double longitude) {
		double radians = 6317;
		double x = latitude, y = longitude;
		double dlat = Math.toRadians(y - lat);
		double dlon = Math.toRadians(x - lon);
		double lat1 = Math.toRadians(lat);
		double lat2 = Math.toRadians(y);

		double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.sin(dlon / 2)
				* Math.sin(dlon / 2) * Math.cos(lat1) * Math.cos(lat2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return c * radians;
	}

	public double getDistance(double longitude, double latitude) {
		double x1 = longitude, y1 = latitude;
		double dx = (lon - x1) * (lon - x1);

		double dy = (lat - y1) * (lat - y1);
		return Math.sqrt(dx + dy);
	}

	public void updateGPS() {
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		List<String> providers = lm.getProviders(true);
		Location l = null;
		for (int i = providers.size() - 1; i >= 0; i--) {
			l = lm.getLastKnownLocation(providers.get(i));
			if (l != null)
				break;
		}
		if (l != null) {
			lat = l.getLatitude();
			lon = l.getLongitude();
		}
	}
}
