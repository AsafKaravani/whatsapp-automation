package com.asaf.final_project.whatsapp_automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;

import utilities.Browser;
import utilities.WhatsAppDriver;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		WhatsAppDriver whatsapp = new WhatsAppDriver(Browser.CHROME);
		whatsapp.open();
		try {
			whatsapp.waitForConnection();

			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			whatsapp.openConvWith("עד מתי 2014");

			System.out.println(whatsapp.getCurrentConvImg());

			System.out.println(whatsapp.getCurrentConvName());
			
			for (int i = 0; i < 100; i++) {
				whatsapp.sendMsg("DDoS");
			}

//			whatsapp.sendMsg("❤❤❤❤❤\n🖤🖤❤🖤🖤\n🖤🖤❤🖤🖤\n🖤🖤❤🖤🖤\n❤❤❤❤❤");
//			
//			whatsapp.sendMsg("❤");
//			
//			whatsapp.sendMsg("❤🖤🖤🖤❤\n❤🖤🖤🖤❤\n❤🖤🖤🖤❤\n❤🖤🖤🖤❤\n🖤❤❤❤🖤");


		} catch (TimeoutException e) {
			System.out.println("WhatsApp Web didn't connected. Aborting...");
			System.out.println(e);
		}
	}
}
