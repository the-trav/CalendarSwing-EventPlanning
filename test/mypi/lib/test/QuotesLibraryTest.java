/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypi.lib.test;

import mypi.quote.QuotesLibrary;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author trav
 */
public class QuotesLibraryTest {
    
    public QuotesLibraryTest() {
    }
    
    @Test
    public void testGetQuote(){
        QuotesLibrary quotes = new QuotesLibrary();
        String testLastQuote = quotes.getQuote( quotes.getLibrarySize() -1 ); 
        assertEquals("A river cuts through a rock not because of its power but its persistence",testLastQuote);
        
        String testFirstQuote = quotes.getQuote(0);
        assertEquals("The happiest people are those who are too busy to notice wether they are or not",testFirstQuote);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
