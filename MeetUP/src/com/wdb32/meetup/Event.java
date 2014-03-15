package com.wdb32.meetup;

import com.google.gson.JsonObject;

public class Event {
	String name, id, modNum, date, time;

	public Event() {
		name = null;
		id = null;
		modNum = null;
		date = null;
		time = null;
	}

	public Event(JsonObject object) {

		name = object.get("name").getAsString();
		id = object.get("id").getAsString();
		date = object.get("date").getAsString();
		time = object.get("time").getAsString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getModNum() {
		return modNum;
	}

	public void setModNum(String modNum) {
		this.modNum = modNum;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name + "\n" + id + "\n" + modNum + "\n" + date + "\n" + time;
				
	}
}
