import java.math.BigDecimal;
import java.util.ArrayList;

public class Rate {

    // Enum representing the type of car park (standard, staff, etc.)
    public enum CarParkKind {
        STANDARD, STAFF, STUDENT, MANAGEMENT, VISITOR
    }

    private CarParkKind kind;
    private ArrayList<Period> reducedPeriods;
    private ArrayList<Period> normalPeriods;
    private BigDecimal normalRate;
    private BigDecimal reducedRate;

    // Constructor for Rate class
    public Rate(CarParkKind kind, ArrayList<Period> reducedPeriods, ArrayList<Period> normalPeriods, BigDecimal normalRate, BigDecimal reducedRate) {
        if (normalRate.compareTo(BigDecimal.ZERO) < 0 || reducedRate.compareTo(BigDecimal.ZERO) < 0 ||
                normalRate.compareTo(new BigDecimal("10")) > 0 || reducedRate.compareTo(new BigDecimal("10")) > 0 ||
                normalRate.compareTo(reducedRate) < 0) {
            throw new IllegalArgumentException("Rates are not valid");
        }
        if (periodsOverlap(reducedPeriods) || periodsOverlap(normalPeriods) || periodsOverlapBetween(reducedPeriods, normalPeriods)) {
            throw new IllegalArgumentException("Periods overlap");
        }
        this.kind = kind;
        this.reducedPeriods = reducedPeriods;
        this.normalPeriods = normalPeriods;
        this.normalRate = normalRate;
        this.reducedRate = reducedRate;
    }

    // Calculate method
    public BigDecimal calculate(Period periodStay) {
        BigDecimal total = BigDecimal.ZERO;

        int startHour = periodStay.getStartHour();
        int endHour = periodStay.getEndHour();

        for (int hour = startHour; hour < endHour; hour++) {
            if (isInReducedPeriod(hour)) {
                total = total.add(reducedRate);
            } else if (isInNormalPeriod(hour)) {
                total = total.add(normalRate);
            }
        }

        return total;
    }

    // Helper method to check if a given hour is in the reduced rate period
    private boolean isInReducedPeriod(int hour) {
        for (Period period : reducedPeriods) {
            if (hour >= period.getStartHour() && hour < period.getEndHour()) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check if a given hour is in the normal rate period
    private boolean isInNormalPeriod(int hour) {
        for (Period period : normalPeriods) {
            if (hour >= period.getStartHour() && hour < period.getEndHour()) {
                return true;
            }
        }
        return false;
    }

    // Helper method to check if any periods overlap within a list
    private boolean periodsOverlap(ArrayList<Period> periods) {
        for (int i = 0; i < periods.size(); i++) {
            for (int j = i + 1; j < periods.size(); j++) {
                if (periods.get(i).overlaps(periods.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    // Helper method to check if periods overlap between two lists
    private boolean periodsOverlapBetween(ArrayList<Period> periods1, ArrayList<Period> periods2) {
        for (Period p1 : periods1) {
            for (Period p2 : periods2) {
                if (p1.overlaps(p2)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Getter for CarParkKind
    public CarParkKind getKind() {
        return kind;
    }

    // Getters for reduced and normal periods
    public ArrayList<Period> getReducedPeriods() {
        return reducedPeriods;
    }

    public ArrayList<Period> getNormalPeriods() {
        return normalPeriods;
    }

    // Getters for rates
    public BigDecimal getNormalRate() {
        return normalRate;
    }

    public BigDecimal getReducedRate() {
        return reducedRate;
    }
}
