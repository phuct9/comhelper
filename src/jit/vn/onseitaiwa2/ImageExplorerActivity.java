package jit.vn.onseitaiwa2;

import slideshows.SlideShowActivity;
import customize.BackButton;
import customize.ImageGridView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class ImageExplorerActivity extends Activity {

	private ImageGridView igvPhotos;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo_explorer);
		
		igvPhotos = (ImageGridView) findViewById(R.id.igvPhotos);
		igvPhotos.setPath("/communicationhelper/image");
		
		igvPhotos.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> vadp, View v, int i, long l) {
				showSlideImage(i);
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

	protected void showSlideImage(int i) {
		Intent intent = new Intent(ImageExplorerActivity.this, SlideShowActivity.class);
		intent.putExtra("position", i);
		startActivity(intent);
		
	}
	
}
