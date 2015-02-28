package com.nmu.mainmenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class teachers extends Activity {
	TextView tv;
	Document doc;
	Button btn_search;
	SearchView sv;
	TableLayout tableLayout;
	TableRow tr;
	boolean click = false; 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teachers);
		btn_search = (Button) findViewById(R.id.btn_search);
		sv = (SearchView) findViewById(R.id.sv);

		OnClickListener btn_search_click = new OnClickListener() {
			@Override
			public void onClick(View v) {
				click = true;
				new parser()
						.execute("http://m.nmu.org.ua/ajax/phonebook2.php?search="
								+ sv.getQuery());

			}
		};

		// присвоим обработчик кнопке OK (btnOk)
		btn_search.setOnClickListener(btn_search_click);

	}

	public void addRow(String c1, String c2, String c3, String c4, String c5,
			String c6) {
		TextView tv;
		// Сначала найдем в разметке активити саму таблицу по идентификатору
		tableLayout = (TableLayout) findViewById(R.id.table);
		// Создаём экземпляр инфлейтера, который понадобится для создания строки
		// таблицы из шаблона. В качестве контекста у нас используется сама
		// активити
		LayoutInflater inflater = LayoutInflater.from(getBaseContext());
		// Создаем строку таблицы, используя шаблон из файла
		// /res/layout/table_row.xml
		tr = (TableRow) inflater.inflate(R.layout.table_row, null);

		// ФИО
		tv = (TextView) tr.findViewById(R.id.col1);
		tv.setText(c1 + " ");
		// Должность
		tv = (TextView) tr.findViewById(R.id.col2);
		tv.setText(c2 + " ");
		// Факультет/отдел
		tv = (TextView) tr.findViewById(R.id.col3);
		tv.setText(c3 + " ");
		// Комната
		tv = (TextView) tr.findViewById(R.id.col4);
		tv.setText(c4 + " ");
		// Внутренний телефон
		tv = (TextView) tr.findViewById(R.id.col5);
		tv.setText(c5 + " ");
		// Городской телефон
		tv = (TextView) tr.findViewById(R.id.col6);
		tv.setText(c6 + " ");
		tableLayout.addView(tr); // добавляем созданную строку в таблицу
	}

	public String parse(String str) {
		String result;
		result = str.replaceAll(" ", "\n");
		return result;
	}

	public class parser extends AsyncTask<String, Void, String> {
		StringBuilder sb;
		String response;
		Element el;
		Elements els;

		@Override
		protected String doInBackground(String... links) {
			try {
				HttpClient httpclient = new DefaultHttpClient();
				HttpPost http = new HttpPost(links[0]);
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						1);
				nameValuePairs.add(new BasicNameValuePair("", ""));

				http.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				response = httpclient.execute(http, new BasicResponseHandler());
				response = response.replaceAll("<tr>", "");
				response = response.replaceAll("</tr>", "");
				doc = Jsoup.parse(response);
			} catch (ClientProtocolException e) {
				Log.i("Logs", e.toString());
			} catch (IOException e) {
				Log.i("Logs", e.toString());
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			
			if (doc.getElementsByTag("h2").text().contains("Ничего не найдено")){
				Toast.makeText(getBaseContext(),doc.getElementsByTag("h2").text(), Toast.LENGTH_LONG).show();
			} else {
				try {
			
				els = doc.getElementsByTag("th");
				
				addRow((el = els.first()).text(),
						(el = el.nextElementSibling()).text(),
						(el = el.nextElementSibling()).text(),
						(el = el.nextElementSibling()).text(),
						parse((el = el.nextElementSibling()).text()),
						parse((el = el.nextElementSibling()).text()));
				els = doc.getElementsByTag("td");
				els = doc.select("td");
				String tmp;
				el = els.first();
				tmp = el.text();
				do {
					addRow(parse(tmp), (el = el.nextElementSibling()).text(),
							parse((el = el.nextElementSibling()).text()),
							(el = el.nextElementSibling()).text(),
							(el = el.nextElementSibling()).text(),
							(el = el.nextElementSibling()).text());
					tmp = (el = el.nextElementSibling()).text();
					// Log.i("Logs", el.text());
				} while (el != els.last());
			} catch (Exception e) {
				Log.i("Logs", e.toString());
				
			}}
		}
	}

}
