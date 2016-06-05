/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypi.IO.Test;

import mypi.service.EventLinkedList;
import mypi.pojo.Events;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author trav
 */
public class EventLinkedListTest {

    Events event1, event2, event3;

    public EventLinkedListTest() {
        setUpEventObjects();
    }

    /**
     * event1 should be 1st event2 should be 2nd event3 should be last
     */
    public void setUpEventObjects() {
        String eventDescription = "Comcast $100.96";
        String month = "February";
        String day = "01";
        String year = "2016";
        event1 = new Events(eventDescription, day, month, year);

        String eventDescription2 = "gas $22.96";
        String month2 = "February";
        String day2 = "10";
        String year2 = "2016";
        event2 = new Events(eventDescription2, day2, month2, year2);

        String eventDescription3 = "phone $2.96";
        String month3 = "May";
        String day3 = "10";
        String year3 = "2016";
        event3 = new Events(eventDescription3, day3, month3, year3);
    }

    @Test
    public void testToString() {
        EventLinkedList testingList = new EventLinkedList();
        testingList.add(event1);
        assertEquals(event1.toString(), testingList.toString());
    }

    private String displayEvent2AndThree(int setEvent){
        return (setEvent==2)?event2.toString():event3.toString();
    }
    @Test
    public void testForLoop(){
        EventLinkedList testingList = new EventLinkedList();
        testingList.add(event1);
        testingList.add(event2);
        testingList.add(event3);
        int setEvent = 1;
        for(Comparable currentEvent : testingList){
            assertEquals(currentEvent.toString(), (setEvent==1)?event1.toString():displayEvent2AndThree(setEvent));
            setEvent++;
        }
    }
    private String expectedOrder() {
        StringBuilder expectedToStringOrder = new StringBuilder();
        expectedToStringOrder.append(event1.toString());
        expectedToStringOrder.append(", ");
        expectedToStringOrder.append(event2.toString());
        expectedToStringOrder.append(", ");
        expectedToStringOrder.append(event3.toString());
        return expectedToStringOrder.toString();
    }

    @Test
    public void testRemoveMiddle() {
        EventLinkedList removeMiddle = new EventLinkedList();
        removeMiddle.add(event1);
        removeMiddle.add(event2);
        removeMiddle.add(event3);
        assertEquals(expectedOrder(), removeMiddle.toString());
        removeMiddle.remove(event2.toString());
        assertEquals(event1.toString() + ", " + event3.toString(), removeMiddle.toString());
    }

    @Test
    public void testRemoveLast() {
        EventLinkedList removeLast = new EventLinkedList();
        removeLast.add(event1);
        removeLast.add(event2);
        removeLast.add(event3);
        assertEquals(expectedOrder(), removeLast.toString());
        removeLast.remove(event3.toString());
        assertEquals(event1.toString() + ", " + event2.toString(), removeLast.toString());
    }

    @Test
    public void testRemoveFirst() {
        EventLinkedList removeFirst = new EventLinkedList();
        removeFirst.add(event1);
        removeFirst.add(event2);
        removeFirst.add(event3);
        assertEquals(expectedOrder(), removeFirst.toString());
        removeFirst.remove(event1.toString());
        assertEquals(event2.toString() + ", " + event3.toString(), removeFirst.toString());

    }

    @Test
    public void testAdd() {
        EventLinkedList testList = new EventLinkedList();
        testList.add(event3);
        testList.add(event1);
        testList.add(event2);
        assertEquals(expectedOrder(), testList.toString());
        
        EventLinkedList testList2 = new EventLinkedList();
        testList2.add(event2);
        testList2.add(event3);
        testList2.add(event1);
        assertEquals(expectedOrder(), testList2.toString());

        EventLinkedList testList3 = new EventLinkedList();
        testList3.add(event1);
        testList3.add(event2);
        testList3.add(event3);
        assertEquals(expectedOrder(), testList3.toString());

    }
}
