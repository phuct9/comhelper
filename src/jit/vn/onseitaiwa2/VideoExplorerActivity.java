package jit.vn.onseitaiwa2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import customize.BackButton;
import mediaplayers.MediaPlayerActivity;
import utilities.Util;
import models.VideoFile;
import adapters.VideoExplorerAdapter;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class VideoExplorerActivity extends Activity {

	private ListView lsvVideoFiles;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_file_explorer);
		lsvVideoFiles = (ListView) findViewById(R.id.lsvVideoFiles);
		VideoExplorerAdapter adapter = new VideoExplorerAdapter(this, R.layout.list_video_file_explorer_item, getFiles());				
		lsvVideoFiles.setAdapter(adapter);
		lsvVideoFiles.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adp, View v, int i, long l) {
				Util.log("on video click ",i);
				activityMediaPlayer(i);
			}
		});
		
		BackButton btBack = (BackButton) findViewById(R.id.btBack);
		btBack.setSize(148,152);
		btBack.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
//		Typeface tf02 = Typeface.createFromAsset(getAssets(), "fonts/KozGoPr6N-Bold.otf");
//		SetViewSizeByPixel size = new SetViewSizeByPixel(this);
//		TextView tvBack = (TextView) findViewById(R.id.tvBack);
//		size.textSize(tvBack, size.RH(54));
//		size.width(tvBack, size.RH(180));
//		tvBack.setTypeface(tf02);
//		tvBack.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				finish();
//			}
//		});
	}

	private List<VideoFile> getFiles(){
		List<VideoFile> lstVideoFile = new ArrayList<VideoFile>();
		File sdCard = Environment.getExternalStorageDirectory();
    	File directory = new File (sdCard.getAbsolutePath() + "/communicationhelper/video");
    	if (!directory.exists()){
    		directory.mkdirs();
    	}
    	String[] lst = directory.list();
    	for(String name : lst){
    		lstVideoFile.add(new VideoFile(name,directory.getAbsolutePath()));
    	}
    	return lstVideoFile;
	}
	
	private void activityMediaPlayer(int i){
		Intent intent = new Intent(this, MediaPlayerActivity.class);
		intent.putExtra("FORLDER", "video");
		intent.putExtra("POSITION", i);
		startActivity(intent);		
	}
	
	
}
