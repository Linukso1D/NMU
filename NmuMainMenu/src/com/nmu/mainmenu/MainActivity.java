package com.nmu.mainmenu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import rembo.network.urss.RSSactivity;
import transport.Transport;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

	public String GetDay(int now) {
		now -= 1;
		String day[] = { " воскресенье ", " понедельник ", " вторник ",
				" среда ", " четверг ", " п€тница ", " суббота " };
		return day[now];

	}
	
	private int getScreenOrientation(){    
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
		    return 1;
		else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
			return 2;
		else
			return 0;
	}
	public String Week(int now) {
		
		if(now%2==1) {return " «наменатель (второй) ";}
		else {return " „ислитель (первый) ";}

	}

	public String Lent(int now) {
		if (now > 800 && now < 920) {
			return " 1-€ пара ";
		}
		if (now > 935 && now < 1055) {
			return " 2-€ пара ";
		}
		if (now > 1120 && now < 1240) {
			return " 3-€ пара ";
		}
		if (now > 1255 && now < 1415) {
			return " 4-€ пара ";
		}
		if (now > 1430 && now < 1550) {
			return " 5-€ пара ";
		}
		if (now > 1605 && now < 1725) {
			return " 6-€ пара ";
		}
		if (now > 1735 && now < 1855) {
			return " 7-€ пара ";
		}
		if (now > 1905 && now < 2025) {
			return " 8-€ пара ";
		}
		if (now < 740 && now > 2045) {
			return "";
		}
		return " ѕеремена ";

	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ImageButton transportBtn = (ImageButton) findViewById(R.id.transport);
		transportBtn.setOnClickListener(this);
		ImageButton btn_rasp = (ImageButton) findViewById(R.id.schedule);
		btn_rasp.setOnClickListener(this);

		ImageButton btn_news = (ImageButton) findViewById(R.id.news);
		btn_news.setOnClickListener(this);

		ImageButton btn_library = (ImageButton) findViewById(R.id.library);
		btn_library.setOnClickListener(this);

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
		
		
		
		TextView ShowInfText = (TextView) findViewById(R.id.main_text);

		// функц main_text
		// инициализаци€-----
		SimpleDateFormat NumberMonth = null;
		SimpleDateFormat Time = null;
		SimpleDateFormat Time2 = null;
		Calendar newCal = new GregorianCalendar();
		Date d = new Date();
		// ----------------------------------
		ShowInfText.setText(" d= " + d.toString());
		Date currentDate = new Date();
		// число и мес€ц

		NumberMonth = new SimpleDateFormat("dd MMMM ");
		Time = new SimpleDateFormat("HH:mm");
		Time2 = new SimpleDateFormat("HH mm");
		// день недели
		newCal.setTime(newCal.getTime());
		int day = newCal.get(Calendar.DAY_OF_WEEK);
		int week =  newCal.get(Calendar.WEEK_OF_YEAR);
		int parsetime = (Integer.parseInt(Time2.format(currentDate).replaceAll(
				" ", "")));

		int week2=17+week;
		if(week2>35){week2-=(35+17);}
		
		//WEEK_OF_YEAR
		 

		//out
		if(getScreenOrientation()==1){
		 ShowInfText.setText(Time.format(currentDate)+","+Lent(parsetime)+", "+NumberMonth.format(currentDate)+","
				 +GetDay(day)+"\n"+Week(week)+", "+week2+"-€ недел€ "+""	 );
		}
		else
		{
			ShowInfText.setText(Time.format(currentDate)+","+Lent(parsetime)+", "+NumberMonth.format(currentDate)+","
					 +GetDay(day)+Week(week)+", "+week2+"-€ недел€ "+""	 );
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.schedule:
			Intent intent_rasp = new Intent(this, rasp.class);
			startActivity(intent_rasp);
			break;
			

		case R.id.news:
			Intent intent_news = new Intent(this, RSSactivity.class);
			startActivity(intent_news);
			break;
		case R.id.library:
			Intent intent_library = new Intent(this, library.class);
			startActivity(intent_library);
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
			Intent directory_Intent = new Intent(this, navi.class);
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
		case R.id.transport:
			Intent transportIntent = new Intent(this, Transport.class);
			startActivity(transportIntent);
			break;


		default:
			break;

		}
	}
}
