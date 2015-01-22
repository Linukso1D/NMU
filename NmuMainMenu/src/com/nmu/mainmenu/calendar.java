package com.nmu.mainmenu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

@SuppressLint("SetJavaScriptEnabled")
public class calendar extends Activity{
	String calendar_text;
	TextView tv_calendar_text;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar);
			 WebView mWebView = (WebView) findViewById(R.id.mWebView);
			// включаем поддержку JavaScript
		    mWebView.getSettings().setJavaScriptEnabled(true);
			// указываем страницу загрузки
		    mWebView.loadUrl("https://www.google.com/calendar/embed?showTitle=0&showCalendars=0&height=600&wkst=2&bgcolor=%23FFFFFF&src=nmutempus%40gmail.com&color=%232952A3&ctz=Europe%2FKiev");
		    
		    
		    final ProgressDialog progressDialog = ProgressDialog.show(calendar.this, "", "Загрузка. Пожалуйста подождите...", true);

	    
	    // Страница ошибки
		    mWebView.setWebViewClient(new WebViewClient() {
	             
	             public void onPageFinished(WebView view, String url) {
	            	 progressDialog.dismiss();
	               }  
	    });
	}

}
