package fitness;
import static fitness.Location.*;

/**
 Member class defines the attributes of members.
 Every member has a first name, last name, date of birth, expiration date, and location
 @author Leah Ranavat, Tanvi Thigle
 */
public class Member implements Comparable<Member>{
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    /**
     Constructor creates an instance of a member given all the attributes
     @param fname, lname, dob, expire, location
     */
    public Member (String fname, String lname, Date dob, Date expire, Location location){
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
        this.location = location;
    }

    /**
     Gets first name of member
     @return String fname
     */
    public String getFname(){
        return this.fname;
    }

    /**
     Gets last name of member
     @return String lname
     */
    public String getLname(){
        return this.lname;
    }

    /**
     Gets date of birth of member
     @return Date dob
     */
    public Date getDob(){
        return this.dob;
    }

    /**
     Gets expiration date of member
     @return Date expire
     */
    public Date getExpire(){
        return this.expire;
    }

    /**
     Gets gym location of member
     @return Location location
     */
    public Location getLocation(){
        return this.location;
    }

    /**
     Prints all the information of the member
     @return String member
     */
    @Override
    public String toString(){
        String dobStd = dob.getMonth() + "/" + dob.getDay() + "/" + dob.getYear();
        String expireStd = expire.getMonth() + "/" + expire.getDay() + "/" + expire.getYear();
        return fname + " " + lname + " " + dobStd + " " + expireStd + " " + location;
    }


    /**
     Checks if a member is the same as another.
     A member is uniquely identified by first name, last name, and date of birth.
     @param obj the object to be checked
     @return true if the same false if different
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Member){
            Member testMember = (Member) obj; //casting
            return ((testMember.fname.equalsIgnoreCase(this.fname)) && (testMember.lname.equalsIgnoreCase(this.lname)) && ((testMember.getDob().compareTo(this.dob))== 0));
        }
        return false;
    }

    /**
     Compares one member to another.
     Compares is used while sorting by last name then first name alphabetically.
     @param member the member to CompareTo
     @return 1 if alphabetically greater than, 0 if equal to and -1 if less than.
     */
    @Override
    public int compareTo(Member member){
        if(this.lname.compareTo(member.getLname())>0||this.lname.compareTo(member.getLname())==0 && this.fname.compareTo(member.getFname()) >0)
            return 1;
        if(this.lname.compareTo(member.getLname())==0 && this.fname.compareTo(member.getFname()) ==0)
            return 0;
        return -1;
    }

    /**
     Testbed main to check the compareTo() method.
     */
    public static void main (String [] args){
        Date d1= new Date(2002, 8, 07);
        Date e1 = new Date(2027, 12, 28);
        Date d2 = new Date(2002, 10, 28);
        Date e2 = new Date(2027, 12, 28);
        Member m1 = new Member("Leah", "Ranavat",d1,e1,PISCATAWAY);
        Member m2 = new Member("Tanvi", "Thigle",d2,e2, BRIDGEWATER);
        Member m3 = new Member("Mugdha","Thigle",d2,e2,FRANKLIN);
        Member m4 = new Member("Tanvi","Thigle",d2,e2,FRANKLIN);

        int expectedOutput = -1;
        int actualOutput = m1.compareTo(m2);
        System.out.println("**Test case #1: Last name 'Thigle' is alphabetically after 'Ranavat': ");
        testResult(m1, m2, expectedOutput, actualOutput);

        expectedOutput = 1;
        actualOutput = m2.compareTo(m1);
        System.out.println("**Test case #2: Last name of 'Thigle' is alphabetically after 'Ranavat': ");
        testResult(m2, m1, expectedOutput, actualOutput);

        expectedOutput = 1;
        actualOutput = m2.compareTo(m3);
        System.out.println("**Test case #3: Last name of 'Thigle' is same but first name 'Tanvi' is alphabetically after 'Mugdha': ");
        testResult(m2, m3, expectedOutput, actualOutput);

        expectedOutput = 0;
        actualOutput = m2.compareTo(m4);
        System.out.println("**Test case #4: Last name and first name are same: ");
        testResult(m2, m4, expectedOutput, actualOutput);

    }

    /**
     Helper to the testbed main.
     */
    private static void testResult(Member m1, Member m2, int expectedOutput, int actualOutput){
        System.out.println(m1.toString());
        System.out.println(m2.toString());
        System.out.println("compareTo() returns " + actualOutput);
        if(actualOutput==expectedOutput)
            System.out.println(", PASS.\n");
        else
            System.out.println(", FAIL.\n");
    }

}