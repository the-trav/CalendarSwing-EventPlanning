/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypi.service;

import mypi.service.SendText;

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

    private SendText textTrav;
    public SendTextOnDayOfEvent(){
        
    }

    
   /**
    * 
    * @param eventOfToday is the event that is planned that is about to be texted to you 
    */
    public SendTextOnDayOfEvent(String eventOfToday){
        textTrav = new SendText(VERIZON_NUMBER,eventOfToday);
    }
    
    public void sendTextReminders (){
        if(sendText==0){
            textTrav.sendText();
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
