package com.wdb32.meetup;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import android.os.AsyncTask;
import android.util.Log;

public class EventJson extends AsyncTask<Void, Void, Void> {
	public static String server = "http://68.38.173.34:9001";
	public static String info = "info";

	@Override
	protected Void doInBackground(Void... params) {
		StringBuffer response = new StringBuffer();
		String inputLine;
		BufferedReader in;
		URL url;
		HttpURLConnection request;
		try {
			url = new URL(server + "/" + info);
			request = (HttpURLConnection) url.openConnection();
			in = new BufferedReader(new InputStreamReader(
					request.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			JsonParser jp = new JsonParser();
			JsonElement root = jp.parse(response.toString());

			System.out.println(root.toString());
		} catch (Exception e) {
			Log.i("Error", e.toString());
		}
		return null;
	}

}
