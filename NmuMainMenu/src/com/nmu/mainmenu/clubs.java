package com.nmu.mainmenu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class clubs extends Activity {
	String[] club_names = { "���������", "������", "������������ �����", "���� �������  � ����������",
			"���� ������ �� ��������", "���� ������ �� �����", "���� ��������� ����� �++",
			"������ ��������", "����������", "���������� �����", "������������ ������� ���� ������� ����",
			"���? ���? �����?"};
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clubs);
		
		// ������� ������
		ListView ClubList = (ListView) findViewById(R.id.ClubList);
		// ������� �������
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		android.R.layout.simple_list_item_1, club_names);
		// ����������� ������� ������
		ClubList.setAdapter(adapter);
		
		
	}

}
