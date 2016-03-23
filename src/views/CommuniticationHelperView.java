package views;

import java.util.List;

import jit.vn.onseitaiwa2.MainActivity;
import jit.vn.onseitaiwa2.MainActivity.SCREEN;
import jit.vn.onseitaiwa2.OnseitaiwaApp;
import jit.vn.onseitaiwa2.R;
import utilities.SetViewSizeByPixel;
import utilities.Util;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import datas.models.ButtonData;

public class CommuniticationHelperView extends LinearLayout{
	
	public OnseitaiwaApp		app;
	
	public Typeface				tf01	= MainActivity.tf01, tf02 = MainActivity.tf02;
	public TextView				tvTitle	= MainActivity.tvTitle;
	public TextView				tvBack	= MainActivity.tvBack;
	public SetViewSizeByPixel	size;
	public SCREEN				previousScr;
	public CommuniticationHelperView(Context context){
		super(context);
		setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		app = (OnseitaiwaApp) context.getApplicationContext();
		size = new SetViewSizeByPixel(context);
		setTitleAndBackpress();
	}
	
	public void setTitle(String title){
		tvTitle.setText(title);
	}
	
	private void setTitleAndBackpress(){
		switch (MainActivity.currentTab){
			case TOPVIEW:
//				tvTitle.setText(getResources().getString(R.string.A));
				break;
			case Daily:
				setBackScreen(SCREEN.TOPVIEW);
				tvTitle.setText(getResources().getString(R.string.B));
				break;
			case HEALTH:
				setBackScreen(SCREEN.TOPVIEW);
				tvTitle.setText(getResources().getString(R.string.C));
				break;
			case COMMUNICATION:
				setBackScreen(SCREEN.TOPVIEW);
				tvTitle.setText(getResources().getString(R.string.D));
				break;
			case E:
				setBackScreen(SCREEN.TOPVIEW);
				tvTitle.setText(getResources().getString(R.string.E));
				break;
			case B01:
				setBackScreen(SCREEN.Daily);
				setButtonView(MainActivity.lyMain, MainActivity.currentTab);
				tvTitle.setText(getResources().getString(R.string.B1));
				break;
			case B02:
				setBackScreen(SCREEN.Daily);
				setButtonView(MainActivity.lyMain, MainActivity.currentTab);
				tvTitle.setText(getResources().getString(R.string.B2));
				break;
			case B03:
				setBackScreen(SCREEN.Daily);
				setButtonView(MainActivity.lyMain, SCREEN.B03);
				tvTitle.setText(getResources().getString(R.string.B3));
				break;
			case B04:
				setBackScreen(SCREEN.Daily);
				setButtonView(MainActivity.lyMain, MainActivity.currentTab);
				tvTitle.setText(getResources().getString(R.string.B4));
				break;
			case B05:
				setBackScreen(SCREEN.Daily);
				setButtonView(MainActivity.lyMain, MainActivity.currentTab);
				tvTitle.setText(getResources().getString(R.string.B5));
				break;
			case B06:
				setBackScreen(SCREEN.Daily);
				setButtonView(MainActivity.lyMain, MainActivity.currentTab);
				tvTitle.setText(getResources().getString(R.string.B6));
				break;
			case B07:
				setBackScreen(SCREEN.Daily);
				setButtonView(MainActivity.lyMain, MainActivity.currentTab);
				tvTitle.setText(getResources().getString(R.string.B7));
				break;
			case B08:
				setBackScreen(SCREEN.Daily);
				setButtonView(MainActivity.lyMain, MainActivity.currentTab);
				tvTitle.setText(getResources().getString(R.string.B8));
				break;
			case ENTERTAINMENT:
				setBackScreen(SCREEN.TOPVIEW);
				tvTitle.setText(getResources().getString(R.string.ENTERTAINMENT));
				break;
		}
	}
	
	public void setButtonView(RelativeLayout root, SCREEN scr){
		LayoutInflater li = LayoutInflater.from(getContext());
		View scrollBar = (View) li.inflate(R.layout.scrollbar, null);
		root.addView(scrollBar);
		RelativeLayout lyContent = (RelativeLayout) scrollBar.findViewById(R.id.scrollView_lyContent);
		ButtonData btnData = new ButtonData();
		int viewId;
		List<ButtonData> listBtn = btnData.getByViewId(getContext(),viewId = btnData.getViewID(scr));
		for(int i = 0; i < listBtn.size(); i++){
			final ButtonData btn = listBtn.get(i);
			Button btnView = new Button(getContext());
			btnView = (Button) li.inflate(R.layout.button, null);
			btnView.setTypeface(tf02);
			size.textSize(btnView, size.RH(48));
			lyContent.addView(btnView);
			btnView.setText(btn.getDisplay());
			//Util.log("button"+i+" size:"+btn.getSize().x+"X"+btn.getSize().y+" pos:"+btn.getPosition().x+"X"+btn.getPosition().y);
			size.size(btnView, size.RW(btn.getSize().x), size.RH(btn.getSize().y));
			size.margin(btnView, size.RW(btn.getPosition().x),size.RH(btn.getPosition().y),0, 0);
			btnView.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					speak(btn.getSpeak());
				}
			});
		}
	}
	
	private int fontsize = 42; 
	private int w=600,h=150;
	public void setButtonViewMenu(RelativeLayout root, int viewId){
		LayoutInflater li = LayoutInflater.from(getContext());
		View scrollBar = (View) li.inflate(R.layout.scrollbar, null);
		root.addView(scrollBar);
		RelativeLayout lyContent = (RelativeLayout) scrollBar.findViewById(R.id.scrollView_lyContent);
		
		ButtonData btnData = new ButtonData();
		//int viewId;
		List<ButtonData> listBtn = btnData.getByViewId(getContext(),viewId);
		
		for(int i = 0; i < listBtn.size(); i++){
			final ButtonData btn = listBtn.get(i);
			Button btnView = new Button(getContext());
			btnView = (Button) li.inflate(R.layout.button, null);
			btnView.setTypeface(tf02);
			size.textSize(btnView, size.RH(fontsize));
			lyContent.addView(btnView);
			btnView.setText(btn.getDisplay());
//			size.size(btnView, size.RW(btn.getSize().x), size.RH(btn.getSize().y));
//			size.margin(btnView, size.RW(btn.getPosition().x),size.RH(btn.getPosition().y),0, 0);
			size.size(btnView, size.RW(w), size.RH(h));
			size.margin(btnView, size.RW(0),size.RH(i*h*0.95f),0, 0);
			btnView.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					speak(btn.getSpeak());
				}
			});
		}
	}
	
	
	protected void speak(String str){
		Log.v("MyDebug", "CommuncationHelperView.speak "+str);
		app.tts.speak(str);
	}
	
	private void setBackScreen(final SCREEN scr){
		previousScr = scr;
		tvBack.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				onBackBtn();
			}
		});
	}
	public void onBackBtn(){
		if(previousScr != MainActivity.currentTab)
			MainActivity.changeTab(previousScr);
	}
	
	public void changeTitle(int id){
		
	}
	
}
