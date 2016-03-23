package resources;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class ResourceManager {
	
	private Context ctx;
	private Map<String, Bitmap> bmps = new HashMap<String, Bitmap>();	//
	
	public ResourceManager(Context ctx){
		this.ctx = ctx;
	}
	
	public Bitmap getBitmap(String filename) {
		Bitmap bm = bmps.get(filename);
		if (bm == null){
			BufferedInputStream buf;
			try {
				buf = new BufferedInputStream( ctx.getAssets().open(filename));
				bm = BitmapFactory.decodeStream(buf);
				bmps.put(filename, bm);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bm;
	}
	
	
	public Bitmap getBitmapNew(String filename) {
		Bitmap bm = bmps.get(filename);
		if (bm == null){
			BufferedInputStream buf;
			try {
				buf = new BufferedInputStream( ctx.getAssets().open(filename));
				bm = BitmapFactory.decodeStream(buf);
				bmps.put(filename,bm);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bm;
	}
	
	
	
	
	public String[] getBitmapArray(String path) {
		String[] bms_path = null;
		try {
			String[] list = ctx.getAssets().list(path);
			int len = list.length;
//			bms = new Bitmap[len];
			bms_path = new String[len];
			for(int i=0;i<len;i++){
				//bms[i] = getBitmap(path+"/"+list[i]);
				bms_path[i] = path+"/"+list[i];
//				Bitmap bm = bms[i];
//				Matrix matrix = new Matrix();
//				float rate = 320f/bm.getWidth();
//				matrix.postScale(rate, rate);
//				bms[i] = Bitmap.createBitmap(bm, 0, 0, (int) bm.getWidth(),(int) bm.getHeight(), matrix, true);
//				bm.recycle();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bms_path;
	}
	
	
	
	public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
    // Raw height and width of image
    final int height = options.outHeight;
    final int width = options.outWidth;
    int inSampleSize = 1;

    if (height > reqHeight || width > reqWidth) {

        final int halfHeight = height / 2;
        final int halfWidth = width / 2;

        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
        // height and width larger than the requested height and width.
        while ((halfHeight / inSampleSize) > reqHeight
                && (halfWidth / inSampleSize) > reqWidth) {
            inSampleSize *= 2;
        }
    }

    return inSampleSize;
}
	
	
	public static Bitmap decodeSampledBitmapFromResource(BufferedInputStream buf, int resId,
	        int reqWidth, int reqHeight) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    Rect rect = new Rect(-1,-1,-1,-1);
		BitmapFactory.decodeStream(buf,rect,options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeStream(buf,rect,options);
	}
}
