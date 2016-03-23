package dialog;

import android.app.Dialog;
import android.content.Context;

public class VolumeDialog extends Dialog {

	public VolumeDialog(Context context) {
		super(context);
		setCancelable(true);
		setCanceledOnTouchOutside(true);
	}

}
