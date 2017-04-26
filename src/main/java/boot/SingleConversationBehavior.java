package boot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
		contactNames = Arrays.asList("עד מתי 2014","גיא גולן");
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
					if(namesWithImageMap.get(msgName) == null)
						namesWithImageMap.put(msgName, answerImage);
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
		whatsapp.sendMsg("TEST");
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
