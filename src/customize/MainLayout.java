package customize;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import jit.vn.onseitaiwa2.OnseitaiwaApp;
import jit.vn.onseitaiwa2.R;
import models.Screen;
import models.ScreenButton;
import settings.UIDATA;
import utilities.SetViewSizeByPixel;
import utilities.Util;
import views.TopView;

public class MainLayout  extends LinearLayout {

	public Screen screen;
	public int width;
	public int height;
	public SetViewSizeByPixel size;
	public int row;
	public int col;
	private OnseitaiwaApp app;
	public TopView top;
	
	public MainLayout(Context ctx,Screen screen) {
		super(ctx);
		this.screen = screen;
		setOrientation(HORIZONTAL);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		setGravity(Gravity.CENTER_HORIZONTAL);
		app = (OnseitaiwaApp) ctx.getApplicationContext();
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

	public void softButtons(List<ScreenButton> scrbts){
		int num = scrbts.size();
		col = num/row;
		if (num%row>0){
			col += 1;
		}
		Util.log("col = ",col);
		int num_on_col = num/col;
		if (num%col>0){
			num_on_col+=1;
		}
		if (num_on_col < row){
			num_on_col = row;
		}
		int id = 0;
		for(int i=0;i<col;i++){
			LinearLayout lyCol = new LinearLayout(getContext());
			lyCol.setLayoutParams(new LinearLayout.LayoutParams(0,LayoutParams.MATCH_PARENT,1));
			lyCol.setOrientation(VERTICAL);
			for(int j=0;j<num_on_col;j++){
				if (id<num){
					final ScreenButton scrbt = scrbts.get(id);
					NormalButton bt = new NormalButton(getContext(),scrbt);
					lyCol.addView(bt);
					bt.setLayoutParams(new LinearLayout.LayoutParams(size.RW(width/col),size.RH(height/num_on_col)));
					id++;
					bt.setOnClickListener(new OnClickListener(){
						@Override
						public void onClick(View v){
							if (scrbt.speak!=null){
								app.tts.speak(scrbt.speak);
							}else{
								top.loadButtons(scrbt.next_screen);
							}
						}
					});
				}
			}
			addView(lyCol);
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
