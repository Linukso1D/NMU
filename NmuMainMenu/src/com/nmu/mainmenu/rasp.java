package com.nmu.mainmenu;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class rasp extends Activity {
	TabHost tabHost;
	Button btn_search;

	SearchView sv;

	TextView tv_monday;
	TextView tv_tuesday;
	TextView tv_wednesday;
	TextView tv_thursday;
	TextView tv_friday;

	TextView monday;
	TextView tuesday;
	TextView wednesday;
	TextView thursday;
	TextView friday;

	TextView tv_teach_monday;
	TextView tv_teach_tuesday;
	TextView tv_teach_wednesday;
	TextView tv_teach_thursday;
	TextView tv_teach_friday;

	Spinner spinner_facult;
	Spinner spinner_group;

	ArrayAdapter<String> adapter_group;
	ArrayAdapter<String> adapter_facult;

	List<String> facult_names = new ArrayList<String>();
	List<String> group_names = new ArrayList<String>();

	ProgressDialog pd;

	String get_facult = "http://m.nmu.org.ua/ajax/getFacultNames.php",
			get_group = "http://m.nmu.org.ua/ajax/getGroupNames.php?facultName=",
			get_schedule = "http://m.nmu.org.ua/ajax/getSchedule.php?facult=Fname&group=Gname",
			get_teacher_shedule = "http://m.nmu.org.ua/ajax/getTeacherSchedule.php?lecturerName=";

	boolean group_flag, schedule_flag, schedule_teacher_flag;

	int tab_index, facult, group;
	String teacher;

	public void onCreate(Bundle savedInstanceState) {
		// сохранение значений
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

		tv_monday = (TextView) findViewById(R.id.tv_monday);
		tv_tuesday = (TextView) findViewById(R.id.tv_tuesday);
		tv_wednesday = (TextView) findViewById(R.id.tv_wednesday);
		tv_thursday = (TextView) findViewById(R.id.tv_thursday);
		tv_friday = (TextView) findViewById(R.id.tv_friday);
		btn_search = (Button) findViewById(R.id.btn_search);
		sv = (SearchView) findViewById(R.id.sv);

		tv_teach_monday = (TextView) findViewById(R.id.tv_teach_monday);
		tv_teach_tuesday = (TextView) findViewById(R.id.tv_teach_tuesday);
		tv_teach_wednesday = (TextView) findViewById(R.id.tv_teach_wednesday);
		tv_teach_thursday = (TextView) findViewById(R.id.tv_teach_thursday);
		tv_teach_friday = (TextView) findViewById(R.id.tv_teach_friday);

		monday = (TextView) findViewById(R.id.monday);
		tuesday = (TextView) findViewById(R.id.tuesday);
		wednesday = (TextView) findViewById(R.id.wednesday);
		thursday = (TextView) findViewById(R.id.thursday);
		friday = (TextView) findViewById(R.id.friday);

		new get_facultet().execute(get_facult);

		spinner_facult.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					new get_group().execute(get_group
							+ URLEncoder.encode(spinner_facult
									.getSelectedItem().toString(), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		spinner_group.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				try {
					String tmp = get_schedule;
					tmp = tmp.replace("Fname", URLEncoder.encode(spinner_facult
							.getSelectedItem().toString(), "UTF-8"));
					tmp = tmp.replace("Gname", URLEncoder.encode(spinner_group
							.getSelectedItem().toString(), "UTF-8"));
					new get_schedule().execute(tmp);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (spinner_facult.getItemAtPosition(0).toString()
						.contains("Выберите факультет")) {
					adapter_facult.remove((String) spinner_facult
							.getItemAtPosition(0));
					spinner_facult.setAdapter(adapter_facult);
				}
				if (!spinner_group.getItemAtPosition(0).toString()
						.contains("Выберите группу")) {
					spinner_group.setSelection(1);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		btn_search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				group_flag = false;
				schedule_teacher_flag = true;
				schedule_flag = false;
				if (sv.getQuery().toString().equals("")) {
					Toast.makeText(getBaseContext(),
							"Введите фамилию для поиска", Toast.LENGTH_LONG)
							.show();
				} else {
					try {
						new get_teacher_shedule().execute(get_teacher_shedule
								+ URLEncoder.encode(sv.getQuery().toString(), "UTF-8"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});

	}
	protected boolean isOnline() {
		String cs = Context.CONNECTIVITY_SERVICE;
		ConnectivityManager cm = (ConnectivityManager) getSystemService(cs);
		if (cm.getActiveNetworkInfo() == null) {
			return false;
		} else {
			return true;
		}
	}
	
	protected String get_html(String link) {
		String response = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost http = new HttpPost(link);
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
					1);
			nameValuePairs.add(new BasicNameValuePair("", ""));
			http.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			response = httpclient.execute(http,
					new BasicResponseHandler());
			
			
		} catch (Exception e) {
			Log.d("Logs", e.toString());
		}
			return response;
		
	}
	// ------------------------------------
	public class get_facultet extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... links) {
			Document doc = null;
			try {
				doc = Jsoup.connect(links[0]).get();
				JSONArray facults = new JSONArray(doc.text());
				facult_names.clear();
				facult_names.add("Выберите факультет...");
				for (int i = 0; i < facults.length(); i++) {
					JSONObject facult = facults.getJSONObject(i);
					String facult_name = facult.getString("value");
					facult_names.add(facult_name);
				}
			} catch (Exception e) {
				Log.d("Logs", e.toString());

				facult_names.clear();
				SystemClock.sleep(800);
				facult_names.add("Can't load data.");
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
				
				doc = Jsoup.parse(get_html(links[0]));
				JSONArray groups = new JSONArray(doc.text());
				group_names.clear();
				group_names.add("Выберите группу...");
				for (int i = 4; i < groups.length(); i++) {
					JSONObject group = groups.getJSONObject(i);
					String group_name = group.getString("value");
					group_names.add(group_name);

					// Log.d("Logs", "группа: " + group_name);
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
				doc = Jsoup.parse(get_html(links[0]));
			} catch (Exception e) {
				Log.d("Logs", e.toString());
			}
			return doc.text();
		}

		protected void onPostExecute(String result) {
			try {
				JSONArray all_shedule = new JSONArray(result);
				String cur = null;
				tv_monday.setText("");
				tv_tuesday.setText("");
				tv_wednesday.setText("");
				tv_thursday.setText("");
				tv_friday.setText("");
				for (int i = 0; i < all_shedule.length(); i++) {
					JSONObject current = all_shedule.getJSONObject(i);
					cur = current.getString("weekday");
					if (cur.contains("ПОНЕДІЛОК")) {

						tv_monday.setText(tv_monday.getText() + "\n" + "№ "
								+ current.getString("numberOfLecture") + " - "
								+ current.getString("lecture") + "\n");
						// Log.d("Logs", current.getString("lecture"));

					}
					// tv_friday tv_tuesday tv_wednesday tv_thursday tv_friday
					if (cur.contains("ВІВТОРОК")) {

						tv_tuesday.setText(tv_tuesday.getText() + "\n" + "№ "
								+ current.getString("numberOfLecture") + " - "
								+ current.getString("lecture") + "\n");
						// Log.d("Logs", current.getString("lecture"));

					}
					if (cur.contains("СЕРЕДА")) {

						tv_wednesday.setText(tv_wednesday.getText() + "\n"
								+ "№ " + current.getString("numberOfLecture")
								+ " - " + current.getString("lecture") + "\n");
						// Log.d("Logs", current.getString("lecture"));

					}
					if (cur.contains("ЧЕТВЕР")) {

						tv_thursday.setText(tv_thursday.getText() + "\n" + "№ "
								+ current.getString("numberOfLecture") + " - "
								+ current.getString("lecture") + "\n");
						// Log.d("Logs", current.getString("lecture"));

					}
					if (cur.contains("П'ЯТНИЦЯ")) {

						tv_friday.setText(tv_friday.getText() + "\n" + "№ "
								+ current.getString("numberOfLecture") + " - "
								+ current.getString("lecture") + "\n");
						// Log.d("Logs", current.getString("lecture"));

					}

				}

			} catch (Exception e) {
				Log.d("Logs", e.toString());
			}
		}

	}

	public class get_teacher_shedule extends AsyncTask<String, Void, String> {
		@Override
		protected String doInBackground(String... links) {
			Document doc = null;
			try {
				doc = Jsoup.parse(get_html(links[0]));
			} catch (Exception e) {
				Log.d("Logs", e.toString());
			}
			return doc.text();
		}

		protected void onPostExecute(String result) {

			try {
				if (result.contains("null")) {
					Log.d("Logs", "данные отсутствуют");
					Toast.makeText(getBaseContext(),
							"Информации о таком преподавателе не найдено",
							Toast.LENGTH_LONG).show();

				} else {
					String cur = null;
					JSONArray all_shedule = new JSONArray(result);
					for (int i = 0; i < all_shedule.length(); i++) {
						JSONObject current = all_shedule.getJSONObject(i);
						cur = current.getString("weekday");
						if (cur.contains("ПОНЕДІЛОК")) {
							monday.setVisibility(View.VISIBLE);
							tv_teach_monday.setText(tv_teach_monday.getText()
									+ "\n" + "№ "
									+ current.getString("numberOfLecture")
									+ " " + current.getString("groupName")
									+ " " + " - "
									+ current.getString("lecture") + "\n");

						} // else {tv_teach_monday.setText("-");}

						if (cur.contains("ВІВТОРОК")) {

							tv_teach_tuesday.setText(tv_teach_tuesday.getText()
									+ "\n" + "№ "
									+ current.getString("numberOfLecture")
									+ " " + current.getString("groupName")
									+ " " + " - "
									+ current.getString("lecture") + "\n");
							// Log.d("Logs", current.getString("lecture"));

						} // else {tv_teach_tuesday.setText("-");}
						if (cur.contains("СЕРЕДА")) {

							tv_teach_wednesday.setText(tv_teach_wednesday
									.getText()
									+ "\n"
									+ "№ "
									+ current.getString("numberOfLecture")
									+ " "
									+ current.getString("groupName")
									+ " "
									+ " - "
									+ current.getString("lecture") + "\n");
							// Log.d("Logs", current.getString("lecture"));

						} // else {tv_teach_wednesday.setText("-");}
						if (cur.contains("ЧЕТВЕР")) {
							tv_teach_thursday.setText(tv_teach_thursday
									.getText()
									+ "\n"
									+ "№ "
									+ current.getString("numberOfLecture")
									+ " "
									+ current.getString("groupName")
									+ " "
									+ " - "
									+ current.getString("lecture") + "\n");
							// Log.d("Logs", current.getString("lecture"));

						} // else {tv_teach_thursday.setText("-");}
						if (cur.contains("П'ЯТНИЦЯ")) {
							tv_teach_friday.setText(tv_teach_friday.getText()
									+ "\n" + "№ "
									+ current.getString("numberOfLecture")
									+ " " + current.getString("groupName")
									+ " " + " - "
									+ current.getString("lecture") + "\n");

							// Log.d("Logs", current.getString("lecture"));

						}// else {tv_teach_friday.setText("-");}

					}

				}

			} catch (Exception e) {
				Log.i("Logs", e.toString());
			}
		}

	}

}