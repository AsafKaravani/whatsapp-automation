package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * The {@link WhatsAppDriver} class is and API for taking actions in the WhatsApp Web client. 
 * 
 * @author Asaf Karavani
 *
 */
public class WhatsAppDriver {

	WebDriver driver;
	Wait<WebDriver> wait;
	
	boolean connected;

	/**
	 * 
	 * @param browser takes a {@link Browser} Enumeration to determine which browser to use (e.g. {@link Browser.CHROME}, {@link Browser.FIREFOX}, {@link Browser.PHANTOMJS}, etc.)
	 */
	public WhatsAppDriver(Browser browser) {
		if (browser == Browser.CHROME) {
			driver = new ChromeDriver();
		} else if (browser == Browser.FIREFOX) {
			driver = new FirefoxDriver();
		} else if (browser == Browser.PHANTOMJS) {
			driver = new PhantomJSDriver();
		} else {
			// Default Browser
			driver = new ChromeDriver();
		}
		
		wait = new WebDriverWait(driver, 30);

		connected = false;
	}
	
	/**
	 * Will open the WhatApp Web client.
	 */
	public void open() {
		driver.get("https://web.whatsapp.com/");

	}
	
	public void close() {
		driver.close();
		
	}
	
	public void quit() {
		driver.quit();

	}
	
	public void openConvWith(String contactName) {
		WebElement searchBar = driver.findElement(By.cssSelector(".input.input-search"));
		searchBar.sendKeys(contactName);
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".icon.icon-x-alt")));
		WebElement conv = driver.findElement(By.cssSelector("#pane-side > div > div > div > div:nth-child(2)"));
		conv.click();
		
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("incoming-msgs")));
			driver.findElement(By.className("incoming-msgs")).click();
		} catch (Exception e) {
			System.out.println("Can't find Scrolldown button.");
		}
		
	}
	
	public void waitForConnection() throws TimeoutException{
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className("intro-title")));
			//TimeUnit.SECONDS.sleep(3);
			connected = true;
			System.out.println("Connected.");
			
		} catch (TimeoutException t) {		
			connected = false;
			throw t;
		}
		
		
	}
	
	public String getCurrentConvImg() {
		By byConvImg = By.xpath("//*[@id=\"main\"]/header/div[1]/div/div/img");
		WebElement img = driver.findElement(byConvImg);
		return img.getAttribute("src").replaceFirst("t=s", "t=l");

	}
	
	public String getCurrentConvName() {
		
		WebElement chatHeader = driver.findElement(By.cssSelector(".pane-header.pane-chat-header"));
		WebElement title = chatHeader.findElement(By.className("chat-title"));
		WebElement titleText = title.findElement(By.cssSelector(".emojitext.ellipsify"));
		return titleText.getAttribute("title");

	}
	
	public void sendMsg(String msg) {
		
		
	}
	
	
}
