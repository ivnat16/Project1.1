package fitness;
import java.util.Calendar;

/**
 Date class deals with verifying all the dates used for Gym membership.
 Different methods work on checking or providing the correct date.
 @author Tanvi Thigle, Leah Ranavat
 */
public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public static final int LEAP_FEB_DAYS = 29;
    public static final int NON_LEAP_FEB_DAYS = 28;
    public static final int REQUIRED_AGE = 18;

    public static final int MONTH_DAYS = 30;
    public static final int MONTH_DAYS_EXTRA = 31;
    public static final int MIN_VALID_YEAR = 1900;
    public static final int MAX_VALID_YEAR = 2900;


    public static final int JAN= 1;
    public static final int FEB= 2;
    public static final int MAR= 3;
    public static final int APR= 4;
    public static final int MAY= 5;
    public static final int JUN= 6;
    public static final int JUL= 7;
    public static final int AUG= 8;
    public static final int SEP= 9;
    public static final int OCT= 10;
    public static final int NOV= 11;
    public static final int DEC= 12;


    /**
     Constructor makes a new date instance
     @param year the year of the date
     @param day the day of the date
     @param month the month of the date
     */
    public Date (int year, int day, int month){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     Gets the day instance variable
     @return int day
     */
    public int getDay(){
        return day;
    }

    /**
     Gets the month instance variable
     @return int month
     */
    public int getMonth(){
        return month;
    }

    /**
     Gets the year instance variable
     @return int year
     */
    public int getYear(){
        return year;
    }

    /**
     Default Constructor makes a new date instance that is the current date
     Creates an object with today's date
     */
    public Date() {
        Calendar rightNow = Calendar.getInstance();
        day = rightNow.get(Calendar.DAY_OF_MONTH);
        month = rightNow.get(Calendar.MONTH) + 1; //January starts at 0
        year = rightNow.get(Calendar.YEAR);
    }

    /**
     Constructor makes a new date instance
     Turns string date into int
     Takes mm/dd/yyyy and creates a fitness.Date object
     @param date the date to be created
     */
    public Date(String date) {
        String [] newDate = date.split("/");
        Date d = new Date(Integer.parseInt(newDate[2]), Integer.parseInt(newDate[1]), Integer.parseInt(newDate[0]));
       // this.year = d.getYear();
       // this.day = d.getDay();
       // this.month = d.getMonth();
    }

    /**
     Compares two dates
     @param date date being compared to
     @return int -1 if date less than, 0 if equals, 1 if greater than
     */
    @Override
    public int compareTo(Date date) {
        if(this.year > date.getYear()||this.year==date.getYear()&&this.month>date.getMonth()||this.year==date.getYear()&&this.month==date.getMonth()&&this.day>date.getDay())
            return 1;
        if(date.getYear() == this.year && date.getMonth() == this.month && date.getDay() == this.day) {
            return 0;
        }
        return -1;
    }

    /**
     Checks if a date is a valid calendar date
     @return true if valid false if invalid
     */
    public boolean isValid() {
        if( year<MIN_VALID_YEAR||year>MAX_VALID_YEAR
                || month > DEC || day > MONTH_DAYS_EXTRA )
            return false;
        if( year < 0 || month < 0 || day < 0 )
            return false;
        if( month==JAN||month==MAR||month==MAY||month==JUL
                || month==AUG||month==OCT||month==DEC ){
            if( day>MONTH_DAYS_EXTRA ) return false;
        }
        if( month==APR||month==JUN||month==SEP||month==NOV ) {
            if ( day > MONTH_DAYS ) return false;
        }
        if( month==FEB ) {
            if( year % QUADRENNIAL==0 ){
                if( year % CENTENNIAL==0 ){
                    if( year % QUATERCENTENNIAL==0 ){
                        if( day>LEAP_FEB_DAYS ) return false;
                    }
                    else if( day>NON_LEAP_FEB_DAYS ) return false;
                }if( day>LEAP_FEB_DAYS ) return false;
            }else if( day>NON_LEAP_FEB_DAYS ) return false;
        }
        return true;
    }

    /**
     Checks if age is greater than 18
     Cannot have gym membership if age is less than 18
     @param dob the dateOfBirth of the member
     @return true if equal to or greater than 18 false if less than 18
     */
    public boolean ageIsValid(Date dob){
        Date rn = new Date();
        if( rn.getYear() - dob.getYear() > REQUIRED_AGE
                || rn.getYear() - dob.getYear() == REQUIRED_AGE
                && rn.getMonth() > dob.getMonth()
                || rn.getYear()-dob.getYear() == REQUIRED_AGE
                && rn.getMonth() == dob.getMonth()
                && rn.getDay() > dob.getDay() )
            return true;
        else return false;
    }

    /**
     Checks if expiration date is still valid
     Membership expired
     @param exp the Expiration Date of the member
     @return true if valid false if invalid
     */
    public boolean expIsValid(Date exp){
        Date rn = new Date();
        if( rn.getYear() < exp.getYear()
                || rn.getYear() == exp.getYear()
                && rn.getMonth() < exp.getMonth()
                ||rn.getYear() == exp.getYear()
                && rn.getMonth() == exp.getMonth()
                &&rn.getDay()<exp.getDay() ) {
            return true;
        }
        else return false;
    }

    /**
     Checks if a date is a valid date of birth
     Cannot have a DOB that is today or a future date
     @param dob the datOfBirth of the member
     @return true if valid false if invalid
     */
    public boolean dobIsValid(Date dob){
        Date rn = new Date();
        if( dob.getYear() < rn.getYear()
                || dob.getYear() == rn.getYear()
                && dob.getMonth() < rn.getMonth()
                || dob.getYear() == rn.getYear()
                && dob.getMonth() == rn.getMonth()
                && dob.getDay() < rn.getDay() )
            return true;
        else return false;
    }

    public Date threeMonthsDate(){
        Calendar rightNow = Calendar.getInstance();
        Calendar threemonths = rightNow;
        rightNow.add(Calendar.MONTH,4);
        day = threemonths.get(Calendar.DAY_OF_MONTH);
        month = rightNow.get(Calendar.MONTH);
        year = rightNow.get(Calendar.YEAR) ;
        Date temp = new Date(year,day,month);
        return temp;
    }

    /**
     Prints date
     @param date the date needed to be printed
     @return String form of date
     */
    public String print(Date date){
        return date.getMonth()+ "/" + date.getDay() + "/" + date.getYear();
    }

   /* public String toString{
        return (this.month + "/" + this.day + "/" + this.year);
    }*/


    /** Testbed main to exercise the isValid method. ...*/
    public static void main (String [] args) {
        Date date = new Date("11/21/800");

        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.print("**Test case #1:" +
                " does not take a year less than 1900 as valid year ");
        testResult(date, expectedOutput, actualOutput);
        date = new Date("2/29/2018");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.print("**Test case #2: " +
                "a date in a non-leap year has only 28 days in February: ");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("2/29/2016");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.print("**Test case #3: " +
                "a date in a leap year has 29 days in February: ");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("13/21/1999");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.print("**Test case #4: " +
                "a date with an invalid month value: ");
        testResult(date, expectedOutput, actualOutput);
    }

    /** testResult to help the main. ...
     @param date the date of the member
     @param expectedOutput the correct/expected output
     @param actualOutput the output of the program
     */
    private static void testResult(Date date, boolean expectedOutput, boolean actualOutput){
        System.out.println(date.print(date));
        System.out.println("isValid() returns " + actualOutput);
        if(actualOutput==expectedOutput)
            System.out.println(", PASS.\n");
        else
            System.out.println(", FAIL.\n");
    }

}