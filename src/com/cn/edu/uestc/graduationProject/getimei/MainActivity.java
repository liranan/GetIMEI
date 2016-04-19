package com.cn.edu.uestc.graduationProject.getimei;

import java.util.UUID;

import android.content.Context;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	private TextView android_id;
	private TextView imei;
	private TextView imsi;
	private TextView uuid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		android_id = (TextView) findViewById(R.id.android_id);
		imei = (TextView) findViewById(R.id.imei);
		imsi = (TextView) findViewById(R.id.imsi);
		uuid = (TextView) findViewById(R.id.uuid);

		android_id.setText("Andorid_ID:" + getAndroidId());
		imei.setText("IMEI:" + getImei());
		imsi.setText("IMSI:" + getImsi());
		uuid.setText("UUID:" + getUUID());
	}

	private String getUUID() {
		// TODO Auto-generated method stub
		final TelephonyManager tm = (TelephonyManager) getBaseContext()
				.getSystemService(Context.TELEPHONY_SERVICE);
		final String tmDevice, tmSerial, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = ""
				+ android.provider.Settings.Secure.getString(
						getContentResolver(),
						android.provider.Settings.Secure.ANDROID_ID);
		UUID deviceUuid = new UUID(androidId.hashCode(),
				((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
		return deviceUuid.toString();
	}

	private String getImsi() {
		// TODO Auto-generated method stub
		TelephonyManager telephonyManager = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getSubscriberId();
	}

	private String getImei() {
		// TODO Auto-generated method stub
		TelephonyManager telephonyManager = (TelephonyManager) this
				.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getDeviceId();
	}

	private String getAndroidId() {
		// TODO Auto-generated method stub
		return Secure.getString(getContentResolver(), Secure.ANDROID_ID);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
//just a test