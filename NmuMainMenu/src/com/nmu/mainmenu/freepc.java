package com.nmu.mainmenu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class freepc extends Activity {
	TextView tv;
	WebView browser;
	Document doc;
	int pos;
	ProgressDialog pd;

	@SuppressLint("JavascriptInterface")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.freepc);
		tv = (TextView) findViewById(R.id.tv);
		browser = (WebView) findViewById(R.id.wv);
		pd = new ProgressDialog(this);
		pd.setTitle("Загрузка данных ");
		pd.setMessage("Подождите");
		pd.show();
		pd.setIndeterminate(true);
		class MyJavaScriptInterface {
			@SuppressWarnings("unused")
			public void processHTML(String html) {
				doc = Jsoup.parse(html);
				StringBuffer sb = new StringBuffer(doc.text());
				pos = sb.lastIndexOf("В преподавательской");
				sb = sb.delete(0, pos);
				pos = sb.lastIndexOf("Автор");
				sb = sb.delete(pos, sb.length());
				String res = sb.toString();
				res = res.replaceAll("В аудитории", "\nВ аудитории");
				new SetText().execute(res);
				Log.i("Logs", res);
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
		browser.loadUrl("http://m.nmu.org.ua/#freecomp");
	}

	public class SetText extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... links) {
			return links[0];
		}

		@Override
		protected void onPostExecute(String result) {
			tv.setText(result);
			pd.dismiss();
		}

	}

}
