package views;

import java.util.HashMap;
import java.util.Map;

import bodys.Body;
import bodys.SubBody;


import jit.vn.onseitaiwa2.R;
import utilities.Util;
import jit.vn.onseitaiwa2.MainActivity.SCREEN;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.provider.SyncStateContract.Helpers;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class BodyView extends View {
	
	private Bitmap bm;
//	private Bitmap bmColor;
//	private Bitmap bmFront;
//	private Bitmap bmFrontColor;
//	private Bitmap bmRear;
//	private Bitmap bmRearColor;
	private float width,height;
//	private float rate;
//	private Rect src = new Rect(),dst = new Rect();
//	private float dx,dy; 
	
	
	public HealthView healthView;
	
	private Body body;
	private SubBody subBody;
	
	public BodyView(HealthView healthView,int width,int height) {
		super(healthView.getContext());
		this.healthView = healthView;
		this.width = width;
		this.height = height;
//		bmFront = BitmapFactory.decodeResource(healthView.getContext().getResources(), R.drawable.body_front);
//		bmRear = BitmapFactory.decodeResource(healthView.getContext().getResources(), R.drawable.body_rear);
//		bmFrontColor = BitmapFactory.decodeResource(healthView.getContext().getResources(), R.drawable.body_front_color);
//		bmRearColor = BitmapFactory.decodeResource(healthView.getContext().getResources(), R.drawable.body_rear_color);
//
//		bm = bmRear;
//		bmColor = bmRearColor;
		
//		initSrc();
		
		body = new Body(this,width,height);
		subBody = new SubBody(this,width,height);
		
	}
	
	private Paint pa = new Paint();{
		pa.setStrokeWidth(2);
		pa.setPathEffect(null);
		pa.setColor(Color.BLUE);
	}
	
	private Paint paW = new Paint();{
//		paW.setStyle(Style.STROKE);
		paW.setStrokeWidth(2);
		paW.setPathEffect(null);
		paW.setColor(Color.WHITE);
	}
	
	@Override
	protected void onDraw(Canvas c) {
		if (subBody.bm!=null){
			c.drawColor(Color.GRAY);
			subBody.draw(c);
			if (subBody.isShowTouch){
				c.drawCircle(xtouch, ytouch, 8, pa);
			}
		}else{
			body.draw(c);
		}
	}
	
	private float ex,ey;
	private float xtouch,ytouch;
//	private int color;
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		xtouch = ex = e.getX();
		ytouch = ey = e.getY();
		int color;
		if (subBody.bm != null){
			color = subBody.onTouchEvent(e);
		}else{
			color = body.onTouchEvent(e);
			subBody.setSubBodySelected(color);
		}
		if (color!=0){
			healthView.setMenu(color);
		}
		this.invalidate();
		return false;
	}
	
//	private boolean outOfZoom(float x,float y){
//		return x<dst.left || x>dst.right || y<dst.top || y>dst.bottom;
//	}
	
	
//	private Map<Integer,Rect> srcs = new HashMap<Integer, Rect>();
	
//	private void initSrc(){
//			srcs.put(HealthView.ALL, new Rect(0,0,0,0));
//			srcs.put(HealthView.head, new Rect(159,12,159+124,12+195));
//			srcs.put(HealthView.face, new Rect(159,12,159+124,12+195));
//			srcs.put(HealthView.arm, new Rect(0,166,0+159,166+482));
//			srcs.put(HealthView.leg, new Rect(121,622,121+203,622+449));
//			srcs.put(HealthView.chest, new Rect(121,166,121+203,166+344));//nguc
//			srcs.put(HealthView.belly, new Rect(121,166,121+203,166+344));//bung
//			srcs.put(HealthView.genitals, new Rect(121,490,121+203,490+132));//bo phan sinh duc
//			srcs.put(HealthView.neck, new Rect(159,12,159+124,12+195));//co
//			srcs.put(HealthView.shoulder, new Rect(81,166,81+279,166+158));//vai
//			srcs.put(HealthView.back, new Rect(83,180,83+277,180+316));//lung
//			srcs.put(HealthView.bum, new Rect(83,487,83+277,487+140));//dit
//	}

	
//	private int zoomBody = 2;
//	private float ratez = 1;
//	private void setSrc(int color){
//		src = srcs.get(color);
//		if (src!=null){
//			float ratew = (float) width*0.9f/src.width();
//			float rateh = (float) height*0.9f/src.height();
//			ratez = ratew<rateh?ratew:rateh;
//			
//			dst.left = (int) (width/2 - src.width()*ratez/2);
//			dst.right = (int) (width/2 + src.width()*ratez/2);
//			dst.top = (int) (height/2 - src.height()*ratez/2);
//			dst.bottom = (int) (height/2 + src.height()*ratez/2);
//			
//		}else{
//			
//		}
//	}

	public void switchSide() {
		body.switchSide();
		subBody.switchSide();
	}

	public void showAllbody() {
		subBody.hide();
		healthView.btAllBody.setVisibility(GONE);
		healthView.setMenu(subBody.color_selected);
	}
	
	public void showSubbody() {
		Util.log("show SUB BODY");
		healthView.btAllBody.setVisibility(VISIBLE);
		healthView.setMenu(subBody.color_selected);
	}
}
