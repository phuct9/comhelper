package customize;

import utilities.SetViewSizeByPixel;
import jit.vn.onseitaiwa2.R;
import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.Button;

public class BackButton extends Button {

	public BackButton(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);
			initDrawable();
	}
	
	private void initDrawable(){
		StateListDrawable states = new StateListDrawable();
		states.addState(new int[] {android.R.attr.state_pressed},getResources().getDrawable(R.drawable.back_on));
		states.addState(new int[] {android.R.attr.state_focused},getResources().getDrawable(R.drawable.back_on));
		states.addState(new int[] { },getResources().getDrawable(R.drawable.back_off));
		setBackgroundDrawable(states);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	public void setSize(int w,int h){
		SetViewSizeByPixel sizeMng = new SetViewSizeByPixel(getContext());
		sizeMng.size(this, sizeMng.RW(w), sizeMng.RH(h));
	}
	
	
	
}
