package com.koylubaevnt.praytimes.gps;

import java.util.Date;

import com.koylubaevnt.praytimes.R;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;



public class GPSScreen extends Activity implements OnClickListener{
	private LocationManager locationManager;
	StringBuilder sbGPS = new StringBuilder();
	StringBuilder sbNet = new StringBuilder();
	 
	TextView tvEnabledGPS;
  TextView tvStatusGPS;
  TextView tvLocationGPS;
  TextView tvEnabledNet;
  TextView tvStatusNet;
  TextView tvLocationNet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gps_layout);
		
		tvEnabledGPS = (TextView) findViewById(R.id.tvEnabledGPS);
	    tvStatusGPS = (TextView) findViewById(R.id.tvStatusGPS);
	    tvLocationGPS = (TextView) findViewById(R.id.tvLocationGPS);
	    tvEnabledNet = (TextView) findViewById(R.id.tvEnabledNet);
	    tvStatusNet = (TextView) findViewById(R.id.tvStatusNet);
	    tvLocationNet = (TextView) findViewById(R.id.tvLocationNet);
		
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
	}

	

	@Override
	public void onClick(View v) {
		
	}

	@Override
	  protected void onPause() {
	    super.onPause();
	    locationManager.removeUpdates(locationListener);
	  }
	
	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
		        1000 * 10, 10, locationListener);
	    locationManager.requestLocationUpdates(
	        LocationManager.NETWORK_PROVIDER, 1000 * 10, 10,
	        locationListener);
	    checkEnabled();
	}

	 private LocationListener locationListener = new LocationListener() {

		    @Override
		    public void onLocationChanged(Location location) {
		      showLocation(location);
		    }

		    @Override
		    public void onProviderDisabled(String provider) {
		      checkEnabled();
		    }

		    @Override
		    public void onProviderEnabled(String provider) {
		      checkEnabled();
		      showLocation(locationManager.getLastKnownLocation(provider));
		    }

		    @Override
		    public void onStatusChanged(String provider, int status, Bundle extras) {
		      if (provider.equals(LocationManager.GPS_PROVIDER)) {
		        tvStatusGPS.setText("Status: " + String.valueOf(status));
		      } else if (provider.equals(LocationManager.NETWORK_PROVIDER)) {
		        tvStatusNet.setText("Status: " + String.valueOf(status));
		      }
		    }
		  };

		  private void showLocation(Location location) {
		    if (location == null)
		      return;
		    if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
		      tvLocationGPS.setText(formatLocation(location));
		    } else if (location.getProvider().equals(
		        LocationManager.NETWORK_PROVIDER)) {
		      tvLocationNet.setText(formatLocation(location));
		    }
		  }

		  private String formatLocation(Location location) {
		    if (location == null)
		      return "";
		    return String.format(
		        "Coordinates: lat = %1$.4f, lon = %2$.4f, time = %3$tF %3$tT",
		        location.getLatitude(), location.getLongitude(), new Date(
		            location.getTime()));
		  }

		  private void checkEnabled() {
		    tvEnabledGPS.setText("Enabled: "
		        + locationManager
		            .isProviderEnabled(LocationManager.GPS_PROVIDER));
		    tvEnabledNet.setText("Enabled: "
		        + locationManager
		            .isProviderEnabled(LocationManager.NETWORK_PROVIDER));
		  }

		  public void onClickLocationSettings(View view) {
		    startActivity(new Intent(
		        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
		  };
}
