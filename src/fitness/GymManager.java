package fitness;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 Gym Manager, is a User Interface Class to process the command line
 Can process a single or batch of command lines
 @author Leah Ranavat, Tanvi Thigle
 */
public class GymManager {

    private static final MemberDatabase MLIST_DATA = new MemberDatabase();
    private static final Date DATE_CALL = new Date();
    private static final FitnessClass FITNESS_CALL = new FitnessClass("temp");


    /**
     Checks if the command is valid
     Prints invalid if command is not valid
     @param command the command that needs to be checked
     */
    private void isValidCommand (String command){
        if(command.equals("a") || command.equals("p") || command.equals("pc") || command.equals("pn") || command.equals("pd")){
            System.out.println(command + " " + "is an invalid command!");
        }
        if(command.equals("AA") || command.equals("RR") || command.equals("r") || command.equals("s") || command.equals("c")){
            System.out.println(command + " " + "is an invalid command!");
        }
        if(command.equals("d") || command.equals("SS") || command.equals("CC") || command.equals("DD") || command.equals("q")){
            System.out.println(command + " " + "is an invalid command!");
        }
    }

    /**
     Given dateOfBirth checks whether age Is Valid
     @param dob the dateOfBirth of the person
     @return true if person ageIsValid, false otherwise.
     */
    private boolean checkAge(Date dob) {
        return dob.ageIsValid(dob);
    }

    /**
     Given member checks whether dateOfBirth or Expiration Date Is Valid
     @param member the member whose dateOfBirth or Expiration needs to be checked
     @return false if person's dateofBirth/Expiration is Not Valid, true otherwise.
     */
    private boolean checkCalenderDobExp(Member member){
        if(member.getDob().isValid()== false){
            System.out.println("DOB" + " " + DATE_CALL.print(member.getDob()) + ": invalid calendar date!");
            return false;
        }
        if(member.getExpire().isValid() == false){
            System.out.println("Expiration date" + " " + DATE_CALL.print(member.getExpire()) + ": invalid calendar date!");
            return false;
        }
        return true;
    }

    /**
     Given dateOfBirth checks if the dateOfBirth is Today or Future Date
     @param dob the member whose dateOfBirth needs to be checked
     @return false if person's dob is today or future date, true otherwise
     */
    private boolean checkTodFut(Date dob){
        return dob.dobIsValid(dob);
    }

    /**
     Given Expiration checks if the date is expired
     @param member the member whose expiration needs to be checked
     @return false if membership expired, true otherwise
     */
    private boolean checkExp(Member member){
        Date checkExp = new Date();
        if(member.getExpire().expIsValid(member.getExpire()) == false){
            System.out.println(member.getFname() + " " + member.getLname() + " " + checkExp.print(member.getDob()) + " membership expired.");
            return false;
        }
        return true;
    }

    /**
     Checks the size of MLIST_DATA
     */
    private boolean checkSize(){
        if(MLIST_DATA.getSize() == 0){
            System.out.println("Member database is empty!");
            return true;
        }
        return false;
    }

    /**
     Creates the String to store tokens
     @param st the tokens that have to be stored
     @return the String Array with the tokens
     */
    private String [] createString (StringTokenizer st){
        String[] temp = new String[5];
        for(int i = 0; st.hasMoreTokens(); i++){
            temp[i]= st.nextToken();
        }
        if(temp[4] == null){
            temp[4] = temp[3];
            temp[3] = null;
        }
        //but the date is our date right like october ?
        return temp;
    }

    /**
     Creates the Member, Date and Location Object to be added to the Array
     @param temp the createString above with the member information
     @return the Member created
     */
    private Member createMember(String [] temp){
        Date dobtemp =  new Date(temp[2]);
        /*if(temp[3] == null) {
            temp[3] = "12/12/2028";
            Member tempMember = new Member(temp[0], temp[1], new Date(temp[2]), new Date(temp[3]),Location.NOVALUE);
            return tempMember;
        }*/
        if(temp[3] == null){
            Date threeMonth = DATE_CALL.threeMonthsDate();
            Member tempMember = new Member(temp[0], temp[1], dobtemp, threeMonth, Location.valueOf(temp[4].toUpperCase()));
            //System.out.println(threeMonth.print(threeMonth));
            return tempMember;
        }
        Date expiretemp = new Date(temp[3]);
        //System.out.println(expiretemp.print(expiretemp));
        Member tempMember = new Member(temp[0], temp[1], dobtemp,expiretemp , Location.valueOf(temp[4].toUpperCase()));
        return tempMember;
    }

    /**
     Interprets command for fitness class
     @param st to separate the tokens in the input
     @return String [] to easily access different tokens
     */
    public static String [] createFitness(StringTokenizer st){
        String[] temp = new String[5];
        for(int i = 0; st.hasMoreTokens(); i++){
            temp[i] = st.nextToken();
        }
        return temp;
    }

    /**
     Creates member for Fitness class
     @param temp String from above with Member information
     @param data to enter into database
     @return Member to create a list of participants
     */
    public static Member createFitMember(String [] temp, MemberDatabase data){
        Member fitnessMember = new Member(temp[1],temp[2], new Date(temp[3]),new Date("12/12/2028"),Location.NOVALUE);
        Member addData = data.PublicFindMember(fitnessMember);
        if(addData != null) {
            fitnessMember = new Member(temp[1], temp[2], new Date(temp[3]), addData.getExpire(), addData.getLocation());
        }
        return fitnessMember;
    }


    /*
    Call CG
    - Use find fucntion in Member Database and then use who am I.
     */


    /**
     Adds a person into the Array
     Checks if date of birth is Valid or person not in array
     @param st the tokens that have the member information
     */
    private void commandA(StringTokenizer st){
        String [] createString = createString(st);
        Location temploc = Location.EDISON;
        //System.out.println(createString[4]);
        if(temploc.isValid(createString[4]) == false){
            System.out.println(createString[4] + ": invalid location!");
        }
        else {
            Member newMember = createMember(createString);
            if(checkCalenderDobExp(newMember) == false);
            else if (checkTodFut(newMember.getDob()) == false){
                System.out.println("DOB" + " " + DATE_CALL.print(newMember.getDob()) + ": cannot be today or a future date!");
            }else if(checkAge(newMember.getDob()) == false) {
                System.out.println("DOB" + " " + DATE_CALL.print(newMember.getDob()) + ": must be 18 or older to join!");
            }
            else if(MLIST_DATA.add(newMember) == true){
                System.out.println (newMember.getFname() + " " + newMember.getLname() + " " + "added");
            }
            else if(MLIST_DATA.add(newMember) == false){
                System.out.println(newMember.getFname() + " " + newMember.getLname() + " is already in the database.");
            }
        }
    }

    /**
     Removes a person from the Array
     Checks if person is not in the database
     @param st the tokens that have the member information
     */
    private void commandR(StringTokenizer st){
        String [] createString = createString(st);
        Member deleteMember = createMember(createString);
        boolean check = MLIST_DATA.remove(deleteMember);
        if(check == true){
            System.out.println(deleteMember.getFname() + " " + deleteMember.getLname() +  " removed");
        }
        else {
            System.out.println(deleteMember.getFname() + " " + deleteMember.getLname() +  " is not in the database.");
        }
    }

    /**
     Adds a person to the fitnessClass Array
     Checks if fitness Class exists or if any time Conflicts exist
     @param st the tokens that have the member information
     */
    private void commandC(StringTokenizer st){
        String [] stAsStr = createFitness(st);
        //String originalFitClass = stAsStr[0];
        String fitClass = stAsStr[0].substring(0,1).toUpperCase() + stAsStr[0].substring(1).toLowerCase();
        Member newMember = createFitMember(stAsStr,MLIST_DATA);
        if(FITNESS_CALL.classExists(fitClass) <= -1){
            System.out.println(stAsStr[0] + " class does not exist.");
        }
        else if(checkCalenderDobExp(newMember) == false);
        else if(checkExp(newMember) == false);
        else if(FITNESS_CALL.alreadyCheckedIn(newMember,fitClass) == true){
            System.out.println(newMember.getFname() + " " + newMember.getLname() + " has already checked in " + fitClass + ".");
        }
        else if(FITNESS_CALL.timeConflict(newMember,fitClass) == true){
            if (fitClass.equals("Cardio")){
                System.out.println(fitClass + " time conflict —— " + newMember.getFname() + " " + newMember.getLname() + " has already checked in Spinning.");
            }
            if (fitClass.equals("Spinning")){
                System.out.println(fitClass + " time conflict —— " + newMember.getFname() + " " + newMember.getLname() + " has already checked in Cardio.");
            }
        }
        else if(MLIST_DATA.PublicFind(newMember) > -1){
            if(FITNESS_CALL.checkIn(newMember,fitClass) == true){
                System.out.println(newMember.getFname() + " " + newMember.getLname() + " checked in " + fitClass);
            }
        }
        else {
            System.out.println(newMember.getFname() + " " + newMember.getLname() + " " + DATE_CALL.print(newMember.getDob()) + " is not in the database.");
        }
    }

    /**
     Deletes a person in the fitnessClass Array
     Checks if fitness Class exists
     @param st the tokens that have the member information
     */
    private void commandD(StringTokenizer st){
        String [] stAsStr = createFitness(st);
        String fitClass = stAsStr[0];
        Member newMember = createFitMember(stAsStr,MLIST_DATA);
        if(FITNESS_CALL.classExists(fitClass) <= -1){
            System.out.println(fitClass + " class does not exist.");
        }
        else if(checkCalenderDobExp(newMember) == false);
        else if(FITNESS_CALL.dropClass(newMember,fitClass) == true){
            System.out.println(newMember.getFname() + " " + newMember.getLname() + " dropped " + fitClass + ".");
        }
        else {
            System.out.println(newMember.getFname() + " " + newMember.getLname() + " is not a participant in " + fitClass + ".");
        }
    }

    /**
     All Print commands for MemberDatabase Array
     Prints list as is, by expiration date and county and ZipCode
     @param command the command to be checked
     */
    private void commandP(String command){
        if(command.equals("P")){
            if(checkSize() == true);
            else MLIST_DATA.print();
        }
        else if(command.equals("PC")){
            if(checkSize() == true);
            else MLIST_DATA.printByCounty();
        }
        else if(command.equals("PD")){
            if(checkSize() == true);
            else MLIST_DATA.printByExpirationDate();
        }
        else if(command.equals("PN")){
            if(checkSize() == true);
            else MLIST_DATA.printByName();
        }
    }


    /**
     Run, the method that begins the processing of the commandLine
     Uses Scanner class to take in commandLine Arguments
     */
    public void run() throws IOException {

        //Scanner inFile = new Scanner(new File("memberList.txt"));
        //infile contains the reference to the file that is held here.
        //Can call inFile. () to call the txt
        Scanner scanner = new Scanner(System.in);
        String commandLine = scanner.nextLine();
        StringTokenizer st = new StringTokenizer(commandLine);
        String command = st.nextToken();
        System.out.println("\nGym Manager running...\n");
        while(!(command.equals("Q")) ) {
            isValidCommand(command);
            if(command.equals("A")){ //Plan to make a new private method and return one statement for each.
                commandA(st);
            }
            if(command.equals("R")){
                commandR(st);
            }
            if(command.equals("P") || command.equals("PC") || command.equals("PD") || command.equals("PN")) {
                commandP(command);
            }
            if(command.equals("S")){
                FITNESS_CALL.printSchedule();
            }
            if(command.equals("C")){
                commandC(st);
            }
            if(command.equals("D")){
                commandD(st);
            }
            if(command.equals("LM")) {
                String[] temp = new String[6];
                Scanner inFile = new Scanner(new File("/Users/leah/Desktop/CS213/Project1.1/src/fitness/memberList.txt"));
                while(inFile.hasNext()){
                    //System.out.println("Goes into the while loop");
                    commandLine = inFile.nextLine();
                    System.out.println(commandLine);
                    st = new StringTokenizer(commandLine);
                    temp = createString(st);
                    Member createMemberTemp = createMember(temp);
                    MLIST_DATA.add(createMemberTemp);
                }
                MLIST_DATA.print();
            }
            if(st.hasMoreTokens() == false){
                commandLine = scanner.nextLine();
                while(commandLine.length() == 0){
                    commandLine = scanner.nextLine();
                    st = new StringTokenizer(commandLine);
                }
                st = new StringTokenizer(commandLine);
                command = st.nextToken();
            }
        }
        if(command.equals("Q")){
            System.out.println("\nGym Manager terminated.");
        }
    }
}

/*
Questions :
    Fitness Class and Class Schedule
    - Guest ib fitness class?
    - Do we need multiple array lists
    - If time confict and class exist should be in fitness or classschedule (Class Schedule)
    -
    Member
    - MemberShip Fee she wants inputted (yearly) ?
 */

/*
A John Doe 9/2/2022 BRIDGEWATER
A John Doe 12/2/2022 BRIDGEWATER
A John Doe 12/20/2004 BRIDGEWATER
A John Doe 2/29/2003 BRIDGEWATER
A John Doe 4/31/2003 BRIDGEWATER
A John Doe 13/31/2003 BRIDGEWATER
A John Doe 3/32/2003 BRIDGEWATER
A John Doe -1/31/2003 BRIDGEWATER
LM
A John Doe 1/20/2003 BRIDGEWATER
A john doe 1/20/2003 BRIDGEWATER
A Jane Doe 6/1/1996 ABC
Q
This line is garbage!
 */