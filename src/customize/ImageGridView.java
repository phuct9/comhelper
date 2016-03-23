package customize;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageGridView extends GridView {

	public GridViewImageAdapter adapter;
	private int columnsNum = 3;
	private int padding = 8;
	private List<String> file_extns = Arrays.asList("jpg", "jpeg","png");
	
	public ImageGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		setHorizontalSpacing(padding);
		setVerticalSpacing(padding);
		setNumColumns(columnsNum);
		
//		setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> vadp, View v, int i,long l) {
//				Log.v("MyDebug","i = "+i);
//			}
//			
//		});
		
		
		
//		createBitmaps();
	}
	
	public void setPath(String path) {
		init();
		int columnWidth = (int) ((getLayoutParams().width - (columnsNum + 1) * padding)) / columnsNum;
		ArrayList<String> imagePaths = getFilePaths(path);
		adapter = new GridViewImageAdapter(getContext(), imagePaths,columnWidth);
		setAdapter(adapter);
	}

	private void init(){
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager winMng;
		winMng = ((Activity) getContext()).getWindowManager(); 
		if (winMng!=null){ 
			winMng.getDefaultDisplay().getMetrics(metrics);
			getLayoutParams().width = metrics.widthPixels;
		}
	}
	
	private void createBitmaps(){
		Paint pa = new Paint();
		pa.setTextSize(1920/3);
		for(int i=0;i<500;i++){
			Bitmap bm = Bitmap.createBitmap(1080,1920,Config.RGB_565);
			Canvas c = new Canvas(bm);
			c.drawColor(Color.GREEN);
			c.drawText(i+"", 1080/4, 1920/2, pa);
			saveBitmapToFile(bm, i);
			bm.recycle();
		}
	}
	
	private void saveBitmapToFile(Bitmap bmp,int i) {
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File(sdCard.getAbsolutePath() + "/testLoadBitmap");
        if (!directory.exists())
        	directory.mkdirs();
        File file = new File(directory, "test" + String.format("%03d", i) + ".jpeg");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
            Log.v("MyDebug", "save to file "+file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
	private ArrayList<String> getFilePaths(String path) {
		ArrayList<String> filePaths = new ArrayList<String>();
		File directory = new File(android.os.Environment.getExternalStorageDirectory() + File.separator + path);
		if (directory.isDirectory()) {
			File[] listFiles = directory.listFiles();
			if (listFiles.length > 0) {
				for (int i = 0; i < listFiles.length; i++) {
					String filePath = listFiles[i].getAbsolutePath();
					if (IsSupportedFile(filePath)) {
						filePaths.add(filePath);
					}
				}
			} else {
				Toast.makeText(getContext(),path + " is empty. Please load some images in it !",Toast.LENGTH_LONG).show();
			}

		} else {
			AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
			alert.setTitle("Error!");
			alert.setMessage(path + " directory path is not valid! Please set the image directory name AppConstant.java class");
			alert.setPositiveButton("OK", null);
			alert.show();
		}
		return filePaths;
	}
	
	private boolean IsSupportedFile(String filePath) {
		String ext = filePath.substring((filePath.lastIndexOf(".") + 1),filePath.length());
		if (file_extns.contains(ext.toLowerCase(Locale.getDefault())))
			return true;
		else
			return false;
	}
	
	public String getPath(int position) {
		return adapter._filePaths.get(position);
	}

}

class GridViewImageAdapter extends BaseAdapter {

	private Activity _activity;
	protected ArrayList<String> _filePaths = new ArrayList<String>();
	private int imageWidth;

	public GridViewImageAdapter(Context ctx, ArrayList<String> filePaths,int imageWidth) {
		this._activity = (Activity)ctx;
		this._filePaths = filePaths;
		this.imageWidth = imageWidth;
	}

	@Override
	public int getCount() {
		return this._filePaths.size();
	}

	@Override
	public Object getItem(int position) {
		return this._filePaths.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FlatImageView imageView;
		if (convertView == null) {
			imageView = new FlatImageView(_activity,position,imageWidth);
		} else {
			imageView = (FlatImageView) convertView;
		}

		imageView.setBackgroundColor(Color.WHITE);
		imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		imageView.setLayoutParams(new GridView.LayoutParams(imageWidth,imageWidth));
		
		
		// image view click listener
//		final int pos = position; 
//		imageView.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Log.v("MyDebug", "img"+pos+" clicked");
//				if (onImageClickListener!=null){
//					onImageClickListener.onImageClick(pos);
//				}
//			}
//		});
//		Log.v("MyDebug","pos = "+position+":"+_filePaths.get(position));
		imageView.setTag(position);
		String path = _filePaths.get(position);
		imageView.load(path);
		return imageView;
	}

}


	