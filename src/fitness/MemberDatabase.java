package fitness;

/**
MemberDatabase, Array-Based Linear data structure to hold the list of members
Used to store, add, remove and print members in different orders
@author Tanvi Thigle, Leah Ranavat
*/
public class MemberDatabase {
    private Member[] mlist;
    private int size;
    public static final int NOTFOUND = -1;

    /**
    Getter method for size
    @return size of mlist
    */
    public int getSize(){
        return size;
    }

     /**
     Private Find, searches a member in the list
     @param member the member to find
     @return the index i of the member or NOTFOUND(-1) if the member is not in the list
     */
    private int find(Member member) {
        for(int i = 0; i < size; i++){
            if(member.equals(mlist[i])){
                return i;
            }
        }
        return NOTFOUND;
    }

    /**
    Public Find, searches a member in the list
    Calls the private function find
    @param member the member to find
    @return the index i of the member or NOTFOUND(-1) if the member is not in the list
    */
    public int PublicFind(Member member) {
        return find(member);
    }

    /**
    Public Find, searches a member in the list
    Calls the private function find
    @param member the member to find
    @return the member at index i or null if the NOTFOUND
    */
    public Member PublicFindMember(Member member) {
        int index = find(member);
        if( index == NOTFOUND ){
            return null;
        }
        return mlist[index];
    }

    /**
    Grow, a growable container with an initial capacity of 4
    Grows the container by 4 automatically when full
    */
    private void grow() {
        if(size == 0){
            size = 4;
            this.mlist = new Member[size];
        }
        if(mlist[size-1]!=null){
            size = size+4;
            Member [] templist = new Member[size];
            for(int i =0; i<size-4; i++){
                templist[i] = mlist[i];
            }
            this.mlist = templist;
        }
    }

    /**
    Adds the member to mlist
    Calls the grow() function to increase capacity
    @param member the member to add to the list
    @return true if person was added, false otherwise.
    */
    public boolean add(Member member) {
        if(size==0) grow();
        if(mlist[size-1]!=null){
            grow();
        }
        if(find(member)==NOTFOUND){
            for(int i=0; i<size; i++){
                if(mlist[i]==null){
                    mlist[i] = member;
                    return true;
                }
            }
        }

        return false;
    }

    /**
    Removes the member from mlist
    Calls the private find function
    @param member the member to delete.
    @return true if person was deleted, false otherwise.
    */
    public boolean remove(Member member) {
        if(find(member)!=NOTFOUND){
            for(int i = 0; i < size;i++){
                int j = i+1;
                if(member.equals(mlist[i])){
                    while(j<size) {
                        mlist[i] = mlist[j];
                        j++;
                    }
                }
            }
            size --;
            return true;
        }
        return false;
    }

    /**
    Prints the array contents as is
    */
    public void print () {
        System.out.println("\n-list of members-");
        for(int i =0; i<size; i++){
            if(mlist[i] != null){
                System.out.println(mlist[i].getFname() + " "
                        + mlist[i].getLname() + ", "
                        + "DOB: " + mlist[i].getDob().print(mlist[i].getDob())
                        + ", " + "Membership expires "
                        + mlist[i].getExpire().print(mlist[i].getExpire())
                        + "," + " Location: " + mlist[i].getLocation()
                        + ", " + mlist[i].getLocation().getZipcode()
                        + ", " + mlist[i].getLocation().getCounty().toUpperCase());
            }
        }
        System.out.println("-end of list-\n");
    }

    /**
    Prints the array sorted by county and then zipcode
    */
    public void printByCounty() {
        Member temp = new Member("", "", null, null, null);
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if(mlist[i] != null) {
                    if (mlist[i].getLocation().locCompareTo(mlist[j].getLocation()) > 0) {
                        temp = mlist[i];
                        mlist[i] = mlist[j];
                        mlist[j] = temp;
                    }
                }
            }
        }
        System.out.println("\n-list of members sorted by county and zipcode-");
        for (int i = 0; i < size; i++) {
            if(mlist[i] != null){
                System.out.println(mlist[i].getFname() + " "
                        + mlist[i].getLname() + ", " +  "DOB: "
                        + mlist[i].getDob().print(mlist[i].getDob()) + ", "
                        + "Membership expires "
                        + mlist[i].getExpire().print(mlist[i].getExpire())
                        + "," + " Location: " + mlist[i].getLocation()
                        + ", " + mlist[i].getLocation().getZipcode()
                        + ", " + mlist[i].getLocation().getCounty().toUpperCase());
            }
        }
        System.out.println("-end of list-" + "\n");
    }

    /**
    Prints the array sorted by the expiration date
    */
    public void printByExpirationDate() { //sort by the expiration date
        Member temp1 = new Member("", "", null, null, null);
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if(mlist[i] != null) {
                    if(mlist[j] != null){
                        if (mlist[i].getExpire().compareTo(mlist[j].getExpire()) > 0) {
                            // swapping
                            temp1 = mlist[i];
                            mlist[i] = mlist[j];
                            mlist[j] = temp1;
                        }
                    }
                }
            }
        }
        System.out.println("\n-list of members sorted by membership expiration date-");
        for (int i = 0; i < size; i++) {
            if(mlist[i] != null){
                System.out.println(mlist[i].getFname() + " "
                        + mlist[i].getLname() + ", "
                        + "DOB: " + mlist[i].getDob().print(mlist[i].getDob())
                        + ", " + "Membership expires "
                        + mlist[i].getExpire().print(mlist[i].getExpire()) + ","
                        + " Location: " + mlist[i].getLocation()
                        + ", " + mlist[i].getLocation().getZipcode()
                        + ", " + mlist[i].getLocation().getCounty().toUpperCase());
            }
        }
        System.out.println("-end of list-\n");
    }

    /**
    Prints the array sorted by last name and then first name
    */
    public void printByName(){ //sort by last name and then first name
        Member temp3 = new Member("", "", null, null, null);
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
               if(mlist[i] != null) {
                   if (mlist[i].compareTo(mlist[j]) > 0) {
                       temp3 = mlist[i];
                       mlist[i] = mlist[j];
                       mlist[j] = temp3;
                   }
               }
            }
        }
        System.out.println("\n-list of members sorted by last name, and first name-");
        for (int i = 0; i < size; i++) {
            System.out.println(mlist[i].getFname() + " "
                    + mlist[i].getLname() + ", " +  "DOB: "
                    + mlist[i].getDob().print(mlist[i].getDob())
                    + ", " + "Membership expires "
                    + mlist[i].getExpire().print(mlist[i].getExpire())
                    + "," + " Location: " + mlist[i].getLocation()
                    + ", " + mlist[i].getLocation().getZipcode()
                    + ", " + mlist[i].getLocation().getCounty().toUpperCase());
        }
        System.out.println("-end of list-" + "\n");
    }

    public static void main (String []args){
        Member t1 = new Member("Leah", "Ranavat",new Date("10/28/2002"), new Date("12/28/2027"),Location.SOMERVILLE);
        Member t5 = new Member("Tanvi", "Ranavat",new Date("5/13/2002"), new Date("12/11/2027"),Location.PISCATAWAY);
        Member t2 = new Member("Tanvi", "Thigle",new Date("5/4/2002"), new Date("12/28/2027"),Location.BRIDGEWATER);
        Member t3 = new Member("Neil", "Surujdeen", new Date ("11/28/2000"), new Date("11/13/2025"), Location.FRANKLIN);
        Member t4 = new Member("Allison", "Fu", new Date ("03/25/2002"), new Date("11/11/2024"), Location.EDISON);

        MemberDatabase test = new MemberDatabase();
        test.add(t1);
        test.add(t2);
        test.add(t3);
        test.add(t4);

        test.add(t5);
        test.print();

        test.remove(t1);
        test.print();

        test.printByName();
        test.printByCounty();
        test.printByExpirationDate();

    }
}
