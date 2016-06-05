/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypi.data.test;

import mypi.pojo.Events;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author trav
 */
public class EventsTest {
    Events testEvents,testEvent2;
    private String event, month, day,year,event2,month2,day2,year2;
    public EventsTest() {
        testEvents = setUp();
        testEvent2 = setUp2();
    }
    private Events setUp2(){
        event2="Travs birtday";
        month2 = "August";
        day2 ="24";
        year2="2016";
        return new Events(event2,day2,month2,year2);
    }
    private Events setUp(){
        event ="Comcast $122.96";
        month = "February";
        day="01";
        year="2016";
        return new Events(event,day,month,year);
    }
    @Test
    public void testMonth(){
        assertEquals(month, testEvents.getMonth());
    }
    
    @Test
    public void testDay(){
        assertEquals(day,testEvents.getDay() );
    }
    
    @Test
    public void testYear(){
        assertEquals(year,testEvents.getYear());
    }
    
    @Test
    public void testEvent(){
        assertEquals(event, testEvents.getEvent());
    }
    @Test
    public void testToString(){
        String expectedToString = String.format("%s is due on the %sst of %s %s", event, day, month, year);
        assertEquals(expectedToString ,testEvents.toString() );
        
        String expectedToStringOfEvent2 = String.format("%s on the %sth of %s %s", event2,day2,month2,year2);
        assertEquals(expectedToStringOfEvent2, testEvent2.toString());
    }

}
