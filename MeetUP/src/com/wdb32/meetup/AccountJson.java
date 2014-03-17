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

public class AccountJson extends AsyncTask<String, Void, ArrayList<Account>> {

	public static final String server = "http://68.38.173.34:9001";
	public static final String info = "info";
	public static final String createAccount = "createUser";
	public static final String getAllUsers = "getAllUsers";
	public static final ArrayList<Account> accounts = new ArrayList<Account>();
	public static final String getData = "getData";

	@Override
	protected ArrayList<Account> doInBackground(String... args) {
		if (args[0].equals("getAll")) {// get All
			try {
				getAll();
			} catch (Exception e) {
				Log.i("GetAllAccounts failed", e.toString());
			}
		} else if (args[0].equals("createAccount")) {// Create
			try {
				createAccount(args[1], args[2]);
			} catch (Exception e) {
				Log.i("CreateAccount failed", e.toString());
			}
		} else if (args[0].equals("CheckMemberShip")) {// check
			try {
				checkMembership(args[1]);
			} catch (Exception e) {
				Log.i("Check Account failed", e.toString());
			}
			return null;
		}
		return accounts;
	}

	private void checkMembership(String number) {
		accounts.clear();
		for (int x = 0; x < accounts.size(); x++) {
			if (accounts.get(x).getNum().equals(number)) {
				return;
			}
		}
		accounts.clear();
		return;
	}

	public static void createAccount(String name, String number)
			throws Exception {
		StringBuffer response = new StringBuffer();
		String inputLine;
		BufferedReader in;
		URL url;
		HttpURLConnection request;

		url = new URL(server + "/" + createAccount + "/" + name + "/" + number);
		request = (HttpURLConnection) url.openConnection();
		in = new BufferedReader(new InputStreamReader(request.getInputStream()));
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		JsonParser jp = new JsonParser();
		JsonElement root = jp.parse(response.toString());
		try {
			accounts.add(new Account(root.getAsJsonObject()));
		} catch (Exception e) {
			return;
		}
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
		JsonArray array = root.getAsJsonObject().get("Users").getAsJsonArray();
		for (int x = 0; x < array.size(); x++) {
			accounts.add(new Account(array.get(0).getAsJsonObject()));
		}
	}
}
