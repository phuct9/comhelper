package views;

import jit.vn.onseitaiwa2.MainActivity.SCREEN;
import jit.vn.onseitaiwa2.R;
import utilities.Util;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import datas.models.ButtonData;

public class HealthView extends CommuniticationHelperView implements View.OnTouchListener {

	private BodyView vBody;

	public HealthView(Context ctx) {
		super(ctx);
		setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		inflate(ctx, R.layout.healthview, this);
		setView();
		switchSide();
		setMenu(R.string.All, SCREEN.C_All);
	}

	RelativeLayout lyContent, lyImage;
	// ImageView imgBody;
	TextView tvTitle;
	Button btnSwitch;
	Button btAllBody;
	Bitmap bodyColor;
	int w = 443, h = 1080;
	float rh = 1920 / (float) size.heightPixels();
	float rw = 1080 / (float) size.widthPixels();
	int currentSide = -1;

	private void setView() {

		// Root
		size.margin(findViewById(R.id.health_root), size.RH(40));

		// BtnSwitch
		btnSwitch = (Button) findViewById(R.id.health_switch);
		size.textSize(btnSwitch, size.RH(42));
		size.size(btnSwitch, size.RH(360), size.RH(120));
		btnSwitch.setTypeface(tf02);
		btnSwitch.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switchSide();
			}
		});

		//
		btAllBody = (Button) findViewById(R.id.btAllBody);
		btAllBody.setVisibility(GONE);
		size.textSize(btAllBody, size.RH(42));
		size.size(btAllBody, size.RH(360), size.RH(120));
		btAllBody.setTypeface(tf02);
		btAllBody.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showAllbody();
			}
		});
		
		// TvGuide
		size.textSize(findViewById(R.id.health_guide01), size.RH(30));
		size.margin(findViewById(R.id.health_guide01), size.RH(10));
		size.marginTop(findViewById(R.id.health_guide02), size.RH(20));
		size.textSize(findViewById(R.id.health_guide02), size.RH(30));

		// lyImage
		lyImage = (RelativeLayout) findViewById(R.id.health_lyImage);
		size.marginRight(findViewById(R.id.health_lyLeft), size.RH(15));
		int w = size.RH(620);
		int h = size.RH(900);
		size.size(lyImage, w, h);
		vBody = new BodyView(this, w, h);
		lyImage.addView(vBody);

		// ImageBody
		// imgBody = (ImageView) findViewById(R.id.health_image);
		// size.size(imgBody, size.RH(443), size.RH(1080));
		// imgBody.setOnTouchListener(this);

		// Content Title
		tvTitle = (TextView) findViewById(R.id.health_title);
		tvTitle.setTypeface(tf02);
		size.textSize(tvTitle, size.RH(42));
		// size.height(tvTitle, size.RH(83));

		// lyRightMenu

		size.marginLeft(findViewById(R.id.health_lyMenu), size.RH(15));

		// lyContent
		lyContent = (RelativeLayout) findViewById(R.id.health_lyContent);
	}

	private void switchSide() {
		vBody.switchSide();

		// currentSide: 0 is front, 1 is rear
		if (currentSide != 0) {
			// imgBody.setImageResource(R.drawable.body_front);
			btnSwitch.setText(getContext().getResources().getString(R.string.FrontSide));
			bodyColor = BitmapFactory.decodeResource(getResources(), R.drawable.body_front_color);
			currentSide = 0;
		} else {
			// imgBody.setImageResource(R.drawable.body_rear);
			btnSwitch.setText(getContext().getResources().getString(R.string.RearSide));
			bodyColor = BitmapFactory.decodeResource(getResources(), R.drawable.body_rear_color);
			currentSide = 1;
		}
		if (lyImage.getChildCount() > 1) {
			lyImage.removeViewAt(1);
		}
//		setMenu(R.string.All, SCREEN.C_All);
		
		vBody.invalidate();
	}

	public static final int ALL = 0;
	public static final int HEAD = 0xffcb0000;
	public static final int face = 0xff434a54;// 434A54
	public static final int HAND = 0xff877744;
	public static final int LEG = 0xff456fac;
	public static final int CHEST = 0xffdd83b9;
	public static final int belly = 0xffd4ac43;
	public static final int GENITALS = 0xff8560a8;
	public static final int neck = 0xffd6a81c;// 413F41
	public static final int shoulder = 0xff2a74c8;
	public static final int BACK = 0xffae57ad;
	public static final int BUM = 0xff46aae3;

	public static final int EYES = 0XFFFFFF00;// 目：mắt
	public static final int EARS = 0XFF00FF00;// 耳：tai
	public static final int NOSE = 0XFF999999;// 鼻：mũi
	public static final int MOUTH = 0XFFFF00FF;// 口：miệng
	
	
	public static final int BACKHEAD = 0XFFCBFF00;// 口：sau đầu
	public static final int BACKHAND = 0XFF8777FF;// 口：sau tay
	public static final int BACKLEG = 0XFF456FFF;// 口：sau chan
	
	

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		/*
		 * if((v.getId() == imgBody.getId()) && (event.getAction() ==
		 * MotionEvent.ACTION_DOWN)){ if(lyImage.getChildCount() > 1){
		 * lyImage.removeViewAt(1); } lyImage.addView( new
		 * ViewDraw(getContext(), (int) event.getX(), (int) event.getY())); int
		 * x = (int) (event.getX() * rh); int y = (int) (event.getY() * rh);
		 * Log.e("OnTouch", x + " | " + y); int color = bodyColor.getPixel(x,
		 * y); switch (color){ case all: return setMenu(R.string.All,
		 * SCREEN.C_All); case head: return setMenu(R.string.Head,
		 * SCREEN.C_Head); case face: return setMenu(R.string.Face,
		 * SCREEN.C_Face); case arm: return setMenu(R.string.Arm, SCREEN.C_Arm);
		 * case leg: return setMenu(R.string.Leg, SCREEN.C_Leg); case chest:
		 * return setMenu(R.string.Chest, SCREEN.C_Chest); case belly: return
		 * setMenu(R.string.Belly, SCREEN.C_Belly); case genitals: return
		 * setMenu(R.string.Genitals, SCREEN.C_Genitals); case neck: return
		 * setMenu(R.string.Neck, SCREEN.C_Neck); case shoulder: return
		 * setMenu(R.string.Shoulder, SCREEN.C_Shoulder); case back: return
		 * setMenu(R.string.Back, SCREEN.C_Back); case bum: return
		 * setMenu(R.string.Bum, SCREEN.C_Bum); default: break; } }
		 */
		return false;
	}

	public boolean setMenu(int color) {
		Util.log("color",color);
		switch (color) {
		case ALL:
			return setMenu(R.string.All, SCREEN.C_All);
		case HEAD:// 頭：đầu
			return setMenu(R.string.Head, SCREEN.C_Head);
		case face:
		case BACKHEAD://gáy
			return setMenu("首から肩", 5500);
		case HAND:
		case 0XFF877755:
		case BACKHAND:
		case -7899273:
			return setMenu(R.string.Arm, SCREEN.C_Arm);
		case LEG:
		case BACKLEG:
			return setMenu(R.string.Leg, SCREEN.C_Leg);
		case CHEST:
			return setMenu(R.string.Chest, SCREEN.C_Chest);
		case belly:
			return setMenu(R.string.Belly, SCREEN.C_Belly);
		case GENITALS:
			return setMenu(R.string.Genitals, SCREEN.C_Genitals);
		case neck:
			return setMenu(R.string.Neck, SCREEN.C_Neck);
		case shoulder:
			return setMenu(R.string.Shoulder, SCREEN.C_Shoulder);
		case BACK:
			return setMenu(R.string.Back, SCREEN.C_Back);
		case BUM:
			return setMenu(R.string.Bum, SCREEN.C_Bum);
		case EYES:// 目：mắt
			return setMenu(R.string.EYES, SCREEN.EYES);
		case EARS:// 耳：tai
			return setMenu(R.string.EARS, SCREEN.EARS);
		case NOSE: // 鼻：mũi
			return setMenu(R.string.NOSE, SCREEN.NOSE);
//		case MOUTH: // 口：miệng
//			return setMenu(R.string.MOUTH, SCREEN.MOUTH);
		case 0XFF00FFFF : //vai 
			return setMenu("肩", 3320);
		case 0XFFFF00FF : //bàn tay 
			return setMenu("手", 3310);
		case 0XFFFF6FFF://đầu gối
			return setMenu("ひざ", 3720);
		case 0XFF45FFFF://bắp chân
			return setMenu("すね", 3730);
		case 0XFF9900FF://bàn chân
			return setMenu("足", 3740);
		case 0XFFF0000F://miệng
			return setMenu("口", 4500);
		default:
			Log.v("MyDebug", "color:"+color+"");
			return false;
		}
	}

	public class ViewDraw extends View {
		int x, y;
		Paint paint = new Paint();

		public ViewDraw(Context context, int x, int y) {
			super(context);
			this.x = x;
			this.y = y;
			paint.setStrokeWidth(2);
			paint.setPathEffect(null);
			paint.setColor(Color.BLUE);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvas.drawCircle(x, y, 8, paint);
		}
	}

	private boolean setMenu(int id, SCREEN scr) {
		tvTitle.setText(getContext().getResources().getString(id));
		lyContent.removeAllViews();
		if (scr != null) {
			CommuniticationHelperView view = new CommuniticationHelperView(getContext());
			ButtonData btnData = new ButtonData();
			view.setButtonViewMenu(lyContent, btnData.getViewID(scr));
			for (int i = 0; i < lyContent.getChildCount(); i++) {
				View child = lyContent.getChildAt(i);
				size.marginLeft(child, size.RW(10));
				size.marginRight(child, size.RH(10));
			}
		}
		return false;
	}

	/*private boolean setMenu(String title, SCREEN scr) {
		tvTitle.setText(title);
		lyContent.removeAllViews();
		if (scr != null) {
			CommuniticationHelperView view = new CommuniticationHelperView(getContext());
			view.setButtonView(lyContent, scr);
			for (int i = 0; i < lyContent.getChildCount(); i++) {
				View child = lyContent.getChildAt(i);
				size.marginLeft(child, size.RW(10));
				size.marginRight(child, size.RH(10));
			}
		}
		return false;
	}*/

	
	private boolean setMenu(String title, int idView) {
		tvTitle.setText(title);
		lyContent.removeAllViews();
			CommuniticationHelperView view = new CommuniticationHelperView(getContext());
			view.setButtonViewMenu(lyContent, idView);
			for (int i = 0; i < lyContent.getChildCount(); i++) {
				View child = lyContent.getChildAt(i);
				size.marginLeft(child, size.RW(10));
				size.marginRight(child, size.RH(10));
			}
		return false;
	}
	
	
	private void showAllbody(){
		vBody.showAllbody();
		vBody.invalidate();
	}
	
}
