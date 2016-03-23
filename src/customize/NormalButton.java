package customize;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import jit.vn.onseitaiwa2.OnseitaiwaApp;
import models.ScreenButton;
import views.TopView;

public class NormalButton extends MyButton implements android.view.View.OnClickListener {

	private ScreenButton scrbt;
	private OnseitaiwaApp app;
	private MainLayout parent;
	
	public NormalButton(View parent, AttributeSet attrs) {
		super(parent.getContext(), attrs);
	}
	
	public NormalButton(MainLayout parent, ScreenButton scrbt){
		super(parent.getContext());
		this.parent = parent;
		this.scrbt = scrbt;
		app = (OnseitaiwaApp) getContext().getApplicationContext();
		setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/KozGoPr6N-Bold.otf"));
		setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (scrbt.speak != null){
			app.tts.speak(scrbt.speak);
		}else{
			parent.loadButtons(scrbt.next_screen);
		}
	}
	
	

}
