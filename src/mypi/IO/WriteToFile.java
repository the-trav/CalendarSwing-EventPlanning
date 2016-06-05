package mypi.IO;

import java.io.IOException;
import java.io.ObjectOutputStream;
import mypi.CalendarApp;
import mypi.pojo.Events;
import mypi.service.EventLinkedList;

/**
 *
 * Class is used to write into the correct file
 */
public class WriteToFile extends FileWriteIO {

    /**
     * method is called to add and write the list into the file
     * @param eventToAdd 
     */
    public void writeEventToFile(Events eventToAdd) {
        try(ObjectOutputStream sendToFile = getObjectOutputStream() ){
            if(CalendarApp.thePlannedEventList==null){//handles if the list is null
                CalendarApp.thePlannedEventList = new EventLinkedList();
            }
            CalendarApp.thePlannedEventList.add(eventToAdd);
            sendToFile.writeObject(CalendarApp.thePlannedEventList);
            sendToFile.close();
        } catch (IOException io) {
            System.out.println("COULD NOT Write To File Error Check File path?!!");
        }
    }


}
