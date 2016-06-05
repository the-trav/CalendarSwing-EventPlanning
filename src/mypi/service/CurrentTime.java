package mypi.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author trav
 * <CurrentTime> class is used to grab the exact time when called. in 12 hour format
 * EXAMPLE -> 1:12 AM 
 */
public class CurrentTime {

    public CurrentTime(){
        
    }
    
    public String getTime() {
        String currentTime;
        currentTime = setTime(new Date());
        return currentTime;
    }


    private String setTime(Date currentDate){
        DateFormat formatDate = new SimpleDateFormat("h:mm a");
        return formatDate.format(currentDate);
    }
    

}
