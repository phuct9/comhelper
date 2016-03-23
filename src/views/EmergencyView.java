package views;

import android.content.Context;
import android.util.Log;
import http.HttpManager;

public class EmergencyView extends CommuniticationHelperView {
	
	private HttpManager httpMng;
	private String strEmergency;
	public ThrEmergency thrEmergency;
	
	
	public EmergencyView(Context context) {
		super(context);
		httpMng = new HttpManager(context);
	}

	public void speak(String str) {
		stopSpeak();
		this.strEmergency = str;
		thrEmergency = new ThrEmergency();
		thrEmergency.start();
	}

	public class ThrEmergency extends Thread{
		
		public boolean speaking = true;
		
		public void run() {
			httpMng.sendSms(strEmergency);
			while(speaking){
				Log.v("MyDebug", "EmergencyView.speak run "+strEmergency);
				app.tts.speak(strEmergency);
				int tdelay = 200;
				while(tdelay>0 && speaking){
					try {
						sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					tdelay--;
				}
			}
		};
	};
	
	public void stopSpeak(){
		app.tts.tts.stop();
		if (thrEmergency!=null)
			thrEmergency.speaking = false;
	}
	
}
