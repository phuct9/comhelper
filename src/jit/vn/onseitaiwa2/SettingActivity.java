package jit.vn.onseitaiwa2;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import views.SettingView;

public class SettingActivity extends Activity {
	
	private SettingView vSetting;
	private EditText txtFriendNickName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		vSetting = new SettingView(this);
		setContentView(vSetting);
		
		OnseitaiwaApp app = (OnseitaiwaApp) getApplicationContext();
		
		txtFriendNickName = (EditText) findViewById(R.id.txtFriendNickName);
		txtFriendNickName.setText(app.friendNickskype);
		
		final String url = "file:///android_asset/help/help.html";
        WebView webHelp = (WebView) findViewById(R.id.webHelp);
        webHelp.loadUrl(url);
	}
	
	public void ok(View v){
		OnseitaiwaApp app = (OnseitaiwaApp) getApplicationContext();
		app.friendNickskype = txtFriendNickName.getText().toString();
		app.save();
		finish();
	}
	
}
