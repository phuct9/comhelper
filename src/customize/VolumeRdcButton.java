package customize;

import android.content.Context;
import android.view.View;

public class VolumeRdcButton extends VolumeButton {

	public VolumeRdcButton(Context ctx) {
		super(ctx);
		setText("RDC");
	}
	
	@Override
	public void onClick(View v) {
		volumeRdc();
	}
	
	private void volumeRdc() {
		IVOLUME--;
		if (IVOLUME < 0)
			IVOLUME = 0;
		volume = IVOLUME * dvolume;
		setVolume(volume);
	}

}
