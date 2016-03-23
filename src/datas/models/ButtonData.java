package datas.models;

import java.util.ArrayList;
import java.util.List;

import jit.vn.onseitaiwa2.MainActivity.SCREEN;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Point;
import android.util.Log;
import datas.MDatabase;

public class ButtonData{
	final String	table	= "buttondatas";
	int				id;
	int				viewid;
	String			display;
	String			speak;
	Point			position;
	Point			size;
	
	public ButtonData(){}
	
	public ButtonData(int viewid, Point position, Point size, String display, String speak){
		super();
		this.viewid = viewid;
		this.display = display;
		this.speak = speak;
		this.position = position;
		this.size = size;
	}
	
	public void save(SQLiteDatabase database){
		ContentValues updateValues = new ContentValues();
		updateValues.put("viewId", viewid);
		updateValues.put("positionX", position.x);
		updateValues.put("positionY", position.y);
		updateValues.put("sizeX", size.x);
		updateValues.put("sizeY", size.y);
		updateValues.put("display", display);
		updateValues.put("speak", speak);
		
		database.insertWithOnConflict(table, null, updateValues,
				SQLiteDatabase.CONFLICT_REPLACE);
		database.close();
	}
	
	public int getView(){
		return viewid;
	}
	public String getDisplay(){
		return display;
	}
	public String getSpeak(){
		return speak;
	}
	public Point getPosition(){
		return position;
	}
	public Point getSize(){
		return size;
	}
	public void setViewId(int viewid){
		this.viewid = viewid;
	}
	public void setDisplay(String display){
		this.display = display;
	}
	public void setSpeak(String speak){
		this.speak = speak;
	}
	public void setPosition(Point position){
		this.position = position;
	}
	public void setSize(Point size){
		this.size = size;
	}
	
	public List<ButtonData> getAll(Context ctx){
		SQLiteDatabase database = new MDatabase(ctx).getWritableDatabase();
		List<ButtonData> list = new ArrayList<ButtonData>();
		Cursor cursor = database.rawQuery("SELECT * FROM " + table, null);
		Log.e("MyDebug", "count = " + cursor.getCount());
		if(cursor.moveToFirst()){
			do{
				ButtonData bt = new ButtonData();
				bt.id = cursor.getInt(0);
				bt.viewid = Integer.parseInt(cursor.getString(1));
				bt.position = new Point(cursor.getInt(2), cursor.getInt(3));
				bt.size = new Point(cursor.getInt(4), cursor.getInt(5));
				bt.display = cursor.getString(6);
				bt.speak = cursor.getString(7);
				list.add(bt);
			}while(cursor.moveToNext());
		}
		return list;
	}
	
	public List<ButtonData> getByViewId(Context ctx, int viewid){
		List<ButtonData> list = new ArrayList<ButtonData>();
		SQLiteDatabase db = new MDatabase(ctx).getWritableDatabase();
		Cursor cursor = db.query(table, new String[] { "id", "viewId", "positionX",
				"positionY", "sizeX", "sizeY", "display", "speak" }, "viewid=?",
				new String[] { String.valueOf(viewid) }, null, null, null, null);
		if(cursor.moveToFirst()){
			do{
				ButtonData bt = new ButtonData();
				bt.id = cursor.getInt(0);
				bt.viewid = Integer.parseInt(cursor.getString(1));
				bt.position = new Point(cursor.getInt(2), cursor.getInt(3));
				bt.size = new Point(cursor.getInt(4), cursor.getInt(5));
				bt.display = cursor.getString(6);
				bt.speak = cursor.getString(7);
				list.add(bt);
			}while(cursor.moveToNext());
		}
		return list;
	}
	
	public int getViewID(SCREEN scr){
		switch (scr){
			case EMERGENCYDIALOG:
				return 200;
			case G:
				return 100;
				
			case B01:
				return 1000;
			case B02:
				return 1100;
			case B03:
				return 1200;
			case B04:
				return 1300;
			case B05:
				return 1400;
			case B06:
				return 1500;
			case B07:
				return 1600;
			case B08:
				return 1700;
				
			case C_All:
				return 3000;
			case C_Head:
				return 3100;
			case C_Face:
				return 3200;
			case C_Arm:
				return 3300;
			case C_Chest:
				return 3400;
			case C_Belly:
				return 3500;
			case C_Genitals:
				return 3600;
			case C_Leg:
				return 3700;
			case C_Neck:
				return 3800;
			case C_Shoulder:
				return 3900;
			case C_Back:
				return 4000;
			case C_Bum:
				return 4100;
			
			
			case NOSE:
				return 4200;
			case EARS:
				return 4300;
			case EYES:
				return 4400;
			case MOUTH:
				return 4500;
			
				
			case D01:
				return 5000;
			case D02:
				return 5100;
			case D03:
				return 5200;
			default:
				return -1;
		}
	}

	public void daleteAll(Context ctx){
		  SQLiteDatabase database = new MDatabase(ctx).getWritableDatabase();
		  database.delete(table, null, null);
	}
	
}
