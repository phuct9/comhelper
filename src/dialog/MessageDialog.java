package dialog;

import jit.vn.onseitaiwa2.MainActivity;
import jit.vn.onseitaiwa2.R;
import utilities.SetViewSizeByPixel;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MessageDialog extends Dialog{
	private SetViewSizeByPixel	size;
	private Activity act;
	
	public MessageDialog(Context context, String str){
		super(context);
		act = (Activity) context;
		size = new SetViewSizeByPixel(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		setContentView(R.layout.dialog_message);
		setCanceledOnTouchOutside(false);
		setCancelable(false);
		
		size = new SetViewSizeByPixel(context);
		
		size.size(findViewById(R.id.inputext_root), size.RH(1000), size.RH(700));
		size.margin(findViewById(R.id.inputext_content), size.RH(30), size.RH(40), size.RH(30),
				size.RH(30));
		
		//editText
		TextView editText = (TextView) findViewById(R.id.txtMessage);
		size.height(editText, size.RH(400));
		size.marginBottom(editText, size.RH(30));
		editText.setText(str);
		
		//Button
		Button btnExit = (Button) findViewById(R.id.btExit);
		btnExit.setTypeface(MainActivity.tf02);
		size.textSize(btnExit, size.RH(52));
		btnExit.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View arg0){
				MessageDialog.this.dismiss();
				act.finish();
			}
		});
		this.show();
	}
}
