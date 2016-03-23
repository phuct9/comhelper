package jit.vn.onseitaiwa2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.volume);
	}
	
	private void turnoff(){
 Log.v("MyDebug", "OFF!");
	     
	     PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
	     PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "My Tag");

//	     pm.goToSleep(10000);
	     
	     wl.acquire();
//	       ..screen will stay on during this section..
	     
	     
	     try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//	     
//	     wl.release();
	}
	
}
