package bodys;

import java.util.HashMap;
import java.util.Map;

import jit.vn.onseitaiwa2.R;
import utilities.Util;
import views.BodyView;
import views.HealthView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

public class SubBody {
	
	public float x;
	public float y;
	public Bitmap bm;
	private Bitmap bmColor;
	public int color_selected;
	
	private Map<Integer, Bitmap[]> bmSubbodys = new HashMap<Integer, Bitmap[]>();
	
	private float rate;
	private int w,h;
//	public int colorSelected;
	
	private Paint pa = new Paint();
	
	private BodyView vBody;
	
	public SubBody(BodyView vBody,int w,int h){
		this.vBody = vBody;
		this.w = w;
		this.h = h;
		init(vBody.getContext());
	}
	
	private void init(Context ctx){
		bmSubbodys.put(HealthView.ALL, null);
		//dau, mat, co
		bmSubbodys.put(HealthView.HEAD, new Bitmap[]{BitmapFactory.decodeResource(ctx.getResources(), R.drawable.head),BitmapFactory.decodeResource(ctx.getResources(), R.drawable.headcolor)});
		bmSubbodys.put(HealthView.face,  new Bitmap[]{BitmapFactory.decodeResource(ctx.getResources(), R.drawable.head),BitmapFactory.decodeResource(ctx.getResources(), R.drawable.headcolor)});
		bmSubbodys.put(HealthView.neck,  new Bitmap[]{BitmapFactory.decodeResource(ctx.getResources(), R.drawable.head),BitmapFactory.decodeResource(ctx.getResources(), R.drawable.headcolor)});
		//tay, chan
		bmSubbodys.put(HealthView.HAND,  new Bitmap[]{BitmapFactory.decodeResource(ctx.getResources(), R.drawable.arm),BitmapFactory.decodeResource(ctx.getResources(), R.drawable.armcolor)});
		bmSubbodys.put(0XFF877755,  new Bitmap[]{BitmapFactory.decodeResource(ctx.getResources(), R.drawable.arm2),BitmapFactory.decodeResource(ctx.getResources(), R.drawable.arm2color)});
		
		//BACK HAND
		bmSubbodys.put(HealthView.BACKHAND,  new Bitmap[]{BitmapFactory.decodeResource(ctx.getResources(), R.drawable.backhand),BitmapFactory.decodeResource(ctx.getResources(), R.drawable.backhandcolor)});
		bmSubbodys.put(0XFF877777,  new Bitmap[]{BitmapFactory.decodeResource(ctx.getResources(), R.drawable.backhand2),BitmapFactory.decodeResource(ctx.getResources(), R.drawable.backhand2color)});
		
		bmSubbodys.put(HealthView.LEG,  new Bitmap[]{BitmapFactory.decodeResource(ctx.getResources(), R.drawable.leg),BitmapFactory.decodeResource(ctx.getResources(), R.drawable.legcolor)});
		//nguc, bung, vai
		bmSubbodys.put(HealthView.CHEST,  new Bitmap[]{BitmapFactory.decodeResource(ctx.getResources(), R.drawable.body),BitmapFactory.decodeResource(ctx.getResources(), R.drawable.bodycolor)});
		bmSubbodys.put(HealthView.belly,  new Bitmap[]{BitmapFactory.decodeResource(ctx.getResources(), R.drawable.body),BitmapFactory.decodeResource(ctx.getResources(), R.drawable.bodycolor)});
		bmSubbodys.put(HealthView.shoulder,  new Bitmap[]{BitmapFactory.decodeResource(ctx.getResources(), R.drawable.body),BitmapFactory.decodeResource(ctx.getResources(), R.drawable.bodycolor)});
		
		bmSubbodys.put(HealthView.GENITALS,  new Bitmap[]{BitmapFactory.decodeResource(ctx.getResources(), R.drawable.genitals),BitmapFactory.decodeResource(ctx.getResources(), R.drawable.genitals)});

		
		//BACK HAED
		bmSubbodys.put(HealthView.BACKHEAD,  new Bitmap[]{BitmapFactory.decodeResource(ctx.getResources(), R.drawable.backhead),BitmapFactory.decodeResource(ctx.getResources(), R.drawable.backheadcolor)});
		//BACK BODY
		bmSubbodys.put(HealthView.BACK,  new Bitmap[]{BitmapFactory.decodeResource(ctx.getResources(), R.drawable.back),BitmapFactory.decodeResource(ctx.getResources(), R.drawable.backcolor)});
		//ASS
		bmSubbodys.put(HealthView.BUM,  new Bitmap[]{BitmapFactory.decodeResource(ctx.getResources(), R.drawable.bum),BitmapFactory.decodeResource(ctx.getResources(), R.drawable.bum)});
		
		//BACK LEG
		bmSubbodys.put(HealthView.BACKLEG,  new Bitmap[]{BitmapFactory.decodeResource(ctx.getResources(), R.drawable.backleg),BitmapFactory.decodeResource(ctx.getResources(), R.drawable.backlegcolor)});

		//BACK HEAD
		bmSubbodys.put(0XFFCBFF00,  new Bitmap[]{BitmapFactory.decodeResource(ctx.getResources(), R.drawable.backhead),BitmapFactory.decodeResource(ctx.getResources(), R.drawable.backheadcolor)});
		
		
		
	}
	
	public void draw(Canvas c){
		if (bm!=null){
//			pa.setColor(colorSelected);
//			c.drawCircle(100, 50, 50, pa);
			c.save();
			c.scale(rate, rate);
			c.drawBitmap(bm, x, y, null);
			c.restore();
		}
	}
	
	public void setBitmap(Bitmap bm,float x,float y){
		this.bm = bm;
		this.x = x;
		this.y = y;
	}

	public void setSubBodySelected(int color) {
		color_selected = color;
		Bitmap[] bms = bmSubbodys.get(color);
		if (bms!=null){
			bm = bms[0];
			bmColor = bms[1];
			rate(w,h);
			vBody.showSubbody();
		}
	}
	
	private void rate(int w,int h){
		float wr = (float)w/bm.getWidth();
		float hr = (float)h/bm.getHeight();
		rate = wr>hr?hr:wr;
		x = (w/rate - bm.getWidth())/2;
		y = (h/rate - bm.getHeight())/2;
	}
	
	public boolean isShowTouch;
	
	public int onTouchEvent(MotionEvent e) {
		if (bm!=null){
			float ex = e.getX()/rate - x;
			float ey = e.getY()/rate - y;
			color_selected = getColorSelected((int)ex, (int)ey);
			if (color_selected == HealthView.ALL){
//				vBody.showAllbody();
				isShowTouch = false;
			}else{
				isShowTouch = true;
			}
			return color_selected;
		}
		return HealthView.ALL;
	}
	
	private int getColorSelected(int x,int y){
		if (x<0 || x>=bmColor.getWidth() || y<0 || y>=bmColor.getHeight()){
			return HealthView.ALL;
		}else{
			return bmColor.getPixel(x, y);
		}
	}
	
	public void hide(){
		Util.log("HIDE");
		bm = null;
		isShowTouch = false;
		color_selected = HealthView.ALL;
	}

	public void switchSide() {
		isShowTouch = false;
		switch (color_selected) {
		case HealthView.HEAD:
			setSubBodySelected(HealthView.BACKHEAD);
			break;
		case HealthView.BACKHEAD:
			setSubBodySelected(HealthView.HEAD);
			break;
		case HealthView.CHEST:
		case HealthView.belly:
		case HealthView.shoulder:
			setSubBodySelected(HealthView.BACK);
			break;
		case HealthView.BACK:
			setSubBodySelected(HealthView.CHEST);
			break;
		case HealthView.GENITALS:
			setSubBodySelected(HealthView.BUM);
			break;
		case HealthView.BUM:
			setSubBodySelected(HealthView.GENITALS);
			break;
		case HealthView.LEG:
			setSubBodySelected(HealthView.BACKLEG);
			break;
		case HealthView.BACKLEG:
			setSubBodySelected(HealthView.LEG);
			break;
		case HealthView.HAND:
			setSubBodySelected(HealthView.BACKHAND);
			break;
		case HealthView.BACKHAND:
			setSubBodySelected(HealthView.HAND);
			break;
		}	
	}
	
}

