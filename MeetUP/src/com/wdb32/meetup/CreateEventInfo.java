package com.wdb32.meetup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class CreateEventInfo extends Activity {
	EditText idfield;
	TimePicker time;
	DatePicker date;
	Button button;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_event_info);
		idfield = (EditText) findViewById(R.id.idfield);
		date = (DatePicker) findViewById(R.id.date);
		time = (TimePicker) findViewById(R.id.time);
		button = (Button) findViewById(R.id.button);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("id", idfield.getText().toString());
				intent.putExtra("date",
						date.getMonth() + "" + date.getDayOfMonth());
				intent.putExtra("time",
						time.getCurrentHour() + ":" + time.getCurrentMinute());
				setResult(RESULT_OK, intent);
				finish();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_event_info, menu);
		return true;

	}

}
