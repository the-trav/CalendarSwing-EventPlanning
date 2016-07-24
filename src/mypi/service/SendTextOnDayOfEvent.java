package mypi.service;


/**
 *
 * @author trav
 * use http://www.emailtextmessages.com/ to see your extension onto your carrier
 * examples
 *  private String TEXT_SPRINT = "10digitphoneNumber@messaging.sprintpcs.com";
    private String TEX_ATT = "10digitphoneNumber@mobile.att.net";
 * 
 * edit STRING TEXT_VERIZON to your own number if you want to get text reminders.
 * 
 * 
 */
public class SendTextOnDayOfEvent  {
    private int sendText =1;
    private String VERIZON_NUMBER = "10digitphoneNumber@vtext.com";
    private String eventOfToday;

    private SendText textTrav;
    public SendTextOnDayOfEvent(){
        
    }
    
    public void setEventOfToday(String event){
        eventOfToday = event;
    }
    
    public void sendTextReminders (){
        if(sendText==0){
            textTrav = new SendText(VERIZON_NUMBER,eventOfToday);
            sendText = 1;
        }    
    }
        
    /**
     * primeTextToBeSent is used to allow only one text reminder in a day
     */
    public void primeTextToBeSent(){
        sendText=0;
    }
    
    
}
