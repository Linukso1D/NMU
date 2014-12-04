package com.nmu.mainmenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;


public class MainActivity extends Activity implements OnClickListener {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 
		 ImageButton btn_rasp = (ImageButton) findViewById(R.id.schedule);
		 btn_rasp.setOnClickListener(this);

		 ImageButton btn_news = (ImageButton) findViewById(R.id.news);
		 btn_news.setOnClickListener(this);
		 
		 ImageButton btn_teachers = (ImageButton) findViewById(R.id.teachers);
		 btn_teachers.setOnClickListener(this);
		 
		 ImageButton btn_webcams = (ImageButton) findViewById(R.id.webcams);
		 btn_webcams.setOnClickListener(this);
		 }
	@Override
	public void onClick(View v) {
	    switch (v.getId()) {

	    case R.id.schedule:
	        Intent intent_rasp = new Intent(this, rasp.class);
	        startActivity(intent_rasp);
	        break;
	        
	    case R.id.news:
	    	Intent intent_news = new Intent(this, news.class);
	        startActivity(intent_news);
	        break;
	    case R.id.teachers:
	    	Intent intent_teachers = new Intent(this, teachers.class);
	        startActivity(intent_teachers);
	        break;
	    case R.id.webcams:
	    	Intent intent_webcams = new Intent(this, webcams.class);
	        startActivity(intent_webcams);
	        break;
	    default: 	      break;
	    }
	  }
}
