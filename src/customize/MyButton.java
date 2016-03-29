package customize;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;
import jit.vn.onseitaiwa2.R;

public class MyButton extends Button {
	
	private boolean pressed;
	private float rate;
	private Bitmap bmPressed,bmNormal;
	private Rect[] rscs = new Rect[9];
	private Rect[] dsts = new Rect[9];

	public MyButton(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);
		setBackgroundResource(R.drawable.btn01_9p);
		try {
			setTypeface(Typeface.createFromAsset(ctx.getAssets(), "fonts/KozGoPr6N-Bold.otf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MyButton(Context ctx){
		super(ctx);
		//setBackgroundResource(R.drawable.btn01_9p);
		setBackgroundColor(Color.TRANSPARENT);
		try {
			setTypeface(Typeface.createFromAsset(ctx.getAssets(), "fonts/KozGoPr6N-Bold.otf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		bmPressed = BitmapFactory.decodeResource(getResources(), R.drawable.button9p_on);
		bmNormal = BitmapFactory.decodeResource(getResources(), R.drawable.button9p_off);
		
		
		int wbm = bmNormal.getWidth();
		int hbm = bmNormal.getHeight();
		
		rscs[0] = new Rect(0, 0, 32, 32);
		rscs[1] = new Rect(wbm-32, 0, wbm, 32);
		rscs[2] = new Rect(0, hbm-32, 32, hbm);
		rscs[3] = new Rect(wbm-32,hbm-32,wbm,hbm);
		
		rscs[4] = new Rect(rscs[0].right,rscs[0].top,rscs[1].left,rscs[1].bottom);
		rscs[5] = new Rect(rscs[2].right,rscs[2].top,rscs[3].left,rscs[3].bottom);
		
		rscs[6] = new Rect(rscs[0].left,rscs[0].bottom,rscs[2].right,rscs[2].top);
		rscs[7] = new Rect(rscs[1].left,rscs[1].bottom,rscs[3].right,rscs[3].top);
		
		rscs[8] = new Rect(rscs[0].right,rscs[0].bottom,rscs[3].left,rscs[3].top);
		
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		float bmw = bmNormal.getWidth();
		float bmh = bmNormal.getHeight();
		
		float ratew = w/bmw;
		float rateh = h/bmh;
		rate = ratew<rateh?ratew:rateh;
		
		dsts[0] = new Rect(0,0,32,32);
		dsts[1] = new Rect(w-32,0,w,32);
		dsts[2] = new Rect(0,h-32,32,h);
		dsts[3] = new Rect(w-32, h-32,w,h);
		
		
		dsts[4] = new Rect(dsts[0].right,dsts[0].top,dsts[1].left,dsts[1].bottom);
		dsts[5] = new Rect(dsts[2].right,dsts[2].top,dsts[3].left,dsts[3].bottom);
		
		dsts[6] = new Rect(dsts[0].left,dsts[0].bottom,dsts[2].right,dsts[2].top);
		dsts[7] = new Rect(dsts[1].left,dsts[1].bottom,dsts[3].right,dsts[3].top);
		
		dsts[8] = new Rect(dsts[0].right,dsts[0].bottom,dsts[3].left,dsts[3].top);
		
//		if (getPaint().getTextSize() > h-46){
//			getPaint().setTextSize(h - 46);
//		}
//		getPaint().setTextSize(20);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			pressed = true;
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			pressed = false;
			invalidate();
			break;
		default:
			break;
		}
		return super.onTouchEvent(e);
	}
	
	@Override
	public void draw(Canvas c) {
		if (pressed){
			draw9(c, bmPressed);
		}else{
			draw9(c, bmNormal);
		}
		super.draw(c);
	}
	
	private void draw9(Canvas c,Bitmap bm){
		for(int i=0;i<9;i++){
			c.drawBitmap(bm, rscs[i],dsts[i],null);
		}
	}
	
}
