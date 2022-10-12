package fitness;

/**
 Time enum class defines the times for fitness classes
 There are two set times at which different Fitness classes take place.
 @author Leah Ranavat, Tanvi Thigle
 */
public enum Time {

    MORNING ("9:30"),
    AFTERNOON("14:00"),
    EVENING("18:30");

    private final String hour;
    //private final int minute;

    /**
     Constructor used to set time
     @param hour, minute that it needs to be set to
     */
    Time(String hour){
        this.hour = hour;
       // this.minute = minute;
    }

    /**
     Gets the value for hour
     @return int hour to access the private instance variable
     */
    public String getHour(){
        return hour;
    }

    /**
     Gets the value for minute
     @return int minute to access the private instance variable
     */
    //public int getMinute(){
        //return minute;
    //}

}
