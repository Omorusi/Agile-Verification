public class Period {

    // variables
    int startHour ;
    int endHour ;



    public Period(int startHour, int endHour) {
        this.startHour = startHour;
        this.endHour = endHour;

        if (startHour < 0 || endHour < 0 || startHour > 24 || endHour > 24 || startHour >= endHour) {
            throw new IllegalArgumentException("Invalid time period");
        }
    }

     // Getters
        public int getStartHour()
        {
            return startHour;
        }


        public int getEndHour()
        {
            return endHour;
        }

        public int duration(){
        return endHour - startHour;
        }
}
