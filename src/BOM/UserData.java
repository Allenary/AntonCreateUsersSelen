package BOM;

import java.io.IOException;
import java.util.logging.Logger;

import utils.AppSettings;
import utils.MyLogger;

public class UserData {
	private Logger log;
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
		log = MyLogger.getLogger();
		try {
			settings = new AppSettings();
		} catch (IOException e) {
			log.severe(e.getLocalizedMessage());
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
