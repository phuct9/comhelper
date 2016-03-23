package bodys;

import jit.vn.onseitaiwa2.R;
import views.BodyView;
import views.HealthView;

import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

public class Body {
	
	public float x,y;
	private Bitmap bm;
	private Bitmap bmColor;
	
	private float rate; 
	private BodyView vBody;
	private int colorSelected;
	private Paint pa = new Paint();

	
//	private Bitmap getBitmap(String fileName){
//		try {
//			InputStream is = vBody.getContext().getAssets().open(fileName);
//			return BitmapFactory.decodeStream(is);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	
	public Body(BodyView vBody,int w,int h){
		this.vBody = vBody;
		bm = BitmapFactory.decodeResource(vBody.getResources(), R.drawable.body_front);
		bmColor = BitmapFactory.decodeResource(vBody.getResources(), R.drawable.body_front_color);
		rate(w,h);
	}
	
	private void rate(int w,int h){
		rate = (float)h/bm.getHeight(); 
		x = (w/rate - bm.getWidth())/2;
	}
	
	public void draw(Canvas c){
//		pa.setColor(colorSelected);
//		c.drawCircle(50, 50, 50, pa);
		c.save();
		c.scale(rate, rate);
		c.drawBitmap(bm, x,y, null);
		c.restore();
	}

	public int onTouchEvent(MotionEvent e) {
		float ex = e.getX()/rate - x;
		float ey = e.getY()/rate - y;
		colorSelected = getColorSelected((int)ex, (int)ey);
		return colorSelected;
		
	}
	
	private int getColorSelected(int x,int y){
		if (x<0 || x>=bmColor.getWidth()){
			return HealthView.ALL;
		}else{
			return bmColor.getPixel(x, y);
		}
	}

	private boolean isFront = false;
	public void switchSide() {
		isFront = !isFront;
		if (isFront){
			bm = BitmapFactory.decodeResource(vBody.getResources(), R.drawable.body_front);
			bmColor = BitmapFactory.decodeResource(vBody.getResources(), R.drawable.body_front_color);
		}else{
			bm = BitmapFactory.decodeResource(vBody.getResources(), R.drawable.body_rear);
			bmColor = BitmapFactory.decodeResource(vBody.getResources(), R.drawable.body_rear_color);
			
		}
	}
	
}
