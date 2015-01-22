package com.nmu.mainmenu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;

public class blogs extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blogs);

		ImageView btn_twitter = (ImageView) findViewById(R.id.btn_twitter);
		btn_twitter.setOnClickListener(this);
		btn_twitter.setClickable(true); 

		ImageView btn_vk = (ImageView) findViewById(R.id.btn_vk);
		btn_vk.setOnClickListener(this);
		btn_vk.setClickable(true); 

		ImageView btn_youtube = (ImageView) findViewById(R.id.btn_youtube);
		btn_youtube.setOnClickListener(this);
		btn_youtube.setClickable(true); 

		ImageView btn_facebook = (ImageView) findViewById(R.id.btn_facebook);
		btn_facebook.setOnClickListener(this);
		btn_facebook.setClickable(true); 
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.btn_twitter:
			Intent browserIntent_twitter = new Intent(Intent.ACTION_VIEW,
					Uri.parse("https://twitter.com/dvnz_nmu"));
			startActivity(browserIntent_twitter);
			break;

		case R.id.btn_vk:
			Intent browserIntent_vk = new Intent(Intent.ACTION_VIEW,
					Uri.parse("https://vk.com/public28412208"));
			startActivity(browserIntent_vk);
			break;

		case R.id.btn_facebook:
			Intent browserIntent_facebook = new Intent(Intent.ACTION_VIEW,
					Uri.parse("https://www.facebook.com/pages/National-Mining-University-NMU/155897531176446"));
			startActivity(browserIntent_facebook);
			break;

		case R.id.btn_youtube:
			Intent browserIntent_youtube = new Intent(Intent.ACTION_VIEW,
					Uri.parse("https://www.youtube.com/user/NMUchannel"));
			startActivity(browserIntent_youtube);
			break;
		}

	}

}

