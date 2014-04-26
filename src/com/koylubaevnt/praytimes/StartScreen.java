package com.koylubaevnt.praytimes;

import com.koylubaevnt.praytimes.preferences.PreferencesAlarmScreen;
import com.koylubaevnt.praytimes.preferences.PreferencesCalculationScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class StartScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_screen);
		
		//���� �������� ���, �� ������������� ������������ 
		//� ���������� ��������� �� ���������
		
		//� ��� ����� 2 ���� ��������:
		//1. ��������� ��������
		//2. ��������� ����������
		
		//��� �� ����� ������, ��� ����������� ���������� � ����������� �����
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, getResources().getString(R.string.preferencesCalculation)).setIntent(new Intent(this, PreferencesCalculationScreen.class));
	    
	    menu.add(0, 2, 1, getResources().getString(R.string.preferencesAlarm)).setIntent(new Intent(this, PreferencesAlarmScreen.class));
	    
	    return super.onCreateOptionsMenu(menu);
	}

}
