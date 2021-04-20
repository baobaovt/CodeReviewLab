package rmi;

import java.io.IOException;

import java.io.Serializable;

public class User implements Serializable {
	private String name;
	private boolean isAdmin;
	private String logCommand = "echo \"ADMIN LOGGED IN\" > /tmp/log.txt";

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAdmin() {
		return this.isAdmin;
	}

	public void setAdmin(boolean admin) {
		this.isAdmin = admin;
	}

	public String toString() {
		if (this.isAdmin()) {
			try {
				Runtime.getRuntime().exec(this.logCommand);
			} catch (IOException var2) {
				var2.printStackTrace();
			}

			return "ADMIN LOGGED IN";
		} else {
			return "USER LOGGED IN";
		}
	}
}