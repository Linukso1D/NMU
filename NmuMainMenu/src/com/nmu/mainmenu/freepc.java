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

	String html = "<html><head><title>���� ������ ������</title>"
			+ "<body><p>���� ����� <del>�</del>������.<br> ��� ������� ������������." +
					"<p>� ��� ��� ����� �������.</p>" +
					"<a href='http://developer.alexanderklimov.ru'>����������� �����</a>" +
					"</body></html>";
		
		Document doc = Jsoup.parse(html);
		//doc.html()
		tv.setText("lol"); 
	
	
	//Document doc = Jsoup.parse("http://m.nmu.org.ua/#freecomp");
	//tv_1.setText(doc.html());
	//Element loginform = doc.getElementById("freecomp");
	


//	Element content = doc.getElementById("freecomp");//<- ��� �� ���� ������� ��������
	//Elements links = content.getElementsByTag("br");
	
	/*
	 � ������� �������� ��������� ��������. �� ����������. ����� � ���������� ������������ ��� 
	 ����������� ���.
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
