package entidadesTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import entidades.User;


public class UserTest {
	private User testUser;
	@BeforeClass
	public void init() {
		this.testUser = new User("User","Password");
	}
	
	@Test
	public void userNameTest() {
		testUser.setName("User1");
		Assert.assertEquals("User1", testUser.getName());
		
	}
	
	@Test
	public void userPasswordTest() {
		testUser.setPassword("Password1");
		Assert.assertEquals("Password1", testUser.getPassword());
	}

}
