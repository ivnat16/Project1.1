package fitness;

import org.junit.Test;

import static org.junit.Assert.*;

public class FitnessClassTest {

    @Test
    public void checkInPremium() {
        Premium p1 = new Premium("Tanvi", "Thigle",new Date("3/16/2002"), new Date("12/28/2027"),Location.FRANKLIN);
        FitnessClass fit = new FitnessClass("Pilates", Instructor.JENNIFER, Time.MORNING,Location.BRIDGEWATER);
        assertTrue(fit.checkIn(p1));

    }
    @Test
    public void checkInFamily() {
        Family f1 = new Family("Tanvi", "Thigl",new Date("3/16/2002"), new Date("12/28/2027"),Location.SOMERVILLE);
        FitnessClass fit = new FitnessClass("Pilates", Instructor.JENNIFER, Time.MORNING,Location.BRIDGEWATER);
        assertTrue(fit.checkIn(f1));

    }
    @Test
    public void checkInMember_Validlocation() {
        Member m1 = new Member("Leah", "Ranavat",new Date("8/31/1999"), new Date("12/28/2023"),Location.BRIDGEWATER);
        FitnessClass fit = new FitnessClass("Pilates", Instructor.JENNIFER, Time.MORNING,Location.BRIDGEWATER);
        assertTrue(fit.checkIn(m1));
    }

    @Test
    public void checkInMember_locationRestriction() {
        Member m2 = new Member("Leah", "Ranavat",new Date("8/31/1999"), new Date("12/28/2023"),Location.PISCATAWAY);
        FitnessClass fit = new FitnessClass("Pilates", Instructor.JENNIFER, Time.MORNING,Location.BRIDGEWATER);
        assertFalse(fit.checkIn(m2));
    }

    @Test
    public void guestCheckInPremium() {
        Premium p1 = new Premium("Tanvi", "Thigle",new Date("3/16/2002"), new Date("12/28/2027"),Location.EDISON);
        FitnessClass fit = new FitnessClass("Cardio", Instructor.EMMA, Time.EVENING,Location.EDISON);
        assertTrue(fit.guestCheckIn(p1));

    }
  /*  @Test
    public void guestCheckInPremium_ValidGuestPass() {
        Premium p1 = new Premium("Tanvi", "Thigle",new Date("3/16/2002"), new Date("12/28/2027"),Location.EDISON);
        FitnessClass fit = new FitnessClass("Cardio", Instructor.EMMA, Time.EVENING,Location.EDISON);
        fit.guestCheckIn(p1);
        fit.guestCheckIn(p1);
        fit.guestCheckIn(p1);
        fit.guestCheckIn(p1);
        assertFalse(fit.guestCheckIn(p1));

    }*/
    @Test
    public void guestCheckInFamily_locationRestriction() {
        Family f1 = new Family("Tanvi", "Thigl",new Date("3/16/2002"), new Date("12/28/2027"),Location.SOMERVILLE);
        FitnessClass fit = new FitnessClass("Cardio", Instructor.EMMA, Time.EVENING,Location.EDISON);
        assertFalse(fit.guestCheckIn(f1));
    }
    @Test
    public void guestCheckInMember() {

        Member m1 = new Member("Leah", "Ranavat",new Date("8/31/1999"), new Date("12/28/2023"),Location.BRIDGEWATER);
        FitnessClass fit = new FitnessClass("Cardio", Instructor.EMMA, Time.EVENING,Location.EDISON);
        assertFalse(fit.guestCheckIn(m1));
    }

    @Test
    public void doneClassPremium() {
        Premium p1 = new Premium("Tanvi", "Thigle",new Date("3/16/2002"), new Date("12/28/2027"),Location.FRANKLIN);
        FitnessClass fit = new FitnessClass("Pilates", Instructor.JENNIFER, Time.MORNING,Location.BRIDGEWATER);
        fit.checkIn(p1);
        assertTrue(fit.doneClass(p1));

    }
    @Test
    public void doneClassFamily() {
        Family f1 = new Family("Tanvi", "Thigl",new Date("3/16/2002"), new Date("12/28/2027"),Location.SOMERVILLE);
        FitnessClass fit = new FitnessClass("Pilates", Instructor.JENNIFER, Time.MORNING,Location.BRIDGEWATER);
        fit.checkIn(f1);
        assertTrue(fit.doneClass(f1));

    }
    @Test
    public void doneClassMember_alreadyRemoved() {
        Member m1 = new Member("Leah", "Ranavat",new Date("8/31/1999"), new Date("12/28/2023"),Location.BRIDGEWATER);
        FitnessClass fit = new FitnessClass("Pilates", Instructor.JENNIFER, Time.MORNING,Location.BRIDGEWATER);
        fit.checkIn(m1);
        fit.doneClass(m1);
        assertFalse(fit.doneClass(m1));
    }
    @Test
    public void doneClassMember_neverCheckedIn() {

        Member m2 = new Member("Leah", "Ranavat",new Date("8/31/1999"), new Date("12/28/2023"),Location.PISCATAWAY);
        FitnessClass fit = new FitnessClass("Pilates", Instructor.JENNIFER, Time.MORNING,Location.BRIDGEWATER);
        assertFalse(fit.doneClass(m2));
    }

    @Test
    public void guestDoneClassPremium() {
        Premium p1 = new Premium("Tanvi", "Thigle",new Date("3/16/2002"), new Date("12/28/2027"),Location.EDISON);
        FitnessClass fit = new FitnessClass("Cardio", Instructor.EMMA, Time.EVENING,Location.EDISON);
        fit.guestCheckIn(p1);
        assertTrue(fit.guestDoneClass(p1));
    }
    @Test
    public void guestDoneClassFamily_neverCheckedIn() {
        Family f1 = new Family("Tanvi", "Thigl",new Date("3/16/2002"), new Date("12/28/2027"),Location.EDISON);
        FitnessClass fit = new FitnessClass("Cardio", Instructor.EMMA, Time.EVENING,Location.EDISON);
        assertFalse(fit.guestDoneClass(f1));
    }
}