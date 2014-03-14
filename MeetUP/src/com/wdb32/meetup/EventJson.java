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
import com.google.gson.JsonParser;

public class EventJson extends AsyncTask<String, Void, ArrayList<Event>> {
	public static final String server = "http://68.38.173.34:9001";
	public static final String info = "info";
	public static final String createEvent = "createEvent";
	public static final String editEvent = "editEvent";
	public static final String getEventsByID = "getEventsByID";
	public static final String getAllEvents = "getAllEvents";

	public static final ArrayList<Event> events = new ArrayList<Event>();

	protected ArrayList<Event> doInBackground(String... params) {

		return events;
	}

	private static void getAllEvents() throws Exception {

		StringBuffer response = new StringBuffer();
		String inputLine;
		BufferedReader in;
		URL url;
		HttpURLConnection request;

		url = new URL(server + "/" + getAllEvents);
		request = (HttpURLConnection) url.openConnection();
		System.out.println(request.toString());
		in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		JsonParser jp = new JsonParser();
		JsonElement root = jp.parse(response.toString());
		JsonArray array = root.getAsJsonArray();
		for (int x = 0; x < array.size(); x++) {
			events.add(new Event(array.get(x).getAsJsonObject()));
			System.out.println(events.get(x));
		}
		System.out.println(root.toString());
	}

	private static void getEventsByID(String id) throws Exception {
		StringBuffer response = new StringBuffer();
		String inputLine;
		BufferedReader in;
		URL url;
		HttpURLConnection request;

		url = new URL(server + "/" + createEvent + "/" + id);
		request = (HttpURLConnection) url.openConnection();
		in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		JsonParser jp = new JsonParser();
		JsonElement root = jp.parse(response.toString());
		System.out.println(root.toString());

	}

	private static void editEvenzt(int id, String modNumber, String date,
			String time, double lat, double lon) throws Exception {
		StringBuffer response = new StringBuffer();
		String inputLine;
		BufferedReader in;
		URL url;
		HttpURLConnection request;

		url = new URL(server + "/" + createEvent + "/" + id + "/" + modNumber
				+ "/" + date + "/" + time + "/" + lat + "/" + lon);
		request = (HttpURLConnection) url.openConnection();
		in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		JsonParser jp = new JsonParser();
		JsonElement root = jp.parse(response.toString());
		System.out.println(root.toString());

	}

	private static void createEvent(int id, String modNumber, String date,
			String time, double lat, double lon) throws Exception {
		StringBuffer response = new StringBuffer();
		String inputLine;
		BufferedReader in;
		URL url;
		HttpURLConnection request;

		url = new URL(server + "/" + createEvent + "/" + id + "/" + modNumber
				+ "/" + date + "/" + time + "/" + lat + "/" + lon);
		request = (HttpURLConnection) url.openConnection();
		in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		JsonParser jp = new JsonParser();
		JsonElement root = jp.parse(response.toString());
		System.out.println(root.toString());
	}

	public static void getInfo() throws Exception {
		StringBuffer response = new StringBuffer();
		String inputLine;
		BufferedReader in;
		URL url;
		HttpURLConnection request;

		url = new URL(server + "/" + info);
		request = (HttpURLConnection) url.openConnection();
		in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		JsonParser jp = new JsonParser();
		JsonElement root = jp.parse(response.toString());
		System.out.println(root.toString());
	}

}
