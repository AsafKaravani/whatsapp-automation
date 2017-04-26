package boot;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import utilities.Browser;
import utilities.WhatsAppDriver;

public abstract class WhatsAppBehaviors {
	
	public WhatsAppDriver whatsapp;
	
	public void start(){
		whatsapp = new WhatsAppDriver(Browser.CHROME);
		whatsapp.open();
		try {
			whatsapp.waitForConnection();

			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}catch(TimeoutException e) {
			System.out.println("WhatsApp Web didn't connected. Aborting...");
			System.out.println(e);
		}
	}
	
	public abstract void openConversation();
	public abstract void sendMessage();
	public abstract WebElement getAnswer();
}
