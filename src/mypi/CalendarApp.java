package mypi;

import mypi.diplayEventPanel.DisplayEvents;
import mypi.calendarPanel.CalendarPanel;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import mypi.service.CurrentTime;
import mypi.quote.QuotesLibrary;
import mypi.service.EventLinkedList;

/**
 *
 * @author trav Main class that runs the calendar
 *
 */
public class CalendarApp extends JFrame {

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final JTabbedPane TABS;
    private final QuotesLibrary THE_QUOTE_BOOK = new QuotesLibrary();
    private int countForQuotePick=0;
    private final int NUMBER_OF_QUOTES =THE_QUOTE_BOOK.getLibrarySize()-1;
    private CalendarPanel calendarTab;
    private GregorianCalendar tempCalendar;

    public CalendarApp() {
        tempCalendar=new GregorianCalendar();
        TABS = new JTabbedPane();
        DisplayEvents eventTab = new DisplayEvents();
        calendarTab = new CalendarPanel(TABS, eventTab, tempCalendar);
        TABS.add("Calendar", calendarTab);
        TABS.add("Events", eventTab);

        //listener that listens for when calendar tab is clicked. when it is clicked
        //the tab will update with events
        TABS.addChangeListener((ChangeEvent e) -> {
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
            timerEvent(theCurrentTimeNow);
        }).start();

        add(TABS);
        this.setSize(screenSize);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

    }

    /**
     * every minute <timer> will fire off this method the strings listed in here
     * are checked against the @param theCurrentTimeNow if they are equal the
     * according action will perform
     *
     * Possible actions that can take place are 1) nothing - if
     * <theCurrentTimeNow> doesnt equal any of the Strings 2) a popup that will
     * display a daily quote 3) a text reminder will be sent to your phone if
     * you set up <SendText.Java> properly
     *
     * @throws HeadlessException
     */
    private void timerEvent(CurrentTime theCurrentTimeNow) throws HeadlessException {
        String afterWorkQuoteTime = "4:30 PM";
        String travmorningQuoteTime = "4:30 AM";
        String colleenMorningQuoteTime = "6:00 AM";
        String beforeBedQuoteTime = "7:00 PM";
        String midnight = "12:00 AM";
        String sendTextReminder = "1:29 AM";

        if (isItQuoteTime(theCurrentTimeNow, travmorningQuoteTime, colleenMorningQuoteTime, afterWorkQuoteTime, beforeBedQuoteTime)) {
            createDialogDisplayingQuote();
        }else
        if (theCurrentTimeNow.getTime().equals(sendTextReminder)) {
            calendarTab.sendReminder.primeTextToBeSent();
            calendarTab.refreshGUI();
        }else
        if(theCurrentTimeNow.getTime().equals(midnight)){
            tempCalendar.add(Calendar.DATE, 1);
            calendarTab.refreshGUI();
        }
    }

    /**
     * Creates a custom dialog setting modal to false for dialog doesn't block thread
     * @throws HeadlessException 
     */
    private void createDialogDisplayingQuote() throws HeadlessException {
        String theMessageToDisplay = THE_QUOTE_BOOK.getQuote(countForQuotePick);
        JOptionPane pane=new JOptionPane(theMessageToDisplay);
        JDialog dialog = pane.createDialog(calendarTab,"Daily Quote");
        dialog.setModal(false);
        dialog.setVisible(true);
        countForQuotePick++;
        if (countForQuotePick > NUMBER_OF_QUOTES) {
            countForQuotePick = 0;
        }
    }

    /**
     * if any of these parameters is the current time
     * @param theCurrentTimeNow
     * @param travmorningQuoteTime
     * @param colleenMorningQuoteTime
     * @param afterWorkQuoteTime
     * @param beforeBedQuoteTime
     * @return 
     */
    private static boolean isItQuoteTime(CurrentTime theCurrentTimeNow, String travmorningQuoteTime, String colleenMorningQuoteTime, String afterWorkQuoteTime, String beforeBedQuoteTime) {
        return theCurrentTimeNow.getTime().equals(travmorningQuoteTime) || theCurrentTimeNow.getTime().equals(colleenMorningQuoteTime)
                || theCurrentTimeNow.getTime().equals(afterWorkQuoteTime) || theCurrentTimeNow.getTime().equals(beforeBedQuoteTime);
    }

    public static void main(String[] args) {
        if (EventLinkedList.instanceOf().getFile().getName().equalsIgnoreCase("Planned_Events_POJO.txt")) {
            new CalendarApp();
        } else {
            System.err.println("YOU NEED TO CHANGE TEST_FILE CHECK FileReadAndWrite.java");
            System.exit(1);
        }
    }

}//end of CalendarApp
