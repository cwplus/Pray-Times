package com.koylubaevnt.praytimes.preferences;

import com.koylubaevnt.praytimes.R;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;

public class PreferencesCalculationScreen extends PreferenceActivity implements Preference.OnPreferenceChangeListener{
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
	     * Wraps legacy {@link #onCreate(Bundle)} code for Android < 3 (i.e. API lvl
	     * < 11).
	     */
	    @SuppressWarnings("deprecation")
	    private void onCreatePreferenceActivity() {
	        addPreferencesFromResource(R.xml.preferences_calculation);
	        ListPreference method = (ListPreference)this.findPreference(getResources().getString(R.string.keyMethod));
	        method.setSummary(method.getEntry());
	        method.setOnPreferenceChangeListener(this);
	    }

	    
	    public boolean onPreferenceChange(Preference preference, Object newValue)
	    {
	        preference.setSummary((CharSequence)newValue);
	 
	        return true;
	    }
	    
	    /**
	     * Wraps {@link #onCreate(Bundle)} code for Android >= 3 (i.e. API lvl >=
	     * 11).
	     */
	    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	    private void onCreatePreferenceFragment() {
	        getFragmentManager().beginTransaction()
	                .replace(android.R.id.content, new PreferencesCalculationScreenFragment())
	                .commit();
	    }
}
