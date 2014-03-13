package com.wdb32.meetup;

public class Event {
	double lat, lon;
	String name;

	public Event() {

	}

	public Event(String nam, double latitude, double longitude) {
		this.name = nam;
		this.lat = latitude;
		this.lon = longitude;
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
