package com.nmu.mainmenu;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;



 public class rasp extends Activity {
	 TabHost tabHost ;
	 WebView browser;
	 
		Spinner spinner_facult;
		Spinner spinner_group;
		
		ArrayAdapter<String> adapter_group;
		ArrayAdapter<String> adapter_facult;
		
		ListView Weekday_List;
		ListView lec_num_List;
		ListView lecture_List;

		List<String> facult_names = new ArrayList<String>();
		List<String> group_names = new ArrayList<String>();
		List<String> lectures = new ArrayList<String>();
		List<String> weekdays = new ArrayList<String>();
		List<String> lecture_numbers = new ArrayList<String>();
		
		String get_facult = "http://m.nmu.org.ua/ajax/getFacultNames.php",
				get_group = "http://m.nmu.org.ua/ajax/getGroupNames.php?facultName=",
				get_schedule = "http://m.nmu.org.ua/ajax/getSchedule.php?facult=Fname&group=Gname";

		boolean group_flag, schedule_flag;
		
		
	int tab_index,facult,group;
	String teacher;
	@SuppressLint("JavascriptInterface") @Override
	public void onCreate(Bundle savedInstanceState) {
		//сохранение значений
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rasp_zvonkov);
        
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec tabSpec;
        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator("Студенту");
        tabSpec.setContent(R.id.tab1);
        tabHost.addTab(tabSpec);
        
        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setIndicator("Преподавателю");
        tabSpec.setContent(R.id.tab2);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setIndicator("Звонки");
        tabSpec.setContent(R.id.tab3);
        tabHost.addTab(tabSpec);
        
        spinner_facult = (Spinner) findViewById(R.id.spinner_facult);
		spinner_group = (Spinner) findViewById(R.id.spinner_group);


		browser = (WebView) findViewById(R.id.wv);
		browser.getSettings().setJavaScriptEnabled(true);
		browser.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
		browser.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				browser.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
			}
		});
		new get_facultet().execute(get_facult);
		spinner_facult.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				group_flag = true;
				schedule_flag = false;

				browser.loadUrl(get_group
						+ spinner_facult.getSelectedItem().toString());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		spinner_group.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				group_flag = false;
				schedule_flag = true;
				String tmp = get_schedule;
				tmp = tmp.replace("Fname", spinner_facult
						.getSelectedItem().toString());
				tmp = tmp.replace("Gname", spinner_group
						.getSelectedItem().toString());
				Log.d("Logs", tmp);
				//tf.setText(tmp);
				browser.loadUrl(tmp);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

	}

	

	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
        tab_index=tabHost.getCurrentTab();
        outState.putInt("T", tab_index);
		
		
		
	}
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
	    // Always call the superclass so it can restore the view hierarchy
	    super.onRestoreInstanceState(savedInstanceState);
	    tab_index = savedInstanceState.getInt("T");
	    //выставляем вкладку которую запомнили активной. 
	    tabHost.setCurrentTab(tab_index);
	    

	}
	class MyJavaScriptInterface {
		public String processHTML(String html) {
			if (group_flag) {
				new get_group().execute(html);
			}
			if (schedule_flag) {
				new get_schedule().execute(html);
				// Log.d("Logs", html);
			}
			return html;
		}

	}

	// ------------------------------------
	public class get_facultet extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... links) {
			Document doc = null;
			try {
				doc = Jsoup.connect(links[0]).get();
				// Log.d("Logs",doc.text());
				JSONArray facults = new JSONArray(doc.text());
				facult_names.clear();
				for (int i = 0; i < facults.length(); i++) {
					JSONObject facult = facults.getJSONObject(i);
					String facult_name = facult.getString("value");
					facult_names.add(facult_name);
					
					
					Log.d("Logs", "факультет: " + facult_name);
				}
			} catch (Exception e) {
				Log.d("Logs", e.toString());
			}
			return null;
		}

		protected void onPostExecute(String result) {
			try {
				adapter_facult = new ArrayAdapter<String>(getBaseContext(),
						android.R.layout.simple_spinner_item, facult_names);
				spinner_facult.setAdapter(adapter_facult);
			} catch (Exception e) {
				Log.i("Logs", e.toString());
			}
		}

	}

	public class get_group extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... links) {
			Document doc = null;
			try {
				doc = Jsoup.parse(links[0]);
				// Log.d("Logs",doc.text());
				JSONArray groups = new JSONArray(doc.text());
				group_names.clear();
				for (int i = 0; i < groups.length(); i++) {
					JSONObject group = groups.getJSONObject(i);
					String group_name = group.getString("value");
					group_names.add(group_name);
					
					Log.d("Logs", "группа: " + group_name);
				}
			} catch (Exception e) {
				Log.d("Logs", e.toString());
			}
			return null;
		}

		protected void onPostExecute(String result) {
			try {
				adapter_group = new ArrayAdapter<String>(getBaseContext(),
						android.R.layout.simple_spinner_item, group_names);
				spinner_group.setAdapter(adapter_group);
			} catch (Exception e) {
				Log.i("Logs", e.toString());
			}
		}

	}

	public class get_schedule extends AsyncTask<String, Void, String> {
		String lecture;
		String weekday;
		String lec_num;
		@Override
		protected String doInBackground(String... links) {
			Document doc = null;
			try {
				doc = Jsoup.parse(links[0]);
				
				JSONArray all_shedule = new JSONArray(doc.text());
				lecture_numbers.clear();
				lectures.clear();
				lecture_numbers.clear();
				for (int i = 0; i < all_shedule.length(); i++) {
					JSONObject current = all_shedule.getJSONObject(i);
					
					lecture = current.getString("lecture");
					lectures.add(lecture);
					
					Log.d("Logs","Лекция: "+ lecture);
					
					weekday = current.getString("weekday");
					weekdays.add(weekday);
					
					lec_num = current.getString("numberOfLecture");
					lecture_numbers.add(lec_num);
					
				}
				
			} catch (Exception e) {
				Log.d("Logs", e.toString());
			}
			return doc.text();
		}

		protected void onPostExecute(String result) {
			
		}

	}
	     
}