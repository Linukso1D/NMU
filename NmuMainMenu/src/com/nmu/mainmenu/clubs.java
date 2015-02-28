package com.nmu.mainmenu;

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
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.TextView;
import android.widget.Toast;

public class clubs extends Activity {
	ListView Club_List;
	TextView tv_description, tv_clubname;
	SearchView search_field;
	ArrayAdapter<String> club_adapter,description_adapter;
	Document doc;
	List<String> club_names = new ArrayList<String>();
	List<String> descriptions = new ArrayList<String>();
	Element el_next;
	Elements els;
	ProgressDialog pd;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clubs);
		
		Club_List = (ListView) findViewById(R.id.ClubList);
		tv_description = (TextView) findViewById(R.id.description);
		tv_clubname = (TextView) findViewById(R.id.club_name);
		search_field = (SearchView) findViewById(R.id.search_field);
		if (!isOnline()) {
			Toast.makeText(getApplicationContext(),
					"Нет соединения с интернетом!", Toast.LENGTH_LONG).show();
			return;
		} else {
			pd = new ProgressDialog(this);
			pd.setTitle("Загрузка данных ");
			pd.setMessage("Подождите");
			pd.show();
			pd.setIndeterminate(true);
			Club_List.requestFocus();
			new select_clubs().execute("http://m.nmu.org.ua/ajax/getClubs.php");
			Club_List.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					String temp = (String) ((TextView) view).getText();
					position = club_names.indexOf(temp);
					tv_clubname.setText(temp);
					tv_description.setText("Описание:\n "
							+ descriptions.get(position));
				}

			});
			search_field
					.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
						@Override
						public boolean onQueryTextSubmit(String query) {
							callSearch(query);
							return true;
						}

						@Override
						public boolean onQueryTextChange(String newText) {
							callSearch(newText);

							return true;
						}

						public void callSearch(String query) {
							// Вызывается, когда пользователь изменяет текст в
							// поле для поиска
							club_adapter.getFilter().filter(search_field.getQuery());
							if (query.contains("")) {
								tv_clubname.setText("");
								tv_description.setText("");
							}
						}

					});
			search_field.setOnCloseListener(new OnCloseListener() {
				public boolean onClose() {
					tv_clubname.setText("");
					tv_description.setText("");
					return false;
				}
			});
		}

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


	public class select_clubs extends AsyncTask<String, Void, String[]> {
		@Override
		protected String[] doInBackground(String... links) {
			try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost http = new HttpPost(links[0]);
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
					1);
			nameValuePairs.add(new BasicNameValuePair("", ""));

			http.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			String response = httpclient.execute(http, new BasicResponseHandler());
			JSONArray clubs = new JSONArray(response);
			for (int i = 0; i < clubs.length(); i++) {
				JSONObject club = clubs.getJSONObject(i);
				String club_name = club.getString("club");
				String club_description = club.getString("description");
				club_names.add(club_name);
				descriptions.add(club_description);
				
				

				// Log.d("Logs", "группа: " + group_name);
			}
			}catch(Exception e){
				Log.i("Logs",e.toString());
			}
			return null;
		}

		@Override
		protected void onPostExecute(String[] result) {
			try {
				club_adapter = new ArrayAdapter<String>(getBaseContext(),
						android.R.layout.simple_spinner_item, club_names);
				Club_List.setAdapter(club_adapter);
				pd.dismiss();
			} catch (Exception e) {
				Log.i("Logs", e.toString());
			}
		}

	}

}