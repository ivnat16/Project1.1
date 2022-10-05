package fitness;
import java.util.StringTokenizer;

/**
 Fitness class provides all the instances of different classes present.
 There are three available classes each day and they have their own set times and instructors
 @author Tanvi Thigle, Leah Ranavat
 */
public class FitnessClass {
    private Time time;
    private String instructor;
    public static final String [] fitness_class_list
            = {"Pilates","Spinning", "Cardio"};
    Member [] pilates_participants = new Member[100];
    Member [] spinning_participants = new Member[100];
    Member [] cardio_participants = new Member[100];

    /**
     Constructor makes instances of classes and assigns time and instructors
     @param fitness_class that the member wants
     */
    public FitnessClass(String fitness_class){
        if(fitness_class.equals("Pilates")){
            this.time = Time.MORNING;
            this.instructor = "Jennifer";
        }
        if(fitness_class.equals("Cardio")) {
            this.time = Time.AFTERNOON;
            this.instructor = "Kim";
        }
        if(fitness_class.equals("Spinning")) {
            this.time = Time.AFTERNOON;
            this.instructor = "Denise";
        }
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

    /**
     Checks in participants if they meet all the criteria
     @param member member that needs to check in
     @param fit_class the class to be added too
     @return true if member checked in, false if not
     */
    public boolean checkIn(Member member, String fit_class){
            if(member.getDob().dobIsValid(member.getDob())){
                if(member.getExpire().expIsValid(member.getExpire())){
                    if(classExists(fit_class) > -1){
                        if(alreadyCheckedIn(member, fit_class) == false){
                            if(timeConflict(member, fit_class)==false){
                                return addParticipant(member, fit_class);
                            }
                        }
                    }
                }
            }
        return false;
    }

    /**
     Adds in participants in the respective class list
     @param member the member that needs to be added
     @param fitness_class the fitness class to add the member
     @return true if member added in, false if not
     */
    public boolean addParticipant(Member member, String fitness_class){
        if( fitness_class.equals("Pilates") ){
            if( pilates_participants[0] == null ){
                pilates_participants[0] = member;
                return true;
            }
            for( int i = 0; i < pilates_participants.length; i++ ){
                int j = i + 1;
                if( pilates_participants[i] != null && pilates_participants[j] == null ){
                    pilates_participants[j] = member;
                    return true;
                }
            }
        }
        if( fitness_class.equals("Spinning") ){
            if( spinning_participants[0] == null ) {
                spinning_participants[0] = member;
                return true;
            }
            for( int i = 0; i < spinning_participants.length; i++ ){
                int j = i + 1;
                if( spinning_participants[i] != null && spinning_participants[j] == null ){
                    spinning_participants[j] = member;
                    return true;
                }
            }
        }
        if( fitness_class.equals("Cardio") ){
            if( cardio_participants[0] == null ){
                cardio_participants[0] = member;
                return true;
            }
            for( int i = 0; i < cardio_participants.length; i++ ){
                int j = i + 1;
                if( cardio_participants[i] !=null && cardio_participants[j] == null ){ cardio_participants[j] = member;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     Checks if class exists at the Gym
     @param fit_class to check with existing classes
     @return int -1 if class does not exist, index value if it does
     */
    public int classExists(String fit_class){
        for( int i = 0; i < fitness_class_list.length; i++ ){
            if( fitness_class_list[i].equals(fit_class) )
                return i;
        }
        return -1;
    }

    /**
     Checks if there is a time conflict for participants to attend classes
     @param member the member to be checked
     @param fit_class the class the member is in
     @return true if there is a time conflict, false if there is not.
     */
    public boolean timeConflict(Member member, String fit_class){ // USE TIME!!
        if( fit_class.equals("Cardio") && alreadyCheckedIn(member, "Spinning") )
            return true;
        if( fit_class.equals("Spinning") && alreadyCheckedIn(member, "Cardio") )
            return true;
        return false;
    }

    /**
     Checks if member has already checked into a class they are trying to check into again
     @param member the member to check
     @param fitness_class the class checked into
     @return true if already checked in, false if not.
     */
    public boolean alreadyCheckedIn(Member member, String fitness_class){
        if(fitness_class.equals("Cardio")){
            if(cardio_participants[0] == null){
                return false;
            }
            for(int i =0; i< cardio_participants.length; i++){
                if(cardio_participants[i]!= null) {
                    if (cardio_participants[i].equals(member)) {
                        return true;
                    }
                }
            }
        }
        if(fitness_class.equals("Spinning")){
            if(spinning_participants[0] == null){
                return false;
            }
            for( int i =0; i< spinning_participants.length; i++ ){
                if( spinning_participants[i]!= null ) {
                    if ( spinning_participants[i].equals(member) ) {
                        return true;
                    }
                }
            }
        }
        if( fitness_class.equals("Pilates") ){
            if( pilates_participants[0] == null ){
                return false;
            }
            for(int i =0; i< pilates_participants.length; i++){
                if(pilates_participants[i]!= null){
                    if(pilates_participants[i].equals(member)){
                        return true;
                    }
                }

            }
        }
        return false;
    }

    /**
     Removes member from participants list
     @param member the member to be dropped
     @param fit_class the class to be dropped from
     @return true if drop is successful, false if not.
     */
    public boolean dropClass(Member member, String fit_class){ //exists in fit_class array then remove
        if(alreadyCheckedIn(member, fit_class)){

            if(fit_class.equals("Cardio")){
                for(int i =0; i <cardio_participants.length ; i++){
                    int j = i+1;
                    if(member.equals(cardio_participants[i])){
                        while(j<cardio_participants.length) {
                            cardio_participants[j - 1] = cardio_participants[j];
                            j++;
                        }
                        return true;
                    }
                }
            }
            if(fit_class.equals("Spinning")){
                for(int i =0; i <spinning_participants.length ; i++){
                    int j = i+1;
                    if(member.equals(spinning_participants[i])){
                        while(j<spinning_participants.length) {
                            spinning_participants[j - 1] = spinning_participants[j];
                            j++;
                        }
                        return true;
                    }
                }
            }
            if(fit_class.equals("Pilates")){
                for(int i =0; i <pilates_participants.length ; i++){
                    int j = i+1;
                    if(member.equals(pilates_participants[i])){
                        while(j<pilates_participants.length) {
                            pilates_participants[j - 1] = pilates_participants[j];
                            j++;
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     Prints the schedule of all fitness classes
     */
    public void printSchedule(){
        System .out.println("\n-Fitness classes-");
        System .out.println("Pilates — JENNIFER 9:30");
        if(pilates_participants[0] != null){
            System.out.println("\t ** participants **");
        }
        for(int i =0; i< pilates_participants.length; i++){
            if(pilates_participants[i]!=null){
                System.out.println("\t\t" + pilates_participants[i].getFname() + " " + pilates_participants[i].getLname() + ", " + "DOB:" + pilates_participants[i].getDob().print(pilates_participants[i].getDob()) + ", " + "Membership expires " + pilates_participants[i].getExpire().print(pilates_participants[i].getExpire()) + ", " + "Location: " + pilates_participants[i].getLocation() + ", " + pilates_participants[i].getLocation().getZipcode() + ", " + pilates_participants[i].getLocation().getCounty().toUpperCase());
            }
        }
        System .out.println("Spinning — DENISE 14:00");
        if(spinning_participants[0] != null){
            System.out.println("\t ** participants **");
        }
        for(int i =0; i< spinning_participants.length; i++){
            if(spinning_participants[i]!=null){
                System.out.println("\t\t" + spinning_participants[i].getFname() + " " + spinning_participants[i].getLname() + ", " + "DOB:" + spinning_participants[i].getDob().print(spinning_participants[i].getDob()) + ", " + "Membership expires " + spinning_participants[i].getExpire().print(spinning_participants[i].getExpire()) + ", " + "Location: " + spinning_participants[i].getLocation() + ", " + spinning_participants[i].getLocation().getZipcode() + ", " + spinning_participants[i].getLocation().getCounty().toUpperCase());
            }
        }
        System .out.println("Cardio — KIM 14:00");
        if(cardio_participants[0] != null){
            System.out.println("\t ** participants **");
        }
        for(int i =0; i< cardio_participants.length; i++){
            if(cardio_participants[i]!=null){
                System.out.println("\t\t" + cardio_participants[i].getFname() + " " + cardio_participants[i].getLname() + ", " + "DOB:" + cardio_participants[i].getDob().print(cardio_participants[i].getDob()) + ", " + "Membership expires " + cardio_participants[i].getExpire().print(cardio_participants[i].getExpire()) + ", " + "Location: " + cardio_participants[i].getLocation() + ", " + cardio_participants[i].getLocation().getZipcode() + ", " + cardio_participants[i].getLocation().getCounty().toUpperCase());
            }
        }
        System.out.println("\n");
    }
}