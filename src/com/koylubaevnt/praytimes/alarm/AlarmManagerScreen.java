package com.koylubaevnt.praytimes.alarm;

import com.koylubaevnt.praytimes.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class AlarmManagerScreen extends Activity {
	private AlarmManagerBroadcastReceiver alarm;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_alarm_manager);
	    alarm = new AlarmManagerBroadcastReceiver();
	}
	 
	@Override
	protected void onStart() {
		super.onStart();
	}
	 
	public void startRepeatingTimer(View view) {
		Context context = this.getApplicationContext();
		if(alarm != null){
			alarm.SetAlarm(context);
		}else{
			Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
		}
	}
	 
	public void cancelRepeatingTimer(View view){
		Context context = this.getApplicationContext();
		if(alarm != null){
			alarm.CancelAlarm(context);
		}else{
			Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
		}
	}
	 
	public void onetimeTimer(View view){
		Context context = this.getApplicationContext();
		if(alarm != null){
			alarm.setOnetimeTimer(context);
		}else{
			Toast.makeText(context, "Alarm is null", Toast.LENGTH_SHORT).show();
		}
	}
	 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//getMenuInflater().inflate(R.menu.activity_widget_alarm_manager, menu);
		return true;
	}
}
