package hms.DAL;

import com.mysql.jdbc.Statement;

public class InitiateDataBase {

private DBConnection dbConnection ;
	
	public InitiateDataBase(){
		
		dbConnection = DBConnection.getInstance();
	}
	
	public void Initialize(){
		
		String mySqlQuery = "Drop table if exists personaccount;";
		dbConnection.ExecuteDeleteQuery(mySqlQuery);
		
		mySqlQuery = "Drop table if exists personprofile;";
		dbConnection.ExecuteDeleteQuery(mySqlQuery);
		
		mySqlQuery = "Drop table if exists personaccount;";
		dbConnection.ExecuteDeleteQuery(mySqlQuery);
		
		mySqlQuery = "Drop table if exists Hostel;";
		dbConnection.ExecuteDeleteQuery(mySqlQuery);
		
		mySqlQuery = "Drop table if exists room;";
		dbConnection.ExecuteDeleteQuery(mySqlQuery);
		
		mySqlQuery = "Drop table if exists warden;";
		dbConnection.ExecuteDeleteQuery(mySqlQuery);
		
		mySqlQuery = "Drop table if exists personaccount;";
		dbConnection.ExecuteDeleteQuery(mySqlQuery);
		
	}
}
