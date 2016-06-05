/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypi.data.test;

import mypi.service.CurrentTime;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author trav
 */
public class CurrentTimeTest {
    
    public CurrentTimeTest() {
    }

    @Test
    public void testGetTime(){
        CurrentTime theCurrentTimeNow = new CurrentTime();
        String theTimeAtTest = "7:18 PM";
        assertEquals("You will have to update <theTimeAtTest> when running", theTimeAtTest, theCurrentTimeNow.getTime());
    
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
