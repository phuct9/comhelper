package views;

import jit.vn.onseitaiwa2.MainActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;

public class SleepView extends View {

	public SleepView(Context context) {
		super(context);
		setVisibility(GONE);
//		setKeepScreenOn(true);
	}
	
	@Override
	protected void onDraw(Canvas c) {
		c.drawColor(Color.GREEN);
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		unactive();
		return super.onTouchEvent(e);
	}

	public void active() {
		setVisibility(View.VISIBLE);
//		LayoutParams params = ((MainActivity)getContext()).getWindow().getAttributes();
//		params.flags |= LayoutParams.FLAG_KEEP_SCREEN_ON;
//		params.screenBrightness = 0;
//		//params.screenBrightness = -1;
//		((MainActivity)getContext()).getWindow().setAttributes(params);
		
	}
	
	private void unactive(){
		Log.v("MyDebug", "touch");
		setVisibility(GONE);
//		LayoutParams params = ((MainActivity)getContext()).getWindow().getAttributes();
//		params.flags |= LayoutParams.FLAG_KEEP_SCREEN_ON;
//		params.screenBrightness = -1;
//		((MainActivity)getContext()).getWindow().setAttributes(params);
	}
	
	
}
