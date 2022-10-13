package fitness;

    public class Premium extends Family{

        public int GUEST_PASS;

        /**
         * Constructor creates an instance of a member given all the attributes
         *
         * @param fname
         * @param lname
         * @param dob
         * @param expire
         * @param location
         */
        public Premium(String fname, String lname, Date dob, Date expire, Location location) {
            super(fname, lname, dob, expire, location);
            this.GUEST_PASS = 3;
        }

        @Override
        public String whoAmI() {
            return "I'm a Premium. ";
        }

        @Override
        public double membershipFee() {
            return (59.99*11);
        }

        @Override
        public int getGUEST_PASS(){
            return GUEST_PASS;
        }

       @Override
        public void setGUEST_PASS(int guestPass){
            this.GUEST_PASS = guestPass;
        }
    }

