package hms.BLL;

import java.util.ArrayList;
import java.util.List;

import hms.DAL.DeleteHandler;
import hms.DAL.InsertHandler;
import hms.DAL.SelectHandler;
import hms.DAL.UpdateHandler;

public class Mess {

	private String mID ;
	private String mHostelId ;
	
	private List<MessItems> messDishes = null ;
	
	public Mess(){
		
		messDishes = new ArrayList<MessItems>(7);
		for (int i = 0 ; i < 7 ; i++){
			
			MessItems dish = new MessItems();
			messDishes.add(dish);
		}
	}
	
	public boolean addNewMess(){
		
		InsertHandler insertMess = new InsertHandler();
		return insertMess.InsertIntoMess(this) ;
	}
	
	public boolean updateMess(){
		
		UpdateHandler updateDB = new UpdateHandler();
		return updateDB.updateMessMenuItems(this);
		
	}
	
	public static Mess viewMess(String mID){
		
		SelectHandler selectDB = new SelectHandler();
		Mess m = selectDB.getMessItems(mID);
		System.out.println(m.mID+"##");
		return m;
	}
	
	public boolean removeMess(){
		
		DeleteHandler deleteMess = new DeleteHandler();
		return deleteMess.DeleteMessItems(this.mID, this.mHostelId);
	}

	public String getmID() {
		return this.mID;
	}

	public String getmHostelId() {
		return this.mHostelId;
	}

	public List<MessItems> getMessDishes() {
		return this.messDishes;
	}

	public void setmID(String mID) {
		this.mID = mID;
	}

	public void setmHostelId(String mHostelId) {
		this.mHostelId = mHostelId;
	}

	public void setMessDishes(List<MessItems> messDishes) {
		this.messDishes = messDishes;
	}
}
