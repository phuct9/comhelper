package views;

import jit.vn.onseitaiwa2.MainActivity;
import jit.vn.onseitaiwa2.R;

import org.json.JSONArray;
import org.json.JSONObject;

import utilities.SetViewSizeByPixel;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import dialog.InputText;

public class CustomizeView extends CommuniticationHelperView implements OnClickListener{
	
	private SetViewSizeByPixel	size;
	private int					currentTab	= 0;
	private Button[]			btnPager, btnSerial, btnText;
	private SharedPreferences	prefs;
	private Editor				editor;
	private String				prefsName	= "JSONArray";
	private JSONArray			jsonArray;
	public Handler				inputText	= null;
	public CustomizeView(Context ctx){
		super(ctx);
		size = new SetViewSizeByPixel(ctx);
		inflate(ctx, R.layout.customize, this);
		prefs = ctx.getSharedPreferences("CommuniticationHelper", Context.MODE_PRIVATE);
		editor = prefs.edit();
		
		getData();
		setView();
		setTab(0);
	}
	
	private void getData(){
		try{
			jsonArray = new JSONArray();
			String jsonStr = prefs.getString(prefsName, "");
			if(jsonStr.equals("")){
				for(int i = 0; i < 50; i++){
					JSONObject jObj = new JSONObject();
					jObj.put("id", i);
					jObj.put("text", "");
					jsonArray.put(jObj);
				}
				saveData();
				Log.e("jarray", jsonArray.toString());
			}else{
				jsonArray = new JSONArray(jsonStr);
			}
			
		}catch (Exception e){
			Log.e("CustomizeView.getData()", e.toString());
		}
		
	}
	
	private void saveData(){
		editor.putString(prefsName, jsonArray.toString());
		editor.commit();
	}
	
	private void setTab(int tab){
		for(int i = 0; i < btnPager.length; i++){
			if(i == tab){
				btnPager[i].setSelected(true);
			}else{
				btnPager[i].setSelected(false);
			}
			setButtonText();
			currentTab = tab;
		}
	}
	
	private void setView(){
		
		//RootView
		size.margin(findViewById(R.id.customize_lyRoot),
				size.RH(40), size.RH(20), size.RH(40), size.RH(20));
		
		//tvGuide
		size.textSize(findViewById(R.id.customize_tvGuide), size.RW(30));
		
		//LyContent
		size.marginTop(findViewById(R.id.customize_lyContent), size.RH(10));
		size.margin(findViewById(R.id.customize_lyContentChild), size.RH(6));
		
		//PagerButton
		btnPager = new Button[] {
				(Button) findViewById(R.id.customize_btnPage01),
				(Button) findViewById(R.id.customize_btnPage02),
				(Button) findViewById(R.id.customize_btnPage03),
				(Button) findViewById(R.id.customize_btnPage04),
				(Button) findViewById(R.id.customize_btnPage05)
		};
		for(int i = 0; i < btnPager.length; i++){
			btnPager[i].setTypeface(tf02);
			size.textSize(btnPager[i], size.RH(56));
			size.square(btnPager[i], size.RH(140));
			final int x = i;
			btnPager[i].setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0){
					setTab(x);
				}
			});
		}
		
		//SerialButton
		btnSerial = new Button[] {
				(Button) findViewById(R.id.customize_btnSTT_01),
				(Button) findViewById(R.id.customize_btnSTT_02),
				(Button) findViewById(R.id.customize_btnSTT_03),
				(Button) findViewById(R.id.customize_btnSTT_04),
				(Button) findViewById(R.id.customize_btnSTT_05),
				(Button) findViewById(R.id.customize_btnSTT_06),
				(Button) findViewById(R.id.customize_btnSTT_07),
				(Button) findViewById(R.id.customize_btnSTT_08),
				(Button) findViewById(R.id.customize_btnSTT_09),
				(Button) findViewById(R.id.customize_btnSTT_10),
		};
		for(int i = 0; i < btnSerial.length; i++){
			btnSerial[i].setTypeface(tf02);
			size.textSize(btnSerial[i], size.RH(46));
			size.width(btnSerial[i], size.RW(128));
			final int x = i;
			btnSerial[i].setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0){
					getInputText(x);
				}
			});
		}
		
		//TextButton
		btnText = new Button[] {
				(Button) findViewById(R.id.customize_btnText_01),
				(Button) findViewById(R.id.customize_btnText_02),
				(Button) findViewById(R.id.customize_btnText_03),
				(Button) findViewById(R.id.customize_btnText_04),
				(Button) findViewById(R.id.customize_btnText_05),
				(Button) findViewById(R.id.customize_btnText_06),
				(Button) findViewById(R.id.customize_btnText_07),
				(Button) findViewById(R.id.customize_btnText_08),
				(Button) findViewById(R.id.customize_btnText_09),
				(Button) findViewById(R.id.customize_btnText_10),
		};
		for(int i = 0; i < btnText.length; i++){
			btnText[i].setTypeface(tf02);
			size.textSize(btnText[i], size.RH(46));
			final int x = i;
			setButtonText();
			btnText[i].setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0){
					String speak = btnText[x].getText().toString();
					if(!speak.equals(""))
						((MainActivity) getContext()).app.tts.speak(speak);
					else
						getInputText(x);
				}
			});
			btnText[i].setOnLongClickListener(new OnLongClickListener(){
				
				@Override
				public boolean onLongClick(View arg0){
					getInputText(x);
					return false;
				}
			});
		}
		
	}
	
	private void getInputText(final int x){
		try{
			inputText = new Handler(new Callback(){
				public boolean handleMessage(Message msg){
					try{
						String str = (String) msg.obj;
						JSONObject json = jsonArray.getJSONObject(currentTab * 10 + x);
						json.put("text", str);
						saveData();
						setButtonText();
					}catch (Exception e){
						Log.e("", e.toString());
					}
					return true;
				}
			});
			JSONObject json = jsonArray.getJSONObject(currentTab * 10 + x);
			new InputText(getContext(), inputText, json.getString("text"));
		}catch (Exception e){
			Log.e("", e.toString());
		}
	}
	
	private void setButtonText(){
		try{
			for(int i = 0; i < btnText.length; i++){
				JSONObject json = jsonArray.getJSONObject(currentTab * 10 + i);
				String text = json.getString("text");
				btnText[i].setText(text);
			}
		}catch (Exception e){
			Log.e("", e.toString());
		}
		
	}
	
	@Override
	public void onClick(View arg0){
		// TODO Auto-generated method stub
		
	}
	
}
