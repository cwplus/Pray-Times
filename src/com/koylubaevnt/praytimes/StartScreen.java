package com.koylubaevnt.praytimes;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class StartScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_screen);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem mi = menu.add(0, 1, 0, getResources().getString(R.string.preferences));
	    mi.setIntent(new Intent(this, PreferencesScreen.class));
	    
		return super.onCreateOptionsMenu(menu);
	}

}
