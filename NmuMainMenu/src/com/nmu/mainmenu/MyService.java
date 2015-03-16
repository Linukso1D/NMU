package com.nmu.mainmenu;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyService extends Service {
  NotificationManager nm;
   
String Npar;
int 	 alltime,timetolast;
  @Override
  public void onCreate() {
    super.onCreate();
    nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    
    
    
  }

  public int onStartCommand(Intent intent, int flags, int startId) {
	 Npar= intent.getStringExtra("NomPar");
	 alltime=intent.getIntExtra("alltime", 0);
	 timetolast=intent.getIntExtra("timetolast", 0);
	final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
	    
	    Log.d("SERVICE", " NomPar"+ Npar +" alltime " + alltime + " timetolast "+timetolast);
	    mBuilder.setContentTitle(Npar)
	        .setContentText(alltime-timetolast +"/"+  alltime +" мин.")
	        .setSmallIcon(R.drawable.ic_launcher_web);
	    if(alltime!=0){
	    // Start a lengthy operation in a background thread
	    new Thread(
	        new Runnable() {
	            @Override
	            public void run() {
	                
	               
	                // Do the "lengthy" operation 20 times
	       
	                        // Sets the progress indicator to a max value, the
	                        // current completion percentage, and "determinate"
	                        // state
	                        mBuilder.setProgress(alltime , alltime-timetolast, false);
	                        // Displays the progress bar for the first time.
	                        nm.notify(1, mBuilder.build());
	                            // Sleeps the thread, simulating an operation
	                            // that takes time
	                           
	              
	                // When the loop is finished, updates the notification
	              
	            }
	        }
	    // Starts the thread by calling the run() method in its Runnable
	    ).start();
	 
	 
	 
	 
	    }
	 
   // sendNotif();
    return super.onStartCommand(intent, flags, startId);
  }
  
  void sendNotif() {
    // 1-я часть
    Notification notif = new Notification(R.drawable.ic_launcher_web, "NMU", 
      System.currentTimeMillis());
    
    // 3-я часть
    Intent intent = new Intent(this, MainActivity.class);
   
    PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
    
    // 2-я часть
    notif.setLatestEventInfo(this, MainActivity.lastParaPeremena, "Notification's text", pIntent);
    
    // ставим флаг, чтобы уведомление пропало после нажатия
    notif.flags |= Notification.FLAG_AUTO_CANCEL|Notification.DEFAULT_SOUND;


    
    // отправляем
  //  nm.notify(1, notif);
  }
  
  public IBinder onBind(Intent arg0) {
    return null;
  }
}