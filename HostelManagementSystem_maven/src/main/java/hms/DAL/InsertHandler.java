package hms.DAL;

import java.util.ArrayList;

import hms.BLL.Hostel;
import hms.BLL.Hosteller;
import hms.BLL.Mess;
import hms.BLL.PersonProfile;
import hms.BLL.Room;
import hms.BLL.Warden;

public class InsertHandler {

	private DBConnection dbConnection ;
	
	public InsertHandler(){
		
		dbConnection = DBConnection.getInstance();
	}
	
	public boolean InsertIntoAttendance(String [] attenList , String [] attenders , String attenDate){
		
		String mySqlQuery = "";
		boolean isInserted = false;
		for(int i = 0 ; i < attenList.length;i++){
			
			mySqlQuery = "Insert into Attendance values ('" + attenDate + "' , '" + attenList[i] +
					"' , '" + attenders[i] + "' )";
			isInserted = dbConnection.ExecuteInsertQuery(mySqlQuery);
		}
		
		return isInserted ;
	}
	
	public boolean InsertIntoMess(Mess newMess){
		
		String mySqlQuery = "" ;
		mySqlQuery = "Insert into Mess values ( '" + newMess.getmID() + "' , '" + 
		             newMess.getmHostelId() + "' )";
		System.out.println("..." + newMess.getmID() + "..." + newMess.getmHostelId());
		boolean isInserted = false ;
		boolean isInserted1 = false ;
		isInserted1 = dbConnection.ExecuteInsertQuery(mySqlQuery);
		System.out.println(isInserted1+" val it is");
		if (isInserted1){
			
			mySqlQuery = "";
			for (int i = 0 ; i < (newMess.getMessDishes().size()); i++){
				
				mySqlQuery = "Insert into MessItems values ( '" + (newMess.getMessDishes().get(i).getItemId()) + 
						      "' , '" + (newMess.getMessDishes().get(i).getMorningItemName()) + "' , '" +
						      (newMess.getMessDishes().get(i).getEveningItemName()) + "' , '" + 
						      (newMess.getMessDishes().get(i).getItemDay()) + "' , '" + (newMess.getmID()) +"' )";
				
				isInserted = dbConnection.ExecuteInsertQuery(mySqlQuery);
			}
		}
		
		return isInserted ;
	}
	
	public boolean InsertIntoWarden(Warden newWarden){
		
		if (InsertIntoPerson(newWarden)){
			
			String mySqlQuery = "" ;
			mySqlQuery = "Insert into warden values ( '" + newWarden.getpID() + "' , '" + 
			newWarden.getHostelID() + "' , '" + newWarden.getDate() + "' , '" + newWarden.getSalary() + "' )" ;
			return dbConnection.ExecuteInsertQuery(mySqlQuery);
		}
		
		return false ;
	}
	
	public boolean InsertIntoHosteller(Hosteller newHosteller){
	
		if (InsertIntoPerson(newHosteller)){
			
			String mySqlQuery = "" ;
			mySqlQuery = "Insert into Hosteller values ( '" + newHosteller.getpID() + "' , '" 
					+ newHosteller.gethRdata() + "' , null )" ;
			return dbConnection.ExecuteInsertQuery(mySqlQuery);
		}
		return false ;
	}
	
	public boolean InsertIntoPerson(PersonProfile person){
		
		boolean isInserted = false ;
		String mySqlQuery = "" ;
		mySqlQuery = "Insert into PersonProfile values ( '" + person.getpID() + "' , '" +
		String.valueOf(person.getFirstName()) + "' , '" + String.valueOf(person.getLastName()) + 
		"' , '" + String.valueOf(person.getAddress()) + "' , '"  + String.valueOf(person.getE_mail()) + 
		"' , '" + person.getRole() + "' , " + person.getAge() + " , '" + person.getGender() + "' , '" + 
		String.valueOf(person.getContact()) + "' )";
		System.out.println(mySqlQuery);
		isInserted = dbConnection.ExecuteInsertQuery(mySqlQuery);
		
		if (isInserted){
			
			mySqlQuery = "" ;
			mySqlQuery = "Insert into personAccount values ( '" + person.getAcountID() + "' , '" +
			person.getAccountPassword() + "' )";
			return dbConnection.ExecuteInsertQuery(mySqlQuery);
		}
		return false ;
	}
	
	public boolean InsertIntoHostel(Hostel newHostel ){
		boolean isInserted = false ;
		
		String mySqlQuery = "" ;
		
		mySqlQuery = "insert into Hostel values ( '"+
		newHostel.getHostelID() + "' , '" + String.valueOf(newHostel.getHostelName()) + "' , '"
		+ String.valueOf(newHostel.getHostelLoc()) + "' ," +" '0' , " + "'" + newHostel.gethRegDate() + "' )";
		//System.err.println(mySqlQuery);
		isInserted = dbConnection.ExecuteInsertQuery(mySqlQuery);
		
		if (isInserted){
			mySqlQuery = "";
			for(int i = 0 ; i < (newHostel.getHostelRooms().size());i++){
				
				mySqlQuery = "Insert into room values ( '" + newHostel.getHostelRooms().get(i).getrID() + "' , '" +
				newHostel.getHostelID() + "' , '" + newHostel.getHostelRooms().get(i).getrStatus() + "' , " +
				newHostel.getHostelRooms().get(i).getrCapacity() + " , " + newHostel.getHostelRooms().get(i).getrSpace() + " )";
				
				isInserted = dbConnection.ExecuteInsertQuery(mySqlQuery);
			}
			
			return isInserted ;
		}
		
		return isInserted ;
	}
}
