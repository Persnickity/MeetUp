package com.wdb32.meetup;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class EventJson extends AsyncTask<String, Void, ArrayList<Event>> {
	public static final String server = "http://68.38.173.34:9001";
	public static final String info = "info";
	public static final String createEvent = "createEvent";
	public static final String editEvent = "editEvent";
	public static final String getEventsByID = "getEventsByID";
	public static final String getAllEvents = "getAllEvents";
	public static final String getData = "getData";
	public static final String checkIn = "checkIn";
	public static final ArrayList<Event> events = new ArrayList<Event>();

	protected ArrayList<Event> doInBackground(String... args) {
		if (args[0].equals("getAllEvents")) {// get All
			try {
				getAll();
			} catch (Exception e) {
				Log.i("get All Events Failed", e.toString());
			}
		} else if (args[0].equals("EditEvent")) {// edit
			try {
				editEvent(args[1], args[2], args[3], args[4]);
			} catch (Exception e) {
				Log.i("Edit Event failed", e.toString());
			}
		} else if (args[0].equals("CreateEvent")) {// create
			try {
				create(args[1], args[2], args[3], args[4]);
			} catch (Exception e) {
				Log.i("Edit Event failed", e.toString());
			}
		} else {
			if (args[0].equals("CheckIn")) {// create
				try {
					checkIn(args[1], args[2], args[3], args[4], args[5],
							args[6]);
				} catch (Exception e) {
					Log.i("Check In failed", e.toString());
				}
			} else {
				return null;
			}
		}
		return events;
	}

	private static void checkIn(String number, String id, String date,
			String time, String lat, String lon) throws Exception {
		StringBuffer response = new StringBuffer();
		String inputLine;
		BufferedReader in;
		URL url;
		HttpURLConnection request;

		url = new URL(server + "/" + checkIn + "/" + number + "/" + id + "/"
				+ date + "/" + time + "/" + lat + "/" + lon);
		request = (HttpURLConnection) url.openConnection();
		in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
	}

	private void editEvent(String id, String modNumber, String date, String time)
			throws Exception {
		StringBuffer response = new StringBuffer();
		String inputLine;
		BufferedReader in;
		URL url;
		HttpURLConnection request;
		url = new URL(server + "/" + editEvent + "/" + id + "/" + modNumber
				+ "/" + date + "/" + time);
		request = (HttpURLConnection) url.openConnection();
		in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
	}

	private static void create(String id, String modNumber, String date,
			String time) throws Exception {
		StringBuffer response = new StringBuffer();
		String inputLine;
		BufferedReader in;
		URL url;
		HttpURLConnection request;

		url = new URL(server + "/" + createEvent + "/" + id + "/" + modNumber
				+ "/" + date + "/" + time);
		request = (HttpURLConnection) url.openConnection();
		in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
	}

	public static void getAll() throws Exception {
		StringBuffer response = new StringBuffer();
		String inputLine;
		BufferedReader in;
		URL url;
		HttpURLConnection request;

		url = new URL(server + "/" + getData);
		request = (HttpURLConnection) url.openConnection();
		in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();
		JsonParser jp = new JsonParser();
		JsonElement root = jp.parse(response.toString());
		JsonArray array = root.getAsJsonObject().get("Events").getAsJsonArray();
		for (int x = 0; x < array.size(); x++) {
			events.add(new Event(array.get(0).getAsJsonObject()));
		}

		System.out.println(root.toString());

	}
}
