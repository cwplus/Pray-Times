package com.koylubaevnt.praytimes;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import com.koylubaevnt.praytimes.alarm.AlarmManagerScreen;
import com.koylubaevnt.praytimes.compas.QiblaLocatorScreen;
import com.koylubaevnt.praytimes.core.Location;
import com.koylubaevnt.praytimes.core.Method;
import com.koylubaevnt.praytimes.core.PrayTimes;

import static com.koylubaevnt.praytimes.core.Configuration.angle;
import static com.koylubaevnt.praytimes.core.Configuration.minutes;

import com.koylubaevnt.praytimes.core.PrayTimes.Time;
import com.koylubaevnt.praytimes.core.Util;
import com.koylubaevnt.praytimes.database.SQLController;
import com.koylubaevnt.praytimes.gps.AndroidGPSTrackingActivity;
import com.koylubaevnt.praytimes.gps.GPSScreen;
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
	
	SQLController sqlcon;
	 
	StringBuilder sb = new StringBuilder();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_screen);
		
		tvInfo = (TextView)findViewById(R.id.tvInfo);
		tvCalc = (TextView)findViewById(R.id.tvCalc);
		btCalc = (Button)findViewById(R.id.btCalc);
		btCalc.setOnClickListener(this);
		
		sqlcon = new SQLController(this);
		
		Resources r = getResources();
		keyMethod = r.getString(R.string.keyMethod);
		keyUseGps = r.getString(R.string.keyUseGPS);
		keyLongitude = r.getString(R.string.keyLongitude);
		keyLatitude = r.getString(R.string.keyLatitude);
		keyElevation = r.getString(R.string.keyElevation);
		keyTimeZone = r.getString(R.string.keyTimeZone);
		keyTimeZoneValue = r.getString(R.string.keyTimeZoneValue);
		keyDST = r.getString(R.string.keyDST);
	/*
		keyAlarmBefore = r.getString(R.string.keyAlarmBefore);
		keyAlarmBeforeList = r.getString(R.string.keyAlarmBeforeList);
		keyAlarmBeforeRington = r.getString(R.string.keyAlarmBeforeRington);
		keyAlarmBeforeVibro = r.getString(R.string.keyAlarmBeforeVibro);
		keyAlarm = r.getString(R.string.keyAlarm);
		keyAlarmRington = r.getString(R.string.keyAlarmRington);
		keyAlarmVibro = r.getString(R.string.keyAlarmVibro);
		*/
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 1, 0, getResources().getString(R.string.preferencesCalculation)).setIntent(new Intent(this, PreferencesCalculationScreen.class));
	    
	    menu.add(0, 2, 1, getResources().getString(R.string.preferencesAlarm)).setIntent(new Intent(this, PreferencesAlarmScreen.class));
	    
	    menu.add(0, 3, 2, getResources().getString(R.string.qiblaLocator)).setIntent(new Intent(this, QiblaLocatorScreen.class));
	    
	    menu.add(0, 4, 3, getResources().getString(R.string.tableCalculation)).setIntent(new Intent(this, TableCalculationScreen.class));
	    
	    menu.add(0, 5, 4, getResources().getString(R.string.alarmManager)).setIntent(new Intent(this, AlarmManagerScreen.class));

	    menu.add(0, 6, 5, getResources().getString(R.string.provider_gps)).setIntent(new Intent(this, GPSScreen.class));
	    
	    menu.add(0, 6, 5, getResources().getString(R.string.activity_gps)).setIntent(new Intent(this, AndroidGPSTrackingActivity.class));
	    

	    return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onClick(View v) {
		if (v.equals(btCalc)){
			//Запускаем расчет
			PrayTimes pt = new PrayTimes(Method.ISNA);

            // Adjustments
            //pt.adjust(Time.FAJR, angle(20));
            //pt.adjust(Time.DHUHR, minutes(2));

            // Offset tunings
            //pt.tuneOffset(Time.FAJR, 2);

            // Calculate praytimes
            Location location = new Location(latitude, longitude, elevation);
            
            GregorianCalendar gc = new GregorianCalendar();
            int lastDay = gc.getActualMaximum(GregorianCalendar.DATE);
            int i;
            sqlcon.open();
            sqlcon.deleteAllData();
            for (i = 1; i <= lastDay; i++){             
	            gc.set(gc.get(GregorianCalendar.YEAR), gc.get(GregorianCalendar.MONTH), i);
            	// Timezone is defined in the calendar
	            Map<Time, Double> times = pt
	                    .getTimes(gc, location);
	            Date today1 = new Date(gc.getTimeInMillis());
	            sqlcon.insertData(gc.getTimeInMillis(),
	            		times.get(Time.IMSAK),
	            		times.get(Time.FAJR),
	            		times.get(Time.SUNRISE),
	            		times.get(Time.DHUHR),
	            		times.get(Time.ASR),
	            		times.get(Time.SUNSET),
	            		times.get(Time.MAGHRIB),
	            		times.get(Time.ISHA),
	            		times.get(Time.MIDNIGHT)
	            		);
	            tvInfo.setText(today1.toString() + " : " +
	            		Util.toTime24(times.get(Time.IMSAK)) + " - " +
	            		Util.toTime24(times.get(Time.FAJR)) + " - " +
	            		Util.toTime24(times.get(Time.SUNRISE)) + " - " +
	            		Util.toTime24(times.get(Time.DHUHR)) + " - " +
	            		Util.toTime24(times.get(Time.ASR)) + " - " +
	            		Util.toTime24(times.get(Time.SUNSET)) + " - " +
	            		Util.toTime24(times.get(Time.MAGHRIB)) + " - " +
	            		Util.toTime24(times.get(Time.ISHA)) + " - " +
	            		Util.toTime24(times.get(Time.MIDNIGHT)));
			}
			sqlcon.close();
        
		}
		
	}

	
	
	@Override
	protected void onResume() {
		super.onResume();
		getPreferences();
	}

	private void getPreferences(){
		settings = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		methodIndex = Integer.parseInt(settings.getString(keyMethod, "1"));
		useGPS = settings.getBoolean(keyUseGps, true);
		if (!useGPS) {
			latitude = Double.parseDouble(settings.getString(keyLatitude, "0"));
			longitude = Double.parseDouble(settings.getString(keyLongitude, "0"));
			elevation = Double.parseDouble(settings.getString(keyElevation, "0"));
		}else
		{
			//получить данные с GPS
		}
		isAutoTimeZone = settings.getBoolean(keyTimeZone, true);
		if (!useGPS) {
			autoTimeZone = Double.parseDouble(settings.getString(keyTimeZoneValue, "0"));
		}else{
			//получить данные с телефона
		}
		
		dstIndex = Integer.parseInt(settings.getString(keyDST, "1"));
		
		useAlarmBefore = settings.getBoolean(keyAlarmBefore, false);
		alarmBeforeIndex = Integer.parseInt(settings.getString(keyAlarmBeforeList, "1"));
		alarmBeforeRingtone = RingtoneManager.getRingtone(getBaseContext(), Uri.parse(settings.getString(keyAlarmBeforeRington, "DEFAULT_RINGTON_URI")));
		alarmBeforeUseVibration = settings.getBoolean(keyAlarmBeforeVibro, false);
		useAlarmNow = settings.getBoolean(keyAlarm, false);
		alarmNowRingtone = RingtoneManager.getRingtone(getBaseContext(), Uri.parse(settings.getString(keyAlarmRington, "DEFAULT_RINGTON_URI")));;
		alarmNowUseVibration = settings.getBoolean(keyAlarmVibro, false);		
	}

}
