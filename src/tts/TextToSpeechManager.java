package tts;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import scenario.ScenarioItem;
import utilities.Util;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;

public class TextToSpeechManager implements OnInitListener, OnUtteranceCompletedListener {

	public TextToSpeech tts;
	public Map<String,ScenarioItem> mapSays =  new HashMap<String, ScenarioItem>();

	public TextToSpeechManager(Context ctx) {
		tts = new TextToSpeech(ctx,this);
	}
	
	public void speak(String text){
		Util.log(text);
		 if(text != null) {
		      HashMap<String, String> myHashAlarm = new HashMap<String, String>();
		      myHashAlarm.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "SOME MESSAGE");
		      tts.speak(text, TextToSpeech.QUEUE_FLUSH, myHashAlarm);
		   }
	}
	
	public void speakFlowScenario(String key) {
		speak(mapSays.get(key).strSay);
	}

	public void onPause() {
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
	}

	@Override
	public void onUtteranceCompleted(String utteranceId) {
		Util.log("onUtteranceCompleted");
	}

	@Override
	public void onInit(int status) {
		if (status != TextToSpeech.ERROR) {
			tts.setOnUtteranceCompletedListener(this);
			if (tts.isLanguageAvailable(Locale.JAPAN) == TextToSpeech.LANG_COUNTRY_AVAILABLE){
				tts.setLanguage(Locale.JAPAN);
//				tts.setPitch(0.1f);
			}
		}
	}

//	private void installVoiceData() {
//	    Intent intent = new Intent(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
//	    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//	    intent.setPackage("com.google.android.tts"/*replace with the package name of the target TTS engine*/);
//	    try {
//	    	((OnseitaiwaActivity)ctx).startActivity(intent);
//	    } catch (ActivityNotFoundException ex) {
//	        ex.printStackTrace();
//	    }
//	}

}
