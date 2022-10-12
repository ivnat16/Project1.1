package fitness;
import java.util.StringTokenizer;
import java.util.ArrayList;

/**
 Fitness class provides all the instances of different classes present.
 There are three available classes each day and they have their own set times and instructors
 @author Tanvi Thigle, Leah Ranavat
 */
public class FitnessClass {
    private Time time;
    private Instructor instructor;//unique

    private Location location;//unique
    private String type;//unique

   private ArrayList <Member> participants = new ArrayList<>();
   private ArrayList <Member> guests = new ArrayList<>();


    /**
     Constructor makes instances of classes and assigns time and instructors
     @param type, @param ins, @param loc, @param time, that the member wants
     */
    public FitnessClass(String type, Instructor ins, Time time,Location loc){
       this.type = type;
       this.instructor = ins;
       this.location = loc;
       this. time = time;
    }
    /**
     Gets class type
     @return String type
     */
    public String getClassType(){
        return this.type;
    }

    /**
     Gets class location
     @return Location location
     */
    public Location getClassLocation(){
        return this.location;
    }

    /**
     Gets class instructor
     @return Instructor instructor
     */
    public Instructor getInstructor(){
        return this.instructor;
    }

    /**
     Gets class time
     @return Time time
     */
    public Time getClassTime(){
        return this.time;
    }

    /**
     Checks if a fitness class is the same as another.
     A fitness class is uniquely identified by type, instructor, and location.
     @param obj the object to be checked
     @return true if the same false if different
     */
    @Override
    public boolean equals(Object obj){
        if(obj instanceof FitnessClass){
            FitnessClass testClass = (FitnessClass) obj; //casting
            return ((testClass.type.equalsIgnoreCase(this.type)) && (testClass.location.equals(this.location)) && (testClass.instructor.equals(this.instructor)));
        }
        return false;
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
                    //put in schedule?
                    if(alreadyCheckedIn(member) == false){
                        return addParticipant(member);
                    }
                }
            }
        return false;
    }

    /**
     Adds in participants in the respective class list
     @param member the member that needs to be added
     @return true if member added in, false if not
     */
    public boolean addParticipant(Member member){
            if( participants.get(0) == null ){
                participants.set(0, member);
                return true;
            }
            for( int i = 0; i < participants.size(); i++ ){
                int j = i + 1;
                if( participants.get(i) != null && participants.get(j) == null ){
                    participants.add(j, member);
                    return true;
                }
            }

        return false;
    }


    /**
     Checks if member has already checked into a class they are trying to check into again
     @param member the member to check
     @return true if already checked in, false if not.
     */
    public boolean alreadyCheckedIn(Member member){
            if(participants.get(0) == null){
                return false;
            }
            for(int i =0; i< participants.size(); i++){
                if(participants.get(i)!= null) {
                    if (participants.get(i).equals(member)) {
                        return true;
                    }
                }
            }

        return false;
    }

    /**
     Removes member from participants list
     @param member the member to be dropped
     @return true if drop is successful, false if not.
     */
    public boolean doneClass(Member member){ //exists in fit_class array then remove
        if(alreadyCheckedIn(member)){

                for(int i =0; i <participants.size() ; i++){
                    if(member.equals(participants.get(i))){
                            participants.remove(i);
                            return true;
                    }
                }
        }
        return false;
    }

    /**
     Checks if there is a time conflict for participants to attend classes
     @param fitnessClass the class the member is in
     @return true if there is a time conflict, false if there is not.
     */
    public boolean timeConflict(FitnessClass fitnessClass){ // USE TIME!! In class schedule?
            if(fitnessClass.getClassTime()==this.time){
                return true;
            }
        return false;
    }

    /**
     Prints all the information of the member
     @return String member
     */
    @Override
    public String toString(){
        return type + " - " + instructor + ", " + time.getHour() + ", " + location;
        //ADD LIST OF PARTICIPANTS HERE
    }
}