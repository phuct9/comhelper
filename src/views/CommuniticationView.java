package views;

import jit.vn.onseitaiwa2.MainActivity;
import jit.vn.onseitaiwa2.MainActivity.SCREEN;
import jit.vn.onseitaiwa2.R;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class CommuniticationView extends CommuniticationHelperView{
	
	public static int TAB_SELECTED;
	
	public CommuniticationView(Context ctx){
		super(ctx);
		inflate(ctx, R.layout.communitication, this);
		setView();
	}
	Button[]		btns;
	int				currentTab;
	RelativeLayout	lyContent;
	private void setView(){
		lyContent = (RelativeLayout) findViewById(R.id.com_lyContent);
		size.margin(findViewById(R.id.com_lyTab), size.RH(40), size.RH(40), size.RH(40),0);
		size.textSize(findViewById(R.id.com_guide), size.RH(42));
		size.margin(findViewById(R.id.com_guide), size.RH(40), size.RH(15), size.RH(40),size.RH(15));
		
		btns = new Button[] {
				(Button) findViewById(R.id.com_Button00),
				(Button) findViewById(R.id.com_Button01),
				(Button) findViewById(R.id.com_Button02),
		};
		
		for(int i = 0; i < btns.length; i++){
			Button btn = btns[i];
			size.size(btn, size.RH(200), size.RH(140));
			size.textSize(btn, size.RH(50));
			btn.setTypeface(MainActivity.tf02);
			final int x = i;
			btn.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0){
					changeTab(x);
				}
			});
		}
		
		changeTab(TAB_SELECTED);
		
	}
	
	private void changeTab(int tab){
		TAB_SELECTED = tab;
		for(int i = 0; i < btns.length; i++){
			Button btn = btns[i];
			if(i == tab){
				btn.setSelected(true);
				loadContent(tab);
			}else{
				btn.setSelected(false);
			}
		}
	}
	
	private void loadContent(int tab){
		lyContent.removeAllViews();
		CommuniticationHelperView view = new CommuniticationHelperView(getContext());
		view.setButtonView(lyContent, getScreen(tab));
	}
	
	private SCREEN getScreen(int tab){
		switch (tab){
			case 0:
				return SCREEN.D01;
			case 1:
				return SCREEN.D02;
			case 2:
				return SCREEN.D03;
			default:
				return SCREEN.D01;
		}
		
	}
}
