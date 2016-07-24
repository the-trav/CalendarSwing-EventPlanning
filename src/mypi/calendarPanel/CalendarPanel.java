package mypi.calendarPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import static java.util.Calendar.MONTH;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import mypi.diplayEventPanel.DisplayEvents;
import mypi.pojo.Events;
import mypi.service.EventLinkedList;
import mypi.service.SendTextOnDayOfEvent;

public class CalendarPanel extends JPanel {

    private JButton nextMonth, previousMonth;//buttons used to navigate through months
    private int firstOfMonth;//is used as a placeholder to distribute the day grid accordingly
    private GregorianCalendar tempCalendar;
    private final String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};//is populated into weekDayLabel
    Font monthFont = new Font("Bookman Old Style", Font.PLAIN, 60);
    Font buttonFont = new Font("Bookman Old Style", Font.ITALIC, 45);
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private CalendarDayButton[] dayButton;
    private JTabbedPane theAppTabs;
    private DisplayEvents eventTab;
    public static SendTextOnDayOfEvent sendReminder = new SendTextOnDayOfEvent();

    public CalendarPanel(JTabbedPane theAppTabs, DisplayEvents eventTab, GregorianCalendar tempCalendar) {
        this.tempCalendar = tempCalendar;
        this.theAppTabs = theAppTabs;
        this.eventTab = eventTab;
        this.setLayout(new GridBagLayout());
        displayTopPanel();
        displayWeekDayPanel();
        displayDayPanel(getDaysInAMonth());
        displayCalendarEvents();
        this.setSize(screenSize);
        this.setVisible(true);

    }

    public void refreshGUI() {
        this.removeAll();
        this.updateUI();
        displayTopPanel();
        displayWeekDayPanel();
        displayDayPanel(getDaysInAMonth());
        displayCalendarEvents();
    }

    
    private void displayTopPanel() {
        JPanel topPanel = new JPanel();
        JLabel monthLabel = new JLabel(getMonth( tempCalendar.get(Calendar.MONTH) ) + " " + tempCalendar.get(Calendar.YEAR));
        monthLabel.setFont(monthFont);
        nextMonth = new JButton(new ImageIcon("right-arrow.png"));
        nextMonth.setBorderPainted(false);
        nextMonth.setContentAreaFilled(false);
        nextMonth.setFocusPainted(false);
        nextMonth.setOpaque(false);

        NextPrevButton buttonClick = new NextPrevButton();
        nextMonth.addActionListener(buttonClick);
        
        previousMonth = new JButton(new ImageIcon("left-arrow.png"));
        previousMonth.setBorderPainted(false);
        previousMonth.setContentAreaFilled(false);
        previousMonth.setFocusPainted(false);
        previousMonth.setOpaque(false);

        previousMonth.addActionListener(buttonClick);
        topPanel.add(previousMonth);
        topPanel.add(monthLabel);
        topPanel.add(nextMonth);
        addComponent(this, topPanel, 0, 0, 0, 1, GridBagConstraints.PAGE_START, GridBagConstraints.BOTH);
    }

    //**DO NOT ALTER**\\
    /**
     * 
     * @param totalDays being total days in the current month 
     */
    private void displayDayPanel(int totalDays) {
        GregorianCalendar calendar = new GregorianCalendar();//creates a calendar for today
        int currentYear = calendar.get(Calendar.YEAR);//is used to highlight current day
        int currentMonth = calendar.get(Calendar.MONTH);//is used to highlight current Day

        for (int i = 0; i < daysOfWeek.length; i++) {
            if (daysOfWeek[i].equalsIgnoreCase(findFirstOfMonth(tempCalendar)))//finding the first of the month
            {
                firstOfMonth = i + 2;
            }
        }
        int column = firstOfMonth;
        int row = firstOfMonth;
        dayButton = new CalendarDayButton[totalDays+1];
        for (int dayNumber = 1; dayNumber <= totalDays; dayNumber++) {
            dayButton[dayNumber] = new CalendarDayButton(dayNumber, tempCalendar, theAppTabs);
            if (tempCalendar.get(Calendar.DAY_OF_MONTH) == dayNumber && currentMonth == tempCalendar.get(Calendar.MONTH) && currentYear == tempCalendar.get(Calendar.YEAR)) {//if its today
                dayButton[dayNumber].setCurrentDay();
            }

            addComponent(this, dayButton[dayNumber], column, row, 1, 1, GridBagConstraints.SOUTH, GridBagConstraints.BOTH);
            if (row == 8) {//provides new row
                row = 1;
                column = column + 1;
            }
            row++;
        }
    }

    /**
     * method cycles through all of the events objects
     * grabs each variable from each object
     * and preforms the following conditions
     * -Is there an event this month? if so display it on the appropriate day button
     * -Is there an event for today? if so send you a text message.
     * 
     * catch block handles the chance of there not being any event objects.
     * 
     */
       private void displayCalendarEvents() {
        try {
            for (Events holdEvent : EventLinkedList.instanceOf()){
                String event = holdEvent.getEvent();
                String month = holdEvent.getMonth();
                String day = holdEvent.getDay();
                String year = holdEvent.getYear();

                if (isThereAnEventThisMonth(month, year)) {
                    dayButton[Integer.parseInt(day)].setEventDescription(event);
                }

                if( isThereAnEventOnTodaysDate(month, year, day) ){
                    sendReminder.setEventOfToday(event);
                    sendReminder.sendTextReminders();
                }
            }
        } catch (NullPointerException e) {
            System.out.println("No events have been made to populate calendar");
        }

    }

    private boolean isThereAnEventThisMonth(String month, String year) throws NumberFormatException {
        return month.equals(getMonth( tempCalendar.get(Calendar.MONTH) )) && Integer.parseInt(year)==tempCalendar.get(Calendar.YEAR);
    }

    private boolean isThereAnEventOnTodaysDate(String month, String year, String day) throws NumberFormatException {
        return month.equals(getMonth( tempCalendar.get( Calendar.MONTH ) )) && Integer.parseInt(year)==tempCalendar.get(Calendar.YEAR)&& Integer.parseInt(day)== tempCalendar.get(Calendar.DAY_OF_MONTH);
    }



    /**
     * method will display the 7 days of the week in a nice border below
     * topPanel
     */
    private void displayWeekDayPanel() {
       JLabel[] weekDayLabel = new JLabel[7];
        Border weekBorder = BorderFactory.createLineBorder(Color.BLACK, 5);

        for (int i = 0; i < 7; i++) {
            weekDayLabel[i] = new JLabel(daysOfWeek[i], SwingConstants.CENTER);
            weekDayLabel[i].setBorder(weekBorder);
            addComponent(this, weekDayLabel[i], 1, i + 2, 1, 1, GridBagConstraints.ABOVE_BASELINE_TRAILING, GridBagConstraints.HORIZONTAL);
        }
    }

    /**
     *
     * Sets the rules for a component destined for a GridBagLayout and then adds
     * it
     */
    private void addComponent(JPanel thePan, Component c, int row, int column, int width, int height, int place, int stretch) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = column; // set gridx
        constraints.gridy = row; // set gridy
        constraints.gridwidth = width; // set gridwidth
        constraints.gridheight = height; // set gridheight
        constraints.weightx = 100;
        constraints.weighty = 100;
        constraints.anchor = place;
        constraints.fill = stretch;
        thePan.add(c, constraints);
    }

    /**
     *
     * @return how many days in the current month
     */
    private int getDaysInAMonth() {
        return tempCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

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

    /**
     *
     * @param cal is the current calendar being used <CLONE> creates a new
     * calendar based off of <cal>
     * <CLONE> is then used to set the calendar to the 1st
     *
     * @returns either one of the 7 mon tue wed thr fri sat sun subString(0,3)
     * grabs the day of the week the first lands on
     *
     *
     */
    private String findFirstOfMonth(GregorianCalendar cal) {
        GregorianCalendar CLONE;
        CLONE = (GregorianCalendar) cal.clone();
        CLONE.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
        String firstDayOfTheMonth;
        firstDayOfTheMonth = CLONE.getTime().toString();
        //System.out.println(firstDayOfTheMonth.substring(0, 3));
        return firstDayOfTheMonth.substring(0, 3);
    }



    /**
     * <NextPrevButton> class handles cycling through the months if the arrow
     * pointing to the right is clicked tempMonth will be incremented if the
     * arrow pointing to the left is clicked tempMonth will be de-incremented
     */
    private class NextPrevButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == nextMonth) {
                tempCalendar.add(MONTH, 1);
                refreshGUI();
            } else if (e.getSource() == previousMonth) {
                tempCalendar.add(MONTH, -1);
                refreshGUI();
            }
        }
    }//end nextButton Listener

    
}//end of CalendarApp
