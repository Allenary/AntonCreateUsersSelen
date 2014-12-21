package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppSettings {
	private InputStream input = null;
	private Properties prop;
	private final String propFileName = "config.properties";

	public AppSettings() throws IOException {
		prop = new Properties();
		input = getClass().getClassLoader().getResourceAsStream(propFileName);
		prop.load(input);
	}

	public int getUsersCount() {
		return Integer.parseInt(prop.getProperty("count_accounts"));
	}

	public String getUserName() {
		return prop.getProperty("username");
	}

	public String getMailDomain() {
		return prop.getProperty("mail_domain");
	}

	public String getPassword() {
		return prop.getProperty("password");
	}

}
