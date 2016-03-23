package views;

import jit.vn.onseitaiwa2.R;
import models.ScreenButton;
import settings.UIDATA;
import utilities.SetViewSizeByPixel;
import utilities.Util;
import java.util.List;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import customize.NormalButton;

public class FooterView extends CommuniticationHelperView{
	
	private SetViewSizeByPixel	size;
	
	public int width,height;
	
	public FooterView(Context ctx){
		super(ctx);
		size = new SetViewSizeByPixel(ctx);
		inflate(ctx, R.layout.footerview, this);
		loadButtons("20-10");
	}
	
	private void loadButtons(String screen_id){
		List<ScreenButton> scrbts = ScreenButton.getByScreen(screen_id);
		softButtons(scrbts);
	}
	
	private void softButtons(List<ScreenButton> scrbts){
		width = 1080;
		height = UIDATA.ALWY_INF[1];
		LinearLayout lyFooter = (LinearLayout) findViewById(R.id.footerview_lyFooter);
		int num = scrbts.size();
		int row = 2;
		int col = num/row;
		if (num%row>0){
			col += 1;
		}
		Util.log("col = ",col);
		int num_on_col = num/col;
		if (num%col>0){
			num_on_col+=1;
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
					bt.setLayoutParams(new LinearLayout.LayoutParams(size.RW(width/col),size.RH(height/num_on_col)));
					id++;
					lyCol.addView(bt);
					bt.setOnClickListener(new OnClickListener(){
						@Override
						public void onClick(View v){
							speak(scrbt.speak);
						}
					});
				}
			}
			lyFooter.addView(lyCol);
		}
	}
}
