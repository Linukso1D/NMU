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
		 
		 ImageButton btn_freepc = (ImageButton) findViewById(R.id.freepc);
		 btn_freepc.setOnClickListener(this);
		 
		 ImageButton btn_calendar = (ImageButton) findViewById(R.id.calendar);
		 btn_calendar.setOnClickListener(this);
		 
		 ImageButton btn_directory = (ImageButton) findViewById(R.id.directory);
		 btn_directory.setOnClickListener(this);
		 
		 ImageButton btn_blogs = (ImageButton) findViewById(R.id.blogs);
		 btn_blogs.setOnClickListener(this);
		 
		 ImageButton btn_clubs = (ImageButton) findViewById(R.id.clubs);
		 btn_clubs.setOnClickListener(this);

		 
		 ImageButton mapBtn = (ImageButton) findViewById(R.id.maps);
		 mapBtn.setOnClickListener(this);
		 ImageButton btn = (ImageButton) findViewById(R.id.schedule);

		 btn.setOnClickListener(this);

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
	    case R.id.maps:
	    	Intent mapIntent = new Intent(this, Map.class);
	        startActivity(mapIntent);
	    	break;
	    case R.id.freepc:
	    	Intent freepc_Intent = new Intent(this, freepc.class);
	        startActivity(freepc_Intent);
	    	break;
	    case R.id.calendar:
	    	Intent calendar_Intent = new Intent(this, calendar.class);
	        startActivity(calendar_Intent);
	    	break;
	    case R.id.directory:
	    	Intent directory_Intent = new Intent(this, teachers.class);
	        startActivity(directory_Intent);
	    	break;
	    case R.id.blogs:
	    	Intent blogs_Intent = new Intent(this, blogs.class);
	        startActivity(blogs_Intent);
	    	break;
	    	
	    case R.id.clubs:
	    	Intent clubs_Intent = new Intent(this, clubs.class);
	        startActivity(clubs_Intent);
	    	break;

	    default: 	      break;

	    }
	  }
}
