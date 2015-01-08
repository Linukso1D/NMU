package com.nmu.mainmenu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class clubs extends Activity {
	String[] club_names = { "Баскетбол", "Борьба", "Велосипедный спорт", "Клуб веселых  и находчивых",
			"Клуб гребли на байдарке", "Клуб гребли на каное", "Клуб любителей языка С++",
			"Легкая атлетика", "Минифутбол", "Настольный тенис", "Студенческий научный клуб кафедры ПЗКС",
			"Что? Где? Когда?"};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clubs);
		
		// находим список
		ListView ClubList = (ListView) findViewById(R.id.ClubList);
		// создаем адаптер
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_list_item_1, club_names);
		// присваиваем адаптер списку
		ClubList.setAdapter(adapter);
		
		
	}

}
