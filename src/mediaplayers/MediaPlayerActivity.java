package mediaplayers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import customize.BackButton;
import customize.VideoLayout;
import customize.VolumeIncButton;
import customize.VolumeRdcButton;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import jit.vn.onseitaiwa2.R;
import utilities.SetViewSizeByPixel;
import utilities.Util;

public class MediaPlayerActivity extends Activity
		implements SurfaceHolder.Callback, OnCompletionListener, SeekBar.OnSeekBarChangeListener {

	private Button btnPlay;
	private Button btnNext;
	private Button btnPrevious;
	private SeekBar songProgressBar;
	// private TextView songTitleLabel;
	// Media Player
	private MediaPlayer mp;
	// Handler to update UI timer, progress bar etc,.
	private Handler mHandler = new Handler();;
	private SongsManager songManager;
	private Utilities utils;
	private int seekForwardTime = 5000; // 5000 milliseconds
	private int seekBackwardTime = 5000; // 5000 milliseconds
	private int currentSongIndex = 0;
	private boolean isShuffle = false;
	private boolean isRepeat = false;

	private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
	
	private SetViewSizeByPixel size;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		
		size = new SetViewSizeByPixel(this);
		
		VolumeRdcButton btVolumeRdc = new VolumeRdcButton(this);
		VolumeIncButton btVolumeInc = new VolumeIncButton(this);
		
		((LinearLayout)findViewById(R.id.layRdc)).addView(btVolumeRdc);
		((LinearLayout)findViewById(R.id.layInc)).addView(btVolumeInc);
		
		size.size(btVolumeRdc, size.RW(316), size.RH(150));
		size.size(btVolumeInc, size.RW(316), size.RH(150));

		size.marginTop(btVolumeRdc, size.RH(22));
		size.marginTop(btVolumeInc, size.RH(22));

		size.marginLeft(btVolumeRdc, size.RW(32));
		size.marginRight(btVolumeRdc, size.RW(35));

		size.marginBottom(btVolumeRdc, size.RH(24));
		size.marginBottom(btVolumeInc, size.RH(24));

//		btVolumeRdc.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				volumeRdc();
//			}
//		});
//
//		btVolumeInc.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				volumeInc();
//			}
//		});

		

		
		
		
		
		
		
		
		
		
		
		

		// All player buttons
		btnPlay = (Button) findViewById(R.id.btnPlay);
		btnNext = (Button) findViewById(R.id.btnNext);
		btnPrevious = (Button) findViewById(R.id.btnPrevious);
		songProgressBar = (SeekBar) findViewById(R.id.songProgressBar);

		initForPlayVideo();

		setMultyScreen();
		// Mediaplayer
		mp = new MediaPlayer();
		songManager = new SongsManager();
		utils = new Utilities();

		// Listeners
		songProgressBar.setOnSeekBarChangeListener(this); // Important
		mp.setOnCompletionListener(this); // Important

//		mp.setOnVideoSizeChangedListener(new OnVideoSizeChangedListener() {
//			@Override
//			public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
//				resizeVideoSize();
//			}
//		});

		// mp.setOnPreparedListener(new OnPreparedListener() {
		// @Override
		// public void onPrepared(MediaPlayer mp) {
		//
		// }
		// });

		Intent intent = getIntent();
		int pos = intent.getExtras().getInt("POSITION");
		currentSongIndex = pos;
		String folder = intent.getExtras().getString("FORLDER");

		// Getting all songs list
		songsList = songManager.getPlayList(folder);

		// By default play first song
		playSong(pos);

		/**
		 * Play button click event plays a song and changes button to pause
		 * image pauses a song and changes button to play image
		 */
		btnPlay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mp.isPlaying()) {
					if (mp != null) {
						play();
						// Changing button image to play button
						btnPlay.setBackgroundResource(R.drawable.btn_play);
					}
				} else {
					// Resume song
					if (mp != null) {
						// mp.start();
						play();
						// Changing button image to pause button
						btnPlay.setBackgroundResource(R.drawable.btn_pause);

					}

				}
			}
		});

		/**
		 * Next button click event Plays next song by taking currentSongIndex +
		 * 1
		 */
		btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				next();
			}
		});

		/**
		 * Back button click event Plays previous song by currentSongIndex - 1
		 */
		btnPrevious.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				previous();
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

	/**
	 * set MultyScreen
	 */
	private void setMultyScreen() {
//		// TODO Auto-generated method stub
//
//		SetViewSizeByPixel setSize = new SetViewSizeByPixel(this);
//		DisplayMetrics metrics = setSize.getMetrics();
//
//		//////////// 480*800
//		Button btn_back = (Button) findViewById(R.id.btBack);
//		setSize.size(btn_back, metrics.widthPixels * 100 / 480, metrics.heightPixels * 70 / 800);
//		// setSize.marginBottom(btn_back, metrics.heightPixels * 120 / 800);
//		// setSize.size(vidSurface, metrics.widthPixels * 480 / 480,
//		// metrics.heightPixels * 320 / 800);
//		// setSize.marginBottom(vidSurface, metrics.heightPixels * 5 / 800);
//
//		LinearLayout layout_video = (LinearLayout) findViewById(R.id.layout_surfaceview);
//		setSize.size(layout_video, metrics.widthPixels * 480 / 480, metrics.heightPixels * 320 / 800);
//		setSize.marginTop(layout_video, metrics.heightPixels * 120 / 800);
//
//	//	setSize.size(songProgressBar, metrics.widthPixels * 480 / 480, metrics.heightPixels * 50 / 800);
//		// setSize.marginBottom(songProgressBar, metrics.heightPixels * 90 /
//		// 800);
//		setSize.marginBottom(songProgressBar, metrics.heightPixels * 50 / 800);
//		LinearLayout layout_1 = (LinearLayout) findViewById(R.id.player_footer_bg);
//		setSize.height(layout_1, metrics.heightPixels * 200 / 800);
//		setSize.size(btnPlay, metrics.widthPixels * 175 / 480, metrics.heightPixels * 175 / 800);
//		setSize.size(btnNext, metrics.widthPixels * 100 / 480, metrics.heightPixels * 100 / 800);
//		setSize.size(btnPrevious, metrics.widthPixels * 100 / 480, metrics.heightPixels * 100 / 800);
//		setSize.margin(btnPlay, metrics.widthPixels * 10 / 480, 0, metrics.widthPixels * 10 / 480, 0);

	}

	private SurfaceHolder vidHolder;
	private SurfaceView vidSurface;

	private void initForPlayVideo() {
		vidSurface = (SurfaceView) findViewById(R.id.surfView);
		vidHolder = vidSurface.getHolder();
		vidHolder.addCallback(this);
	}

	/**
	 * Receiving song index from playlist view and play the song
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 100) {
			currentSongIndex = data.getExtras().getInt("songIndex");
			playSong(currentSongIndex);
		}

	}

	/**
	 * Function to play a song
	 * 
	 * @param songIndex
	 *            - index of song
	 */
	public void playSong(int songIndex) {
		// Play song
		try {
			mp.reset();
			mp.setDataSource(songsList.get(songIndex).get("songPath"));
			mp.prepare();
			mp.start();
			// Displaying Song title
			String songTitle = songsList.get(songIndex).get("songTitle");

			// Changing Button Image to pause image
			// btnPlay.setImageResource(R.drawable.btn_pause);

			// set Progress bar values
			songProgressBar.setProgress(0);
			songProgressBar.setMax(100);

			// Updating progress bar
			updateProgressBar();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update timer on seekbar
	 */
	public void updateProgressBar() {
		mHandler.postDelayed(mUpdateTimeTask, 100);
	}

	/**
	 * Background Runnable thread
	 */
	private Runnable mUpdateTimeTask = new Runnable() {
		public void run() {
			try{
			 long totalDuration = mp.getDuration();
			 long currentDuration = mp.getCurrentPosition();
			
			 // Displaying Total Duration time
//			 songTotalDurationLabel.setText(""+utils.milliSecondsToTimer(totalDuration));
			 // Displaying time completed playing
//			 songCurrentDurationLabel.setText(""+utils.milliSecondsToTimer(currentDuration));
			
			 // Updating progress bar
			 int progress = (int)(utils.getProgressPercentage(currentDuration,totalDuration));
			 //Log.d("Progress", ""+progress);
			 songProgressBar.setProgress(progress);
			
			 // Running this thread after 100 milliseconds
			 mHandler.postDelayed(this, 100);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	};

	/**
	 * 
	 * */
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

	}

	/**
	 * When user starts moving the progress handler
	 */
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// remove message Handler from updating progress bar
		mHandler.removeCallbacks(mUpdateTimeTask);
	}

	/**
	 * When user stops moving the progress hanlder
	 */
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		mHandler.removeCallbacks(mUpdateTimeTask);
		int totalDuration = mp.getDuration();
		int currentPosition = utils.progressToTimer(seekBar.getProgress(), totalDuration);

		// forward or backward to certain seconds
		mp.seekTo(currentPosition);

		// update timer progress again
		updateProgressBar();
	}

	/**
	 * On Song Playing completed if repeat is ON play same song again if shuffle
	 * is ON play random song
	 */
	@Override
	public void onCompletion(MediaPlayer arg0) {
		finish();

//		// check for repeat is ON or OFF
//		if (isRepeat) {
//			// repeat is on play same song again
//			playSong(currentSongIndex);
//		} else if (isShuffle) {
//			// shuffle is on - play a random song
//			Random rand = new Random();
//			currentSongIndex = rand.nextInt((songsList.size() - 1) - 0 + 1) + 0;
//			playSong(currentSongIndex);
//		} else {
//			// no repeat or shuffle ON - play next song
//			if (currentSongIndex < (songsList.size() - 1)) {
//				playSong(currentSongIndex + 1);
//				currentSongIndex = currentSongIndex + 1;
//			} else {
//				// play first song
//				playSong(0);
//				currentSongIndex = 0;
//			}
//		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mp.release();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Log.v("MyDebug", width + "X" + height);

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mp.setDisplay(vidHolder);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	private void setRepeat(boolean repeat) {
		this.isRepeat = repeat;
	}

	private void setShuffle(boolean shuffle) {
		this.isShuffle = shuffle;
	}

	private void back() {
		int currentPosition = mp.getCurrentPosition();
		int pos = currentPosition - seekBackwardTime;
		if (pos >= 0) {
			mp.seekTo(pos);
		} else {
			mp.seekTo(0);
		}
	}

	private void forward() {
		int currentPosition = mp.getCurrentPosition();
		int pos = currentPosition + seekForwardTime;
		if (pos <= mp.getDuration()) {
			mp.seekTo(pos);
		} else {
			mp.seekTo(mp.getDuration());
		}
	}

	private void next() {
		if (currentSongIndex < (songsList.size() - 1)) {
			currentSongIndex++;
			playSong(currentSongIndex);
			resizeVideoSize();
		}
	}

	private void previous() {
		if (currentSongIndex > 0) {
			currentSongIndex = currentSongIndex - 1;
			playSong(currentSongIndex);
			resizeVideoSize();
		}
	}

	private void play() {
		if (mp.isPlaying()) {
			if (mp != null) {
				mp.pause();
			}
		} else {
			if (mp != null) {
				mp.start();
			}
		}
	}

	private void playlist() {
		Intent i = new Intent(getApplicationContext(), PlayListActivity.class);
		startActivityForResult(i, 100);
	}

	public boolean resizeVideoSize() {
//		DisplayMetrics metrics = new DisplayMetrics();
//		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		VideoLayout vly = (VideoLayout) findViewById(R.id.layout_surfaceview);
		int w = vly.w;
		int h = vly.h;
		
//		int w = metrics.widthPixels;
//		int h = metrics.heightPixels / 2;
		Log.v("MyDebug", "layout_surfaceview = " + w + "X" + h);
		int vw = mp.getVideoWidth();
		int vh = mp.getVideoHeight();
		Log.v("MyDebug", "video_size = " + vw + "X" + vh);

		float rw = (float) w / vw;
		float rh = (float) h / vh;
		float r = rw < rh ? rw : rh;

		LayoutParams params = vidSurface.getLayoutParams();
		params.width = (int) (vw * r);
		params.height = (int) (vh * r);
		vidSurface.requestLayout();
		
		return vw!=0 && vh!=0;
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