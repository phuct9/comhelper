package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class HttpManager {

	//public static final String SERVER_ADDRESS = "http://118.70.177.74:8080/";
	public static final String SERVER_ADDRESS = "http://61.118.107.10/";
	private String address = null;
	private Context ctx;
	public JSONArray jsarr;
	
	public HttpManager(Context ctx){
		this.ctx = ctx;
	}
	
	public void sendSms(String sms){
		android.util.Log.v("MyDebug", sms);
		//Log.v("MyDebug","sendSms");
		//address = "http://phucf8.byethost9.com/send_message.php";
		address = SERVER_ADDRESS + "gcm_test/gcmchat.php?comment="+sms.replace(" ", "%20");
		//address = "http://61.118.107.10/gcm_test/gcmchat.php?comment="+sms.replace(" ", "%20");
		new SendTask().execute("message",sms);
	}
	
	
	private void sendGet(String sms){
		HttpClient client = new DefaultHttpClient();
		//client.getParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
		HttpGet get = new HttpGet(address);
//		get.getParams().setParameter("comment", sms.replace(" ", "%20"));
//		Log.v("MyDebug","sendGet2");
		try {
			//post.setEntity(new UrlEncodedFormEntity(pairs));
			HttpResponse response = client.execute(get);
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
			String line;
			while((line = reader.readLine())!=null){
				Log.v("MyDebug",line);	
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private void send(Object ... objs){
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(address);
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		for(int i=0;i<objs.length;i+=2){
			pairs.add(new BasicNameValuePair(objs[i].toString(), objs[i+1].toString()));
		}
		try {
			post.setEntity(new UrlEncodedFormEntity(pairs));
			HttpResponse response = client.execute(post);
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
			String json = reader.readLine();
			
			JSONTokener tokener = new JSONTokener(json);
			jsarr = new JSONArray(tokener);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	
	private class SendTask extends AsyncTask<String, Void, Void> {
//	    private ProgressDialog progressDialog;
	 
	    @Override
	    protected void onPreExecute() {//begin progress
	        super.onPreExecute();
//	        progressDialog = ProgressDialog.show(ctx, "THANK YOU FOR PLAYING", "Sending this comment", true, false);
	    }
	 
	    @Override
	    protected void onPostExecute(Void aVoid) {//end progress
	        super.onPostExecute(aVoid);
//	        progressDialog.dismiss();
//	        ((Activity)ctx).finish();
	    }
	 
	    @Override
	    protected Void doInBackground(String... arrstr) {
	        //send(arrstr);
	        sendGet("XINCHAO");
	        return null;
	    }
	    
	}
	
}
