package com.nmu.mainmenu;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.android.gms.internal.el;

import android.webkit.JavascriptInterface;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class freepc extends Activity {
	TextView tv;
	Document doc;
	Element el;
	Elements els;
	int pos;
	ProgressDialog pd;

	@SuppressLint("JavascriptInterface")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.freepc);
		tv = (TextView) findViewById(R.id.tv);
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
			new SetText().execute("http://m.nmu.org.ua/ajax/pcs.php");
		
		}
	}

//функция проверки подкючения устройства к интернету
	protected boolean isOnline() {
		String cs = Context.CONNECTIVITY_SERVICE;
		ConnectivityManager cm = (ConnectivityManager) getSystemService(cs);
		if (cm.getActiveNetworkInfo() == null) {
			return false;
		} else {
			return true;
		}
	}

	public class SetText extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... links) {
			String res;
			try {
				doc = Jsoup.connect(links[0]).get();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			res = doc.text();
			res = res.replaceAll("В аудитории", "\nВ аудитории");
			return res;
		}

		@Override
		protected void onPostExecute(String result) {
			tv.setText(result);
			pd.dismiss();
		}

	}

}
