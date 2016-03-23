package customize;

import android.content.Context;
import android.view.View;

public class VolumeIncButton extends VolumeButton {
	
	public VolumeIncButton(Context ctx) {
		super(ctx);
		setText("INC");
	}

	@Override
	public void onClick(View v) {
		volumeInc();
	}

	private void volumeInc() {
		IVOLUME++;
		if (IVOLUME > ivolumeMax)
			IVOLUME = ivolumeMax;
		volume = IVOLUME * dvolume;
		if (volume > volumeMax)
			volume = volumeMax;
		setVolume(volume);
	}
	
	
}
