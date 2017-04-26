package boot;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;

public class SingleConversationBehavior extends WhatsAppBehaviors {
	
	List<String> contactNames = Arrays.asList("עד מתי 2014","ספוש","אדמי - כדי שנפסיק להתבלבל");
	int i = 0;
	
	@Override
	public void start(){
		super.start();
		
		while(true){
			WebElement answer = getAnswer(); 
			if(answer != null){
				answer.click();
				String answerImage = whatsapp.getCurrentConvImg();
				System.out.println("answerImage==="+answerImage);
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
	
	@Override
	public void openConversation() {
		if(i < contactNames.size()){
			System.out.println("contactNames.get(i)="+contactNames.get(i));
			whatsapp.openConvWith(contactNames.get(i));
			sendMessage();
			i++;
		}
	}

	@Override
	public void sendMessage() {
		whatsapp.sendMsg("TEST");
	}

	@Override
	public WebElement getAnswer() {
		// TODO Auto-generated method stub
		WebElement answer = whatsapp.getNewMessageChat();
		return answer;
	}

}
