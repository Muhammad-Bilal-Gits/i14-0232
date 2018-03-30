package hms.DAL;

public class DeleteHandler {
	
	private DBConnection dbConnection ;
	
	public DeleteHandler(){
		
		dbConnection = DBConnection.getInstance();
	}
	
	public boolean DeleteMessItems(String mID , String mHostelID){
		
		String mySqlQuery = "" ;
		int itemId = 10 ;
		String itemID = "I" ;
		boolean isDelete = false ;
		
		for (int i = 0 ; i < 7 ; i++){
			
			itemId += 1 ;
			itemID += itemId ;
			mySqlQuery = "delete from MessItems where itemID = '" + itemID +  "' and MessID = '" + mID +"'";
			isDelete = dbConnection.ExecuteDeleteQuery(mySqlQuery);
			itemID = "I";
		}
		
		if (isDelete){
			
			mySqlQuery = "";
			mySqlQuery = "delete from Mess where Mid = '" + mID + "' and mHostelID = '" + mHostelID + "'";
			
			isDelete = dbConnection.ExecuteDeleteQuery(mySqlQuery);
		}
		return isDelete ;
	}

}
