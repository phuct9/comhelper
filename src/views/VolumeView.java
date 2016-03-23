package views;

import utilities.SetViewSizeByPixel;
import utilities.Util;
import jit.vn.onseitaiwa2.R;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class VolumeView extends LinearLayout {
	
	private SetViewSizeByPixel size;
	private Button[] imgVolumeStatuss = new Button[4];
	
	private Handler handler;
	private Runnable run = new Runnable() {
		@Override
		public void run() {
			setVisibility(View.GONE);
		}
	};
	
	public VolumeView(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);
		init(ctx);
	}
	
	public VolumeView(Context ctx) {
		super(ctx);
		init(ctx);
	}
	
	private void init(Context ctx){
		inflate(ctx, R.layout.volume, this);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		//setVisibility(View.GONE);
		handler = new Handler();
		try {
			size = new SetViewSizeByPixel(ctx);
			initVolume();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private float w,h;
	private void initVolume(){
		size.size(findViewById(R.id.layVolumeStatus), size.RW(1016), size.RH(278));
		size.marginTop(findViewById(R.id.layVolumeStatus), size.RH(381));
		size.marginLeft(findViewById(R.id.layVolumeStatus), size.RH(36));
				
		Button imgVolumeStatussSpeaker = (Button) findViewById(R.id.imgVolumeStatusSpeaker);
		imgVolumeStatuss[0] = (Button) findViewById(R.id.imgVolumeStatus0);
		imgVolumeStatuss[1] = (Button) findViewById(R.id.imgVolumeStatus1);
		imgVolumeStatuss[2] = (Button) findViewById(R.id.imgVolumeStatus2);
		imgVolumeStatuss[3] = (Button) findViewById(R.id.imgVolumeStatus3);
		
		size.size(imgVolumeStatussSpeaker,size.RW(171), size.RH(278));
		size.size(imgVolumeStatuss[0],size.RW(200), size.RH(278));
		size.size(imgVolumeStatuss[1],size.RW(191), size.RH(278));
		size.size(imgVolumeStatuss[2],size.RW(187), size.RH(278));
		size.size(imgVolumeStatuss[3],size.RW(267), size.RH(278));
		
	}
	
	public void show(int i) {
		Util.log(getId());
		for(int j=0;j<i;j++){
			imgVolumeStatuss[j].setVisibility(VISIBLE);
		}
		for(int j=i;j<imgVolumeStatuss.length;j++){
			imgVolumeStatuss[j].setVisibility(INVISIBLE);
		}
		setVisibility(View.VISIBLE);
		handler.removeCallbacks(run);
		handler.postDelayed(run, 1000);
	}

	
	
}
