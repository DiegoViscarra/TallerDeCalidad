package repositorios;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

import entidades.User;

public class ConnectionDB  {

	private static final String CONNETTION_DB = "jdbc:sqlite:dbSQL.db;";
	private static String rutaCarpeta = Paths.get(".").toAbsolutePath().normalize().toString();

	public String getConnection() {
		 User user = getUserAndPassFromTXT();
		String CONNETTION_DB_WHIT_USER = CONNETTION_DB + "user=" + user.getName() + "&password=" + user.getPassword();
		return CONNETTION_DB_WHIT_USER;
	}
	
	
	private User getUserAndPassFromTXT() {
		try {
			File file = new File(rutaCarpeta+"\\Users.txt");
			try (Scanner input = new Scanner(file);){
				input.useDelimiter("-|\n");
			    String nombre = "";
			    String password = "";
			    nombre = input.next();
			    nombre = input.next();
			    input.close();
			    User user = new User(nombre, password);
	            return user;
			}
			
		}
			catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new User("","");
		}	
		
	}
	
}
