package views;

import jit.vn.onseitaiwa2.R;
import android.content.Context;
import android.net.Uri;
import android.widget.MediaController;

public class VideoView extends CommuniticationHelperView {

	public VideoView(Context ctx) {
		super(ctx);
		inflate(ctx, R.layout.videoview, this);
		VideoView vidView = (VideoView)findViewById(R.id.myVideo);
		String vidAddress = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
		Uri vidUri = Uri.parse(vidAddress);
//		vidView.setVideoURI(vidUri);
//		vidView.start();
		
		MediaController vidControl = new MediaController(ctx);
		
//		vidView.setMediaController(vidControl);
		
		
	}

}
