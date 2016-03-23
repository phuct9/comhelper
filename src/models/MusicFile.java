package models;

import java.io.File;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.MediaStore.MediaColumns;

public class MusicFile {
	
	private String name;
	private String path;
	
	public MusicFile(String name,String path){
		this.name = name;
		this.path = path;
	}

	public String getName(){
		return name;
	}
	
	public String getPath(){
		return path;
	}
	
	public String getUri(){
		return path+"/"+name;
//		File file = new File(path+"/"+name);
//		Uri uri = Uri.fromFile(file);
//		return uri;
	}
	
	private long getMediaItemIdFromProvider(Uri providerUri, Context appContext, String fileName) {
	    //find id of the media provider item based on filename
	    String[] projection = { MediaColumns._ID, MediaColumns.DATA };
	    Cursor cursor = appContext.getContentResolver().query(
	            providerUri, projection,
	            MediaColumns.DATA + "=?", new String[] { fileName },
	            null);
	    if (null == cursor) {
//	        Log.d(TAG_LOG, "Null cursor for file " + fileName);
	        return -1;
	    }
	    long id = -1;
	    if (cursor.getCount() > 0) {
	        cursor.moveToFirst();
	        id = cursor.getLong(cursor.getColumnIndexOrThrow(BaseColumns._ID));
	    }
	    cursor.close();
	    return id;
	}
}
