package customize;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import models.ScreenButton;

public class NormalButton extends MyButton implements android.view.View.OnClickListener{

	private ScreenButton scrbt;
	
	public NormalButton(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);
	}
	
	public NormalButton(Context ctx, ScreenButton scrt){
		super(ctx);
		this.scrbt = scrt;
		setTypeface(Typeface.createFromAsset(getContext().getAssets(), "fonts/KozGoPr6N-Bold.otf"));
		setText(scrt.title);
		setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (scrbt.speak != null){
			
		}else{
			
		}
	}

	
	
}
