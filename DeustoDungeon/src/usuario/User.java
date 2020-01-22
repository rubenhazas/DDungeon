package usuario;

public class User {

	public String user;
	public String pass;
	public boolean admin;

	public User() {
		user = "";
		pass = "";
		admin = false;
	}

	public User(String user, String pass, boolean admin) {
		this.user = user;
		this.pass = pass;
		this.admin = admin;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
