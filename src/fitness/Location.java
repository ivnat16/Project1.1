package fitness;

/**
 Enum Class Location with zipCode and County of the 5 Gym Chain Locations
 Includes a constructor,getter, isValid and CompareTo method
 @author Leah, Tanvi
 */
public enum Location {
    BRIDGEWATER("08807", "Somerset"),
    EDISON("08837", "Middlesex"),
    FRANKLIN("08873", "Somerset"),
    PISCATAWAY("08854", "Middlesex"),
    SOMERVILLE("08876", "Somerset"),

    NOVALUE("0","0");

    private final String zipcode;
    private final String county;

    /**
     Constructor for the Location Enum class
     @param zipcode the zipCode of the person in the city
     @param county the county of the person in the city
     */
    private Location(String zipcode,String county){
        this.zipcode = zipcode;
        this.county = county;
    }

    /**
     Getter Method for the Location Enum ZipCode
     @return the ZipCode of the given city
     */
    public String getZipcode(){
        return this.zipcode;
    }

    /**
     Getter Method for the Location Enum County
     @return the County of the given city
     */
    public String getCounty(){
        return this.county;
    }

    /**
     Checks if the given location is valid
     Creates a Location type from String
     @param location the location that needs to be checked
     @return true if the location isValid, false otherwise
     */
    public boolean isValid(String location){
        Location [] locArr = Location.values();
        for(int i = 0; i < locArr.length;i++){
            if (location.toUpperCase().equals(locArr[i].toString())){
                return true;
            }
        }
        return false;
    }

    /**
     Compares the ZipCode and County of two locations
     @param location the location that needs to be compared
     @return 1 or -1 if ZipCode or County are not the same, 0 otherwise
     */
    public int locCompareTo(Location location){
        if(this.county.compareTo(location.getCounty())>0||this.county.compareTo(location.getCounty())==0&& this.zipcode.compareTo(location.getZipcode())>0)
            return 1;
        if(this.county.compareTo(location.getCounty())==0&& this.zipcode.compareTo(location.getZipcode())==0)
            return 0;
        return -1;
    }
}

