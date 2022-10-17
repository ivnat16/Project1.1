package fitness;

import static org.junit.Assert.*;

public class DateTest {

    @org.junit.Test
    public void nonleapYearFebDays() {
        Date date = new Date("2/29/2018");
        assertFalse(date.isValid());
    }

    @org.junit.Test
    public void leapYearFebDays() {
        Date date = new Date("2/29/2016");
        assertTrue(date.isValid());
    }

    @org.junit.Test
    public void lessThan1900() {
        Date date = new Date("11/21/800");
        assertFalse(date.isValid());
    }

    @org.junit.Test
    public void invalidMonth() {
        Date date = new Date("13/21/1999");
        assertFalse(date.isValid());
    }

    @org.junit.Test
    public void invalidDay() {
        Date date = new Date("12/32/2000");
        assertFalse(date.isValid());
    }
}