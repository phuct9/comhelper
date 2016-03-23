package adapters;

import java.util.List;

import jit.vn.onseitaiwa2.MusicActivity;
import jit.vn.onseitaiwa2.R;
import jit.vn.onseitaiwa2.VideoActivity;
import utilities.SetViewSizeByPixel;

import mediaplayers.MediaPlayerActivity;
import models.MusicFile;
import models.VideoFile;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MusicExplorerAdapter extends ArrayAdapter<MusicFile> {

	public MusicExplorerAdapter(Context context,int resId, List<MusicFile> lsv) {
		super(context, resId, lsv);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int pos, View v, ViewGroup parent) {
		final MusicFile mfile = getItem(pos);
		LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = inflater.inflate(R.layout.music_explorer_item, parent, false);
		TextView txtFileName = (TextView) v.findViewById(R.id.txtFileName);
		txtFileName.setText(mfile.getName());
//		final int position = pos;
//		txtFileName.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
////				activityMusic(mfile);
//				activityMediaPlayer(position);
//			}
//		});
		
		SetViewSizeByPixel	size = new SetViewSizeByPixel(getContext());
		size.size(v.findViewById(R.id.btMusicIcon), size.RW(135), size.RH(170));
		size.textSize(txtFileName, size.RH(80.04f));
//		size.marginBottom(v.findViewById(R.id.btMusicIcon), size.RH(48));
//		size.marginBottom(txtFileName, size.RH(48));
		
		
		return v;
	}
	
	private void activityMusic(MusicFile mfile){
//		MusicActivity.VIDEOPATH = mfile.getUri();
//		Intent intent = new Intent(getContext(), MusicActivity.class);
//		((Activity)getContext()).startActivity(intent);
		
		Intent i = new Intent();       
		i.setAction(android.content.Intent.ACTION_VIEW);
//		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setDataAndType(Uri.parse("file://" + mfile.getUri()),"audio/mp3");
		((Activity) getContext()).startActivity(i);
	}
	
	private void activityMediaPlayer(int i){
		Intent intent = new Intent(getContext(), MediaPlayerActivity.class);
		intent.putExtra("FORLDER", "music");
		intent.putExtra("POSITION", i);
		((Activity)getContext()).startActivity(intent);		
	}
	
}
