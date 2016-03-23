package jit.vn.onseitaiwa2;

import tts.TextToSpeechManager;
import utilities.Util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import com.activeandroid.ActiveAndroid;

import android.app.Application;
import android.content.SharedPreferences;
import android.text.Editable;
import android.util.Xml;
import models.Screen;
import models.ScreenButton;

public class OnseitaiwaApp extends Application {
	
	private SharedPreferences prefs = null;
	public long lastModified;
	
	
	
//	public int dhour,dmin;
	public long dtime;
	public int hour,min;
	
	public int status;//0 is hurt 1 is 
	
	public boolean isOnboard = false;
	
	public TextToSpeechManager tts;
	public Screen screen;
	
	public String friendNickskype;
	
	
	@Override
	public void onCreate() {
		super.onCreate();
		ActiveAndroid.initialize(this);
//		loadUIData();
		loadTextData();
	}
	
	
	
	public void init(){
		tts = new TextToSpeechManager(this);
		load();
	}
	

	
	public void save(){
		SharedPreferences.Editor editor = prefs.edit();
		editor.putLong("dtime", dtime);
		editor.putLong("lastModified", lastModified);
		editor.putString("friendNickskype", friendNickskype);
		editor.commit();
	}
	
	public void load(){
		prefs = getSharedPreferences("jit.vn.onseitaiwa2", MODE_PRIVATE);
		dtime = prefs.getLong("dtime", 0);
		lastModified = prefs.getLong("lastModified", 0);
		friendNickskype = prefs.getString("friendNickskype", null);
	}
	
	private void loadTextData(){
		if (Screen.getOne() != null) return;
		BufferedReader bfr = null;
		try {
			bfr = new BufferedReader(new InputStreamReader(getAssets().open("configs/text.txt"),"utf-8"));
			String line = null;
			Screen screen = null;
			int nextScreenId = 10;
			while((line = bfr.readLine()) != null){
				if (line.trim().equals(""))
					continue;
				if (line.charAt(0) == '/') 
					continue;
				String[] strs = line.split("\t");
//				String strLine = strs.length+":";
//				for(String str : strs){
//					strLine += str+"|";
//				}
////				Util.log(strLine);
				
				if (strs.length == 2){
					screen = new Screen();
					screen.screen_id = strs[0];
					screen.title = strs[1];
					screen.save();
					nextScreenId = 10;
					Util.log(screen.screen_id,":",screen.title);
				}else if (strs.length >= 3){
					ScreenButton scbt = new ScreenButton();
					scbt.title = strs[2];
					if (strs.length>3){
						scbt.speak = strs[3];
					}
					scbt.screen_id = screen.screen_id;
					scbt.setNextScreen(screen.screen_id,nextScreenId+"");
					nextScreenId++;
					scbt.save();
//					Util.log(scbt.title,scbt.screen_id,scbt.next_screen,scbt.speak,strs.length);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	private void loadUIData(){
//		DataInputStream dis = null;
//		try {
//			dis = new DataInputStream(getAssets().open("configs/ui.json"));
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	/*private XmlPullParser loadUIData(){
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(getAssets().open("configs/ui.xml"));
			XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(dis, null);
            
            parser.next();
            Util.log(parser.getName());
            parser.next();
            
            parser.next();
            Util.log(parser.getName());
            parser.next();
            
            parser.next();
            Util.log(parser.getName());
            parser.next();
            
            parser.next();
            Util.log(parser.getName());
            String namespace = parser.getNamespace();
            fotter_row = Integer.parseInt(parser.getAttributeValue(namespace, "row"));
            fotter_height = Integer.parseInt(parser.getAttributeValue(namespace, "height"));
            parser.next();
            
            return parser;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (XmlPullParserException e) {
			e.printStackTrace();
		}
		return null;
	}*/
}
