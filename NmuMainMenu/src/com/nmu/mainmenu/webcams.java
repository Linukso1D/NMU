package com.nmu.mainmenu;

import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

	
public class webcams extends Activity implements OnClickListener{
	Button btn_cam35;
	Button btn_cam36;
	Button btn_close;
	Timer timer35;
	Timer timer36;
	Thread t;
	Thread t2;
	boolean btn35_click,btn36_click;
	int btn35_activ,btn36_activ;
	ImageView cam_image;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
    setContentView(R.layout.webcams);
    btn_cam35 = (Button) findViewById(R.id.btn_cam35);
    btn_cam36 = (Button) findViewById(R.id.btn_cam36);
    btn_close = (Button) findViewById(R.id.btn_close);
    cam_image = (ImageView) findViewById(R.id.cam_image);
    //скрываем кнопку при первоначальном запуске приложения
    btn_close.setVisibility(View.GONE);
    btn_cam35.setOnClickListener(this);
    btn_cam36.setOnClickListener(this);
    btn_close.setOnClickListener(this);
	}
 // обработчик нажатия
	public static final String TAG = "MyApp";


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
	    	  cam_image.setImageDrawable(null);
	    	  btn_close.setVisibility(View.VISIBLE);
	    	  //удаление изображения
	    	  cam_image.setVisibility(View.VISIBLE);
	    	  timer35 = new Timer(); // Создаем таймер
	    	  btn35_activ=1;
	    	  btn36_activ=0;
	    	  timer35.schedule(new TimerTask() { // Определяем задачу
	  		    @Override
	  		    public void run() {
	  		    	t = new Thread()  {
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
	      	  cam_image.setImageDrawable(null);
	      	  btn_close.setVisibility(View.VISIBLE);
	      	  cam_image.setVisibility(View.VISIBLE);
	          timer36 = new Timer(); // Создаем таймер
	          btn36_activ=1;
	    	  btn35_activ=0;
	    	  timer36.schedule(new TimerTask() { // Определяем задачу
	      		@Override
	      		public void run() {
	  		    	t2 = new Thread()  {
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
	  		          t2.start(); 
	  		          Log.i(TAG,"doit"); 
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
       	  if (btn35_activ==1)
       	  	{	timer35.cancel();
       	  		btn35_activ=0; }
       	  if (btn36_activ==1)
       	  	{	timer36.cancel(); 
       	  		btn36_activ=0;}
	     }
	       break;
}
	}
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
     	  if (btn35_activ==1)
     	  	{	timer35.cancel();  	  }
     	  if (btn36_activ==1)
     	  	{	timer36.cancel();  	  }
     	  
        //tab_index=tabHost.getCurrentTab();
	//	facult=spinner_facult.getId();
	//	group=spinner_group.getId();
        outState.putInt("btn35",  btn35_activ);
        outState.putInt("btn36",  btn36_activ);
		
		
		
	}
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
	    super.onRestoreInstanceState(savedInstanceState);

	    btn35_activ = savedInstanceState.getInt("btn35");
	    btn36_activ = savedInstanceState.getInt("btn36");
	    if (btn35_activ==1)
 	  	{
	    	btn_cam35.setVisibility(View.GONE);
	    	  btn_cam36.setVisibility(View.GONE);
	    	  cam_image.setImageDrawable(null);
	    	  btn_close.setVisibility(View.VISIBLE);
	    	  //удаление изображения
	    	  cam_image.setVisibility(View.VISIBLE);
	    	  timer35 = new Timer(); // Создаем таймер
	    	  btn35_activ=1;
	    	  btn36_activ=0;
	    	  timer35.schedule(new TimerTask() { // Определяем задачу
	  		    @Override
	  		    public void run() {
	  		    	t = new Thread()  {
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
	    
	    if (btn36_activ==1)
 	  	{
	    	btn_cam35.setVisibility(View.GONE);
	      	  btn_cam36.setVisibility(View.GONE);
	      	  cam_image.setImageDrawable(null);
	      	  btn_close.setVisibility(View.VISIBLE);
	      	  cam_image.setVisibility(View.VISIBLE);
	          timer36 = new Timer(); // Создаем таймер
	          btn36_activ=1;
	    	  btn35_activ=0;
	    	  timer36.schedule(new TimerTask() { // Определяем задачу
	      		@Override
	      		public void run() {
	  		    	t2 = new Thread()  {
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
	  		          t2.start(); 
	  		          Log.i(TAG,"doit"); 
	  		    };
	  		}, 0, 2000);
 	  	}
	    //tab_index = savedInstanceState.getInt("T");
	    //выставляем вкладку которую запомнили активной. 
	    //tabHost.setCurrentTab(tab_index);
	    

	}
}
