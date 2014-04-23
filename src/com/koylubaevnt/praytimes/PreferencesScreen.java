package com.koylubaevnt.praytimes;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

public class PreferencesScreen extends PreferenceActivity {
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
	        addPreferencesFromResource(R.xml.preferences);
	    }

	    /**
	     * Wraps {@link #onCreate(Bundle)} code for Android >= 3 (i.e. API lvl >=
	     * 11).
	     */
	    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	    private void onCreatePreferenceFragment() {
	        getFragmentManager().beginTransaction()
	                .replace(android.R.id.content, new PreferencesFragment())
	                .commit();
	    }
	    
	    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	    public static class PreferencesFragment extends PreferenceFragment{

			@Override
			public void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				addPreferencesFromResource(R.xml.preferences);
			}
	    	
	    	
	    }
}
