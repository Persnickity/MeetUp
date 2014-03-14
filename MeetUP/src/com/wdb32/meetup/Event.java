package com.wdb32.meetup;

import com.google.gson.JsonObject;

public class Event {
	double lat, lon;
	String name;

	public Event() {

	}

	public Event(JsonObject object) {

		name = object.get("name").getAsString();
		lat = object.get("Latitude").getAsDouble();
		lon = object.get("longitude").getAsDouble();
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
}
