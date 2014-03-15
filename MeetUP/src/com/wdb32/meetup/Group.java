package com.wdb32.meetup;

import com.google.gson.JsonObject;

public class Group {
	String id, modNum;

	public Group() {
		id = null;
		modNum = null;
	}

	public Group(JsonObject object) {
		id = object.get("id").getAsString();
		modNum = object.get("modPhoneNumber").getAsString();
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

	public String toString() {
		return id + "\n" + modNum;
	}
}
