package slideshows;

import jit.vn.onseitaiwa2.R;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import customize.BackButton;

public class SlideShowActivity extends Activity {
	private Utility utils;
	private FullScreenImageAdapter adapter;
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slideshow);
		viewPager = (ViewPager) findViewById(R.id.pager);
		utils = new Utility(getApplicationContext());
		int position = getIntent().getIntExtra("position", 0);
		adapter = new FullScreenImageAdapter(SlideShowActivity.this, utils.getFilePaths());
		viewPager.setAdapter(adapter);
		// displaying selected image first
		viewPager.setCurrentItem(position);

		// // close button click event
		BackButton btBack = (BackButton) findViewById(R.id.btnBack);
		btBack.setSize(148, 152);
		btBack.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

}