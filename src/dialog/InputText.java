package dialog;

import jit.vn.onseitaiwa2.MainActivity;
import jit.vn.onseitaiwa2.R;
import utilities.SetViewSizeByPixel;
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

public class InputText extends Dialog{
	private SetViewSizeByPixel	size;
	private EditText			editText;
	public InputText(Context context, final Handler inputText, String str){
		super(context);
		size = new SetViewSizeByPixel(context);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		
		setContentView(R.layout.dialog_inputext);
		setCanceledOnTouchOutside(false);
		setCancelable(false);
		
		size = new SetViewSizeByPixel(context);
		
		size.size(findViewById(R.id.inputext_root), size.RH(1000), size.RH(700));
		size.margin(findViewById(R.id.inputext_content), size.RH(30), size.RH(40), size.RH(30),
				size.RH(30));
		
		//editText
		editText = (EditText) findViewById(R.id.inputext_edittext);
		size.height(editText, size.RH(400));
		size.marginBottom(editText, size.RH(30));
		editText.setText(str);
		
		//Button
		Button btnCancel = (Button) findViewById(R.id.inputext_btnCancel);
		btnCancel.setTypeface(MainActivity.tf02);
		size.textSize(btnCancel, size.RH(52));
		btnCancel.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View arg0){
				InputText.this.dismiss();
			}
		});
		
		Button btnOk = (Button) findViewById(R.id.inputext_btnOk);
		btnOk.setTypeface(MainActivity.tf02);
		size.textSize(btnOk, size.RH(52));
		
		btnOk.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View arg0){
				Message msg = new Message();
				msg.obj = editText.getText().toString().trim();
//				msg.obj = editText.getText().toString().trim().replace("\n", " ").replace("\r", " ");
				inputText.sendMessage(msg);
				InputText.this.dismiss();
			}
		});
		this.show();
	}
}
