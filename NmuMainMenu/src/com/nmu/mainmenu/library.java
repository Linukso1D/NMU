package com.nmu.mainmenu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class library extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.library);
		
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.ru/maps/dir//48.4556691,35.0618105/@48.4553096,34.9140874,11z/data=!3m1!4b1!4m2!4m1!3e0"));
		startActivity(browserIntent);
		
	}

}
