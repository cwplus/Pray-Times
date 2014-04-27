package com.koylubaevnt.praytimes;

import com.koylubaevnt.praytimes.alarm.AlarmManagerScreen;
import com.koylubaevnt.praytimes.compas.QiblaLocatorScreen;
import com.koylubaevnt.praytimes.preferences.PreferencesAlarmScreen;
import com.koylubaevnt.praytimes.preferences.PreferencesCalculationScreen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.Ringtone;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StartScreen extends Activity implements OnClickListener{
	TextView tvCalc, tvInfo;
	Button btCalc;
	SharedPreferences settings;
	
	//переменные для расчета
	int methodIndex;
	boolean useGPS;
	double longitude;
	double latitude;
	double elevation;
	boolean isAutoTimeZone;
	double autoTimeZone;
	double dst;
	
	//переменные для будильника
	boolean useAlarmBefore;
	int alarmBeforeIndex;
	Ringtone alarmBeforeRingtone;
	boolean alarmBeforeUseVibration;
	boolean useAlarmNow;
	Ringtone alarmNowRingtone;
	boolean alarmNowUseVibration;
	
	String keyMethod;
	String keyUseGps;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_screen);
		
		tvInfo = (TextView)findViewById(R.id.tvInfo);
		tvCalc = (TextView)findViewById(R.id.tvCalc);
		btCalc = (Button)findViewById(R.id.btCalc);
		
		Resources r = getResources();
		keyMethod = r.getString(R.string.keyMethod);
		useGPS = r.getIn
		
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, getResources().getString(R.string.preferencesCalculation)).setIntent(new Intent(this, PreferencesCalculationScreen.class));
	    
	    menu.add(0, 2, 1, getResources().getString(R.string.preferencesAlarm)).setIntent(new Intent(this, PreferencesAlarmScreen.class));
	    
	    menu.add(0, 3, 2, getResources().getString(R.string.qiblaLocator)).setIntent(new Intent(this, QiblaLocatorScreen.class));
	    
	    menu.add(0, 4, 3, getResources().getString(R.string.tableCalculation)).setIntent(new Intent(this, TableCalculationScreen.class));
	    
	    menu.add(0, 5, 4, getResources().getString(R.string.alarmManager)).setIntent(new Intent(this, AlarmManagerScreen.class));

	    return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onClick(View v) {
		if (v.equals(btCalc)){
			//Запускаем расчет
		}
		
	}

	
	
	@Override
	protected void onResume() {
		super.onResume();
		
	}

	private void getPreferences(){
		settings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		
		methodIndex = settings.getInt(, 1);
		Boolean notif = sp.getBoolean("notif", false);
		    String address = sp.getString("address", "");
		    String text = "Notifications are "
		        + ((notif) ? "enabled, address = " + address : "disabled");
		    tvInfo.setText(text);
	}

}
