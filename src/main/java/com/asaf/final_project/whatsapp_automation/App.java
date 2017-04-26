package com.asaf.final_project.whatsapp_automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;

import boot.SingleConversationBehavior;
import boot.WhatsAppBehaviors;
import utilities.Browser;
import utilities.WhatsAppDriver;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		WhatsAppBehaviors whatsapp = new SingleConversationBehavior();
		whatsapp.start();
	}
}
