package customize;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import jit.vn.onseitaiwa2.R;
import utilities.Util;
import views.VolumeView;

public class VolumeButton extends MyButton implements android.view.View.OnClickListener{

	protected AudioManager audioManager;
	protected int volumeMax;
	protected int volume;
	protected int dvolume;
	protected static int IVOLUME;
	protected int ivolumeMax = 4;
	protected VolumeView vVolume;
	
	public VolumeButton(Context ctx) {
		super(ctx);
		initVolume();
		setOnClickListener(this);
	}

	private void initVolume() {
		vVolume = (VolumeView) ((Activity)getContext()).findViewById(R.id.vVolume);
		audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
		volumeMax = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		dvolume = volumeMax / ivolumeMax;
		volume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		IVOLUME = volume / dvolume;
	}
	
	protected void setVolume(int value) {
		audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, value, 0);
		vVolume.show(IVOLUME);
	}
	
	@Override
	public void onClick(View v) {
		
	}

}
