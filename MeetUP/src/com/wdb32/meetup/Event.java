package com.wdb32.meetup;

import com.google.gson.JsonObject;

public class Event {
	double lat, lon;
	String name, id, modNum, date, time;

	public Event() {
		lat = 0.0;
		lon = 0.0;
		name = null;
		id = null;
		modNum = null;
		date = null;
		time = null;
	}

	public Event(JsonObject object) {

		name = object.get("name").getAsString();
		lat = object.get("Latitude").getAsDouble();
		lon = object.get("longitude").getAsDouble();
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

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name + "\n" + id + "\n" + modNum + "\n" + date + "\n" + time
				+ "\n" + lat + "\n" + lon;
	}
}
