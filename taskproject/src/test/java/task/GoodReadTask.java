package task;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class GoodReadTask {
	WebDriver driver;

	public static WebDriverWait wait;

	@BeforeSuite
	public void start()
	{
		System.out.println("UI Automation");
	}

	@BeforeTest
	public void setup()
	{
		System.out.println("Testing Started......");

	}

	@BeforeClass
	public void launch() {
		System.out.println("Launching Browser......");
	}

	@BeforeMethod
	public void BrowserLauch() throws IOException
	{

		WebDriverManager.chromedriver().setup();
		driver  = new ChromeDriver();
		driver.get("https://www.goodreads.com/");
		driver.manage().window().maximize();
	}

	@Test
	public void Task() {
		//1.User login using username and password
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(60));
			WebElement sign_In = driver.findElement(By.linkText("Sign In"));
			wait.until(ExpectedConditions.visibilityOf(sign_In));
			sign_In.click();
			WebElement sign_Email = driver.findElement(By.xpath("//button[contains(text(),'Sign in with email')]"));
			wait.until(ExpectedConditions.visibilityOf(sign_Email));
			sign_Email.click();	
			WebElement email = driver.findElement(By.id("ap_email"));
			wait.until(ExpectedConditions.visibilityOf(email));	
			email.sendKeys("tsvaishali5@gmail.com");
			WebElement password = driver.findElement(By.id("ap_password"));
			password.sendKeys("Vaishali@30");
			WebElement sign_In_Button = driver.findElement(By.id("signInSubmit"));	
			sign_In_Button.click();
			WebElement good_reads_Title = driver.findElement(By.xpath("//a[@title='Goodreads Home']"));
			wait.until(ExpectedConditions.visibilityOf(good_reads_Title));
			if(good_reads_Title.isDisplayed()==true) {
				System.out.println("User successfully logged into the GoodReads website");
			}
			else {
				System.out.println("User didn't logged into the GoodReads website");
			}
		}
		catch (Exception e) {
			System.out.println("Some Error occurred while login to the GoodReads Website........");
		}

		//2.User search for a specific book title and 
		try {
			WebElement search_Bar = driver.findElement(By.xpath("//input[@placeholder='Search books']"));
			search_Bar.sendKeys("Harry Potter and the Philosopher’s Stone (Harry Potter, #1)");
			WebElement search_Icon = driver.findElement(By.xpath("//button[@type='submit']"));
			wait.until(ExpectedConditions.visibilityOf(search_Icon));
			search_Icon.click();
			List<WebElement> search_Results = driver.findElements(By.xpath("//tbody/tr/td[2]/a/span[contains(text(),'Harry')]"));
			List<String> li = new ArrayList<String>();
			for (WebElement webElement : search_Results) {
				String Titles = webElement.getText();
				li.add(Titles);
			}
			if(li.contains("Harry Potter and the Philosopher’s Stone (Harry Potter, #1)")==true) {
				System.out.println("Searched book is displayed");
			}
			else {
				System.out.println("Searched book is not displayed");
			}

		}
		catch (Exception e) {
			System.out.println("Eroor occured while searching the book");
		}

		//3.Users marks the searched book as "want to read"

		try {
			WebElement want_To_Read_Button = driver.findElement(By.className("wtrToRead"));
			want_To_Read_Button.click();
			System.out.println("User clicks want to read button");
			WebElement saving = driver.findElement(By.className("progressIndicator"));
			wait.until(ExpectedConditions.invisibilityOf(saving));

		}
		catch (Exception e) {
			System.out.println("Error occurred while clicking want to read button");
		}

		//4.user remove the selected book from my book list
		try {
			WebElement my_Books = driver.findElement(By.linkText("My Books"));
			wait.until(ExpectedConditions.elementToBeClickable(my_Books));
			my_Books.click();
			WebElement cancel_btn = driver.findElement(By.xpath("//img[@title='Remove from my books']"));
			cancel_btn.click();
			driver.switchTo().alert().accept();
			System.out.println("user removes the book from my books list");
			Thread.sleep(5000);
			WebElement notification = driver.findElement(By.id("flashContainer"));
			if(notification.isDisplayed()==true) {
				System.out.println("Notification is displayed on removal of boook");
			}
			else {
				System.out.println("Notification is displayed on removal of book");
			}
		}
		catch (Exception e) {
			System.out.println("Error occur while removig the book from my book list");
		}
		
		//5.User logouts
		try {
			WebElement profile_Icon = driver.findElement(By.xpath("(//img[@alt='Vaish'])[1]"));
			profile_Icon.click();
			WebElement sign_Out_Button = driver.findElement(By.xpath("(//li[@aria-label='Sign out'])[1]"));
			sign_Out_Button.click();
			System.out.println("User log outs the application");
		}
		catch (Exception e) {
			System.out.println("Error occurred while logging out the application");
		}
	}


	@AfterMethod
	public void end() {
		System.out.println("Testing Ended......");

	}

	@AfterClass
	public void close() {
		System.out.println("Closing the browser......");
	}

	@AfterTest
	public void quit() {
		driver.quit();

	}
	
	@AfterSuite
	public void teardown() {
		System.out.println("UI Automation Ended");
	}
	


}
