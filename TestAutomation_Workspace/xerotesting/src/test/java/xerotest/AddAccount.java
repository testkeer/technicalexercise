package xerotest;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AddAccount {

	private static ChromeDriver driver;
	String url = "https://xero.com";
	String accountName = RandomStringUtils.randomAlphabetic(8);
	
	@Given("open the browser")
	public void open_the_browser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@And("the user logs into the xero organisation")
	public void the_user_logs_into_the_xero_organisation() {
		driver.navigate().to(url);
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Log in")).click();
		driver.findElement(By.name("Username")).sendKeys("testingqa787@gmail.com");
		driver.findElement(By.name("Password")).sendKeys("Test@123");
		driver.findElement(By.id("xl-form-submit")).click();
		driver.findElement(By.xpath("//button[text()='Use a backup method instead']")).click();
		driver.findElement(By.xpath("//button[text()='Security questions']")).click();
		
		String firstQues = driver.findElement(By.xpath("//label[@class='auth-firstquestion xui-text-label xui-fieldlabel-layout']")).getText();
	
		if (firstQues.equals("What was the name of your first pet?"))
		{
			driver.findElement(By.xpath("//div[@data-automationid='auth-firstanswer']/input")).sendKeys("Tommy");
		}
			else if (firstQues.equals("What street did you live on when you were 10 years old?"))
		{
				driver.findElement(By.xpath("//div[@data-automationid='auth-firstanswer']/input")).sendKeys("yousufguda");
				
			}
			else {
				driver.findElement(By.xpath("//div[@data-automationid='auth-firstanswer']/input")).sendKeys("doctor");
				
			}
		
		
		String secondQues = driver.findElement(By.xpath("//label[@class='auth-secondquestion xui-text-label xui-fieldlabel-layout']")).getText();
	
		if (secondQues.equals("What was the name of your first pet?")) {
		driver.findElement(By.xpath("//div[@data-automationid='auth-secondanswer']/input")).sendKeys("Tommy");
	}
		else if (secondQues.equals("What street did you live on when you were 10 years old?")) {
			driver.findElement(By.xpath("//div[@data-automationid='auth-secondanswer']/input")).sendKeys("yousufguda");
		}
		else {
			driver.findElement(By.xpath("//div[@data-automationid='auth-secondanswer']/input")).sendKeys("doctor");
		}
	driver.findElement(By.xpath("//button[text()='Confirm']")).click();
	}

	@And("the user is in the xero organisation dashboard page")
	public void the_user_is_in_the_xero_organisation_dashboard_page() {
		//driver.manage().timeouts().implicitlyWait(3000);
		WebElement accounting = driver.findElement(By.xpath("//button[text()='Accounting']"));
		Assert.assertTrue(accounting.isDisplayed());
		}

	@When("the user adds Bank Account details in Bank Account page")
	public void add_bank_account() {
		driver.findElement(By.xpath("//button[text()='Accounting']")).click();
		driver.findElement(By.xpath("//a[text()='Bank accounts']")).click();
		driver.findElement(By.xpath("//span[text()='Add Bank Account']")).click();
		driver.findElement(By.xpath("//span[text()='ANZ (NZ)']")).click();
		driver.findElement(By.id("accountname-1025-inputEl")).sendKeys(accountName);
		driver.findElement(By.xpath("//div[@id='accounttype-1027-triggerWrap']")).click();
		driver.findElement(By.xpath("//li[text()='Other']")).click();
		driver.findElement(By.id("accountnumber-1056-inputEl")).sendKeys("12345678");
		driver.findElement(By.xpath("//span[text()='Continue']")).click();
	}

	@Then("the bank account details are added successfully")
	public void added_bank_details() {
		WebElement message = driver.findElement(By.xpath("//div[@class='message']/p"));
		System.out.println(message.getText());
		Assert.assertTrue(message.getText().contains("added. Xero works better with your transactions. Get started by manually importing your bank transactions."));
	}
	
	@And("^logout from the application and close the browser$")
	public void logout_from_the_application_and_close_the_browser() throws Exception {
	   driver.findElement(By.xpath("//button//*[contains(@class,'avatar')]")).click();
	   driver.findElement(By.linkText("Log out")).click();
	   driver.quit();
	}
}
	