package entidades;

public class User {
	private String name, password;
	
	public User(String name, String password){
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passeaords) {
		this.password = passeaords;
	}
	
}	
