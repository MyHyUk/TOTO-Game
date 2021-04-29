package model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPool {
	

	public static Connection con;
	
	private String url = "jdbc:oracle:thin:@211.54.208.143:1521:khu";
	private String user = "toto";
	private String pass = "pass";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	
	public ConnectionPool() throws Exception{
	   
		Class.forName(driver);
		con = DriverManager.getConnection(url, user, pass);
   }
   
	public static Connection getConnection() throws Exception {
		if (con == null) {
			ConnectionPool cp = new ConnectionPool();
		}
		return con;
	}

}
