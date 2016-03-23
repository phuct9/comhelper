package utilities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SetViewSizeByPixel{
	/**
	 * @author MNC
	 * 30.10.2014
	 */
	
	private LayoutParams	params			= null;
	private DisplayMetrics	metrics			= null;
	private Context			mContext;
	public static final int	match_parent	= -1;
	
	public SetViewSizeByPixel(Context context){
		metrics = getMetrics(context);
		this.mContext = context;
	}
	
	/**
	 * 
	 */
	
	public int RH(int x){
		return metrics.heightPixels * x / 1920;
	}
	public int RW(int x){
		return metrics.widthPixels * x / 1080;
	}
	public int RH720p(int x){
		return metrics.heightPixels * x / 1280;
	}
	public int RW720p(int x){
		return metrics.widthPixels * x / 720;
	}
	
	public int RH(float x){
		return (int) (metrics.heightPixels * x / 1920);
	}
	public int RW(float x){
		return (int) (metrics.widthPixels * x / 1080);
	}
	
	/**
	 * Metrics
	 */
	
	public DisplayMetrics getMetrics(Activity activity){
		metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay()
				.getMetrics(metrics);
		return metrics;
	}
	
	public DisplayMetrics getMetrics(Context context){
		return getMetrics((Activity) context);
	}
	
	public DisplayMetrics getMetrics(){
		return metrics;
	}
	
	public int heightPixels(){
		return metrics.heightPixels;
	}
	public int widthPixels(){
		return metrics.widthPixels;
	}
	public float density(){
		return metrics.density;
	}
	public int densityDpi(){
		return metrics.densityDpi;
	}
	
	/**
	 * Size
	 */
	
	public boolean height(View view, int height){
		return size(view, -1, height);
	}
	
	public boolean width(View view, int width){
		return size(view, width, -1);
	}
	
	public boolean square(View view, int x){
		return size(view, x, x);
	}
	
	public boolean size(View view, int width, int height){
		try{
			params = view.getLayoutParams();
			if(width != -1)
				params.width = width;
			else
				params.width = LayoutParams.MATCH_PARENT;
			
			if(height != -1)
				params.height = height;
			else
				params.height = LayoutParams.MATCH_PARENT;
			view.setLayoutParams(params);
			return true;
		}catch (Exception e){
			Log.e("SetSizeByPercent", e.toString());
			return false;
		}
	}
	
	/**
	 * Size with RW, RH.
	 */
	
	public boolean heightR(View view, int height){
		return size(view, -1, this.RH(height));
	}
	
	public boolean widthR(View view, int width){
		return size(view, this.RW(width), -1);
	}
	
	public boolean squareR(View view, int x){
		return size(view, this.RW(x), this.RH(x));
	}
	
	public boolean sizeR(View view, int width, int height){
		return size(view, RW(width), RH(height));
	}
	
	/**
	 * Margin	
	 */
	public void marginLeft(View v, int lef){
		margin(v, lef, -1, -1, -1);
	}
	
	public void marginTop(View v, int top){
		margin(v, -1, top, -1, -1);
	}
	
	public void marginRight(View v, int rig){
		margin(v, -1, -1, rig, -1);
	}
	
	public void marginBottom(View v, int bot){
		margin(v, -1, -1, -1, bot);
	}
	
	public void marginLeft(View v, int lef, boolean keepOldMargin){
		int k = keepOldMargin ? -1 : 0;
		margin(v, lef, k, k, k);
	}
	
	public void marginTop(View v, int top, boolean keepOldMargin){
		int k = keepOldMargin ? -1 : 0;
		margin(v, k, top, k, k);
	}
	
	public void marginRight(View v, int rig, boolean keepOldMargin){
		int k = keepOldMargin ? -1 : 0;
		margin(v, k, k, rig, k);
	}
	
	public void marginBottom(View v, int bot, boolean keepOldMargin){
		int k = keepOldMargin ? -1 : 0;
		margin(v, k, k, k, bot);
	}
	
	public void margin(View v, int margin){
		margin(v, margin, margin, margin, margin);
	}
	
	public void margin(View v, int lef, int top, int rig, int bot){
		if(v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams){
			ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v
					.getLayoutParams();
			lef = (lef == -1) ? p.leftMargin : lef;
			top = (top == -1) ? p.topMargin : top;
			rig = (rig == -1) ? p.rightMargin : rig;
			bot = (bot == -1) ? p.leftMargin : bot;
			
			p.setMargins(lef, top, rig, bot);
			v.requestLayout();
		}
	}
	
	public LayoutParams getParams(){
		return params;
	}
	
	public void setParams(LayoutParams params){
		this.params = params;
	}
	
	/**
	 * Text Size
	 */
	public void textSize(TextView tv, int size){
		tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
	}
	public void textSize(View view, int size){
		try{
			((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
		}catch (Exception e){
			Log.e("", e.toString());
		}
	}
	
	/**
	 * Animation & TypeFace
	 */
	public Animation getAnimation(int id){
		return AnimationUtils.loadAnimation(mContext, id);
	}
	
	public Typeface getTypeface(String typefacePath){
		return Typeface.createFromAsset(mContext.getAssets(),
				typefacePath);
	}
}
