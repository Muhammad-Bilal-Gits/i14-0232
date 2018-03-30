/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hms.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

    private String StrURL;
    private String DBName;
    private String DBConnectionDriver;
    private String DBUserName;
    private String DBPassword;
    private Connection ConObject;
    private Statement ConStatement;
    private ResultSet ConResultSet;

    protected DBConnection() {
    	
        StrURL = "jdbc:mysql://localhost:3306/";
        DBName = "HMSDB?useSSL=false";
        DBConnectionDriver = "com.mysql.jdbc.Driver";
        DBUserName = "root";
        DBPassword = "1234";
    }
    
    private static DBConnection instance = null;

    public static DBConnection getInstance() {

        if (instance == null) {

            instance = new DBConnection();
            instance.StrURL = "jdbc:mysql://localhost:3306/";
            instance.DBName = "HMSDB?useSSL=false";
            instance.DBConnectionDriver = "com.mysql.jdbc.Driver";
            instance.DBUserName = "root";
            instance.DBPassword = "1234";
        }
        return instance;
    }

    private boolean OpenConnection() {
        
    	try {
         
    		//System.out.println("Driver: "+DBConnectionDriver);
    		Class.forName(DBConnectionDriver).newInstance();
            ConObject = DriverManager.getConnection(StrURL + DBName, DBUserName, DBPassword);
            ConStatement = ConObject.createStatement();

            return true;
        } catch (SQLException | ClassNotFoundException | NullPointerException | InstantiationException | IllegalAccessException  ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ResultSet ExecuteSelectQuery(String _query) {
        
    	ResultSet resultSet;
        try {
            if (OpenConnection() == false) {
                return null;
            }
            resultSet = ConStatement.executeQuery(_query);
//            while (ResultST.next()) {
//                String id = ResultST.getString("roll");
//                String msg = ResultST.getString("name");
//                System.out.println(id + "\t" + msg);
//            }
//            int val = ConStatement.executeUpdate("INSERT into abc VALUES('20080511','hassam,')");
            return resultSet;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean ExecuteInsertQuery(String _query) {
        
    	try {
            if (OpenConnection() == false) {
                return false;
            }
            int val = ConStatement.executeUpdate(_query);//"INSERT into abc VALUES('20080511','hassam,')");
            if (val != 0) {
                return true;
            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int ExecuteInsertQueryReturnGenKey(String _query) {
        int GenKey = 0;

        try {
            if (OpenConnection() == false) {
                return GenKey;
            } else {
                ConStatement.executeUpdate(_query, Statement.RETURN_GENERATED_KEYS);
                ConResultSet = ConStatement.getGeneratedKeys();
                if (ConResultSet.next()) {
                    GenKey = ConResultSet.getInt(1);
                }
            }
            return GenKey;
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean ExecuteUpdateQuery(String _query) {
        
    	try {

            if (OpenConnection() == false) {
                return false;
            }
            int val = ConStatement.executeUpdate(_query);
            if (val != 0) {
            	//System.err.println("Hello");
                return true;
            }
        } catch (SQLException | NullPointerException ex) {
        	System.err.println("Error In Updating DataBase");
            //Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false ;
        }
    	//System.err.println("Hello");
        return false;
    }

    public boolean ExecuteDeleteQuery(String _query) {
        
    	try {
            if (OpenConnection() == false) {
                return false;
            }
            int val = ConStatement.executeUpdate(_query);
            if (val != 0) {
                return true;
            }
        } catch (SQLException | NullPointerException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void CloseConnection(){
    	
    	try {
    		if (ConResultSet != null){
    			ConResultSet.close();
    			ConStatement.close();
    			ConObject.close();
    		}
			
			
		} catch (SQLException e) {
			Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, e);
		}
    }
}
