package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Util {

	public static float WIDTHSCREEN,HEIGHTSCREEN;
	
	public static void log(Object... objects) {
		String str = "";
		for (Object obj : objects) {
			str += obj + "   ";
		}
		Log.v("MyDebug", str);
	}
	
	public static JSONObject loadJSONFromAsset(Context ctx,String path) {
	    JSONObject jso = null;
	    try {
	        InputStream is = ctx.getAssets().open(path);
	        int size = is.available();
	        byte[] buffer = new byte[size];
	        is.read(buffer);
	        is.close();
	        jso = new JSONObject(new String(buffer, "UTF-8"));
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    } catch (JSONException e) {
			e.printStackTrace();
		}
	    return jso;
	}
	
	
	public static JSONObject loadJSONFromSdcard(Context ctx,String path) {
		 JSONObject jObject = null; 
		try {
	        File dir = Environment.getExternalStorageDirectory();
	        File yourFile = new File(dir, path);
	        FileInputStream stream = new FileInputStream(yourFile);
	        String jString = null;
	        try {
	            FileChannel fc = stream.getChannel();
	            MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
	            /* Instead of using default, pass in a decoder. */
	            jString = Charset.defaultCharset().decode(bb).toString();
	          }
	          finally {
	            stream.close();
	          }
	          jObject = new JSONObject(jString); 
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		return jObject;
	}
	
	public static void setButtonText(Context ctx,View v, float h, String fontname,int color) {
		Typeface tf = Typeface.createFromAsset(ctx.getAssets(),fontname); 
		float heightdesign = 1920f;
		float size = HEIGHTSCREEN * h / heightdesign;
		((Button) v).setTypeface(tf);
		((Button) v).setTextSize(TypedValue.COMPLEX_UNIT_PX,size);
		((Button) v).setTextColor(color);
	}
	
	public static void setTextView(Context ctx,View v, float h, String fontname,int color) {
		Typeface tf = Typeface.createFromAsset(ctx.getAssets(),fontname); 
		float heightdesign = 1920f;
		float size = HEIGHTSCREEN * h / heightdesign;
		((TextView) v).setTypeface(tf);
		((TextView) v).setTextSize(TypedValue.COMPLEX_UNIT_PX,size);
		((TextView) v).setTextColor(color);
		((TextView) v).setGravity(Gravity.CENTER);
	}
	
	public static void setTextView(Context ctx,View v, float fontsize, String fontname,int color,float w,float h,float x,float y) {
		float widthdesign = 1080f;
		float heightdesign = 1920f;
		float wrate = WIDTHSCREEN/widthdesign;
		float hrate = HEIGHTSCREEN/heightdesign;
		float height = hrate * fontsize;
		Typeface tf = Typeface.createFromAsset(ctx.getAssets(),fontname);
		((TextView) v).setTypeface(tf);
		((TextView) v).setTextSize(TypedValue.COMPLEX_UNIT_PX,height);
		((TextView) v).setTextColor(color);
		((TextView) v).setGravity(Gravity.CENTER_HORIZONTAL);
		resize(v, w, h, x, y, 0, 0);
		
//		int l = (int) (hrate*x);
//		int t = (int) (hrate*y);
//		RelativeLayout.LayoutParams lparams =  (RelativeLayout.LayoutParams) v.getLayoutParams();
//		lparams.setMargins(l,t,0,0);
//		v.requestLayout();
		
	}
	
	public static void resize(View v, float w, float h) {
		float widthdesign = 1080f;
		float heightdesign = 1920f;
		int wv = (int) (w>0?(WIDTHSCREEN * w / widthdesign):0);
		int hv = (int) (h>0?(HEIGHTSCREEN* h / heightdesign):0);
		v.getLayoutParams().width = wv;
		v.getLayoutParams().height = hv;
		v.requestLayout();
	}
	
	public static void resize(View v, float w, float h,float left,float top,float right, float bottom) {
		float widthdesign = 1080f;
		float heightdesign = 1920f;
		float wrate = WIDTHSCREEN/widthdesign;
		float hrate = HEIGHTSCREEN/heightdesign;
		float xc = (left+w/2)*wrate;
//		int l = (int) (left>0?hrate*left:0);
		int t = (int) (top>0?hrate*top:0);
//		int r = (int) (right>0?hrate*right:0);
//		int b = (int) (bottom>0?hrate*bottom:0);
		int vw = v.getLayoutParams().width = (int) (wrate*w);
		v.getLayoutParams().height = (int) (hrate*h);
		
		int l = (int) (xc-vw/2); 
//		int r = l+vw;
		int r = 0;
		int b = 0;
		
		RelativeLayout.LayoutParams lparams =  (RelativeLayout.LayoutParams) v.getLayoutParams();
		
		lparams.setMargins(l,t,r,b);
		v.requestLayout();
	}
	
	public static void top(View v, float y, float w_screen) {
		LinearLayout.LayoutParams lparams =  (LayoutParams) v.getLayoutParams();
		lparams.setMargins(0, (int) (w_screen * y / 640f), 0, 0);
		v.requestLayout();
	}
	
	public static void logMemoryUsage(Context ctx){
		MemoryInfo mi = new MemoryInfo();
		ActivityManager activityManager = (ActivityManager) ctx.getSystemService(ctx.ACTIVITY_SERVICE);
		activityManager.getMemoryInfo(mi);
		long availableMegs = mi.availMem / 1048576L;
		Util.log("mem : "+availableMegs+" Mb");
	}

	public static int sum = 0;
	public static void logSumMemIntArray(int[] pixels) {
		sum+=pixels.length*4;
		Util.log("sum : "+sum/1048576+" Mb");
	}

	public static void addTextView(View v,int bkgrId, int w, int h, int x, int y) {
		RelativeLayout lay = (RelativeLayout) v;
		TextView txtView = new TextView(v.getContext());
		txtView.setBackgroundResource(bkgrId);
		lay.addView(txtView);
		resize(txtView, w, h, x, y, 0, 0);
	}

	public static void init(Activity act) {
		Util.WIDTHSCREEN = act.getWindowManager().getDefaultDisplay().getWidth();
        Util.HEIGHTSCREEN = act.getWindowManager().getDefaultDisplay().getHeight();
	}

	public static void font(View v, String font, int color, int size) {
		float heightdesign = 1920f;
		float hrate = HEIGHTSCREEN/heightdesign;
		Button bt = (Button) v;
		Typeface tf = Typeface.createFromAsset(v.getContext().getAssets(), "fonts/"+font);
		bt.setTypeface(tf);
		bt.setTextColor(color);
		bt.setTextSize(TypedValue.COMPLEX_UNIT_PX,size*hrate);
		
	}
	
}
