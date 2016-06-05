package mypi.pojo;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author travis
 */
public class Events implements Comparable<Events>, Serializable {

    private String event, month, day, year;


    public Events(String event, String day, String month, String year) {
        this.month = month;
        this.day = changeSingleDigitTooTwoDigit(day);
        this.year = year;
        this.event = event;
    }

    /**
     * this constructor is called with in <CalendarDayButton.java>
     * this will provide <CreateEventForm.java> a way to set the exact event that needs to be planned
     * @param day
     * @param month
     * @param year 
     */
    public Events(String day, String month, int year) {
        this.month = month;
        this.day = changeSingleDigitTooTwoDigit(day);
        this.year = Integer.toString(year);
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    public String getDay() {
        return day;
    }

    public String getYear() {
        return year;
    }
        /**
         * will take a single digit and transform it with a leading zero for example
         * @param singleDigit 1
         * @return 01
         * This is important inorder to sort properly
         */
        private String changeSingleDigitTooTwoDigit(String singleDigit) {
            try {
                int valueOfSingleDigit = Integer.parseInt(singleDigit);

                if (valueOfSingleDigit < 10) {
                    NumberFormat formatter = new DecimalFormat("00");
                    String twoDigit = formatter.format(valueOfSingleDigit);
                    //System.out.println(twoDigit +" this is it");
                    return twoDigit;
                } else {
                    return String.valueOf(singleDigit);
                }
            } catch (NumberFormatException num) {
                System.out.println(singleDigit + " <- is not an integer value");
                num.printStackTrace();
                System.exit(1);
            }
            return null;
        }

    private String getMonthNumeric() {
        String numericMonth;
        switch (month) {
            case ("January"):
                numericMonth = "00";
                break;
            case ("February"):
                numericMonth = "01";
                break;
            case ("March"):
                numericMonth = "02";
                break;
            case ("April"):
                numericMonth = "03";
                break;
            case ("May"):
                numericMonth = "04";
                break;
            case ("June"):
                numericMonth = "05";
                break;
            case ("July"):
                numericMonth = "06";
                break;
            case ("August"):
                numericMonth = "07";
                break;
            case ("September"):
                numericMonth = "08";
                break;
            case ("October"):
                numericMonth = "09";
                break;
            case ("November"):
                numericMonth = "10";
                break;
            default:
                numericMonth = "11";
                break;
        }
        return numericMonth;
    }

    /**
     * 1st 2nd 3rd 4th 5th 6th 7th 8th 9th 10th 11th 12th 13th 14th 15th 16th
     * 17th 18th 19th 20th 21st 22nd 23rd 24th 25th 26th 27th 28th 29th 30th
     * 31st
     *
     * @return
     */
    private String ordinalIndicator(String day) {
        StringBuilder dayWithOrdinal = new StringBuilder();
        String regexToSetTH = "0[4-9]|1[4-9]|2[4-9]|10|20|30";
        Pattern theTHPattern = Pattern.compile(regexToSetTH);
        Matcher matchTH = theTHPattern.matcher(day);
        dayWithOrdinal.append(day);
        //day.contains(regexToSetTH)
        if (matchTH.matches()){ 
            dayWithOrdinal.append("th");
        } else if (day.equals("01") || day.equals("21") || day.equals("31")) {
            dayWithOrdinal.append("st");
        } else if ( day.equals("02")||day.equals("22") ) {
            dayWithOrdinal.append("nd");
        } else if (day.contains("3")) {
            dayWithOrdinal.append("rd");
        }
        return dayWithOrdinal.toString();
    }
    /**
     *
     * @return method that will read the description example Comcast$80 if it
     * contains $ than it is a bill if not than its an event planned example
     * Dads Birthday
     */
    private String decideIfDescriptionIsABill() {
        String decide = (event.contains("$")) ? " is due" : "";
        return decide;
    }

    private String getDateOfEvent() {
        return String.format("%s %s %s", year, getMonthNumeric(), day);
    }

    @Override
    public String toString() {
        return String.format("%s%s on the %s of %s %s", event, decideIfDescriptionIsABill(), ordinalIndicator(day), month, year);
    }
//1) CompareTo method must return negative number if current object is less than other object, 
    // positive number if current object is greater than other object and 
    // zero if both objects are equal to each other

//http://javarevisited.blogspot.com/2011/11/how-to-override-compareto-method-in.html#ixzz48wP4WGJg
    @Override
    public int compareTo(Events otherEvent) {
        //System.out.println(this.getDateOfEvent().compareTo(otherEvent.getDateOfEvent())+ "was the result from "+this.toString()+" compare to "+otherEvent.toString());
        return this.getDateOfEvent().compareTo(otherEvent.getDateOfEvent());
    }
}
