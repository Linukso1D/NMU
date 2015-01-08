package com.nmu.mainmenu;

import java.io.File;
import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class freepc extends Activity {
	Document doc;
	
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.freepc);
	TextView tv_1 = (TextView)findViewById(R.id.textView3);
	

	


	
	/*
	File input = new File("//sdcard/temp/input.html");
	
	
		Document doc = null;
		try {
			doc = Jsoup.parse(input, "UTF-8", "http://m.nmu.org.ua/#freecomp");
		} catch (IOException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		}
	
	
	@SuppressWarnings("unused")
	Element content = doc.getElementById("freecomp");//<- вот на этой строчке вылетает
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
