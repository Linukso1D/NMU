package com.nmu.mainmenu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.widget.TabHost.OnTabChangeListener;



 public class rasp extends Activity {
	 TabHost tabHost ;
	
	int tab_index,facult,group;
	String teacher;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//сохранение значений
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rasp_zvonkov);
        
        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec tabSpec;
        tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setIndicator("Студенту");
        tabSpec.setContent(R.id.tab1);
        tabHost.addTab(tabSpec);
        
        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setIndicator("Преподавателю");
        tabSpec.setContent(R.id.tab2);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setIndicator("Звонки");
        tabSpec.setContent(R.id.tab3);
        tabHost.addTab(tabSpec);
        
        //tabHost.setCurrentTabByTag("tag1");
		
		 String[] data_facult = {"Факультет информационных технологий", "Факультет финансов и кредита", "Факультет менеджмента", "Строительный факультет", "Факультет безопасности потоков данных"};
	        // адаптер
	        ArrayAdapter<String> adapter_facult = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_facult);
	        adapter_facult.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        
	        final Spinner spinner_facult = (Spinner) findViewById(R.id.spinner_facult);
	        spinner_facult.setAdapter(adapter_facult);
	        // заголовок
	        spinner_facult.setPrompt("Факультет");
	        // выделяем элемент 
	        spinner_facult.setSelection(0);
	        // устанавливаем обработчик нажатия
	        
	        spinner_facult.setOnItemSelectedListener(new OnItemSelectedListener() {
	  	      public void onItemSelected(AdapterView<?> parent, View view,
	  	          int position, long id) {
	  	        // показываем позиция нажатого элемента
	  	        Toast.makeText(getBaseContext(), "Выбран = " + spinner_facult.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
	  	      }
	  	      @Override
	  	      public void onNothingSelected(AdapterView<?> arg0) {
	  	      } 
	  	});
	        
	        String[] data_group = {"группа_1", "группа_2", "группа_3", "группа_4", "группа_5"};
	        // адаптер
	        ArrayAdapter<String> adapter_group = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_group);
	        adapter_group.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	        
	        final Spinner spinner_group = (Spinner) findViewById(R.id.spinner_group);
	        spinner_group.setAdapter(adapter_group);
	        // заголовок
	        spinner_group.setPrompt("Группа");
	        // выделяем элемент 
	        spinner_group.setSelection(0);
	        spinner_group.setOnItemSelectedListener(new OnItemSelectedListener() {
	  	      public void onItemSelected(AdapterView<?> parent, View view,
	  	          int position, long id) {
	  	        // показываем позиция нажатого элемента
	  	        Toast.makeText(getBaseContext(), "Выбрана = " + spinner_group.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
	  	      }
	  	      @Override
	  	      public void onNothingSelected(AdapterView<?> arg0) {
	  	      } 
	  	});

	}

	

	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
        tab_index=tabHost.getCurrentTab();
	//	facult=spinner_facult.getId();
	//	group=spinner_group.getId();
        outState.putInt("T", tab_index);
		
		
		
	}
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
	    // Always call the superclass so it can restore the view hierarchy
	    super.onRestoreInstanceState(savedInstanceState);
	    tab_index = savedInstanceState.getInt("T");
	    //выставляем вкладку которую запомнили активной. 
	    tabHost.setCurrentTab(tab_index);
	    

	}
	     
}