package mypi.calendarPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import mypi.eventForm.CreateEventForm;
import mypi.pojo.Events;

/**
 *
 * @author trav
 *
 */
public class CalendarDayButton extends JButton {
    private JLabel dayNumber;
    private JTextArea eventDescription;
    private GregorianCalendar tempCalendar;
    private JTabbedPane theAppTabs;
    
    public CalendarDayButton(int dayNum,GregorianCalendar tempCalendar,JTabbedPane theAppTabs) {
        this.tempCalendar = tempCalendar;
        this.theAppTabs = theAppTabs;
        dayNumber = new JLabel(Integer.toString(dayNum));
        setUpEventDescription();
        setUpButton();
    }

    /**
     * method is called to highlight the currentDay as white
     */
    public void setCurrentDay(){
        this.setBackground(Color.WHITE);
        eventDescription.setBackground(Color.WHITE);
    }
    
    /**
     * <eventString> is used to handle multiple events planned in a day
     * eventString adds what is currently already planned in that day copy it and
     * add the @param eventToPlan
     * finally it will be set
     */
    public void setEventDescription(String eventToPlan){
            StringBuilder eventString = new StringBuilder();
            eventString.append(eventDescription.getText() );
            eventString.append("\n");
            eventString.append(eventToPlan);
            eventDescription.setText(eventString.toString());
    }
    /**
     * helper method initilizes the dayButtons textArea location to where the 
     * planned event will be displayed "if any event is planned"
     */
    private void setUpEventDescription(){
        eventDescription = new JTextArea();
        eventDescription.setEditable(false);
        eventDescription.setBackground(Color.lightGray);
        eventDescription.setLineWrap(true);
        eventDescription.setWrapStyleWord(true);
    }
    
    private void setUpButton(){
        this.setBackground(Color.lightGray);
        this.setHorizontalAlignment(SwingConstants.LEFT);
        this.setVerticalAlignment(SwingConstants.TOP);
        this.setLayout(new BorderLayout());
        this.setSize(100, 100);
        this.addActionListener(new DayListener());
        this.add(BorderLayout.NORTH, dayNumber);
        this.add(BorderLayout.SOUTH, eventDescription);
    }

    /**
     * <DayListener> handles anytime a day on the calendar is clicked once
     * clicked the user will be brought to <CreateNewEventPanel>.class the user
     * will only be able to transition out of the new panel until he/she clicks
     * okay or cancel
     */
    private class DayListener implements ActionListener {


        
            /**
     *
     * @param m is from an integer value of the month
     * @return 0=january , 11=december ect.
     */
    private String getMonth(int m) {
        String holder = Integer.toString(m);
        switch (m) {
            case (0):
                holder = "January";
                break;
            case (1):
                holder = "February";
                break;
            case (2):
                holder = "March";
                break;
            case (3):
                holder = "April";
                break;
            case (4):
                holder = "May";
                break;
            case (5):
                holder = "June";
                break;
            case (6):
                holder = "July";
                break;
            case (7):
                holder = "August";
                break;
            case (8):
                holder = "September";
                break;
            case (9):
                holder = "October";
                break;
            case (10):
                holder = "November";
                break;
            case (11):
                holder = "December";
                break;
        }
        return holder;
    }
        @Override
        public void actionPerformed(ActionEvent e) {
                if (CalendarDayButton.this == e.getSource()){
                    Events eventPojo = new Events(dayNumber.getText() , getMonth(tempCalendar.get(Calendar.MONTH)), tempCalendar.get(Calendar.YEAR));
                    CreateEventForm createAEvent = new CreateEventForm(eventPojo, theAppTabs);
                    theAppTabs.add("Creating an Event", createAEvent);
                    theAppTabs.setSelectedComponent(createAEvent);
                    //making tabs calendar events un-clickable until the creating even is complete
                    theAppTabs.setEnabledAt(0, false);
                    theAppTabs.setEnabledAt(1, false);
                } 

        }//end of action

    }//end of dayListener
}
