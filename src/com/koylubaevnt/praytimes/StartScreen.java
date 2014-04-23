package com.koylubaevnt.praytimes;

import com.koylubaevnt.praytimes.preferences.PreferencesAlarmScreen;
import com.koylubaevnt.praytimes.preferences.PreferencesCalculationScreen;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class StartScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_screen);
		
		//Если настроек нет, то предупреждаем пользователя 
		//и используем настройки по умолчанию
		
		//У нас будет 2 вида настроек:
		//1. Настройки рассчета
		//2. Настройки будильника
		
		//Так же будет компас, для отображения информации о направлении Киблы
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem mi = menu.add(0, 1, 0, getResources().getString(R.string.preferencesCalculation));
	    mi.setIntent(new Intent(this, PreferencesCalculationScreen.class));
	    
	    mi = menu.add(0, 2, 0, getResources().getString(R.string.preferencesAlarm));
	    mi.setIntent(new Intent(this, PreferencesAlarmScreen.class));
		return super.onCreateOptionsMenu(menu);
	}

}
