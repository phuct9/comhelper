package views;

import jit.vn.onseitaiwa2.MainActivity;
import jit.vn.onseitaiwa2.MainActivity.SCREEN;
import models.Screen;
import models.ScreenButton;
import settings.UIDATA;
import jit.vn.onseitaiwa2.R;
import java.util.List;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import customize.MainLayout;

public class TopView extends CommuniticationHelperView implements OnClickListener{
	
	private RelativeLayout layTop;
	
	public TopView(Context ctx){
		super(ctx);
		inflate(ctx, R.layout.topview, this);
		layTop = (RelativeLayout) findViewById(R.id.layTop);
		init();
	}
	
	/**
	 * swith to other view
	 * 
	 */
	@Override
	public void onClick(View v){

	}
	
	private void init(){
		app.screen = Screen.getOne();
		loadButtons(app.screen.screen_id);
	}
	
	public void loadButtons(String screen_id){
		if (screen_id.equals("10-12")){
			MainActivity.changeTab(SCREEN.HEALTH);
			return;
		}else if (screen_id.equals("10-13")){
			MainActivity.changeTab(SCREEN.COMMUNICATION);
			return;
		}else if (screen_id.equals("10-14")){
			MainActivity.changeTab(SCREEN.ENTERTAINMENT);
			return;
		}
		
		app.screen = Screen.getByScreenId(screen_id);
		setTitle(app.screen.title);
		
		List<ScreenButton> btns = ScreenButton.getByScreen(screen_id);
		if (btns.size()>0){
			layTop.getChildAt((layTop.getChildCount()-1)).setVisibility(GONE);
			MainLayout lay = new MainLayout(getContext(),app.screen);
			lay.top = this;
			lay.width = UIDATA.MAIN_INF[0];
			lay.height = UIDATA.MAIN_INF[1];
			lay.size = size;
			lay.row = 5;
			lay.softButtons(btns);
			layTop.addView(lay);
		}
	}
	
	

	public boolean back() {
		int i = layTop.getChildCount()-1;
		if (i>1){
			layTop.removeViewAt(i);
			MainLayout layMain = (MainLayout)(layTop.getChildAt(i-1));
			layMain.setVisibility(View.VISIBLE);
			setTitle(layMain.screen.title);
			return true;
		}else{
			return false;
		}
	}
	
}
