package com.nmu.mainmenu;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class library extends Activity implements OnClickListener {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.library);
		TextView tv_click = (TextView) findViewById(R.id.tv_click);
		tv_click.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		case R.id.tv_click:
			Intent library = new Intent(Intent.ACTION_VIEW,
					Uri.parse("http://m.nmu.org.ua/#library"));
			startActivity(library);
			break;
		}

	}

}
