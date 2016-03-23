package datas;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

import jit.vn.onseitaiwa2.MainActivity;
import jit.vn.onseitaiwa2.OnseitaiwaApp;
import jit.vn.onseitaiwa2.R;
import utilities.IOUtil;
import utilities.Util;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Point;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;
import datas.models.ButtonData;

public class MDatabase extends SQLiteOpenHelper{
	private static final int	DATABASE_VERSION	= 1;
	private static final String	DATABASE_NAME		= "CommunicationHelper";
	
	private Context				mContext;
	boolean						created				= false;
	
	private OnseitaiwaApp app = null;
	
	public MDatabase(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.mContext = context;
		app = (OnseitaiwaApp) context.getApplicationContext();
		getReadableDatabase();
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		try{
			sqlFromFile(db, R.raw.db);
			Log.e("Database", "Db.create");
		}catch (IOException e){
			Log.e("MDatabase.onCreate.IOException", e.toString());
		}catch (Exception e){
			Log.e("MDatabase.onCreate.Exception", e.toString());
		}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		try{
			sqlFromFile(db, R.raw.droptables);
			Log.e("Database", "Db.droptables");
		}catch (IOException e){
			Log.e("MDatabase.onCreate.IOException", e.toString());
		}catch (Exception e){
			Log.e("MDatabase.onCreate.Exception", e.toString());
		}
	}
	
	public int sqlFromFile(SQLiteDatabase database, int id) throws IOException{
		int result = 1;
		InputStream insertsStream = mContext.getResources().openRawResource(id);
		BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertsStream));
		
		while(insertReader.ready()){
			Log.e("line", "" + result);
			String insertStmt = insertReader.readLine();
			database.execSQL(insertStmt);
			result++;
		}
		insertReader.close();
		created = true;
		return result;
	}
	
	
	
	public void initdata(Context ctx){
		File dir = Environment.getExternalStorageDirectory();
		File f = new File(dir, "communicationhelper.txt");
		/*if (!f.exists()){
			IOUtil ioutil = new IOUtil();
			ioutil.copyFromAssets(ctx,"communicationhelper.txt",f.getAbsolutePath());
		}*/
		
		if (f.exists()){
			if (f.lastModified() != app.lastModified){
				ButtonData btData = new ButtonData();
				btData.daleteAll(ctx);
				try {
					DataInputStream dis = new DataInputStream(new FileInputStream(f));
					InputStreamReader isr = new InputStreamReader(new FileInputStream(f), "UTF-16");  
					BufferedReader br = new BufferedReader(isr);  
					String line;  
					int viewid = 0;
					String display;
					String speak;
					Point position = null;
					Point size = null;
					while((line = br.readLine()) != null){
						if (line.trim().equals("")) continue;
						String[] arrstr = line.split("\\t+");
						String v = arrstr[0].trim();
						if(!v.equals(""))
							viewid = Integer.parseInt(v);
						v = arrstr[1].trim();
						if(!v.equals("")){
							v = v.replaceAll("\"", "");
							String[] pos = v.split(",");
							position = new Point(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
						}
						v = arrstr[2].trim();
						if(!v.equals("")){
							v = v.replaceAll("\"", "");
							String[] pos = v.split(",");
							size = new Point(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
						}
						display = arrstr[3].trim();
						speak = arrstr[4].trim();
						ButtonData bt = new ButtonData(viewid, position, size, display, speak);
						bt.save(getWritableDatabase());
					}
					Util.log("data updated");
					dis.close();
					
					app.lastModified = f.lastModified();
					app.save();
					
					boolean b = f.delete();
					if (b){
						Util.log("file data init deleted");
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				Util.log("data don't need to update");
			}
		}else{
			//Toast.makeText(mContext, "FILE NOT FOUND!",Toast.LENGTH_LONG).show();
			Util.log("file not found");
		}
		
		
		
	}
	
	public boolean isDataNotInited(){
		return app.lastModified == 0;
	}
	
	public void initdataFromAssets(Context ctx){
		Log.e("initdata", "initdata = " + created);
//		if(!created)
//			return;
		ButtonData btData = new ButtonData();
		btData.daleteAll(ctx);
		
		try{
			InputStreamReader isr = new InputStreamReader(ctx.getAssets().open("communicationhelper.txt"), "UTF-16");
			BufferedReader br = new BufferedReader(isr);
			String line;
			int viewid = 0;
			String display;
			String speak;
			Point position = null;
			Point size = null;
			while((line = br.readLine()) != null){
				if (line.trim().equals("")) continue;
				String[] arrstr = line.split("\\t+");
				String v = arrstr[0].trim();
				if(!v.equals(""))
					viewid = Integer.parseInt(v);
				v = arrstr[1].trim();
				if(!v.equals("")){
					v = v.replaceAll("\"", "");
					String[] pos = v.split(",");
					position = new Point(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
				}
				v = arrstr[2].trim();
				if(!v.equals("")){
					v = v.replaceAll("\"", "");
					String[] pos = v.split(",");
					size = new Point(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]));
				}
				display = arrstr[3].trim();
				speak = arrstr[4].trim();
				ButtonData bt = new ButtonData(viewid, position, size, display, speak);
				bt.save(getWritableDatabase());
			}
			Util.log("data updated from assets, all count : "+btData.getAll(ctx).size());
			isr.close();
		}catch (IOException e1){
			e1.printStackTrace();
		}
	}

	
	
	
	

}
