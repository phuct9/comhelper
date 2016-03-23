package customize;

import mediaplayers.MediaPlayerActivity;
import slideshows.SlideShowView;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.LinearLayout;
import jit.vn.onseitaiwa2.R;

public class VideoLayout extends LinearLayout {
	
	public int w,h;
	
	private MediaPlayerActivity act;
	private SlideShowView vSlideShow;

	public VideoLayout(Context ctx, AttributeSet attrs) {
		super(ctx, attrs);
		try {
			act = (MediaPlayerActivity) ctx;
			vSlideShow = new SlideShowView(getContext());
			vSlideShow.setVisibility(View.GONE);
			addView(vSlideShow);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	private void initDrawable(){
//		StateListDrawable states = new StateListDrawable();
//		states.addState(new int[] {android.R.attr.state_pressed},getResources().getDrawable(R.drawable.back_on));
//		states.addState(new int[] {android.R.attr.state_focused},getResources().getDrawable(R.drawable.back_on));
//		states.addState(new int[] { },getResources().getDrawable(R.drawable.back_off));
//		setBackgroundDrawable(states);
//	}

	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		this.w = w;
		this.h = h;
		Log.v("MyDebug", "video_layout_size = "+w+"X"+h);
		if (act != null){
			if (!act.resizeVideoSize()){
				vSlideShow.setSize(w,h);
				vSlideShow.show();
			}else{
				vSlideShow.hide();
			}
		}
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//		if (((Activity)getContext()).getWindowManager()!=null){
//			SetViewSizeByPixel sizeMng = new SetViewSizeByPixel(getContext());
//			sizeMng.size(this, sizeMng.RW(148), sizeMng.RH(153));
//		}
	}
	
//	public void setSize(int w,int h){
//		SetViewSizeByPixel sizeMng = new SetViewSizeByPixel(getContext());
//		sizeMng.size(this, sizeMng.RW(w), sizeMng.RH(h));
//	}
	
	
	
}
