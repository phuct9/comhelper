package customize;

import android.content.Context;
import android.view.View;
import dialog.EmergencyDialog;
import jit.vn.onseitaiwa2.R;

public class EmergencyButton extends MyButton implements android.view.View.OnClickListener {

	private EmergencyDialog dlgEmergency;
	
	public EmergencyButton(Context ctx) {
		super(ctx);
		setText("Emergency");
		setBackgroundResource(R.drawable.btn02_9p);
		dlgEmergency = new EmergencyDialog(ctx);
		setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		dlgEmergency.show();
	}

}
