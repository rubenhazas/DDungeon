package usuario;

public class User {

	public String user;
	public String pass;
	public int admin;

	public User() {
		user = "";
		pass = "";
		admin = 0;
	}

	public User(String user, String pass, int admin) {
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

	public int isAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}
}
