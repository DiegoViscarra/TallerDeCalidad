package repositorios;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

import entidades.User;

public class ConnectionDB  {

	private static final String CONNETTION_DB = "jdbc:sqlite:dbSQL.db;";
	private static String rutaCarpeta = Paths.get(".").toAbsolutePath().normalize().toString();

	public String getConnection(String archivo) {
		 User user = getUserAndPassFromTXT(archivo);
		String CONNETTION_DB_WHIT_USER = CONNETTION_DB + "user=" + user.getName() + "&password=" + user.getPassword();
		return CONNETTION_DB_WHIT_USER;
	}
	
	
	public User getUserAndPassFromTXT(String archivo) {
		try {
			File file = new File(rutaCarpeta+"\\"+ archivo);
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
			return new User("","");
		}	
		
	}
	
}
