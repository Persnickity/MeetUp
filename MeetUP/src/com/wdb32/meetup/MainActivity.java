package com.wdb32.meetup;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	String rootCause = "Main Activity";
	double lat, lon;
	TextView view;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i("onCreate", "Bundle");
		doThis();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		Log.i("onCreate", "Menu");
		doThis();
		return true;
	}

	public void doThis() {
		getGPS();
		Log.i("TESTING", lat + "," + lon);
		view = new TextView(this);
		view.setId(R.id.gpstemp);
		view.setText(lat + "," + lon);
		view.invalidate();
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

	public void getGPS() {
		// http://developer.android.com/reference/android/location/LocationManager.html
		try {
			Log.i(rootCause, "Getting GPS");

			LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
			Location location = lm
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			Log.i(rootCause, "location=" + location.toString());
			lon = location.getLongitude();
			lat = location.getLatitude();
			Log.i(rootCause, lat + "," + lon);
		} catch (Exception e) {
			Log.i(rootCause, "ERROR IS=" + e.toString());
		}
	}
}
