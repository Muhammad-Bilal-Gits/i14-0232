package hms.BLL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hms.DAL.InsertHandler;
import hms.DAL.SelectHandler;
import hms.DAL.UpdateHandler;

public class Warden extends PersonProfile {

	private String wID ;
	private String date ;
	private String salary ;
	private String hostelID ;
	
	public Warden(){	
	}
	
	public boolean HostellerRegistration(String ID , String fName , String lName , String addr , String role , String age ,
			String eMail , String gender , String contct , String date ){
		
		//boolean isRegister = false ;
		
		Hosteller newHosteller = new Hosteller();
		// set new hosteller data...............
		
		newHosteller.setpID(ID);
		newHosteller.setFirstName(fName.toCharArray());
		newHosteller.setLastName(lName.toCharArray());
		newHosteller.setAddress(addr.toCharArray());
		newHosteller.setAge(Integer.parseInt(age));
		newHosteller.setE_mail(eMail.toCharArray());
		newHosteller.setGender(gender);
		newHosteller.setRole(role);
		newHosteller.setContact(contct.toCharArray());
		
		// Open Account........
		newHosteller.setAccountID(ID);
		newHosteller.setAccountPassword(ID.toCharArray());
		
		newHosteller.sethRdata(date);
		
		return newHosteller.addNewHosteller();
	}
	
	public ArrayList<Hosteller> getHostellers(){
		
		Hostel myHostel = new Hostel();
		myHostel.setHostelID(this.hostelID);
		
		return myHostel.getHostellersList();
	}
	
	public boolean checkRoomsStatus(){
		
		SelectHandler selectDB = new SelectHandler();
		ResultSet rs = selectDB.getHostelRoom(this.hostelID);
		
		try {
			if (rs.next()){
				
				return true ;
			}
		} catch (SQLException e) {
			System.err.println(e);
			return false ;
			//e.printStackTrace();
		}
		return false ;
	}
	
	public boolean AllocateRoom(String Rid , String hostellerID){
		
		Hostel myHostel = new Hostel();
		myHostel.setHostelID(this.hostelID);
		Room emptyRoom = myHostel.getRoom(Rid);
		System.out.println("<<"+emptyRoom.getrID()+">>");
		int rSpace = 0 ;
		UpdateHandler updateRoom = new UpdateHandler();
		boolean isUpdate = false ;
		
		if (emptyRoom != null){
			
			//System.out.println(emptyRoom.getrSpace());
			rSpace = emptyRoom.getrSpace() ;
			
			if (rSpace > 0){
				
				rSpace-- ;
				
				if (rSpace == 0){
					
					emptyRoom.setrStatus("Filled");
				}
				else{
					
					emptyRoom.setrStatus("partialFilled");
				}
				
				emptyRoom.setrSpace(rSpace);
				emptyRoom.setrID(Rid);
				isUpdate = updateRoom.addHostellerRoomID(hostellerID, Rid);
				//isUpdate = updateRoom.updateRoomStatus(emptyRoom);  // update room status in database.................
			}
			
			if(isUpdate){
				
				return updateRoom.updateRoomStatus(emptyRoom , this.hostelID);   // update hosteller table...........
			}
		}
		return false ;
	}
	
	public boolean CreateNewMessMenu(String mID , String mHostelID , List<String> morningDishes , List<String> eveningDishes){
		
		Mess newMess = new Mess();
		newMess.setmID(mID);
		newMess.setmHostelId(mHostelID);
		int itemNo = 10 ;
		for (int i = 0 ; i < 7 ; i++){
			
			String ItemName = morningDishes.get(i);
			itemNo += 1;
			newMess.getMessDishes().get(i).setItemId("I" + itemNo);
			newMess.getMessDishes().get(i).setMorningItemName(ItemName);
			ItemName = eveningDishes.get(i);
			newMess.getMessDishes().get(i).setEveningItemName(ItemName);
		}
		newMess.getMessDishes().get(0).setItemDay("Monday");
		newMess.getMessDishes().get(1).setItemDay("Tuesday");
		newMess.getMessDishes().get(2).setItemDay("Wednesday");
		newMess.getMessDishes().get(3).setItemDay("Thursday");
		newMess.getMessDishes().get(4).setItemDay("Friday");
		newMess.getMessDishes().get(5).setItemDay("Saturday");
		newMess.getMessDishes().get(6).setItemDay("Sunday");
		
		return newMess.addNewMess();
	}
	
	public boolean updateMessMenu(String mID , String mHostelID , List<String> morningDishes , List<String> eveningDishes){
		
		Mess newMess = new Mess();
		newMess.setmID(mID);
		newMess.setmHostelId(mHostelID);
		int itemNo = 10 ;
		for (int i = 0 ; i < 7 ; i++){
			
			String ItemName = morningDishes.get(i);
			itemNo += 1;
			newMess.getMessDishes().get(i).setItemId("I" + itemNo);
			newMess.getMessDishes().get(i).setMorningItemName(ItemName);
			ItemName = eveningDishes.get(i);
			newMess.getMessDishes().get(i).setEveningItemName(ItemName);
		}
		newMess.getMessDishes().get(0).setItemDay("Monday");
		newMess.getMessDishes().get(1).setItemDay("Tuesday");
		newMess.getMessDishes().get(2).setItemDay("Wednesday");
		newMess.getMessDishes().get(3).setItemDay("Thursday");
		newMess.getMessDishes().get(4).setItemDay("Friday");
		newMess.getMessDishes().get(5).setItemDay("Saturday");
		newMess.getMessDishes().get(6).setItemDay("Sunday");
		
		return newMess.updateMess();
	}
	
	public Mess ViewMessMenu(String mID){
		
		return Mess.viewMess(mID);
	}
	
	public boolean removeMessMenu(String mID , String hostelID){
		
		Mess tempMess = new Mess();
		tempMess.setmID(mID);
		tempMess.setmHostelId(hostelID);
		return tempMess.removeMess();
	}
	
	public boolean setAttendance(String [] attendanceList , String [] attenders , String attenDate){
		
		Attendance attendance = new Attendance();
		attendance.setAtteDate(attenDate);
		return attendance.addAttendance(attendanceList, attenders);
		
	}
	
	public boolean updateAttendanceStatus(String [] attendanceList , String [] attenders , String attenDate){
		
		Attendance attendance = new Attendance();
		attendance.setAtteDate(attenDate);
		return attendance.updateAttendance(attendanceList, attenders);
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHostelID() {
		return this.hostelID;
	}

	public void setHostelID(String hostelID) {
		this.hostelID = hostelID;
	}

	public String getSalary() {
		return this.salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getwID() {
		return wID;
	}

	public void setwID(String wID) {
		this.wID = wID;
	}
}
