package com.koylubaevnt.praytimes.preferences;

import com.koylubaevnt.praytimes.R;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PreferencesCalculationScreenFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences_calculation);
		ListPreference method = (ListPreference)this.findPreference(getResources().getString(R.string.keyMethod));
        method.setSummary(method.getEntry());
        method.setOnPreferenceChangeListener(this);
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue)
    {
		if (preference instanceof ListPreference) {
			ListPreference listPref = (ListPreference) preference;
			preference.setSummary(listPref.getEntry());
		}else
			preference.setSummary((CharSequence)newValue);

        return true;
    }
}
