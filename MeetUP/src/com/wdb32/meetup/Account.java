package com.wdb32.meetup;

import com.google.gson.JsonObject;

public class Account {
	public Account() {
	}

	public Account(JsonObject json) {
		System.out.println(json.getAsString());
	}

}
