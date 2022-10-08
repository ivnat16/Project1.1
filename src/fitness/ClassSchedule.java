package fitness;

public class ClassSchedule {
    private FitnessClass [] classes;
    private int numClasses;

    public ClassSchedule(FitnessClass [] classes, int numClasses){
        this.classes = classes;
        this.numClasses = numClasses;
    }
    public int getNumClasses(){
        return numClasses;
    }

    public FitnessClass [] getClasses(){
        return classes;
    }

    public void print(){

    }
}
