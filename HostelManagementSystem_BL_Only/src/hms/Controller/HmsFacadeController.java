package hms.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hms.BLL.Admin;
import hms.BLL.Attendance;
import hms.BLL.Hosteller;
import hms.BLL.Mess;
import hms.BLL.PersonProfile;
import hms.BLL.Warden;
import hms.DAL.InitiateDataBase;
import hms.DAL.SelectHandler;

public class HmsFacadeController {

	private static HmsFacadeController instance = null ;
	private PersonProfile person = null;
	private Admin admin = null ;
	private Warden warden = null;
	
	protected HmsFacadeController(){
		
	}
	
	public static HmsFacadeController getInstance(){
		
		if (instance == null){
			
			instance = new HmsFacadeController();
		}
		
		return instance ;
		
	}
	
	public String checkMessMenuExist(String wid){
		
		SelectHandler selectHostelId = new SelectHandler();
		String whostelID = selectHostelId.getWardenHostelID(wid);
		
		if (whostelID != null){
			
			if(selectHostelId.checkMessID(whostelID)){
				
				return whostelID ;
			}
		}
		return whostelID ;
	}
	
	public boolean CheckRoomSapce (String wardenID){
		
		String wHostelID = "" ;
		SelectHandler selectDB = new SelectHandler();
		wHostelID = selectDB.getWardenHostelID(wardenID);
		warden = new Warden();
		warden.setHostelID(wHostelID);
		return warden.checkRoomsStatus();
	}
	
	public boolean AllocateRoomToHosteller(String hostelID , String rID , String hostellerID){
		
		warden = new Warden();
		warden.setHostelID(hostelID);
		return warden.AllocateRoom(rID, hostellerID);
	}
	
	public PersonProfile LoginPerson(String userName , String password , String role){
		
		person = new PersonProfile();
		person.setAccountID(userName);
        person.setAccountPassword(password.toCharArray());
        return person.LoginPersonAccount(person, role);
	}
	
	
	public boolean hostelRegistration(String hostelID , String hostelName , String hostelLoc ,
			                         String hostelRegDate , String totalRooms , String WID){
		admin = new Admin();
		return admin.registerNewHostel(hostelID, hostelName, hostelLoc, hostelRegDate,
				                Integer.parseInt(totalRooms) , WID);
	}
	
	public boolean registerNewPerson(String ID , String fName , String lName , String addr 
			                       , String e_mail , String age , String gender , String contact
			                       , String date , String salary , String hostelID){
		if (ID.startsWith("W")){
			
			admin = new Admin();
			return admin.WardenRegistration(ID, fName, lName, addr, e_mail, "Warden" ,  age, gender, contact, date, salary , hostelID);
			// Call Warden class registration................
		}
		else if(ID.startsWith("H")){
			
			warden = new Warden();
			return warden.HostellerRegistration(ID, fName, lName, addr, "Hosteller" , age, e_mail, gender, contact, date);
		}
		return false ;
	}
	
	public String getHostelInfo(String hostelID){
		
		admin = new Admin();
		return admin.getHostelName(hostelID);
	}
	
	public boolean updateHostelInfo(String hID , String hostelName){
		
		admin = new Admin();
		return admin.updateHostelName(hID , hostelName);
	}
	
	public PersonProfile getPersonInfo(String personID){
		
		admin = new Admin();
		return admin.getPersonProfile(personID);
	}
	
	public boolean updatePersonInfo(String ID , String fName , String lName , String addr , String eMail
			, String age , String gender , String contct){
		
		if (ID.startsWith("W")){
			
			admin = new Admin();
			return admin.updatePersonProfile(ID , fName , lName , addr , "Warden" , eMail , age , gender , contct);
		}
		
		return false ;
	}
	
	public boolean MakeNewMess(String mId , String hostelId , List<String> morningItems , List<String> eveningItems ){
		
		warden = new Warden();
		return warden.CreateNewMessMenu(mId, hostelId, morningItems, eveningItems);
		
	}
	
	public boolean updateMessItems(String mId , String hostelId , List<String> morningItems , List<String> eveningItems ){
		
		warden = new Warden();
		return warden.updateMessMenu(mId, hostelId, morningItems, eveningItems);
		
	}
	
	public Mess getMessMenu(String pID){
		
		String hostelId , mID ;
		SelectHandler selectDB = new SelectHandler();
		if (pID.startsWith("W")){
			
			warden = new Warden();
			hostelId = selectDB.getWardenHostelID(pID);
			if (hostelId != null){
				System.out.println("..???"+hostelId+"???..");
				mID = selectDB.getMessMenuId(hostelId);
				System.out.println("???"+mID+"???");
				return warden.ViewMessMenu(mID);
			}
			
		}
		else if (pID.startsWith("H")){
			
			Hosteller hosteller = new Hosteller();
			String rID = selectDB.getHostellerRoomID(pID);
			
			if (rID != null){
				
				hostelId = selectDB.getHostelId(rID);
				mID = selectDB.getMessMenuId(hostelId);
				
				return hosteller.viewMess(mID);
			}	
		}
		return null ;
	}
	
	public boolean removeMessItems(String hostelID){
		
		SelectHandler selectDB = new SelectHandler();
		String mId = selectDB.getMessMenuId(hostelID);
		warden = new Warden();
		return warden.removeMessMenu(mId, hostelID);
	}
	
	public ArrayList<Hosteller> getRegisteredHostellers(String wID){
		
		SelectHandler selectDB = new SelectHandler();
		String hostelID = selectDB.getWardenHostelID(wID);
		
		warden = new Warden();
		warden.setHostelID(hostelID);
		return warden.getHostellers();
	}
	
	public boolean saveAttendance(String atteDate , String [] attendanceList , String [] attenders){
		
		warden = new Warden();
		
		if (checkAttendanceDate(atteDate)){
			
			return warden.updateAttendanceStatus(attendanceList, attenders, atteDate);
			
		}
		else{
			
			return warden.setAttendance(attendanceList, attenders, atteDate);
		}
	}
	
	public boolean checkAttendanceDate(String attedate){
		
		SelectHandler selectDB = new SelectHandler();
		return selectDB.isAttenDateExist(attedate);
		
	}
	
	public boolean changePass(String oldPass,String newPass,String pID){
		admin = new Admin();
		return admin.changePass(oldPass, newPass, pID);
	}
	
	public boolean isValidID(String ID){
		
		SelectHandler selectDB = new SelectHandler();
		return selectDB.checkIDExist(ID);
	}
	
	public String getPersonID(String whoseID){
    	
    	SelectHandler selectDB = new SelectHandler();
 		String ID = null;
 		
 		if (whoseID.equals("selectWardenID")){
      		
 			ID = selectDB.getLastId("Warden");
 			if (ID != null){
 				
 				ID = getNextID(ID);
 			}
      	}
 		else if(whoseID.equals("selectHostellerID")){
 			
			ID = selectDB.getLastId("Hosteller");    // get last ID from person table were role = hosteller.............
 			
 			if (ID != null){
 				
 				ID = getNextID(ID);
 			}
 		}
 		else if(whoseID.equals("selectHostelID")){
 			
 			ID = selectDB.getLastId("Hostel");
 			
 			if (ID != null){
 				
 				ID = getNextID(ID);
 			}
 		}
 		else if (whoseID.equals("Mess")){
 			
 			ID = selectDB.getLastId("Mess");
 			
 			if (ID != null){
 				
 				ID = getNextID(ID);
 			}
 		}
 		return ID ;
    }
	
	private String getNextID(String ID){
		
		int id = 0;
		char first = (ID.substring(0, (ID.length()))).charAt(0);
		id = Integer.parseInt(ID.substring(1, (ID.length())));
		id++;
		ID = "";
		ID += first ;
		ID += id ;
		return ID ;
	}
}
