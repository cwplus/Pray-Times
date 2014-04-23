package com.koylubaevnt.praytimes.preferences;

import com.koylubaevnt.praytimes.R;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.RingtonePreference;

public class PreferencesAlarmScreen extends PreferenceActivity implements Preference.OnPreferenceChangeListener{
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            onCreatePreferenceActivity();
        } else {
            onCreatePreferenceFragment();
        }
	  }
	  
	  /**
	     * Wraps legacy {@link #onCreate(Bundle)} code for Android < 3 (i.e. API lvl < 11).
	     */
	    @SuppressWarnings("deprecation")
	    private void onCreatePreferenceActivity() {
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

	    
	    public boolean onPreferenceChange(Preference preference, Object newValue)
	    {
	    	if (preference instanceof ListPreference) {
				preference.setSummary(((ListPreference) preference).getEntry());
			}else if (preference instanceof RingtonePreference) {
				//preference.setSummary(((RingtonePreference) preference).get);
				//���-�� ���� ��� ������� �������� �����
				//� � ����������, ���-�� �������� ����� ����� ������!
			}else				
				preference.setSummary((CharSequence)newValue);
	    	return true;
	    }
	    
	    /**
	     * Wraps {@link #onCreate(Bundle)} code for Android >= 3 (i.e. API lvl >= 11).
	     */
	    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	    private void onCreatePreferenceFragment() {
	        getFragmentManager().beginTransaction()
	                .replace(android.R.id.content, new PreferencesAlarmScreenFragment())
	                .commit();
	    }
}
