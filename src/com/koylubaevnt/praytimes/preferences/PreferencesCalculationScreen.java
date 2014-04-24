package com.koylubaevnt.praytimes.preferences;

import com.koylubaevnt.praytimes.R;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;

public class PreferencesCalculationScreen extends PreferenceActivity implements OnSharedPreferenceChangeListener{
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
	        addPreferencesFromResource(R.xml.preferences_calculation);
	        
/*	        PreferenceManager.setDefaultValues(this, R.xml.preferences_calculation, false);
	        for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
	            initSummary(getPreferenceScreen().getPreference(i));
	        }*/
	    }

	    @SuppressWarnings("deprecation")
		@Override
		public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
			updatePrefSummary(findPreference(key));
		}


		@SuppressWarnings("deprecation")
		@Override
		public void onPause() {
			super.onPause();
			// Unregister the listener whenever a key changes
	        getPreferenceScreen().getSharedPreferences()
	                .unregisterOnSharedPreferenceChangeListener(this);
		}

		@SuppressWarnings("deprecation")
		@Override
		public void onResume() {
			super.onResume();
	        // Set up a listener whenever a key changes
	        getPreferenceScreen().getSharedPreferences()
	                .registerOnSharedPreferenceChangeListener(this);
		}
		/*
		private void initSummary(Preference p) {
	        if (p instanceof PreferenceCategory) {
	            PreferenceCategory pCat = (PreferenceCategory) p;
	            for (int i = 0; i < pCat.getPreferenceCount(); i++) {
	                initSummary(pCat.getPreference(i));
	            }
	        } else {
	            updatePrefSummary(p);
	        }
	    }
		*/

		private void updatePrefSummary(Preference p) {
	        if (p instanceof ListPreference) {
	            ListPreference listPref = (ListPreference) p;
	            p.setSummary(listPref.getEntry());
	        }
	        if (p instanceof EditTextPreference) {
	            EditTextPreference editTextPref = (EditTextPreference) p;
	            if (p.getTitle().toString().contains("assword"))
	            {
	                p.setSummary("******");
	            } else {
	                p.setSummary(editTextPref.getText());
	            }
	        }
	        /*if (p instanceof MultiSelectListPreference) {
	            EditTextPreference editTextPref = (EditTextPreference) p;
	            p.setSummary(editTextPref.getText());
	        }*/
	    }
	    /**
	     * Wraps {@link #onCreate(Bundle)} code for Android >= 3 (i.e. API lvl >= 11).
	     */
	    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	    private void onCreatePreferenceFragment() {
	        getFragmentManager().beginTransaction()
	                .replace(android.R.id.content, new PreferencesCalculationScreenFragment())
	                .commit();
	    }
}
