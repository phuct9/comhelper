package jit.vn.onseitaiwa2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import customize.BackButton;
import mediaplayers.MediaPlayerActivity;
import utilities.SetViewSizeByPixel;

import models.MusicFile;
import adapters.MusicExplorerAdapter;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class MusicExplorerActivity extends Activity {

	private ListView lsvExplorer;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.music_explorer);
		lsvExplorer = (ListView) findViewById(R.id.lsvExplorer);
		MusicExplorerAdapter adapter = new MusicExplorerAdapter(this, R.layout.music_explorer_item, getFiles());				
		lsvExplorer.setAdapter(adapter);
		lsvExplorer.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adp, View v, int i, long l) {
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
		
	}
	
	private List<MusicFile> getFiles(){
		List<MusicFile> lstVideoFile = new ArrayList<MusicFile>();
		File sdCard = Environment.getExternalStorageDirectory();
    	File directory = new File (sdCard.getAbsolutePath() + "/communicationhelper/music");
    	if (!directory.exists()){
    		directory.mkdirs();
    	}
    	String[] lst = directory.list();
    	for(String name : lst){
    		lstVideoFile.add(new MusicFile(name,directory.getAbsolutePath()));
    	}
    	return lstVideoFile;
	}
	
	private void activityMediaPlayer(int i){
		Intent intent = new Intent(this, MediaPlayerActivity.class);
		intent.putExtra("FORLDER", "music");
		intent.putExtra("POSITION", i);
		startActivity(intent);		
	}

}
