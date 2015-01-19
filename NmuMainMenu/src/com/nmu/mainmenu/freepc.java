package com.nmu.mainmenu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class freepc extends Activity {

	
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	TextView tv = (TextView) findViewById(R.id.textView1);
	setContentView(R.layout.freepc);

	String html = "<html><head><title>Коты учатся кодить</title>"
			+ "<body><p>Коты умеют <del>ш</del>кодить.<br> Они великие программисты." +
					"<p>А еще они умеют мяукать.</p>" +
					"<a href='http://developer.alexanderklimov.ru'>Подробности здесь</a>" +
					"</body></html>";
		
		Document doc = Jsoup.parse(html);
		//doc.html()
		tv.setText("lol"); 
	
	
	//Document doc = Jsoup.parse("http://m.nmu.org.ua/#freecomp");
	//tv_1.setText(doc.html());
	//Element loginform = doc.getElementById("freecomp");
	


//	Element content = doc.getElementById("freecomp");//<- вот на этой строчке вылетает
	//Elements links = content.getElementsByTag("br");
	
	/*
	 я сначала пробовал загрузить страницу. не получилось. потом я попробовал использовать уже 
	 сохраненную стр.
	 try {
		doc  = Jsoup.connect("http://m.nmu.org.ua/#freecomp").get();
		
	} catch (IOException e) {
  		e.printStackTrace();
	}
	//String title = doc.title();
	//tv_1.setText(title);*/
	
	/*Element Element = doc.select("br").first();
	String linkHref = Element.attr("href"); 
	tv_1.setText(linkHref); */
	}

}
