package jit.vn.onseitaiwa2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import http.HttpManager;

public class Setting extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView txtSetting = new TextView(this);
		setContentView(txtSetting);
		txtSetting.append(HttpManager.SERVER_ADDRESS);
	}
	
	
	
}
