package tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.AppSettings;
import utils.MyLogger;
import utils.Parallelized;
import BOM.UserData;

@RunWith(Parallelized.class)
public class SignUpPageTest {

	private final static String url = "http://185.25.119.129/signup";
	private static WebDriver driver;
	static WebDriverWait wait;
	static Logger log;
	private String username;
	private String password;
	private String email;

	public SignUpPageTest(String username, String password, String email) {
		this.email = email;
		this.password = password;
		this.username = username;
	}

	@BeforeClass
	public static void init() {
		log = MyLogger.getLogger();
		log.info("Initialization");
	}

	@Parameters
	public static Collection<String[]> getParams() {
		int count = 0;
		try {
			count = new AppSettings().getUsersCount();
		} catch (IOException e) {
			e.printStackTrace();
		}
		UserData data;
		String[][] params = new String[count][3];
		for (int i = 0; i < count; i++) {
			data = new UserData(i);
			params[i][0] = data.getUsername();
			params[i][1] = data.getPassword();
			params[i][2] = data.getMail();
		}
		return Arrays.asList(params);

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
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 15);
		driver.get(url);

		long startTime, endTime, duration;

		log.info("creation accounts started");
		log.info("creating account for " + username);
		startTime = System.nanoTime();
		createAccount(username, email, password);
		endTime = System.nanoTime();
		duration = (endTime - startTime) / 1000000;
		log.info("duration: " + duration + "ms for account " + username);
		driver.close();

	}
}
