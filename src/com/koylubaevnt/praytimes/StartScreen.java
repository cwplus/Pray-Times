package com.koylubaevnt.praytimes;

import com.koylubaevnt.praytimes.alarm.AlarmManagerScreen;
import com.koylubaevnt.praytimes.compas.QiblaLocatorScreen;
import com.koylubaevnt.praytimes.database.DatabaseActivity;
import com.koylubaevnt.praytimes.preferences.PreferencesAlarmScreen;
import com.koylubaevnt.praytimes.preferences.PreferencesCalculationScreen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
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
	int dstIndex;
	
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
	String keyLongitude;
	String keyLatitude;
	String keyElevation;
	String keyTimeZone;
	String keyTimeZoneValue;
	String keyDST;
	
	String keyAlarmBefore;
	String keyAlarmBeforeList;
	String keyAlarmBeforeRington;
	String keyAlarmBeforeVibro;
	String keyAlarm;
	String keyAlarmRington;
	String keyAlarmVibro;
	
	StringBuilder sb = new StringBuilder();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_screen);
		
		tvInfo = (TextView)findViewById(R.id.tvInfo);
		tvCalc = (TextView)findViewById(R.id.tvCalc);
		btCalc = (Button)findViewById(R.id.btCalc);
		
		Resources r = getResources();
		keyMethod = r.getString(R.string.keyMethod);
		keyUseGps = r.getString(R.string.keyUseGPS);
		keyLongitude = r.getString(R.string.keyLongitude);
		keyLatitude = r.getString(R.string.keyLatitude);
		keyElevation = r.getString(R.string.keyElevation);
		keyTimeZone = r.getString(R.string.keyTimeZone);
		keyTimeZoneValue = r.getString(R.string.keyTimeZoneValue);
		keyDST = r.getString(R.string.keyDST);
	
		keyAlarmBefore = r.getString(R.string.keyAlarmBefore);
		keyAlarmBeforeList = r.getString(R.string.keyAlarmBeforeList);
		keyAlarmBeforeRington = r.getString(R.string.keyAlarmBeforeRington);
		keyAlarmBeforeVibro = r.getString(R.string.keyAlarmBeforeVibro);
		keyAlarm = r.getString(R.string.keyAlarm);
		keyAlarmRington = r.getString(R.string.keyAlarmRington);
		keyAlarmVibro = r.getString(R.string.keyAlarmVibro);
		
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, getResources().getString(R.string.preferencesCalculation)).setIntent(new Intent(this, PreferencesCalculationScreen.class));
	    
	    menu.add(0, 2, 1, getResources().getString(R.string.preferencesAlarm)).setIntent(new Intent(this, PreferencesAlarmScreen.class));
	    
	    menu.add(0, 3, 2, getResources().getString(R.string.qiblaLocator)).setIntent(new Intent(this, QiblaLocatorScreen.class));
	    
	    menu.add(0, 4, 3, getResources().getString(R.string.tableCalculation)).setIntent(new Intent(this, TableCalculationScreen.class));
	    
	    menu.add(0, 5, 4, getResources().getString(R.string.alarmManager)).setIntent(new Intent(this, AlarmManagerScreen.class));

	    menu.add(0, 6, 5, getResources().getString(R.string.databaseActivity)).setIntent(new Intent(this, DatabaseActivity.class));

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
		getPreferences();
	}

	private void getPreferences(){
		/*		
		settings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		methodIndex = settings.getInt(keyMethod, 1);
		useGPS = settings.getBoolean(keyUseGps, true);
		latitude = settings.getLong(keyLatitude, 0);
		longitude = settings.getLong(keyLongitude, 0);
		elevation = settings.getLong(keyElevation, 0);
		isAutoTimeZone = settings.getBoolean(keyTimeZone, true);
		autoTimeZone = settings.getLong(keyTimeZoneValue, 0);
		dstIndex = settings.getInt(keyDST, 1);
		
		useAlarmBefore = settings.getBoolean(keyAlarmBefore, false);
		alarmBeforeIndex = settings.getInt(keyAlarmBeforeList, 1);
		alarmBeforeRingtone = RingtoneManager.getRingtone(getBaseContext(), Uri.parse(settings.getString(keyAlarmBeforeRington, "DEFAULT_RINGTON_URI")));
		alarmBeforeUseVibration = settings.getBoolean(keyAlarmBeforeVibro, false);
		useAlarmNow = settings.getBoolean(keyAlarm, false);
		alarmNowRingtone = RingtoneManager.getRingtone(getBaseContext(), Uri.parse(settings.getString(keyAlarmRington, "DEFAULT_RINGTON_URI")));;
		alarmNowUseVibration = settings.getBoolean(keyAlarmVibro, false);
*/
		
	}

}
