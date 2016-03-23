package customize;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;
import jit.vn.onseitaiwa2.R;

public class MyButton extends Button {

	public MyButton(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);
		setBackgroundResource(R.drawable.btn01_9p);
		try {
			setTypeface(Typeface.createFromAsset(ctx.getAssets(), "fonts/KozGoPr6N-Bold.otf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MyButton(Context ctx){
		super(ctx);
		setBackgroundResource(R.drawable.btn01_9p);
		try {
			setTypeface(Typeface.createFromAsset(ctx.getAssets(), "fonts/KozGoPr6N-Bold.otf"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	
	
}
