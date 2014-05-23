package com.JohnnyWorks.GlassQRcodeTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.barcodeeye.scan.CaptureActivity;

public class MainActivity extends Activity {

	private TextView sampleTextview;
	public static int SCAN_QR = 0x000001;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card_main);

		sampleTextview = (TextView) findViewById(R.id.sample_txt);

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				startScanQRcode();
			}
		}, 100);
	}

	public void startScanQRcode() {
		Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent, SCAN_QR);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == SCAN_QR) {
			if (resultCode == RESULT_OK) {
				String contents = data.getStringExtra("SCAN_RESULT");
				String format = data.getStringExtra("SCAN_RESULT_FORMAT");
				// Handle successful scan
				sampleTextview.setText(contents);

			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.scan:
			startScanQRcode();
			return true;
		case R.id.stop:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
			openOptionsMenu();
			return true;
		}
		return false;
	}
}
