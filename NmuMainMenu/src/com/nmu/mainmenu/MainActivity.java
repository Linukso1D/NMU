package com.nmu.mainmenu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import rembo.network.urss.RSSactivity;
import transport.Transport;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class MainActivity extends Activity implements OnClickListener {
int TimeTo;
int TimeToP;
String VerTex,HorTex;
Timer timer;
SimpleDateFormat NumberMonth = null;
SimpleDateFormat Time = null;
SimpleDateFormat Time2 = null;
Calendar newCal = new GregorianCalendar();
	public String GetDay(int now) {
		now -= 1;
		String day[] = { " воскресенье ", " понедельник ", " вторник ",
				" среда ", " четверг ", " пятница ", " суббота " };
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
		
		if(now%2==1) {return " Знаменатель (второй) ";}
		else {return " Числитель (первый) ";}

	}

	public String Lent(int now) {
		TimeTo=now;
		TimeToP=now;
		String tmp = "";
		if (now >= 800 && now < 920) {
			TimeTo=920-now;
			TimeToP=0;
			tmp= " 1-я пара, ";
		}
		else if (now >= 935 && now < 1055) {
			TimeTo=1055-now;
			TimeToP=0;
			tmp= " 2-я пара, ";
		}
		else if (now >= 1120 && now < 1240) {
			TimeTo=1240-now;
			TimeToP=0;
			tmp= " 3-я пара, ";
		}
		else if (now >= 1255 && now < 1415) {
			TimeTo=1415-now;
			TimeToP=0;
			tmp= " 4-я пара, ";
		}
		else if (now >= 1430 && now < 1550) {
			TimeTo=1550-now;
			TimeToP=0;
			tmp= " 5-я пара, ";
		}
		else if (now >= 1605 && now < 1725) {
			TimeTo=1725-now;
			TimeToP=0;
			tmp= " 6-я пара, ";
		}
		else if (now >=1735 && now < 1855) {
			TimeTo=1855-now;
			TimeToP=0;
			tmp= " 7-я пара, ";
		}
		else if (now > 1905 && now < 2025) {
			TimeTo=2025-now;
			TimeToP=0;
			tmp= " 8-я пара, ";
		}
		
		else if (now < 740 && now > 2045) {
			TimeTo=0;
			TimeToP=0;
			
			tmp= "";

			return tmp;
		}
		else if (now >= 920 && now < 935) {
			TimeToP=935-now;
			TimeTo=0;
			tmp= " Перемена, ";
		}
		else if (now >= 1055 && now < 1120) {
			TimeToP=1120-now;
			TimeTo=0;
			tmp= " Перемена, ";
		}
		else if (now >= 1240 && now < 1255) {
			TimeToP=1255-now;
			TimeTo=0;
			tmp= " Перемена, ";
		}
		else if (now >= 1415 && now < 1430) {
			TimeToP=1430-now;
			TimeTo=0;
			tmp= " Перемена, ";
		}
		else if (now >= 1550 && now < 1605) {
			TimeToP=1605-now;
			TimeTo=0;
			tmp= " Перемена, ";
		}
		else if (now >= 1725 && now < 1735) {
			TimeToP=1735-now;
			TimeTo=0;
			tmp= " Перемена, ";
		}
		else if (now >= 1855 && now < 1905) {
			TimeToP=1905-now;
			TimeTo=0;
			tmp= " Перемена, ";
		}
		else if (TimeToP>40 && TimeTo>80) {
			TimeToP=0;
			TimeTo=0;
			tmp= " ";
		}
		if (TimeTo>59){TimeTo-=(TimeTo/60)*40;}
		if (TimeToP>59){TimeToP-=(TimeToP/60)*40;}
		return tmp;
		
	}
	TextView ShowInfText;
	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final Handler mHandler = new Handler();
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
		
		
		
		
		
		ShowInfText = (TextView) findViewById(R.id.main_text);
		ShowInfText.setOnClickListener(this);
		// функц main_text
		// инициализация-----

		
		
		timer= new Timer();
  	  timer.schedule(new TimerTask() { // Определяем задачу
		    @Override
		    public void run() {
		    	Thread t = new Thread()  {
		              @Override
		              public void run() {
		                  try {
		                	 
		              		Date d = new Date();
		            		// ----------------------------------
		            		
		            		Date currentDate = new Date();
		            		// число и месяц

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
		            		Log.d("for", "Thead"+parsetime);
		            		//WEEK_OF_YEAR
		            		 
		            		 VerTex=Time.format(currentDate)+","+Lent(parsetime)+NumberMonth.format(currentDate)+","
		            				 +GetDay(day)+"\n"+Week(week)+", "+week2+"-я неделя ";
		            		 HorTex=Time.format(currentDate)+","+Lent(parsetime)+NumberMonth.format(currentDate)+","
		            				 +GetDay(day)+Week(week)+", "+week2+"-я неделя ";
		            	 	 Runnable done = new Runnable() {
		            	         public void run() {
		            	        	 if(getScreenOrientation()==1){
		         		            	
		 		            			ShowInfText.setText(VerTex	 );
		 		            		
		 		            		
		 		            		 Log.d("for", "Set text "  +VerTex);
		 		            		}
		 		            		else
		 		            		{
		 		            			
		 		           			ShowInfText.setText(HorTex );
		 		            		}
		            	             }
		            	         };
		            		//out
		            		 runOnUiThread(done);
		            		

		                  } catch (Exception e) { e.printStackTrace(); } 
		              };
		          };
		          t.start();
		    };
		}, 0, 8000);
  	  
 
  	  
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.schedule:
			Intent intent_rasp = new Intent(this, rasp.class);
			startActivity(intent_rasp);
			break;
		case R.id.main_text:
			
		
			
			if(TimeTo!=0){
			Toast toast = Toast.makeText(getApplicationContext(), 
					   "До конца пары осталось " + (TimeTo > 59 ? "1 ч. " : "") + Integer.toString(TimeTo%60) + " мин.", Toast.LENGTH_SHORT); 
					toast.show(); 
					
			}
			if(TimeToP!=0){
				Toast toast = Toast.makeText(getApplicationContext(), 
						   "До конца перемены осталось "+TimeToP+" мин.", Toast.LENGTH_SHORT); 
						toast.show(); 
				}
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
