package dialog;

import models.Screen;
import models.ScreenButton;
import jit.vn.onseitaiwa2.R;
import utilities.SetViewSizeByPixel;
import views.EmergencyView;

import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout.LayoutParams;
import customize.NormalButton;
import android.widget.Button;
import android.widget.LinearLayout;

public class EmergencyDialog extends Dialog{
	private SetViewSizeByPixel	size;
	private EmergencyView view;
	
	public EmergencyDialog(Context context){
		super(context);
		size = new SetViewSizeByPixel(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		view = new EmergencyView(context);
		setContentView(R.layout.dialog_emergency);
		loadButtons("30-00");
		
//		Btn Exit
		Button btnExit = (Button) findViewById(R.id.emergency_btnExit);
		Typeface tf02 = Typeface.createFromAsset(getContext().getAssets(),"fonts/KozGoPr6N-Bold.otf");
		btnExit.setTypeface(tf02);
		size.textSize(btnExit, size.RH(48));
		size.size(btnExit, size.RW(360), size.RH(150));
		
		btnExit.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				if (view.thrEmergency != null && view.thrEmergency.speaking){
					view.stopSpeak();
				}else{
					EmergencyDialog.this.dismiss();
				}
			}
		});
		
		setCanceledOnTouchOutside(false);
		setCancelable(false);
	}
	
	public void loadButtons(String screen_id){
		LinearLayout lyContent = (LinearLayout) findViewById(R.id.emergency_content);
		Screen.getByScreenId(screen_id);
		List<ScreenButton> scrbts = ScreenButton.getByScreen(screen_id);
		int col = 1;
		int num = scrbts.size();
		int num_on_col = num/col; 
		int id = 0;
		for(int i=0;i<col;i++){
			LinearLayout lyCol = new LinearLayout(getContext());
			lyCol.setLayoutParams(new LayoutParams(0,LayoutParams.MATCH_PARENT,1));
			lyCol.setOrientation(LinearLayout.VERTICAL);
			for(int j=0;j<num_on_col;j++){
				if (id<num){
					final ScreenButton scrbt = scrbts.get(id);
					Button bt = new NormalButton(getContext(),scrbt);
					bt.setLayoutParams(new LinearLayout.LayoutParams(size.RW(1000),size.RH(150)));
					id++;
					lyCol.addView(bt);
					bt.setOnClickListener(new View.OnClickListener(){
						@Override
						public void onClick(View v){
							view.speak(scrbt.speak);
						}
					});
				}
			}
			lyContent.addView(lyCol);
		}
		
	}
}
