package test;
import java.sql.Connection;

import database.DBConnection;
import database.DataAccessException;


	public class TestDBConnection {
	    public static void main(String[] args) {
	        // Get a DBConnection instance
	        DBConnection dbConnection = null;
			try {
				dbConnection = DBConnection.getInstance();
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        // Get a database connection
	        Connection con = dbConnection.getConnection();
	        if (con != null) {
	        	System.out.println("success");
	        }
	    }
	}



