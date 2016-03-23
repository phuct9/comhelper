package jit.vn.onseitaiwa2;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import customize.BackButton;
import utilities.SetViewSizeByPixel;

public class Help extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN
						| WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
						| WindowManager.LayoutParams.SCREEN_ORIENTATION_CHANGED,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.help);      
        
        final String url = "file:///android_asset/help/help.html";
        WebView webHelp = (WebView) findViewById(R.id.webHelp);
        webHelp.loadUrl(url);

        SetViewSizeByPixel size = new SetViewSizeByPixel(this);
        LinearLayout lyHeader = (LinearLayout) findViewById(R.id.mainAct_lyTitleH);
		size.height(lyHeader, size.RH(152));
        BackButton btBack = (BackButton) findViewById(R.id.mainAct_tvBack);
		btBack.setSize(148,152);
		btBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		TextView tvTitle = (TextView) findViewById(R.id.mainAct_tvTitle);
		size.textSize(tvTitle, size.RH(60));
		Typeface tf02 = Typeface.createFromAsset(getAssets(), "fonts/KozGoPr6N-Bold.otf");
		tvTitle.setTypeface(tf02);
        
        
    }
}