package com.nmu.mainmenu;

import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class webcams extends Activity implements OnClickListener {
	Button btn_cam35;
	Button btn_cam36;
	Button btn_close;
	Timer timer;
	Thread t;
	ProgressDialog pd;
	int activ;
	ImageView cam_image;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webcams);
		btn_cam35 = (Button) findViewById(R.id.btn_cam35);
		btn_cam36 = (Button) findViewById(R.id.btn_cam36);
		btn_close = (Button) findViewById(R.id.btn_close);
		cam_image = (ImageView) findViewById(R.id.cam_image);
		if (!isOnline()) {
			Toast.makeText(getApplicationContext(),
					"Нет соединения с интернетом!", Toast.LENGTH_LONG).show();
			btn_close.setVisibility(View.GONE);
			btn_cam35.setVisibility(View.GONE);
			btn_cam36.setVisibility(View.GONE);
			return;
			} else {
			// скрываем кнопку при первоначальном запуске приложения
			btn_close.setVisibility(View.GONE);
			btn_cam35.setOnClickListener(this);
			btn_cam36.setOnClickListener(this);
			btn_close.setOnClickListener(this);
		}
	}

	protected boolean isOnline() {
		String cs = Context.CONNECTIVITY_SERVICE;
		ConnectivityManager cm = (ConnectivityManager) getSystemService(cs);
		if (cm.getActiveNetworkInfo() == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Автоматически созданная заглушка метода
		// по id определеяем кнопку, вызвавшую этот обработчик

		switch (v.getId()) {
		case R.id.btn_cam35: {
			Btn_Click(btn_cam35, "http://m.nmu.org.ua/camera/2.jpg",35);
		}
			// --------------------
			break;
		case R.id.btn_cam36: {// кнопка Cancel
			Btn_Click(btn_cam36, "http://m.nmu.org.ua/camera/1.jpg",36);
		}
			break;
		case R.id.btn_close: { // кнопка Cancel
			btn_cam35.setVisibility(View.VISIBLE);
			btn_cam36.setVisibility(View.VISIBLE);
			btn_close.setVisibility(View.GONE);
			cam_image.setVisibility(View.GONE);
			timer.cancel();
		}
			break;
		}
	}

	public void Btn_Click(View v, final String url,int active_bnt) {
		btn_cam35.setVisibility(View.GONE);
		btn_cam36.setVisibility(View.GONE);
		cam_image.setImageDrawable(null);
		btn_close.setVisibility(View.VISIBLE);
		cam_image.setVisibility(View.VISIBLE);
		timer = new Timer(); // Создаем таймер
		activ = active_bnt;
		pd = new ProgressDialog(this);
		pd.setTitle("Загрузка данных ");
		pd.setMessage("Подождите");
		pd.show();
		pd.setIndeterminate(true);
		timer.schedule(new TimerTask() { // Определяем задачу
					@Override
					public void run() {
						t = new Thread() {
							@Override
							public void run() {
								try {
									final Bitmap bitmap = BitmapFactory
											.decodeStream(new URL(url)
													.openStream());
									cam_image.post(new Runnable() {
										@Override
										public void run() {
											cam_image.setImageBitmap(bitmap);
										}
									});
									pd.dismiss();
								} catch (Exception e) {
									e.printStackTrace();
								}
							};
						};
						t.start();
						Log.i("Logs", "doit");
					};
				}, 0, 2000);

	}

	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		timer.cancel();
		outState.putInt("btn", activ);
	}

	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		activ = savedInstanceState.getInt("btn");
		if (activ == 35) {
			Btn_Click(btn_cam35, "http://m.nmu.org.ua/camera/2.jpg",35);
		}
		if (activ == 36) {
			Btn_Click(btn_cam36, "http://m.nmu.org.ua/camera/1.jpg",36);
		}
	}
}
