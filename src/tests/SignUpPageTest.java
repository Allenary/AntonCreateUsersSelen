package tests;

import java.io.IOException;
import java.util.logging.Logger;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.AppSettings;
import utils.MyLogger;
import BOM.UserData;

import com.google.code.tempusfugit.concurrency.ConcurrentTestRunner;


@RunWith(ConcurrentTestRunner.class)
public class SignUpPageTest {
	private final static String url = "http://185.25.119.129/signup";
	static Logger log;

	@BeforeClass
	public static void init() {
		
		 log = MyLogger.getLogger("C:\\Nina\\log.txt");
	}

	protected void createAccount(WebDriver driver, WebDriverWait wait,String userName, String email, String password) {
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
	
	public void createAccounts(String prefix) throws IOException{
		String username, mail, password;
		AppSettings settings =new AppSettings(); 
		int countUsers = settings.getUsersCount();
		UserData data;
		
		log.info(prefix+"Initialization");
		
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, 15);
		driver.get(url);

		long startTime, endTime, duration;

		log.info(prefix+"creation accounts started");
		for (int i = 0; i < countUsers; i++) {
			data = new UserData(i,settings);
			username = prefix+data.getUsername();
			mail = prefix+data.getMail();
			password = data.getPassword();
			startTime = System.nanoTime();
			createAccount(driver, wait, username, mail, password);
			endTime = System.nanoTime();
			duration = (endTime - startTime) / 1000000;
			log.info("duration for "+username+": " + duration + "ms");
		}
	}

	@Test
	public void test1() throws SecurityException, IOException {
		createAccounts("test1");
	}
	@Test
	public void test2() throws SecurityException, IOException {
		createAccounts("test2");
	}
	@Test
	public void test3() throws SecurityException, IOException {
		createAccounts("test3");
	}
	@Test
	public void test4() throws SecurityException, IOException {
		createAccounts("test4");
	}
	@Test
	public void test5() throws SecurityException, IOException {
		createAccounts("test5");
	}
}
