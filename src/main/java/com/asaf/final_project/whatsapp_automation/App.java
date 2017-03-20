package com.asaf.final_project.whatsapp_automation;

import org.openqa.selenium.TimeoutException;

import utilities.Browser;
import utilities.WhatsAppDriver;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        WhatsAppDriver whatsapp = new WhatsAppDriver(Browser.CHROME);
        whatsapp.open();
        try {
			 whatsapp.waitForConnection();

			 whatsapp.openConvWith("עד מתי  2014");
			 
			 System.out.println(whatsapp.getCurrentConvImg());
			 
			 System.out.println(whatsapp.getCurrentConvName());
			 
			 
			 
		} catch (TimeoutException e) {
			System.out.println("WhatsApp Web didn't connected. Aborting...");
			System.out.println(e);
		}
    }
}
