package customize;

import jit.vn.onseitaiwa2.R;
import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.support.v4.widget.SimpleCursorAdapter.ViewBinder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class SleepButton extends MyButton implements android.view.View.OnClickListener {

	private boolean isSleep = false;
	
	public SleepButton(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);
	}
	
	public SleepButton(Context ctx){
		super(ctx);
		changeIcon();
		setOnClickListener(this);
	}
	
	/**
	 * chang icon of button on/off
	 */
	public void changeIcon(){
		if (isSleep){
			isSleep = false;
			setBackgroundDrawable(getResources().getDrawable(R.drawable.btsleepoff));
		}else{
			isSleep = true;
			setBackgroundDrawable(getResources().getDrawable(R.drawable.btsleepon));
		}
		invalidate();
	}

	@Override
	public void onClick(View v) {
		Activity act = (Activity) getContext();
		try {
			int brightness = android.provider.Settings.System.getInt(getContext().getContentResolver(), android.provider.Settings.System.SCREEN_BRIGHTNESS);
			Log.v("MyDebug", "brightness = "+brightness);
			isSleep =  brightness <= 0;
		} catch (SettingNotFoundException e) {
			e.printStackTrace();
		}
		
		WindowManager.LayoutParams lp = act.getWindow().getAttributes();
	    if (!isSleep){
	    	isSleep = true; 
	    	Settings.System.putInt(getContext().getContentResolver(),Settings.System.SCREEN_BRIGHTNESS_MODE, 0);
			Settings.System.putInt(getContext().getContentResolver(),Settings.System.SCREEN_BRIGHTNESS, 0);
	    	lp.screenBrightness = 0.1f;
	    }else{
	    	isSleep = false;
	    	Settings.System.putInt(getContext().getContentResolver(),Settings.System.SCREEN_BRIGHTNESS_MODE, 255);
			Settings.System.putInt(getContext().getContentResolver(),Settings.System.SCREEN_BRIGHTNESS, 255);
	    	lp.screenBrightness = 1.0f;
	    }
	    
	    act.getWindow().setAttributes(lp);
		changeIcon();
	}
	
	
	
}
