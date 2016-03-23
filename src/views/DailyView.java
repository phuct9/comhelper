package views;

import jit.vn.onseitaiwa2.MainActivity;
import jit.vn.onseitaiwa2.MainActivity.SCREEN;
import jit.vn.onseitaiwa2.R;
import utilities.SetViewSizeByPixel;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DailyView extends CommuniticationHelperView implements OnClickListener{
	
	private SetViewSizeByPixel	size;
	
	public DailyView(Context ctx){
		super(ctx);
		size = new SetViewSizeByPixel(ctx);
		inflate(ctx, R.layout.dailyview, this);
		
		setView();
	}
	
	private void setView(){
		
		size.margin(findViewById(R.id.lyDaily), size.RH(20));
		
		Button[] btns = new Button[] {
				(Button) findViewById(R.id.dailyview_btn01),
				(Button) findViewById(R.id.dailyview_btn02),
				(Button) findViewById(R.id.dailyview_btn03),
				(Button) findViewById(R.id.dailyview_btn04),
				(Button) findViewById(R.id.dailyview_btn05),
				(Button) findViewById(R.id.dailyview_btn06),
				(Button) findViewById(R.id.dailyview_btn07),
				(Button) findViewById(R.id.dailyview_btn08)
		};
		
		for(int i = 0; i < btns.length; i++){
			btns[i].setTypeface(tf02);
			btns[i].setBackgroundResource(R.drawable.btn01_9p);
			size.textSize(btns[i], size.RH(66));
			size.height(btns[i], size.RH(188));
			int x = (i <= 1) ? size.RH(120) : 0;
			size.margin(btns[i], size.RH(20), x, size.RH(20), size.RH(50));
			btns[i].setOnClickListener(this);
		}
		
	}
	

	
	@Override
	public void onClick(View arg0){
		switch (arg0.getId()){
			case R.id.dailyview_btn01:
				MainActivity.changeTab(SCREEN.B01);
				break;
			case R.id.dailyview_btn02:
				MainActivity.changeTab(SCREEN.B02);
				break;
			case R.id.dailyview_btn03:
				MainActivity.changeTab(SCREEN.B03);
				break;
			case R.id.dailyview_btn04:
				MainActivity.changeTab(SCREEN.B04);
				break;
			case R.id.dailyview_btn05:
				MainActivity.changeTab(SCREEN.B05);
				break;
			case R.id.dailyview_btn06:
				MainActivity.changeTab(SCREEN.B06);
				break;
			case R.id.dailyview_btn07:
				MainActivity.changeTab(SCREEN.B07);
				break;
			case R.id.dailyview_btn08:
				MainActivity.changeTab(SCREEN.B08);
				break;
			default:
				break;
		}
	}
}
