package utils;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {
	public static Logger getLogger() {
		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		FileHandler fileTxt;
		logger.setLevel(Level.INFO);
		try {
			fileTxt = new FileHandler("D:\\Logging.txt");
			SimpleFormatter formatterTxt = new SimpleFormatter();
			fileTxt.setFormatter(formatterTxt);
			logger.addHandler(fileTxt);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return logger;
	}

}
