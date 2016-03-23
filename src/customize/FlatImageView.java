package customize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.AttributeSet;
import android.widget.ImageView;

public class FlatImageView extends ImageView {

	private int position;
	private Paint pa = new Paint();{
		pa.setTextSize(48);
	}
	private LoadBitmapSyncTask lbmSync;
	private int imageWidth;
	
	public FlatImageView(Context ctx,int position,int imageWidth){
		super(ctx);
		this.position = position;
		this.imageWidth = imageWidth;
	}
	
	public FlatImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public void draw(Canvas c) {
		super.draw(c);
	}

	public void load(String path){
		if (lbmSync != null){
			lbmSync.cancel(true);
		}
		lbmSync = new LoadBitmapSyncTask();
		lbmSync.execute(path);
		
	}
	
	private Bitmap getThumb(String path){
		Uri uri = Uri.fromFile(new File(path));
		String[] columns = {MediaColumns._ID}; 
		Cursor cursor =  getContext().getContentResolver().query(uri, columns, null, null, null); 
		cursor.moveToFirst(); 
		long imgId = cursor.getLong(0);
		return MediaStore.Images.Thumbnails.getThumbnail(getContext().getContentResolver(), imgId,MediaStore.Images.Thumbnails.MINI_KIND,(BitmapFactory.Options) null );
	}
	
	private Bitmap decodeFile(String filePath, int WIDTH, int HIGHT) {
		try {
			File f = new File(filePath);
			BitmapFactory.Options o = new BitmapFactory.Options();
			o.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(new FileInputStream(f), null, o);
			final int REQUIRED_WIDTH = WIDTH;
			final int REQUIRED_HIGHT = HIGHT;
			int scale = 1;
			while (o.outWidth / scale / 2 >= REQUIRED_WIDTH && o.outHeight / scale / 2 >= REQUIRED_HIGHT)
				scale *= 2;
			BitmapFactory.Options o2 = new BitmapFactory.Options();
			o2.inSampleSize = scale;
			return BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private class LoadBitmapSyncTask extends AsyncTask<String, Integer, Boolean>{
		
		private Bitmap bm;
		
		public LoadBitmapSyncTask() {
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		
		@Override
		protected Boolean doInBackground(String... paths) {
			bm = decodeFile(paths[0], imageWidth,imageWidth);
			return null;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			FlatImageView.this.setImageBitmap(bm);
			super.onPostExecute(result);
		}
		
	}
	
}
