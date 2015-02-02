package com.nmu.mainmenu;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.TextView;

@SuppressLint("JavascriptInterface") public class clubs extends Activity {
	WebView browser;
	ListView ClubList;
	TextView tv_description, tv_clubname;
	SearchView search_field;
	ArrayAdapter<String> adapter;
	Document doc;
	List<String> club_names = new ArrayList<String>();
	Element el_next;
	Elements els;
	String global_html;
	ProgressDialog pd;
	String[] description = {
			"���������� ������ ����������",
			"���������� ������ ������",
			"���������� ������ ����������",
			"��� (����� ������ � �����������) � ����������"
					+ "  �������������� ����, � ������� ������� ��������� "
					+ "����������� (������� ���������, �����, ����������� � �. �.) "
					+ "����������� � �������������� ������� �� �������� �������, "
					+ "������������� �� �������� ����, ������������ ������� ������������� ���� � �. �.",
			"���������� ���� ������ �� ��������",
			"���������� ���� ������ �� �����",
			"���������� ���� ��������� � �������������� ���������� ���� ������!",
			"���������� ������ ������ ��������",
			"���������� ������ �����������",
			"���������� ������ ����������� �������",
			"������������ ������� ���� ������� ���� ���������� ���� ��������� ������� "
					+ "�������� ���� ������ �������� � �������� ������ ����������� �����.",
			"���� ��������" };

	@SuppressLint("JavascriptInterface")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clubs);
		browser = (WebView) findViewById(R.id.wv);
		ClubList = (ListView) findViewById(R.id.ClubList);
		tv_description = (TextView) findViewById(R.id.description);
		tv_clubname = (TextView) findViewById(R.id.club_name);
		search_field = (SearchView) findViewById(R.id.search_field);
		pd = new ProgressDialog(this);
		pd.setTitle("�������� ������ ");
		pd.setMessage("���������");
		pd.show();
		pd.setIndeterminate(true);
		class MyJavaScriptInterface {
			@SuppressWarnings("unused")
			public void processHTML(String html) {
				new select_clubs().execute(html);
				global_html = html;
			}
		}
		/* JavaScript must be enabled if you want it to work, obviously */
		browser.getSettings().setJavaScriptEnabled(true);
		/* Register a new JavaScript interface called HTMLOUT */
		browser.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
		/* WebViewClient must be set BEFORE calling loadUrl! */
		browser.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(WebView view, String url) {
				browser.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
			}
		});
		browser.loadUrl("http://m.nmu.org.ua/#clubs");
		ClubList.requestFocus();
		ClubList.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//Log.d("Logs", "itemClick: position = " + position + ", id = "
				//		+ id);
				String temp = (String) ((TextView) view).getText();
				position = club_names.indexOf(temp);
				tv_clubname.setText(temp);
				tv_description.setText("��������:\n " + description[position]);
			}

		});
		search_field.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
				// ����������, ����� ������������ �������� ����� � ���� ���
				// ������
            	adapter.getFilter().filter(search_field.getQuery());
                if (query.contains("")){
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
	public class select_clubs extends AsyncTask<String, Void, String[]> {
		@Override
		protected String[] doInBackground(String... links) {
			doc = Jsoup.parse(links[0]);
			els = doc.select("option");
			el_next = els.first();
			club_names.clear();
			club_names.add(el_next.text());
			while (el_next != els.last()) {
				el_next = el_next.nextElementSibling();
				club_names.add(el_next.text());
			}
			return null;
		}

		@Override
		protected void onPostExecute(String[] result) {
			try {
				adapter = new ArrayAdapter<String>(getBaseContext(),
						android.R.layout.simple_spinner_item, club_names);
				ClubList.setAdapter(adapter);
				pd.dismiss();
			} catch (Exception e) {
				Log.i("Logs", e.toString());
			}
		}

	}

}