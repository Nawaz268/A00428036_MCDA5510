package com.mcds5510.conn;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	static Connection connection = null;
	
	private DBConnection() {
		
	}
	
	public static Connection getDBConnection() {
		
		try {
			
			if (connection == null)
			{
				Class.forName("com.mysql.cj.jdbc.Driver");

				connection = DriverManager.getConnection("j" + 
				"dbc:mysql://localhost:3306/JavaAssignment02?useUnicode=t"
						+ "rue&useJDBCCompliantTimezoneShift=" + 
				"true&useLegacyDatet" + "imeCode=false&server"
						+ "Timezone=UTC", "root", "Arsenal20!");
			}
		
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return connection;
	}
	
}
