package tests;

import java.io.IOException;
import java.util.logging.Logger;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import BOM.UserData;
import utils.AppSettings;
import utils.MyLogger;

public class SignUpPageTest {

	private final static String url = "http://185.25.119.129/signup";
	private static WebDriver driver;
	static WebDriverWait wait;
	static Logger log;

	@BeforeClass
	public static void init() {
		log = MyLogger.getLogger("C:\\Nina\\log.txt");
		log.info("Initialization");
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 15);
		driver.get(url);

	}

	protected void createAccount(String userName, String email, String password) {
		// first screen
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")))
				.sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("passconf")).sendKeys(password);
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("terms")).click();
		driver.findElement(By.id("submit")).click();
		// second screen
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("1_1_3")))
				.sendKeys("firstName");
		driver.findElement(By.id("1_1_4")).sendKeys("lastName");
		driver.findElement(By.id("submit")).click();
		// third screen
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.id("skiplink")))
				.click();
		// logout
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By
						.className("core_mini_auth"))).click();
		// create new account
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By
						.className("core_mini_signup"))).click();
	}

	@Test
	public void test() throws SecurityException, IOException {
		String username, mail, password;
		int countUsers = new AppSettings().getUsersCount();
		UserData data;

		long startTime, endTime, duration;

		log.info("creation accounts started");
		for (int i = 0; i < countUsers; i++) {
			data = new UserData(i);
			username = data.getUsername();
			mail = data.getMail();
			password = data.getPassword();
			log.info("creating account for " + username);
			startTime = System.nanoTime();
			createAccount(username, mail, password);
			endTime = System.nanoTime();
			duration = (endTime - startTime) / 1000000;
			log.info("duration: " + duration + "ms");
		}

	}
}
