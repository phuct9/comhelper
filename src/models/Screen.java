package models;

import java.util.ArrayList;
import java.util.List;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

@Table(name = "screen")
public class Screen	extends Model {
	
	@Column(name = "screen_id")
	public String screen_id;
	@Column(name = "title")
	public String title;
	
	public List<ScreenButton> buttons = new ArrayList<ScreenButton>();
	
	public static Screen getOne(){
		return new Select("id,title,screen_id").from(Screen.class).executeSingle();
	}

	public static Screen getByScreenId(String screen_id) {
		return new Select("id,title,screen_id").from(Screen.class).where("screen_id = ?",screen_id).executeSingle();
	}
	
}
