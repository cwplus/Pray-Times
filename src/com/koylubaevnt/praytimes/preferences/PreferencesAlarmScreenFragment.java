package com.koylubaevnt.praytimes.preferences;

import com.koylubaevnt.praytimes.R;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.RingtonePreference;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PreferencesAlarmScreenFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_alarm);
		
		ListPreference alarmBefore = (ListPreference)this.findPreference(getResources().getString(R.string.keyAlarmBefore));
		alarmBefore.setSummary(alarmBefore.getEntry());
		alarmBefore.setOnPreferenceChangeListener(this);
		
		RingtonePreference rington = (RingtonePreference)this.findPreference(getResources().getString(R.string.keyAlarmBeforeRington));
		rington.setSummary(rington.getTitle());
		rington.setOnPreferenceChangeListener(this);
        
		rington = (RingtonePreference)this.findPreference(getResources().getString(R.string.keyAlarmRington));
		rington.setSummary(rington.getTitle());
		rington.setOnPreferenceChangeListener(this);
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue)
    {
		if (preference instanceof ListPreference) {
			preference.setSummary(((ListPreference) preference).getEntry());
		}else if (preference instanceof RingtonePreference) {
			//preference.setSummary(((RingtonePreference) preference).get);
			//как-то надо тут считать название песни
			//а в настройках, как-то добавить выбор любой музыки!
		}else				
			preference.setSummary((CharSequence)newValue);
    	return true;
    }
}
