package scenario;

public class ScenarioItem {

	public String strSay;
	public char[] bs;
	
	public ScenarioItem(String strSCIbs,String strSay){
		this.strSay = strSay;
		int len = strSCIbs.length();
		bs = new char[len];
		for(int i=0;i<len;i++){
			bs[i] = strSCIbs.charAt(i);
		}
	}
	
}
