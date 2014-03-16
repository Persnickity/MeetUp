package com.wdb32.meetup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddToGroup extends Activity {
	EditText idfield;
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_to_group);
		idfield = (EditText) findViewById(R.id.idfield);
		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("id", idfield.getText().toString());
				setResult(RESULT_OK, intent);
				finish();
			}
		});
	}

}
