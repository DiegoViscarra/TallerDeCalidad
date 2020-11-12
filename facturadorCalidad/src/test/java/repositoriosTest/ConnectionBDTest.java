package repositoriosTest;

import entidades.User;
import java.io.FileNotFoundException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import repositorios.ConnectionDB;

public class ConnectionBDTest {
	public ConnectionDB connection= null;
	
	@BeforeMethod
	public void initGetUserAndPassException() {
		connection = new ConnectionDB();
	}

	@Test
	public void getUserAndPassException() {
		User scanned = connection.getUserAndPassFromTXT("Test.txt");
		Assert.assertEquals(scanned.getName(), "");
		Assert.assertEquals(scanned.getPassword(), "");
		
	}
}
