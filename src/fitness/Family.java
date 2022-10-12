package fitness;

    public class Family extends Member{

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
        public Family(String fname, String lname, Date dob, Date expire, Location location) {
            super(fname, lname, dob, expire, location);
            this.GUEST_PASS = 1;
        }

        @Override
        public String whoAmI() {
            return "Family.";
        }

        @Override
        public double membershipFee() {
            //How to do annually and per month???
            return 29.99 + (59.99*3);
        }





    }

