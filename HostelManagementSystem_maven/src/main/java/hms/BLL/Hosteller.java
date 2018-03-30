package hms.BLL;

import hms.DAL.InsertHandler;

public class Hosteller extends PersonProfile {
	
	//private int hID ;
	private String hRId ;
	private String hRdata ;
	
	public Hosteller(){
		//hID = 0;
	}

	/*public int gethID() {
		return this.hID;
	}

	public void sethID(int hID) {
		this.hID = hID;
	}*/

	public boolean addNewHosteller(){
		
		InsertHandler insertDB = new InsertHandler();
		return insertDB.InsertIntoHosteller(this);
	}
	
	public Mess viewMess(String mID){
		
		return Mess.viewMess(mID);
	}
	
	public String gethRdata() {
		return this.hRdata;
	}

	public void sethRdata(String hRdata) {
		this.hRdata = hRdata;
	}

	public String gethRId() {
		return this.hRId;
	}

	public void sethRId(String hRId) {
		this.hRId = hRId;
	}
}
