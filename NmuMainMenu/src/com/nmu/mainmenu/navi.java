package com.nmu.mainmenu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class navi extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.library);
		Intent browserIntent = new Intent(Intent.ACTION_VIEW,
		Uri.parse("https://www.google.ru/maps/dir/48.4812906,34.9461833/48.4556691,35.0618105/@48.4740905,34.9665299,13z/data=!3m1!4b1!4m5!4m4!1m1!4e1!1m0!3e0"));
		startActivity(browserIntent);
		
	}

}
