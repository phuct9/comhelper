package mediaplayers.customize;

import java.text.AttributedCharacterIterator.Attribute;

import jit.vn.onseitaiwa2.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

public class OnOffButton extends Button {

	public OnOffButton(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);
		
		int off = attrs.getAttributeIntValue(R.attr.off, 0);
		Log.v("MyDebug", "off = "+off);
	}

	
	@Override
	public void draw(Canvas c) {
		super.draw(c);
		
//		int left = 4;
//		int top = 4;
//		int right = left + getWidth() - 8;
//		int bottom = top + getHeight() - 8;
//		RectF rectf = new RectF(left, top, right, bottom);
//		if (pressed){
//			pa.setStyle(Style.FILL);
//			pa.setColor(0xFFFFFFCC);
//			c.drawRoundRect(rectf, rx, ry, pa);
//			
//			pa.setStyle(Style.STROKE);
//			pa.setColor(0XFFE96F8D);
//			c.drawRoundRect(rectf, rx, ry, pa);
//			
//		}else{
//			pa.setStyle(Style.FILL);
//			pa.setColor(Color.WHITE);
//			c.drawRoundRect(rectf, rx, ry, pa);
//			
//			pa.setStyle(Style.STROKE);
//			pa.setColor(0XFF3CD1EF);
//			c.drawRoundRect(rectf, rx, ry, pa);
//		}
//		if (pressed){
//			c.save();
//			c.scale(0.9f, 0.9f,(left+right)/2,(top+bottom)/2);
//			super.draw(c);
//			c.restore();
//		}else{
//			c.save();
//			c.scale(0.7f, 0.7f,(left+right)/2,(top+bottom)/2);
//			super.draw(c);
//			c.restore();
//		}

	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			invalidate();
			break;
		}
		return super.onTouchEvent(e);
	}
	
	private void getDrawable(){
//		StateListDrawable states = new StateListDrawable();
//		states.addState(new int[] {android.R.attr.state_pressed},getResources().getDrawable(R.drawable.pressed));
//		states.addState(new int[] {android.R.attr.state_focused},getResources().getDrawable(R.drawable.focused));
//		states.addState(new int[] { },getResources().getDrawable(R.drawable.normal));
////		imageView.setImageDrawable(states);
	}
	
//	private void getResourceType(){
//		Attribute 
//	}
//	
//
//	public class MyAttribute extends Attribute{
//
//		protected MyAttribute(String arg0) {
//			super(arg0);
//		}
//		
//	}
	
}


