package com.wdb32.meetup;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import android.os.AsyncTask;
import android.util.Log;

public class GroupJson extends AsyncTask<String, Void, ArrayList<Group>> {

	public static final String server = "http://68.38.173.34:9001";
	public static final String info = "info";
	public static final String createGroup = "createGroup";
	public static final String addToGroup = "addToGroup";
	public static final ArrayList<Group> groups = new ArrayList<Group>();
	public static final String where = "GroupJson Failes";
	public static final String getData = "getData";

	protected ArrayList<Group> doInBackground(String... args) {
		if (args[0].equals("createGroup")) {// create
			try {
				create();
			} catch (Exception e) {
				Log.i("Create Group failed", e.toString());
			}
		} else if (args[0].equals("addToGroup")) {// add To
			try {
				add(args[1], args[2]);
			} catch (Exception e) {
				Log.i("Add To Group failed", e.toString());
			}
		} else if (args[0].equals("getAllGroups")) {
			try {
				getAll();
			} catch (Exception e) {
				Log.i("Add To Group failed", e.toString());
			}
		} else {
			return null;
		}
		return groups;
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
		JsonArray array = root.getAsJsonObject().get("Groups").getAsJsonArray();
		for (int x = 0; x < array.size(); x++) {
			groups.add(new Group(array.get(0).getAsJsonObject()));
		}

		System.out.println(root.toString());

	}

	private void add(String number, String id) throws Exception {
		StringBuffer response = new StringBuffer();
		String inputLine;
		BufferedReader in;
		URL url;
		HttpURLConnection request;

		url = new URL(server + "/" + addToGroup + "/" + number + "/" + id);
		request = (HttpURLConnection) url.openConnection();
		in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
	}

	private void create() throws Exception {
		StringBuffer response = new StringBuffer();
		String inputLine;
		BufferedReader in;
		URL url;
		HttpURLConnection request;

		url = new URL(server + "/" + createGroup);
		request = (HttpURLConnection) url.openConnection();
		in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
	}

}
