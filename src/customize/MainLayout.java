package customize;

import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import jit.vn.onseitaiwa2.OnseitaiwaApp;
import models.Screen;
import models.ScreenButton;
import utilities.SetViewSizeByPixel;

public class MainLayout  extends LinearLayout {

	public Screen screen;
	
	public MainLayout(Context ctx,Screen screen) {
		super(ctx);
		this.screen = screen;
		setOrientation(VERTICAL);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		setGravity(Gravity.CENTER_HORIZONTAL);
//		int w = Integer.parseInt(parser.getAttributeValue(null,"width"));
//		int h = Integer.parseInt(parser.getAttributeValue(null,"height"));
//		button_width = Integer.parseInt(parser.getAttributeValue(null,"button_width"));
//		button_height = Integer.parseInt(parser.getAttributeValue(null,"button_height"));
//		metrics = new DisplayMetrics();
//		((Activity)ctx).getWindowManager().getDefaultDisplay().getMetrics(metrics);
//		setLayoutParams(new LayoutParams(metrics.widthPixels*w/1080,metrics.heightPixels*h/1920));
//		setBackgroundColor(Color.YELLOW);
//		setOrientation(VERTICAL);
//		setGravity(Gravity.CENTER_HORIZONTAL);
	}

	public void addButtons(List<ScreenButton> btns,SetViewSizeByPixel size) {
		for(int i = 0; i < btns.size(); i++){
			final ScreenButton scrbt = btns.get(i); 
			Button bt = new NormalButton(this,scrbt);
			//bt.setText(scrbt.title+" "+scrbt.screen_id+" "+scrbt.next_screen+" "+scrbt.speak);
			bt.setText(scrbt.title);
			bt.setLayoutParams(new LinearLayout.LayoutParams(size.RW(1080),size.RW(188)));
			
			addView(bt);
		}
	}

//	public void setButtons(List<ScreenButton> buttons) {
//		int textSize = (int) (metrics.heightPixels*button_height/1920)/2;
//		LayoutParams params = new LayoutParams((int)(metrics.widthPixels*button_width/1080),(int)(metrics.heightPixels*button_height/1920));
//		for(ScreenButton scbt : buttons){
//			Button bt = new Button(getContext());
//			bt.setText(scbt.title);
//			bt.setLayoutParams(params);
//			bt.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
//			addView(bt);
//		}
//	}

}
