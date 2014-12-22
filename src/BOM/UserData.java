package BOM;

import java.io.IOException;

import utils.AppSettings;

public class UserData {
	private String username;
	private String password;
	private String mail;
	private AppSettings settings;

	public UserData(int userNumber) {
		init();
		username = settings.getUserName() + userNumber;
		password = settings.getPassword();
		mail = username + "@" + settings.getMailDomain();
	}

	private void init() {
		try {
			settings = new AppSettings();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getMail() {
		return mail;
	}

}
