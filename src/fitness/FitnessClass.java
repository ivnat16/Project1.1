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
     Checks in participants if they meet all the criteria
     @param member member that needs to check in
     @return true if member checked in, false if not
     */
    public boolean checkIn(Member member){
            if(member.getDob().dobIsValid(member.getDob())){
                if(member.getExpire().expIsValid(member.getExpire())){
                    if(memberTimeConflict(member) < 0){

                        if(member instanceof Member && member.getLocation() == this.location){
                            System.out.println("In check in");
                            if(alreadyCheckedIn(member) == false){
                                return participants.add(member);
                            }
                        }
                        else if(member instanceof Family || member instanceof Premium){
                            if(alreadyCheckedIn(member) == false){
                                return participants.add(member);
                            }
                        }
                    }


                    //put in schedule?

                }
            }
        return false;
    }

    /**
     Checks in guests if they meet all the criteria
     @param member member that needs to check in
     @return true if guest checked in, false if not
     */
    public boolean guestCheckIn(Member member){
        if(member.getDob().dobIsValid(member.getDob())){
            if(member.getExpire().expIsValid(member.getExpire())){
                //put in schedule?
                    return addGuest(member);
            }
        }
        return false;
    }

    /**
     Adds in guests in the respective list
     @param member the member that needs to be added
     @return true if guest added in, false if not
     */
    public boolean addGuest(Member member){
        if(member instanceof Family && ((Family) member).GUEST_PASS > 0 && member.getLocation() == this.location){
            guests.add(member);
            int b = ((Premium) member).getGUEST_PASS();
            ((Premium) member).setGUEST_PASS(b-1);
             return true;
        }
        else if (member instanceof Premium && ((Premium) member).GUEST_PASS > 0 && member.getLocation() == this.location){
            guests.add(member);
            int a = ((Premium) member).getGUEST_PASS();
            ((Premium) member).setGUEST_PASS(a-1);
            return true;
        }
        return false;
    }


    /**
     Checks if member has already checked into a class they are trying to check into again
     @param member the member to check
     @return true if already checked in, false if not.
     */
    public boolean alreadyCheckedIn(Member member){
            if(participants == null){
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
     Checks if time conflict
     @param member to check with existing classes
     @return int true if class does not exist, index value if it does
     */
    public int memberTimeConflict(Member member){ // in class schedule?
        ClassSchedule c = new ClassSchedule();
       FitnessClass[] x =  c.timeConflict(this);
       for(int i =0; i< x.length; i++){
           if(x[i]!=null){
               for(int j =0; j< x[i].participants.size(); j++){
                   if(member.equals(x[i].participants.get(j))){
                       return i;
                   }
               }
           }

       }
       return -1;
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
     Removes member from participants list
     @param member the member to be dropped
     @return true if drop is successful, false if not.
     */
    public boolean guestDoneClass(Member member){ //exists in fit_class array then remove

            for (int i = 0; i < guests.size(); i++) {
                if (member.equals(guests.get(i))) {
                    guests.remove(i);
                    return true;
                }
            }

        return false;
    }


    /**
     Prints all the information of the member
     @return String member
     */
    @Override
    public String toString(){
        String std = type + " - " + instructor + ", " + time.getHour() + ", " + location;
        String p = "\n Participants ";
        String g = "\n Guests ";
        //System.out.println("Participants");
        for(int i=0; i< participants.size(); i++){
            if(participants.get(i)!= null){
               p += "\n" + participants.get(i);

            }
        }
        for(int i=0; i< guests.size(); i++){
            if(guests.get(i)!= null){
                g += "\n" + guests.get(i);

            }
        }
        String res = std;
        if(participants.size()>0)
            res += p;
        if(guests.size()>0)
            res += g;
        return res;

    }

    /*FitnessClass x = new FitnessClass(Pilates, KIM, MORNING, FRANKLIN);
    FitnessClass y = new FitnessClass(Pilates, KIM, MORNING, FRANKLIN);

    x.getTime()
    y.getTime()

    participantsX = x.getParticipants()
    participantsY = y

    for i in x.getParticipants{
        if is in y.getParticipants {

        }
    }*/
}