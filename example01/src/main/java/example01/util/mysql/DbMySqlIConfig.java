package example01.util.mysql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DbMySqlIConfig {
	private static boolean isRead = false;
	
	public static String URL = ""; // "jdbc:mysql://localhost:3316/flight_app?useSSL=false";
	public static String USER = ""; // "root";
	public static String PASSWORD = ""; // "4321";
	
	static {
		doRead();
	}
	public static void doRead() {
		if(isRead) {
			return;
		}
		
		
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	        String connectionFilePath = classLoader.getResource("connection.prop").getFile();
	        
	        FileInputStream connectionFileStream = new FileInputStream(connectionFilePath);
        	Properties connectionProperties = new Properties (); 
			connectionProperties.load (connectionFileStream);	
        
			URL = (String) connectionProperties.get ("URL"); 
			USER = (String) connectionProperties.get ("USER"); 
			PASSWORD = (String) connectionProperties.get ("PASSWORD");
			
			//System.out.println(connectionFilePath); //???
	        //System.out.println(URL); //???
	        //System.out.println(USER); //???
	        //System.out.println(PASSWORD); //???
		} catch (FileNotFoundException e) {
			e.printStackTrace(); //???
		} catch (IOException e) {
			e.printStackTrace(); //???
		} 
	    
	}
}
