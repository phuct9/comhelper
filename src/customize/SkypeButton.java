package customize;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import jit.vn.onseitaiwa2.OnseitaiwaApp;

public class SkypeButton extends MyButton implements android.view.View.OnClickListener {

	public SkypeButton(Context context) {
		super(context);
		setText("SKYPE");
		setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		OnseitaiwaApp app = (OnseitaiwaApp) getContext().getApplicationContext();
		if (app.friendNickskype!=null)
			callSkype(app.friendNickskype, getContext());
	}
	
	public void callSkype(String number, Context ctx) {
        try {
            //Intent sky = new Intent("android.intent.action.CALL_PRIVILEGED");
            //the above line tries to create an intent for which the skype app doesn't supply public api
            Intent sky = new Intent("android.intent.action.VIEW");
            sky.setData(Uri.parse("skype:" + number));
            ctx.startActivity(sky);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }

    }
	
	

}
