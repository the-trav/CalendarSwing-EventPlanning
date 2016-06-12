/**
 * this test ensures the sorted integrity of EventLinkedList.java
 * while testing various of methods including adding events in various of orders
 * removing events in various of orders,
 * This test class will also ensure Iterator method was written properly 
 */
package mypi.service.Test;
import java.io.File;
import mypi.service.EventLinkedList;
import mypi.pojo.Events;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author trav
 */
public class EventLinkedListTest {

    Events event1, event2, event3;
    EventLinkedList testingList;

    public EventLinkedListTest() {
        setUpEventObjects();
        testingList = EventLinkedList.instanceOf();
    }

    /**
     * will throw nullPointer Exception when attempting to remove an element not in the list
     */
    private void removeAllElementsInList(){
        try{
        testingList.remove(event1.toString());
        testingList.remove(event2.toString());
        testingList.remove(event3.toString());
        }
        catch(NullPointerException npe){
            
        }
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
    
    @Before
    public void changesToBeMade(){
        assertEquals("comment-out FILENAME and un comment TEST_FILE in FileReadAndWrite.java", new File("Testing_Events_POJO.txt"), testingList.getFile());
    }

    @Test
    public void testToString() {
        removeAllElementsInList();
        testingList.add(event1);
        assertEquals(event1.toString(), testingList.toString());
    }

    private String displayEvent2AndThree(int setEvent){
        return (setEvent==2)?event2.toString():event3.toString();
    }
    @Test
    public void testForLoop(){
        removeAllElementsInList();
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
        removeAllElementsInList();
        testingList.add(event1);
        testingList.add(event2);
        testingList.add(event3);
        assertEquals(expectedOrder(), testingList.toString());
        testingList.remove(event2.toString());
        assertEquals(event1.toString() + ", " + event3.toString(), testingList.toString());
    }

    @Test
    public void testRemoveLast() {
        removeAllElementsInList();
        testingList.add(event1);
        testingList.add(event2);
        testingList.add(event3);
        assertEquals(expectedOrder(), testingList.toString());
        testingList.remove(event3.toString());
        assertEquals(event1.toString() + ", " + event2.toString(), testingList.toString());
    }

    @Test
    public void testRemoveFirst() {
        removeAllElementsInList();
        testingList.add(event1);
        testingList.add(event2);
        testingList.add(event3);
        assertEquals(expectedOrder(), testingList.toString());
        testingList.remove(event1.toString());
        assertEquals(event2.toString() + ", " + event3.toString(), testingList.toString());

    }

    @Test
    public void testAdd() {
        removeAllElementsInList();
        testingList.add(event3);
        testingList.add(event1);
        testingList.add(event2);
        assertEquals(expectedOrder(), testingList.toString());

        removeAllElementsInList();
        testingList.add(event2);
        testingList.add(event3);
        testingList.add(event1);
        assertEquals(expectedOrder(), testingList.toString());

        removeAllElementsInList();
        testingList.add(event1);
        testingList.add(event2);
        testingList.add(event3);
        assertEquals(expectedOrder(), testingList.toString());

    }
    
    @After
    public void removeAllElements(){
        removeAllElementsInList();
    }
}
