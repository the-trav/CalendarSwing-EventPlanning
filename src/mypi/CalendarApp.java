
package mypi;

import mypi.service.SendTextOnDayOfEvent;
import mypi.diplayEventPanel.DisplayEvents;
import mypi.calendarPanel.CalendarPanel;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import mypi.IO.DataStructuresFromFile;
import mypi.service.EventLinkedList;
import mypi.service.CurrentTime;
import mypi.quote.QuotesLibrary;

/**
 *
 * @author trav Main class that runs the calendar
 *
 * I decided to use a static accessor on <EventLinkedList thePlannedEventList> for the reasons being
 * of not having an expensive overhead of opening streams to read a file to load 
 * the list each time it needs to be accessed
 * 
 * <thePlannedEventList> is used in the followinig 4 classes:
 * RemoveItemFromList.java , WriteToFile.java, CalendarPanel.java, DisplayEvents.java, 
 */
public class CalendarApp extends JFrame {

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JTabbedPane tabs;
    private QuotesLibrary theQuoteBook = new QuotesLibrary();
    private int countForQuotePick, numberOfQuotes;
    private CalendarPanel calendarTab;
    public static EventLinkedList thePlannedEventList;

    public CalendarApp() {
        thePlannedEventList = new DataStructuresFromFile().loadEventsFromFile();
        
        numberOfQuotes = theQuoteBook.getLibrarySize() - 1;
        countForQuotePick = 0;
        tabs = new JTabbedPane();
        DisplayEvents eventTab = new DisplayEvents();
        calendarTab = new CalendarPanel(tabs, eventTab);
        tabs.add("Calendar", calendarTab);
        tabs.add("Events", eventTab);

        //listener that listens for when calendar tab is clicked. when it is clicked
        //the tab will update with events
        tabs.addChangeListener((ChangeEvent e) -> {
            JTabbedPane tabSource = (JTabbedPane) e.getSource();
            //tabb selected grabs the name of the tab selected
            String tabSelected = tabSource.getTitleAt(tabSource.getSelectedIndex());
            if (tabSelected.equals("Calendar")) {
                calendarTab.refreshGUI();
            } else if (tabSelected.equals("Events")) {
                eventTab.refreshGUI();
            }
        });

        CurrentTime theCurrentTimeNow = new CurrentTime();
        int TIME_TICK = 60000;
        new Timer(TIME_TICK, (ActionEvent e) -> {
            timerQuoteEvent(theCurrentTimeNow);
        }).start();

        add(tabs);
        this.setSize(screenSize);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    /**
     * every minute <timer> will fire off this method
     * the strings listed in here are checked against the @param theCurrentTimeNow
     * if they are equal the according action will perform
     * 
     * Possible actions that can take place are
     * 1) nothing - if <theCurrentTimeNow> doesnt equal any of the Strings
     * 2) a popup that will display a daily quote
     * 3) a text reminder will be sent to your phone if you set up <SendText.Java> properly
     * @throws HeadlessException 
     */
    private void timerQuoteEvent(CurrentTime theCurrentTimeNow) throws HeadlessException {
        String afterWorkQuoteTime = "4:30 PM";
        String travmorningQuoteTime = "4:30 AM";
        String colleenMorningQuoteTime = "6:00 AM";
        String beforeBedQuoteTime = "8:00 PM";
        String sendTextReminder = "12:10 AM";
        //if current time is any of these ^
        if (theCurrentTimeNow.getTime().equals(travmorningQuoteTime) || theCurrentTimeNow.getTime().equals(colleenMorningQuoteTime)
                || theCurrentTimeNow.getTime().equals(afterWorkQuoteTime) || theCurrentTimeNow.getTime().equals(beforeBedQuoteTime)) {
            String theMessageToDisplay = theQuoteBook.getQuote(countForQuotePick);
            JOptionPane.showMessageDialog(calendarTab, theMessageToDisplay);
            countForQuotePick++;
            if (countForQuotePick > numberOfQuotes) {
                countForQuotePick = 0;
            }
        } else if (theCurrentTimeNow.getTime().equals(sendTextReminder)) {
            SendTextOnDayOfEvent sendReminder = new SendTextOnDayOfEvent();
            sendReminder.primeTextToBeSent();
            calendarTab.refreshGUI();
        }
    }

    public static void main(String[] args) {
        new CalendarApp();
    }

}//end of CalendarApp
