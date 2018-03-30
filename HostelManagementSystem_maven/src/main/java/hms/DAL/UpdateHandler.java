package hms.DAL;

import hms.BLL.Mess;
import hms.BLL.PersonProfile;
import hms.BLL.Room;

public class UpdateHandler {

	private DBConnection dbConnection ;
	
	public UpdateHandler(){
		
		dbConnection = DBConnection.getInstance();
	}
	
	public boolean updateAttendanceDB(String [] attenList , String [] attenders , String attenDate){
		
		dbConnection = DBConnection.getInstance();
		String mySqlQuery = "";
		boolean isUpdated = false;
		for(int i = 0 ; i < attenList.length;i++){
			
			mySqlQuery = "Update Attendance set attenStatus = '" + attenList[i] + "' where attenHid = '" + attenders[i] +
					"' and attenDate = '" + attenDate + "'";
			isUpdated = dbConnection.ExecuteUpdateQuery(mySqlQuery);
		}
		return isUpdated ;
	}
	
	public boolean updatePass(String pID,String newPass){
		String mySqlQuery = "" ;
				
				mySqlQuery = "update personaccount set pPassword= '" +newPass + "' where aPID ='" + pID +"'";
						
				
				return dbConnection.ExecuteUpdateQuery(mySqlQuery);
			}
	
	public boolean updateMessMenuItems(Mess newMess){
		
		String mySqlQuery = "" ;
		boolean isUpdate = false ;
		
		for (int i = 0 ; i < 7 ; i++){
			
			mySqlQuery = "update MessItems set itemNameMorning = '" + (newMess.getMessDishes().get(i).getMorningItemName())
					      + "' , itemNameEvening = '" + (newMess.getMessDishes().get(i).getEveningItemName()) + "' , itemDay = '" 
					      + (newMess.getMessDishes().get(i).getItemDay()) + "' where itemId = '" 
					      + (newMess.getMessDishes().get(i).getItemId()) + "' and MessId = '" + newMess.getmID() + "'";
			
			isUpdate = dbConnection.ExecuteUpdateQuery(mySqlQuery);
		}
		
		return isUpdate ;
	}
	
	public boolean updateWardenHostelID(String hostelID , String wid){
		
		String mySqlQuery = "" ;
		mySqlQuery = "update warden set wHostelID =  '" + hostelID + "' where wid = '" + wid + "'" ;
		return dbConnection.ExecuteUpdateQuery(mySqlQuery);
	}
	
	public boolean updateProfile(PersonProfile person){
		
		String mySqlQuery = "" ;
		mySqlQuery = "update PersonProfile set pFirstName = '" + String.valueOf(person.getFirstName()) + "' , " +
		"pLastName = '" + String.valueOf(person.getLastName()) + "' , pAddress = '" + String.valueOf(person.getAddress()) +
		"' , pE_mail = '" + String.valueOf(person.getE_mail()) + "' , pRole = '" + person.getRole() + "' , " +
		"pAge = " + person.getAge() + " , pGender = '" + person.getGender() + "' , pContact = '"
		+ String.valueOf(person.getContact()) + "' where pid = '" + person.getpID() + "'" ;
		
		return dbConnection.ExecuteUpdateQuery(mySqlQuery);	
	}
	
	public boolean updateHostelInfo(String hostelID , String hostelName){
		
		String mySqlQuery = "" ;
		mySqlQuery = "update Hostel set HostelName = '" + hostelName + "' where hostelId = '" + hostelID + "'";
		return dbConnection.ExecuteUpdateQuery(mySqlQuery);
		
	}
	
	public boolean updateRoomStatus(Room room , String rHostelID){
		
		String mySqlQuery = "" ;
		mySqlQuery = "update room set RSpace = " + room.getrSpace() + " , RStatus = '" + room.getrStatus() + "'"+
		" where Rid = '" + room.getrID() + "' and RHostelID = '" + rHostelID + "'" ;
		//System.out.println(mySqlQuery);
		return dbConnection.ExecuteUpdateQuery(mySqlQuery);
	}
	
	public boolean addHostellerRoomID(String hostellerID , String Rid){
		
		String mySqlQuery = "" ;
		mySqlQuery = "update Hosteller set HRID =  '" + Rid +"' where HID = '" + hostellerID + "' and HRID is null";
		return dbConnection.ExecuteUpdateQuery(mySqlQuery);
		
	}
	
	public boolean UpdateHostelStatus(String hostelID , char update){
		
		String mySqlQuery = "" ;
		
		mySqlQuery = "update hostel set isWardenAssigned = '" + update + "' where hostelID = "
				+ "'" + hostelID + "'";
		System.err.println(mySqlQuery);
		return dbConnection.ExecuteUpdateQuery(mySqlQuery);
	}
}
