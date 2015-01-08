package com.nmu.mainmenu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class lib extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clubs);
		
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.ru/maps/dir//48.4556691,35.0618105/@48.4557645,35.0619213,19z/data=!4m2!4m1!3e1"));
		startActivity(browserIntent);
		
	}

}
