package models;

import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import utilities.Util;

@Table(name = "screen_button")
public class ScreenButton extends Model {

	@Column(name = "title")
	public String title;
	@Column(name = "next_screen")
	public String next_screen;
	@Column(name = "speak")
	public String speak;
	@Column(name = "screen_id")
	public String screen_id;
	
	public static List<ScreenButton> getByScreen(String screen_id){
		//return new Select("title,screen_id,next_screen").from(ScreenButton.class).where("screen_id = ?",screen_id).execute();
		List<ScreenButton> bts = new Select("title,screen_id,next_screen,speak").from(ScreenButton.class).where("screen_id = ?",screen_id).execute();
//		for(ScreenButton bt:bts){
//			Util.log(bt.title,bt.screen_id,bt.next_screen,bt.speak);
//		}
		return bts;
	}

	public void setNextScreen(String currScreen,String nextScreen) {
		if (currScreen.indexOf("00")>-1){
			next_screen = currScreen.replace("00", nextScreen);
		}else {
			next_screen = currScreen +"-"+ nextScreen;
		}
		
		
		
	}
	
}
