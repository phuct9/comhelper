package views;

import jit.vn.onseitaiwa2.ImageExplorerActivity;
import jit.vn.onseitaiwa2.MainActivity;
import jit.vn.onseitaiwa2.MusicExplorerActivity;
import jit.vn.onseitaiwa2.VideoExplorerActivity;
import jit.vn.onseitaiwa2.MainActivity.SCREEN;
import jit.vn.onseitaiwa2.R;
import utilities.SetViewSizeByPixel;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EntertainmentView extends CommuniticationHelperView implements OnClickListener{
	
	private SetViewSizeByPixel	size;
	
	public EntertainmentView(Context ctx){
		super(ctx);
		size = new SetViewSizeByPixel(ctx);
		inflate(ctx, R.layout.entertainment_view, this);
		
		setView();
	}
	
	private void setView(){
		
		size.margin(findViewById(R.id.lyEntertainment), size.RH(20));
		size.marginTop(findViewById(R.id.lyEntertainment), size.RH(120));
		
		Button[] btns = new Button[] {
				(Button) findViewById(R.id.btImage),
				(Button) findViewById(R.id.btMusic),
				(Button) findViewById(R.id.btVideo)
		};
		
		for(int i = 0; i < btns.length; i++){
			btns[i].setTypeface(tf02);
			btns[i].setBackgroundResource(R.drawable.btn01_9p);
			size.textSize(btns[i], size.RH(66));
			size.height(btns[i], size.RH(188));
			size.margin(btns[i], size.RH(20), 0, size.RH(20), size.RH(50));
			btns[i].setOnClickListener(this);
		}
		
	}
	

	
	@Override
	public void onClick(View arg0){
		switch (arg0.getId()){
			case R.id.btImage:
				activityImage();
				break;
			case R.id.btMusic:
				activityMusic();
				break;
			case R.id.btVideo:
				activityVideo();
				break;
		}
	}
	

	private void activityVideo(){
		Intent intent = new Intent(getContext(), VideoExplorerActivity.class);
		((Activity)getContext()).startActivity(intent);
	}

	private void activityMusic(){
		Intent intent = new Intent(getContext(), MusicExplorerActivity.class);
		((Activity)getContext()).startActivity(intent);
	}

	private void activityImage(){
		Intent intent = new Intent(getContext(), ImageExplorerActivity.class);
		((Activity)getContext()).startActivity(intent);
	}
}
