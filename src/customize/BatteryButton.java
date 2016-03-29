package customize;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.os.BatteryManager;
import utilities.Util;

public class BatteryButton extends MyButton {
	
	private int level;

	private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context ctxt, Intent intent) {
			level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
			setText(level+"%");
		}
	};

	public BatteryButton(Context ctx) {
		super(ctx);
		((Activity)ctx).registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		getPaint().setTextSize(20);
	}
	
	@Override
	public void draw(Canvas c) {
		super.draw(c);
		Util.log(getPaint().getTextSize(),getHeight());
	}

}
