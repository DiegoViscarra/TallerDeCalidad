package repositorios;

import java.io.*;
import java.util.Scanner;

public class ConnectionDB  {

	private static final String CONNETTION_DB = "jdbc:sqlite:dbSQL.db;";
	
	public String getConnection() {
		String [] user1 = getUserAndPassFromTXT();
		String CONNETTION_DB_WHIT_USER = CONNETTION_DB + "user=" + user1[0] + "&password=" + user1[1];
		return CONNETTION_DB_WHIT_USER;
	}
	
	
	private String [] getUserAndPassFromTXT() {
		 try {
		      File myObj = new File("Users.txt");
		      Scanner myReader = new Scanner(myObj);
		        String data = myReader.nextLine();
		      myReader.close();
		      return data.split("\\s+");
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return null;
	}
	
}
