package fitness;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.util.StringTokenizer;

public class ClassSchedule {
    private FitnessClass [] classes;
    private int numClasses;

    public ClassSchedule(){
        this.classes = null;
        this.numClasses = 0;
    }
    public int getNumClasses(){
        return numClasses;
    }

    public FitnessClass [] getClasses(){

        return classes;
    }

    /**
     Checks if class exists at the Gym
     @param fitnessClass to check with existing classes
     @return int -1 if class does not exist, index value if it does
     */
    public int classExists(FitnessClass fitnessClass){ // in class schedule?
        for( int i = 0; i < classes.length; i++ ){
            if( classes[i].equals(fitnessClass) )
                return i;
        }
        return -1;
    }
    /**
     Grow, a growable container with an initial capacity of 4
     Grows the container by 4 automatically when full
     */
    private void grow() {
        if(numClasses == 0){
            numClasses = 4;
            this.classes = new FitnessClass[numClasses];
        }
        if(classes[numClasses-1]!=null){
            numClasses = numClasses+4;
            FitnessClass [] tempClasses = new FitnessClass[numClasses];
            for(int i =0; i<numClasses-4; i++){
                tempClasses[i] = classes[i];
            }
            this.classes = tempClasses;
        }
    }

    /**
     Adds the member to mlist
     Calls the grow() function to increase capacity
     @param fitnessClass the member to add to the list
     @return true if person was added, false otherwise.
     */
    public boolean add(FitnessClass fitnessClass) {
        if(numClasses==0) grow();
        if(classes[numClasses-1]!=null){
            grow();
        }
            for(int i=0; i<numClasses; i++){
                if(classes[i]==null){
                    classes[i] = fitnessClass;
                    return true;
                }
            }


        return false;
    }



    /**
     Prints the schedule of all fitness classes
*/
    public void printSchedule(){
        //System .out.println("\n-Fitness class-");
        System .out.println("-Fitness classes loaded-");
        for(int i =0; i< numClasses; i++){
            if(classes[i]!=null){
                System.out.println(classes[i]);

            }
        }
        System .out.println("-end of class list.");

    }


    private static String [] createString (StringTokenizer st){
        String[] temp = new String[4];
        for(int i = 0; st.hasMoreTokens(); i++){
            temp[i]= st.nextToken();
        }
        return temp;
    }

    private static FitnessClass createFitnessClass(String [] temp){
        FitnessClass tempFit = new FitnessClass(temp[0].toUpperCase(), Instructor.valueOf(temp[1].toUpperCase()),Time.valueOf(temp[2].toUpperCase()) ,Location.valueOf(temp[3].toUpperCase()));
        return tempFit;
    }

    public static void main (String [] args) throws FileNotFoundException {
        Scanner inFile = new Scanner( new File("/Users/tanvi/Desktop/IntelliJ/Project1.1/src/fitness/classSchedule.txt"));
        String command;
        StringTokenizer st;
        String [] temp;
        ClassSchedule check = new ClassSchedule();
        while(inFile.hasNext()){
            command = inFile.nextLine();
            st = new StringTokenizer(command);
            temp = createString(st);
            FitnessClass newFit = createFitnessClass(temp);
            check.add(newFit);
           // System.out.println(newFit);
        }
    check.printSchedule();
    }


}
