package com.wdb32.meetup;

import java.util.List;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	String rootCause = "Main Activity";
	double lat, lon;
	TextView view;
	Button updateGPSD, createEvent, editEvent, createGroup, addToGroup,
			Checkin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		doThis();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		doThis();
		return true;
	}

	public void doThis() {
		TelephonyManager tMgr = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		String myPhoneNumber = tMgr.getLine1Number();
		Log.i(rootCause, myPhoneNumber);
		view = (TextView) findViewById(R.id.gpstemp);
		linkButtons();
	}

	public void linkButtons() {
		updateGPSD = (Button) findViewById(R.id.gpsButton);
		createEvent = (Button) findViewById(R.id.createEvent);
		editEvent = (Button) findViewById(R.id.editEvent);
		createGroup = (Button) findViewById(R.id.createGroup);
		addToGroup = (Button) findViewById(R.id.addToGroup);

		buttonsOnClickSet();
	}

	private void buttonsOnClickSet() {
		updateGPSD.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {// getting location in button click
				updateGPS(); // then update display
				view.setText("lat:" + lat + "\nlon" + lon);
				view.invalidate();
			}
		});
		createEvent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		editEvent.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		createGroup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
		addToGroup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
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
