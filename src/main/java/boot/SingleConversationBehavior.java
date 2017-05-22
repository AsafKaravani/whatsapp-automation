package boot;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.WebElement;

public class SingleConversationBehavior extends WhatsAppBehaviors {
	
	//the list of contacts we want to get their image
	List<String> contactNames;
	//save name with images
	Map<String, String> namesWithImageMap;
	
	int i;
	
	/*
	 * CTOR - initialize the global params
	 */
	public SingleConversationBehavior(){
		namesWithImageMap = new HashMap<String, String>();
		contactNames = Arrays.asList("אסף לימודים");
		i = 0;
	}
	/*
	 * (non-Javadoc)
	 * @see boot.WhatsAppBehaviors#start()
	 * gets a connection, see if there is a new message, if true, take the image  else send message to the next ccontact in the list
	 */
	@Override
	public void start(){
		super.start();
		
		while(true){
			WebElement answer = getAnswer(); 
			if(answer != null){
				answer.click();
				String answerImage = whatsapp.getCurrentConvImg();
				if(!answerImage.startsWith("data")){
					String msgName = whatsapp.getCurrentConvName(); 
					if(namesWithImageMap.get(msgName) == null){
						namesWithImageMap.put(msgName, answerImage);
						byte[] imageBytes = null;
						try {
							imageBytes = IOUtils.toByteArray(new URL(answerImage));
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String base64 = Base64.getEncoder().encodeToString(imageBytes);
					    System.out.println("base64-==="+base64);
					}
				}
			}else{
				openConversation();
				
			}
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(namesWithImageMap);
		}
	}
	/*
	 * (non-Javadoc)
	 * @see boot.WhatsAppBehaviors#openConversation()
	 * open new conversation with a contact from the list, if he already has an image - take and save it else send message
	 */
	@Override
	public void openConversation() {
		if(i < contactNames.size()){
			whatsapp.openConvWith(contactNames.get(i));
			String img = whatsapp.getCurrentConvImg();
			if(!img.startsWith("data")){
				namesWithImageMap.put(contactNames.get(i), img);
				System.out.println("answerImage==="+img);
				Image image = null;
				try {
					byte[] imageBytes = IOUtils.toByteArray(new URL(img));
					String base64 = Base64.getEncoder().encodeToString(imageBytes);
				    System.out.println("base64-==="+base64);
				} catch (IOException e) {
				}
			}else sendMessage();
			i++;
		}
	}
	/*
	 * (non-Javadoc)
	 * @see boot.WhatsAppBehaviors#sendMessage()
	 * send message to the current open conversation
	 */
	@Override
	public void sendMessage() {
		whatsapp.sendMsg("מה נשמע?");
	}
	/*
	 * (non-Javadoc)
	 * @see boot.WhatsAppBehaviors#getAnswer()
	 * checks if there is new message
	 */
	@Override
	public WebElement getAnswer() {
		return whatsapp.getNewMessageChat();
		
	}

}
