package fitness;

import org.junit.Test;

import static org.junit.Assert.*;

public class PremiumTest {

    @Test
    public void membershipFeeforPremium() {
        Premium p1 = new Premium("Tanvi", "Thigle",new Date("3/16/2002"), new Date("12/28/2027"),Location.FRANKLIN);
        double fee1 = p1.membershipFee();
        assertEquals(59.99*11,fee1, 0.00);
    }

    @Test
    public void membershipFeeforMember() {
        Member m1 = new Member("Tanvi", "Thigle",new Date("3/16/2002"), new Date("12/28/2027"),Location.FRANKLIN);
        double fee1 = m1.membershipFee();
        assertNotEquals(59.99*11,fee1, 0.00);
    }

    @Test
    public void membershipFeeforFamily() {
        Family f1 = new Family("Leah", "Ranavat",new Date("8/31/1999"), new Date("12/28/2023"),Location.PISCATAWAY);
        double fee1 = f1.membershipFee();
        assertNotEquals(59.99*11,fee1, 0.00);
    }
}