package adapters;

import java.util.List;


import utilities.SetViewSizeByPixel;
import utilities.Util;

import jit.vn.onseitaiwa2.R;
import jit.vn.onseitaiwa2.VideoActivity;

import mediaplayers.MediaPlayerActivity;
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

public class VideoExplorerAdapter extends ArrayAdapter<VideoFile> {

	public VideoExplorerAdapter(Context ctx, int resid,List<VideoFile> lst) {
		super(ctx, resid, lst);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int pos, View v, ViewGroup parent) {
		final VideoFile vfile = getItem(pos);
		LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = inflater.inflate(R.layout.list_video_file_explorer_item, parent, false);
		TextView txtFileName = (TextView) v.findViewById(R.id.txtFileName);
		txtFileName.setText(vfile.getName());
		SetViewSizeByPixel	size = new SetViewSizeByPixel(getContext());
		size.size(v.findViewById(R.id.btVideoFileIcon), size.RW(135), size.RH(170));
		size.textSize(txtFileName, size.RH(80.04f));
		
		return v;
	}

	private void activityVideo(VideoFile vfile){
//		VideoActivity.VIDEOPATH = vfile.getUri();
//		Intent intent = new Intent(getContext(), VideoActivity.class);
//		((Activity)getContext()).startActivity(intent);
		
		Intent i = new Intent();       
		i.setAction(android.content.Intent.ACTION_VIEW);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setDataAndType(Uri.parse("file://" + vfile.getUri()),"video/mpeg");
		((Activity) getContext()).startActivity(i);
		
	}
	
	private void activityMediaPlayer(int i){
		Intent intent = new Intent(getContext(), MediaPlayerActivity.class);
		intent.putExtra("FORLDER", "video");
		intent.putExtra("POSITION", i);
		((Activity)getContext()).startActivity(intent);		
	}
	
	
}
