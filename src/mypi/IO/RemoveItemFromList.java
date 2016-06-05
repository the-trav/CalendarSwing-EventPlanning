/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypi.IO;

import java.io.IOException;
import java.io.ObjectOutputStream;
import mypi.CalendarApp;

/**
 *
 * @author trav
 */
public class RemoveItemFromList extends FileWriteIO{
        /**
     * method is called after removing an event from the file
     * @param eventToRemove
     */
    public void removeItem(String eventToRemove) {
        try ( ObjectOutputStream sendToFile = getObjectOutputStream() ) {
            CalendarApp.thePlannedEventList.remove(eventToRemove);
            sendToFile.writeObject(CalendarApp.thePlannedEventList);
        } catch (IOException io) {
            System.out.println("COULD NOT Write To File Error Check File path?!!");
        }
    }
}
