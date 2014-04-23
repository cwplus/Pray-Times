package com.koylubaevnt.praytimes.preferences;

import com.koylubaevnt.praytimes.R;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PreferencesAlarmScreenFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_alarm);
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue)
    {
		if (preference instanceof ListPreference) {
			preference.setSummary(((ListPreference) preference).getEntry());
		}else
			preference.setSummary((CharSequence)newValue);
    	return true;
    }
}
