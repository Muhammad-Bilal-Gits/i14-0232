package hms.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hms.BLL.Hostel;
import hms.BLL.Hosteller;
import hms.BLL.Mess;
import hms.BLL.PersonAccount;
import hms.BLL.PersonProfile;
import hms.BLL.Room;

public class SelectHandler {
	 
	private DBConnection dbConnection;
	
	 public SelectHandler() {
		 
		 dbConnection = DBConnection.getInstance();
	 }
	 
	 public ResultSet getAttendance(String Hid){
		 
		 String mySqlQuery = "" ;
		 mySqlQuery = "select * from Attendance where attenHid = '" + Hid + "'";
		 return dbConnection.ExecuteSelectQuery(mySqlQuery);
	 }
	 
	 public ResultSet getWholeAttendance(){
		 
		 String mySqlQuery = "" ;
		 mySqlQuery = "select * from Attendance ";
		 return dbConnection.ExecuteSelectQuery(mySqlQuery);
	 }
	 
	 
	 public boolean comparePassword(String pID,String oldPass){
		 String query="Select pPassword from PersonAccount where aPid='"+pID+"'";
		 ResultSet rs=dbConnection.ExecuteSelectQuery(query);
		
			 try {
				 if(rs.next())
				 {
					 if(rs.getString("pPassword").equals(oldPass)){
						 return true;
					 
					 }
					 
				 } 
			 }
			 
			 catch (SQLException e) {
				System.err.println(e);
				return false;
			}
			 return false;
		 
	 }
	 
	 public ArrayList<Hosteller> getHostellers(String hostelID){
		 
		 String mySqlQuery = "" ;
		 mySqlQuery = "select Rid from room where RHostelID = '" + hostelID + "'";
		 ResultSet rs , rs1  ;
		 String rID = "" ;
		 ArrayList<Hosteller> hostellers = new ArrayList<Hosteller>();
		 
		 rs = dbConnection.ExecuteSelectQuery(mySqlQuery);
		 
		 try{
			 
			 while(rs.next()){
				 
				 rID = rs.getString("Rid");
				 
				 mySqlQuery = "";
				 mySqlQuery = "select pID , pFirstName , pLastName from PersonProfile where pID in "
				 		+ "(select HID from hosteller where HRID = '" + rID + "' )";
				 rs1 = dbConnection.ExecuteSelectQuery(mySqlQuery);
				 
				 while(rs1.next()){
					 
					 Hosteller newHosteller = new Hosteller();
					 newHosteller.setpID(rs1.getString("pID"));
					 newHosteller.setFirstName(rs1.getString("pFirstName").toCharArray());
					 newHosteller.setLastName(rs1.getString("pLastName").toCharArray());
					 hostellers.add(newHosteller);
				 }
			}
			if (hostellers.size() > 0){
				return hostellers ;
			}
			return null ;
		 }
		 catch(SQLException e){
			 
			 System.err.println(e);
			 return null ;
		 }
	 }
	 
	 public String getHostelId(String rID){
		 
		 String mySqlQuery = "";
		 mySqlQuery = "Select RHostelID from room where rID = '" + rID + "'";
		 
		 ResultSet rs = dbConnection.ExecuteSelectQuery(mySqlQuery);
		 
		 try{
			 
			 if (rs.next()){
				 
				 return rs.getString("RHostelID");
			 }
			 
		 }
		 catch(SQLException e){
			 System.err.println(e);
			 return null ;
		 }
		 
		 return null ;
	 }
	 
	 public String getHostellerRoomID(String hID){
		 
		 String mySqlQuery = "";
		 mySqlQuery = "Select HRId from Hosteller where HID = '" + hID + "'";
		 
		 ResultSet rs = dbConnection.ExecuteSelectQuery(mySqlQuery);
		 
		 try{
			 
			 if (rs.next()){
				 
				 return rs.getString("HRID");
			 }
			 
		 }
		 catch(SQLException e){
			 System.err.println(e);
			 return null ;
		 }
		 
		 return null ;
	 }
	 
	 public String getMessMenuId(String hostelID){
		 
		 String mySqlQuery = "";
		 mySqlQuery = "Select Mid from Mess where mHostelID = '" + hostelID + "'";
		 
		 ResultSet rs = dbConnection.ExecuteSelectQuery(mySqlQuery);
		 
		 try{
			 
			 if (rs.next()){
				 
				 return rs.getString("Mid");
			 }
			 
		 }
		 catch(SQLException e){
			 System.err.println(e);
			 return null ;
		 }
		 
		 return null ;
	 }
	 
	 public Mess getMessItems(String mID){
		 
		 String mySqlQuery = "";
		 mySqlQuery = "select * from MessItems where MessID = '" + mID + "'";
		 ResultSet rs = dbConnection.ExecuteSelectQuery(mySqlQuery);
		 Mess mess = new Mess();
		 
		 try{
			 
			 int i = 0 ;
			 
			 while(rs.next()){
				 
				 mess.getMessDishes().get(i).setMorningItemName(rs.getString("itemNameMorning"));
				 mess.getMessDishes().get(i).setEveningItemName(rs.getString("itemNameEvening"));
				 i++;
			 }
			 
			 if (i == 7){
				 
				 mess.setmID(mID);
				 return mess ; 
			 }
		 }
		 catch(SQLException e){
			 System.err.println(e);
			 return null ;
		 }
		 
		 return null ;
	 }
	 
	 public boolean checkMessID(String mHostelID){
		 
		 String mySqlQuery = "" ;
		 mySqlQuery = "select Mid from Mess where mHostelID = '" + mHostelID + "'";
		 ResultSet rs = dbConnection.ExecuteSelectQuery(mySqlQuery);
		 
		 try {
			 
			if(rs.next()){
				 
				return true ;
			}
		} catch (SQLException e) {
			System.err.println(e);
			return false ;
			//e.printStackTrace();
		}
		return false ; 
	 }
	 
	 public ResultSet getWardensList(){
		 
		 String mySqlQuery = "" ;
		 mySqlQuery = "select * from personProfile where pid in ( select wid from warden where whostelID = null)";
		 return dbConnection.ExecuteSelectQuery(mySqlQuery);
	 }
	 
	 public String SelectHostelName(String hostelID){
		 
		 String mySqlQuery = "" ;
		 mySqlQuery = "select HostelName from Hostel where HostelId = '" + hostelID + "'" ;
		 ResultSet rs = dbConnection.ExecuteSelectQuery(mySqlQuery);
		 
		 try {
			if (rs.next()){
				 
				return rs.getString("HostelName");
			 }
			
		} catch (SQLException e) {
			System.err.println(e);
			return null ;
			//e.printStackTrace();
		}
		 
		return null ;
	}
	 
	 public String getWardenHostelID(String wID){
		 
		 String mySqlQuery = "" ;
		 mySqlQuery = "select whostelID from warden where wid = '"+wID +"'" ;
		 ResultSet rs = dbConnection.ExecuteSelectQuery(mySqlQuery);
		 
		 try {
			
			 if(rs.next()){
			 	
				return rs.getString("wHostelID"); 
			 }		
		 } catch (SQLException e) {
			System.err.println(e);
			return null ;
			//e.printStackTrace();
		}
		 
		return null ;
	 }
	 
	 public ResultSet getHostelRoom(String hostelID){
	 	 
	 	String mySqlQuery = "" ;
	 	mySqlQuery = "select * from room where "
	 			+ "RHostelID = '" + hostelID + "' and (RStatus = 'empty' or RStatus = 'partialFilled')" ;
	 	return dbConnection.ExecuteSelectQuery(mySqlQuery);
	 }
	 
	 public Room getHRoom(String Rid){
	 	 
		 String mySqlQuery = "" ;
		 mySqlQuery = "select * from room where "
		 		+ "Rid = '" + Rid + "' and (RStatus = 'empty' or RStatus = 'partialFilled')" ;
		 ResultSet rs = dbConnection.ExecuteSelectQuery(mySqlQuery);
		 Room room = new Room();	
		 
		 try{
		 	 
		 	if(rs.next()){
			 	
		 		room.setrID(rs.getString("Rid"));
		 		room.setrSpace(rs.getInt("RSpace"));
		 		room.setrStatus(rs.getString("RStatus"));
		 		return room ;
			}
		 }
		 catch(SQLException e){
		 	System.err.println(e);
		 	return null ;
		 }
		 return null ;
	 }
	 
	 public boolean isAttenDateExist(String attenDate){
		 
		 String mySqlQuery = "";
		 mySqlQuery = "select attenDate from Attendance where attenDate = '" + attenDate + "'";
		 ResultSet rs = dbConnection.ExecuteSelectQuery(mySqlQuery);
		 
		 try{
			 
			 if(rs.next()){
				 
				 return true ;
			 }
		 }
		 catch(SQLException e){
			 System.err.println(e);
			 return false ;
		 }
		 
		 return false ;
	 }
	 
	 public ArrayList<Object> getHostelInfo(String purpose){
		 
		 ArrayList<Object> hostelDetail = new ArrayList<Object>();
		 ArrayList<Hostel> hostels = new ArrayList<Hostel>();
		 ArrayList<Integer> hostelRooms = new ArrayList<Integer>();
		 ArrayList<Integer> totalHosteller = new ArrayList<Integer>();
		 String mySqlQuery = "" ;
		 if(purpose.equals("View")){
			 
			mySqlQuery  = "select * from Hostel ";
		 }
		 else{
			 
			 mySqlQuery = "select * from Hostel where isWardenAssigned = '0'";
		 }
		 
		 ResultSet rs1 = dbConnection.ExecuteSelectQuery(mySqlQuery);
		 
		 String hostelID = "" ;
		 Hostel tempHostel ;
		 ResultSet rs2 ;
		 
		 try {
			while(rs1.next()){
				 
				 
				 tempHostel = new Hostel();
				 hostelID = rs1.getString("HostelId");
				 tempHostel.setHostelID(hostelID);
				 tempHostel.setHostelName(rs1.getString("HostelName").toCharArray());
				 tempHostel.setHostelLoc(rs1.getString("HostelLocation").toCharArray());
				 hostels.add(tempHostel);
				 //hostelDetail.add(hostels);
				 mySqlQuery = "select count(Rid) from Room where RHostelID = '" + hostelID + "'";
				 rs2 = dbConnection.ExecuteSelectQuery(mySqlQuery);
				 
				 if (rs2.next()){
					System.err.println(rs2.getInt(1)); 
					hostelRooms.add(rs2.getInt(1)); 
				 }
				 //hostelDetail.add(hostelRooms);
				 mySqlQuery = "select count(HRID) from Hosteller where HRID in "
				 		+ "( select Rid from Room where RHostelID = '" + hostelID + "' )" ;
				 rs2 = dbConnection.ExecuteSelectQuery(mySqlQuery);
				 
				 if (rs2.next()){
					 
					 totalHosteller.add(rs2.getInt(1));
				 }
				 
				 //hostelDetail.add(totalHosteller);
			 }
		} catch (SQLException e) {
			System.err.println(e);
			//e.printStackTrace();
		}
		 hostelDetail.add(hostels);
		 hostelDetail.add(hostelRooms);
		 hostelDetail.add(totalHosteller);
		 return hostelDetail ;
	 }
	 public String SelectUserAccount(PersonAccount UserAccountToSelect, String Role) {
		 
		 String typedPassword = String.valueOf(UserAccountToSelect.getPassword());
		 
	     String mySqlQuery = "SELECT pa.* FROM personaccount pa INNER JOIN PersonProfile p ON p.pID = pa.aPid WHERE  "
	     		+"p.pID = '" + UserAccountToSelect.getpAId() + "'" + " and p.pRole = '" + Role + "'";
	     
	     ResultSet resultSet = dbConnection.ExecuteSelectQuery(mySqlQuery);
	     String PersonID = "";

	     try {
	    	 
	    	 if (resultSet == null) {
	            
	    		 return null;
	         }
	         while (resultSet.next()) {
	        	 
	            	
	        	 if (typedPassword.equals(resultSet.getString("pPassword"))) {
	        		 System.out.println("Yes");
	        		 
	        		 PersonID = resultSet.getString("aPid");
	             } 
	        	 else {
	        		 System.out.println("No");
	                    return null;
	              }
	            }
	            return PersonID;
	     } catch (SQLException ex) {
	            return null;
	     }
	     finally{
	    	
	    	 dbConnection.CloseConnection();
	     }
	 }
	 
	 public PersonProfile SelectPerson(String PersonID) {
	        
		 String _query = "SELECT * FROM personProfile WHERE pid = '" + PersonID + "'" ;
	     ResultSet resultSet = dbConnection.ExecuteSelectQuery(_query);
	     PersonProfile person = new PersonProfile();

	     try {
	    	 
	    	 if (resultSet == null) {
	    		 return person;
	         }
	    	 
	         while (resultSet.next()) {
	        	 
	        	 // Get person information from personProfile table...............
	        	 person.setpID(PersonID);
	        	 person.setFirstName(resultSet.getString("pFirstName").toCharArray());
	        	 person.setLastName(resultSet.getString("pLastName").toCharArray());
	        	 person.setAddress(resultSet.getString("pAddress").toCharArray());
	        	 person.setE_mail(resultSet.getString("pE_mail").toCharArray());
	        	 person.setAge(resultSet.getInt("pAge"));
	        	 person.setGender(resultSet.getString("pGender"));
	        	 person.setContact(resultSet.getString("pContact").toCharArray());
	         }
	         
	         return person;
	         
	    } catch (SQLException ex) {
	    	return person;
	     }
	  }
	 
	 public boolean checkIDExist(String ID){
		 
		 String mySqlQuery = "" ;
		 mySqlQuery = "select * from warden where wid = '" + ID + "'";
		 ResultSet rs = dbConnection.ExecuteSelectQuery(mySqlQuery);
		 
		 try {
			 
			if (rs.next()){
				
				return true ;
			 }
		} catch (SQLException e) {
			
			System.out.println(e);
			return false ;
			//e.printStackTrace();
		}
		 
		return false ;
	 }
	 
	 public String getLastId(String role){
		 
		 //System.out.println("RunQuery");
		 String mySqlQuery = "";
		 if (role.equals("Hostel")){
			 
			 mySqlQuery = "select HostelId from Hostel" ;
		 }
		 else if (role.equalsIgnoreCase("Mess")){
			 
			 mySqlQuery = "select Mid from Mess" ;
		 }
		 else{
			 mySqlQuery = "select pid from personProfile where pRole = '" + role + "'"; 
		 }
		 
		 //System.out.println(mySqlQuery);
		 ResultSet rs = dbConnection.ExecuteSelectQuery(mySqlQuery);
		 
		 String ID = null ;
		 if (rs != null){
			// System.out.println("DBID: " + ID);
			 try {
				 
				while(rs.next()){
					
					ID = rs.getString(1);
					
				}
				return ID ;
			} catch (SQLException e) {
				System.err.println(e);
				return null ;
				//e.printStackTrace();
			}
		 }
		 return null ;
	 }
}
