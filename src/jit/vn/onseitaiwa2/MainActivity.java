package jit.vn.onseitaiwa2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import services.TheService;
import settings.UIDATA;
import utilities.IOUtil;
import utilities.SetViewSizeByPixel;
import utilities.Util;
import views.CommuniticationHelperView;
import views.CommuniticationView;
import views.CustomizeView;
import views.DailyView;
import views.EntertainmentView;
import views.FooterView;
import views.HealthView;
import views.SleepView;
import views.TopView;
import views.VolumeView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Settings;
import android.provider.Settings.SettingNotFoundException;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import customize.BackButton;
import customize.BatteryButton;
import customize.EmergencyButton;
import customize.SMSButton;
import customize.SkypeButton;
import customize.SleepButton;
import customize.VolumeIncButton;
import customize.VolumeRdcButton;
import datas.MDatabase;
import dialog.EmergencyDialog;
import dialog.MessageDialog;
import dialog.VolumeDialog;
import models.ScreenButton;

public class MainActivity extends Activity {
	public static Activity mActivity;
	public OnseitaiwaApp app;

	public static SQLiteDatabase database;

	private Intent iservice;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.SCREEN_ORIENTATION_CHANGED,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Util.init(this);
		mActivity = this;

		app = (OnseitaiwaApp) getApplicationContext();
		OnseitaiwaApp app = (OnseitaiwaApp) getApplicationContext();
		app.init();
		MDatabase db = new MDatabase(this);
		db.initdataFromAssets(this);
//		db.initdata(this);
	
//		if (db.isDataNotInited()){
//			MessageDialog dlgMessage = new MessageDialog(this,"DATABASE IS NOT AVAILABLE");
//		}
		
		initFolders("image");
		initFolders("music");
		initFolders("video");
		setViews();
		iservice = new Intent(this, TheService.class);
		
		
		
		

	}

	private void initFolders(String foldername) {
		File sdCard = Environment.getExternalStorageDirectory();
		File directory = new File(sdCard.getAbsolutePath() + "/communicationhelper/" + foldername);
		if (!directory.exists()) {
			directory.mkdirs();
		}
	}

	// ---->
	private SetViewSizeByPixel size;

	public static enum SCREEN {
		TOPVIEW, // TopView
		Daily, // Daily
		HEALTH, // Health
		COMMUNICATION, // Commucation
		E, // Customzise
		ENTERTAINMENT, EMERGENCYDIALOG, // EmergencyDialog
		G, // FooterView
		B01, B02, B03, B04, B05, B06, B07, B08, // SubViews of Daily
		C_All, C_Head, C_Face, C_Arm, C_Chest, C_Belly, C_Genitals, C_Leg, C_Neck, C_Shoulder, C_Back, C_Bum,NOSE,EARS,EYES,MOUTH, // SubVies
																												// of
																												// Health
		D01, D02, D03, // SubVies of Commucation
	}

	public static SCREEN currentTab = SCREEN.TOPVIEW;
	public static View currentView;
	public static TextView tvTitle;
	public static BackButton tvBack;
	public static ImageView btnSettings;
	
	public static LinearLayout lySys;
	public static LinearLayout lyTitle;
	public static RelativeLayout lyMain;
	public static LinearLayout lyAwly;
	public static RelativeLayout lyContent, layBkgr;
	public static Typeface tf01, tf02;
	// ---->

	private void setViews() {
		setContentView(R.layout.activity_main);
		size = new SetViewSizeByPixel(this);
		tf01 = Typeface.createFromAsset(getAssets(), "fonts/kaiu.ttf");
		tf02 = Typeface.createFromAsset(getAssets(), "fonts/KozGoPr6N-Bold.otf");

		lySys = (LinearLayout) findViewById(R.id.lySys);
		
		lyTitle = (LinearLayout) findViewById(R.id.lyTitle);//TITLE AREA
		
		lyMain = (RelativeLayout) findViewById(R.id.lyMain);
		lyAwly = (LinearLayout) findViewById(R.id.lyAwly);
		layBkgr = (RelativeLayout) findViewById(R.id.layBkgr);
		
		//SYS
		size.size(lySys, size.RW(UIDATA.SYS_INF[0]),size.RH(UIDATA.SYS_INF[1]));
		//TITLE
		size.size(lyTitle, size.RW(UIDATA.TTL_INF[0]),size.RH(UIDATA.TTL_INF[1]));
		//MAIN
		size.size(lyMain, size.RW(UIDATA.MAIN_INF[0]),size.RH(UIDATA.MAIN_INF[1]));
		//AWLY
		size.size(lyAwly, size.RW(UIDATA.ALWY_INF[0]),size.RH(UIDATA.ALWY_INF[1]));

		softButtons(createButtons());
		
		btnSettings = (ImageView) findViewById(R.id.mainAct_btnSettings);
		size.square(btnSettings, size.RH(148));
		btnSettings.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				setting();
				return false;
			}
		});

		TextView tvClock = (TextView) findViewById(R.id.mainAct_Clock);
		size.textSize(tvClock, size.RH(48));
		tvClock.setTypeface(tf01, Typeface.BOLD);

		tvTitle = (TextView) findViewById(R.id.mainAct_tvTitle);
		size.textSize(tvTitle, size.RH(80));
//		tvTitle.setTypeface(tf02);

		tvBack = (BackButton) findViewById(R.id.mainAct_tvBack);
		tvBack.setSize(148,152);
		
		// Footer
		View footerView = new FooterView(this);
		lyAwly.addView(footerView);

		changeTab(SCREEN.TOPVIEW);

		// Set BG
		ImageView imgBG = (ImageView) findViewById(R.id.mainAct_imgBG);
		String path = Environment.getExternalStorageDirectory().getAbsolutePath();
		File file = new File(path, "jch_bg.jpg");
		if (!file.exists()) {
			IOUtil ioutil = new IOUtil();
			ioutil.copyFromAssets(this, "jch_bg.jpg", file.getAbsolutePath());
		}
		if (!file.exists()) {
			// file = new File(path, "jch_bg.png");
			// if(!file.exists()){
			// imgBG.setImageResource(R.drawable.bg_app);
			// }else{
			// setImgBG(imgBG, file);
			// }
		} else {
			setImgBG(imgBG, file);
		}

	}

	private void setImgBG(ImageView imgBG, File imgFile) {
		Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
		imgBG.setImageBitmap(myBitmap);

	}

	public static void changeTab(SCREEN scr) {
		currentTab = scr;
		removeAllViews();
		currentView = getView(currentTab);
		lyMain.addView(currentView);
	}

	private static View getView(SCREEN scr) {
		if (scr == SCREEN.TOPVIEW) {
			tvBack.setVisibility(View.GONE);
			btnSettings.setVisibility(View.VISIBLE);
		} else {
			tvBack.setVisibility(View.VISIBLE);
			btnSettings.setVisibility(View.GONE);
		}
		switch (scr) {
		case TOPVIEW:
			return new TopView(mActivity);
		case Daily:
			return new DailyView(mActivity);
		case HEALTH:
			return new HealthView(mActivity);
		case COMMUNICATION:
			return new CommuniticationView(mActivity);
		case E:
			return new CustomizeView(mActivity);
		case ENTERTAINMENT:
			return new EntertainmentView(mActivity);
		default:
			return new CommuniticationHelperView(mActivity);
		}
	}

	public static void removeAllViews() {
		lyMain.removeAllViews();
		currentView = null;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onBackPressed() {
		if (currentTab != SCREEN.TOPVIEW)
			((CommuniticationHelperView) currentView).onBackBtn();
		else{
			if (currentView instanceof TopView){
				TopView v = (TopView) currentView;
				if (!v.back()){
					finish();
				}
			}
		}
			
	}

	@Override
	protected void onPause() {
		super.onPause();
		LayoutParams params = getWindow().getAttributes();
		params.flags |= LayoutParams.FLAG_KEEP_SCREEN_ON;
		params.screenBrightness = -1f;
		getWindow().setAttributes(params);

	}

	@Override
	protected void onResume() {
		super.onResume();
		stopService(iservice);
	}

	private void setting(){
		Intent i = new Intent(this, SettingActivity.class);
		startActivity(i);
	}


	private void softButtons(List<Button> scrbts){
		LinearLayout lyMain = (LinearLayout) findViewById(R.id.lySys);
		int height = lyMain.getLayoutParams().height;
		int ibt = 0;
		int col = 4;
		int row = 2;
		for(int i=0;i<col;i++){
			LinearLayout lyCol = new LinearLayout(this);
			lyCol.setOrientation(LinearLayout.VERTICAL);
			lyCol.setLayoutParams(new  LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1));
			for(int j=0;j<row;j++){
				if (ibt<scrbts.size()){
					Button bt = scrbts.get(ibt);
					if (ibt == scrbts.size() - 1){
						bt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height/row*(col*row-ibt)));
					}else{
						bt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,height/row));
					}
					lyCol.addView(bt);
					ibt++;
				}
			}
			lyMain.addView(lyCol);
		}
	}
	
	private List<Button> createButtons(){
		List<Button> bts = new ArrayList<Button>();
		for(String str : UIDATA.SYS_BUTTONS){
			bts.add(createButton(str));
		}
		return bts;
	}

	private Button createButton(String key){
		Button bt;
		if (key.equals("VolumeInc")){
			bt = new VolumeIncButton(this);
		}else if (key.equals("VolumeRdc")){
			bt = new VolumeRdcButton(this);
		}else if (key.equals("Sleep")){
			bt = new SleepButton(this);
		}else if (key.equals("Battery")){
			bt = new BatteryButton(this);
		}else if (key.equals("Emergency")){
			bt = new EmergencyButton(this);
		}else if (key.equals("SMS")){
			bt = new SMSButton(this);
		}else if (key.equals("SKYPE")){
			bt = new SkypeButton(this);
		}else{
			bt = new Button(this);
		}
		return bt;
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Util.log(keyCode);
		if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)){
			Util.log("KEY VOLUME DOWN");
		}else if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)){
			Util.log("KEY VOLUME UP");
		}
		return super.onKeyDown(keyCode, event);
	}

}
