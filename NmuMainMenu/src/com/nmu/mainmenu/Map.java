package com.nmu.mainmenu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;


@SuppressLint("SetJavaScriptEnabled")
public class Map extends Activity implements OnClickListener{

	private static final String TAG = "MapActivity";

	private Button up_btn;
	private Button down_btn;
	private Button main_btn;
	private WebView main_levels;
	private static int currentCorp = 0;
	private static int currentLevel = 0;
	private static int WEB_WIDTH = 0;
	private static int WEB_HEIGHT = 0;
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		WEB_WIDTH = (int)(getResources().getDimension(R.dimen.web_view_width) / getResources().getDisplayMetrics().density);
		WEB_HEIGHT = (int)(getResources().getDimension(R.dimen.web_view_height) / getResources().getDisplayMetrics().density);
		up_btn = (Button) findViewById(R.id.maps_up_button);
		down_btn = (Button) findViewById(R.id.maps_down_button);
		main_btn = (Button) findViewById(R.id.maps_main_button);
		up_btn.setOnClickListener(this);
		down_btn.setOnClickListener(this);
		main_btn.setOnClickListener(this);
		main_levels = (WebView) findViewById(R.id.image_view_id);
		main_levels.getSettings().setJavaScriptEnabled(true);
		main_levels.getSettings().setDefaultTextEncodingName("utf-8");
		main_levels.getSettings().setSupportZoom(true);
		main_levels.getSettings().setBuiltInZoomControls(true);
		main_levels.setScrollbarFadingEnabled(true);
		main_levels.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		main_levels.loadUrl("file:///android_asset/main.html?divWidth="+WEB_WIDTH+"&divHeight="+WEB_HEIGHT+"&redirect=0");
	}

	
	@Override
	public void onClick(View arg0) {
		StringBuffer strb = new StringBuffer(main_levels.getUrl());
		if(!strb.substring(0, 26).equals("file:///android_asset/main")){
		int start = strb.indexOf("&redirect=");
		int start1 = strb.indexOf("file:///android_asset/for");
		String level = strb.substring(start+"&redirect=".length(), strb.length());
		StringBuffer strbuf = new StringBuffer(strb.substring(start1+"file:///android_asset/for".length(), "file:///android_asset/for".length()+2));
		currentLevel = Integer.parseInt(level);
		if(strbuf.charAt(strbuf.length()-1) == '.'){
			strbuf.deleteCharAt(strbuf.length()-1);
		}
		currentCorp = Integer.parseInt(strbuf.toString());
		}
		switch (arg0.getId()) {
		
		case R.id.maps_up_button:
			if(strb.substring(0, 26).equals("file:///android_asset/main")){
				//
			}else{
				currentLevel++;
				verifycurrentLevel();
				Log.d(TAG, "URL currentLevel = "+currentLevel);
				Log.d(TAG, "URL currentCorp = "+currentCorp);
				main_levels.loadUrl("file:///android_asset/for"+currentCorp+".html?divWidth="+WEB_WIDTH+"&divHeight="+WEB_HEIGHT+"&redirect="+currentLevel);
			}
			break;
		case R.id.maps_down_button:
			if(strb.substring(0, 26).equals("file:///android_asset/main")){
				//
			}else{
				currentLevel--;
				verifycurrentLevel();
				Log.d(TAG, "URL currentLevel = "+currentLevel);
				Log.d(TAG, "URL currentCorp = "+currentCorp);
			    main_levels.loadUrl("file:///android_asset/for"+currentCorp+".html?divWidth="+WEB_WIDTH+"&divHeight="+WEB_HEIGHT+"&redirect="+currentLevel);
			}
			break;
		case R.id.maps_main_button:
			 currentCorp = 0;
			 currentLevel = 0;
			 main_levels.loadUrl("file:///android_asset/main.html?divWidth="+WEB_WIDTH+"&divHeight="+WEB_HEIGHT+"&redirect=0");
			 
			break;
		}
	}
	

	
    private void verifycurrentLevel(){
    	switch(currentCorp){
    	case 1:
    		if(currentLevel > 1 || currentLevel < 0){
    			currentLevel = 0;
    		}
    		break;
    	case 2:
    		if(currentLevel > 2 || currentLevel < 0){
    			currentLevel = 0;
    		}
    		break;
    	case 3:
    		if(currentLevel > 2 || currentLevel < 0){
    			currentLevel = 0;
    		}
    		break;
    	case 4:
    		if(currentLevel > 4 || currentLevel < 0){
    			currentLevel = 0;
    		}
    		break;
    	case 5:
    		if(currentLevel > 2 || currentLevel < 0){
    			currentLevel = 0;
    		}
    		break;
    	case 6:
    		break;
    	case 7:
    		if(currentLevel > 13 || currentLevel < 0){
    			currentLevel = 0;
    		}
    		break;
    	case 10:
    		if(currentLevel > 6 || currentLevel < 0){
    			currentLevel = 0;
    		}
    		break;
    	}
    }
}
