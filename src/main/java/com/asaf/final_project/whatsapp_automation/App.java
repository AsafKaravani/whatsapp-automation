package com.asaf.final_project.whatsapp_automation;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		// WhatsAppBehaviors whatsapp = new SingleConversationBehavior();
		// whatsapp.start();

		
		try {
			JSONObject json = new JSONObject();
			json.put("msg", "this is my time");
			json.put("currentTime", System.currentTimeMillis());
			final Socket socket = IO.socket("http://localhost:3000");
			socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

				public void call(Object... args) {
//					socket.disconnect();
				}

			}).on("event", new Emitter.Listener() {

				public void call(Object... args) {
					JSONObject data = new JSONObject(args[0]);
					System.out.println(data.toString());
				}

			}).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

				public void call(Object... args) {
				}

			});
			socket.connect();

			socket.emit("event", json);
			
			Scanner scaner = new Scanner(System.in);
			System.out.println("Enter to exit.");
			scaner.nextLine();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
