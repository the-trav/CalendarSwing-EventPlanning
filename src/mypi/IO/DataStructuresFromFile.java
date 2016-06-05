package mypi.IO;

import mypi.service.EventLinkedList;
import java.io.IOException;
import java.io.ObjectInputStream;


/**
 *
 * @author trav this class reads from the file specified within FileReadIO
 * provides an eventLinkedList of what is in the File
 */
public class DataStructuresFromFile extends FileReadIO {

    public EventLinkedList loadEventsFromFile() throws NullPointerException {
        EventLinkedList eventList = null;
        try( ObjectInputStream inputObject = getObjectInputStream() ){
            eventList = (EventLinkedList) inputObject.readObject();
            inputObject.close();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("List is loaded");
        }
        return eventList;
    }

}
