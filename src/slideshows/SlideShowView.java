package slideshows;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.View;

public class SlideShowView extends View {
	
	private Utility utils;
	private ArrayList<String> _imagePaths;
	private Random rd = new Random();
	private float w,h;
	private Bitmap bm;
	private float rate;
	private float dx,dy;
	private Handler handler;
	
	
	public SlideShowView(Context ctx) {
		super(ctx);
		utils = new Utility(ctx);
		_imagePaths = utils.getFilePaths();
		handler = new Handler();
	}
	
	public void setSize(float w,float h){
		this.w = w;
		this.h = h;
	}
	
	@Override
	protected void onDraw(Canvas c) {
		c.save();
		c.translate(dx, dy);
		c.scale(rate, rate);
		c.drawBitmap(bm, 0,0, null);
		c.restore();
	}
	
	public Bitmap randomBitmap(){
		int position = rd.nextInt(_imagePaths.size());
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		options.inSampleSize = 2;
		return BitmapFactory.decodeFile(_imagePaths.get(position), options);
	}
	
	private void slide(){
		bm = randomBitmap();
		float ratew = w/bm.getWidth();
		float rateh = h/bm.getHeight();
		rate = ratew<rateh?ratew:rateh;
		dx = (w - bm.getWidth()*rate)/2;
		dy = (h - bm.getHeight()*rate)/2;
		invalidate();
	}
	
	public void show(){
		setVisibility(View.VISIBLE);
		slide();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				show();
			}
		}, 4000);
	}

	public void hide() {
		setVisibility(View.GONE);
	}

}
