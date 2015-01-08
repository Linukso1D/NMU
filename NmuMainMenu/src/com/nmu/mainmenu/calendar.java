package com.nmu.mainmenu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class calendar extends Activity{
	String calendar_text;
	TextView tv_calendar_text;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar);
		calendar_text = "≈сли ты знаешь, что будет происходить в ближайшее врем€, то скорей спеши сообщить нам" 
						+"по электронной почте: nmutempus@gmail.com";
		tv_calendar_text = (TextView) findViewById(R.id.textView2);
		tv_calendar_text.setText(calendar_text);
	}

}
