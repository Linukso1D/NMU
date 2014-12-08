package com.nmu.mainmenu;

import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.util.TimerTask;
import java.util.Timer;
import android.widget.Toast;
	
public class webcams extends Activity implements OnClickListener{
	Button btn_cam35;
	Button btn_cam36;
	Button btn_close;
	Timer timer;
	Timer timer35;
	Timer timer36;
	ImageView cam_image;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
    setContentView(R.layout.webcams);
    btn_cam35 = (Button) findViewById(R.id.btn_cam35);
    btn_cam36 = (Button) findViewById(R.id.btn_cam36);
    btn_close = (Button) findViewById(R.id.btn_close);
    timer = new Timer(); // Создаем таймер
    timer35 = new Timer(); // Создаем таймер
    timer36 = new Timer(); // Создаем таймер
    cam_image = (ImageView) findViewById(R.id.cam_image);
    //скрываем кнопку при первоначальном запуске приложения
    btn_close.setVisibility(View.GONE);
    btn_cam35.setOnClickListener(this);
    btn_cam36.setOnClickListener(this);
    btn_close.setOnClickListener(this);
	}
 // обработчик нажатия
 

	@Override
	public void onClick(View v) {
		// TODO Автоматически созданная заглушка метода
		// по id определеяем кнопку, вызвавшую этот обработчик
	     switch (v.getId()) {
	     case R.id.btn_cam35:
	     {
	       // кнопка cam 35
	    	  btn_cam35.setVisibility(View.GONE);
	    	  btn_cam36.setVisibility(View.GONE);
	    	  btn_close.setVisibility(View.VISIBLE);
	    	  //удаление изображения
	    	  cam_image.setImageDrawable(null);
	    	  cam_image.setVisibility(View.VISIBLE);
	    	  
	    	  
	    	  timer.schedule(new TimerTask() { // Определяем задачу
	  		    @Override
	  		    public void run() {
	  		    	Thread t = new Thread()  {
	  		              @Override
	  		              public void run() {
	  		                  try {
	  		                      final Bitmap bitmap = BitmapFactory.decodeStream(new URL("http://m.nmu.org.ua/camera/2.jpg").openStream());
	  		                      cam_image.post(new Runnable() {
	  		                          @Override
	  		                          public void run() {
	  		                        	  cam_image.setImageBitmap(bitmap);
	  		                          }
	  		                      });
	  		                  } catch (Exception e) { e.printStackTrace(); } 
	  		              };
	  		          };
	  		          t.start();
	  		    };
	  		}, 0, 2000);
	      }
	     //--------------------
	       break;
	     case R.id.btn_cam36:
	     {// кнопка Cancel
	    	  btn_cam35.setVisibility(View.GONE);
	      	  btn_cam36.setVisibility(View.GONE);
	      	  btn_close.setVisibility(View.VISIBLE);
	      	  cam_image.setImageDrawable(null);
	      	  cam_image.setVisibility(View.VISIBLE);
	     
	      	timer.schedule(new TimerTask() { // Определяем задачу
	      		@Override
	      		public void run() {
	  		    	Thread t = new Thread()  {
	  		              @Override
	  		              public void run() {
	  		                  try {
	  		                      final Bitmap bitmap = BitmapFactory.decodeStream(new URL("http://m.nmu.org.ua/camera/1.jpg").openStream());
	  		                      cam_image.post(new Runnable() {
	  		                          @Override
	  		                          public void run() {
	  		                        	  cam_image.setImageBitmap(bitmap);
	  		                          }
	  		                      });
	  		                  } catch (Exception e) { e.printStackTrace(); } 
	  		              };
	  		          };
	  		          t.start();
	  		    };
	  		}, 0, 2000);
	      	          
	     }
	     break;
	     case R.id.btn_close:
	     {      // кнопка Cancel
	      btn_cam35.setVisibility(View.VISIBLE);
       	  btn_cam36.setVisibility(View.VISIBLE);
       	  btn_close.setVisibility(View.GONE);
       	  cam_image.setVisibility(View.GONE);
       	  timer.cancel();
	     }
	     
	
	       break;
	     
	
	
}
	}
}
