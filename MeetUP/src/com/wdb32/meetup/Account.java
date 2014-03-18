package com.wdb32.meetup;

import com.google.gson.JsonObject;

public class Account {
	String name, num;

	public Account() {
		name = null;
		num = null;
	}

	public Account(JsonObject object) {
	try{
		name = object.get("name").getAsString();
		num = object.get("phoneNumber").getAsString();
	}
	catch(Exception e){return;}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String toString() {
		return name + "\n" + num;
	}
}
