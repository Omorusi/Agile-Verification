import java.util.List;

public class Rate {
    public enum CarParkKind {
        STAFF, STUDENT, VISITOR, MANAGEMENT
    }

    private CarParkKind carParkKind;
    private List<Period> reducedPeriods;
    private List<Period> normalPeriods;
    private double normalRate;
    private double reducedRate;

    public Rate(CarParkKind carParkKind, List<Period> reducedPeriods, List<Period> normalPeriods, double normalRate, double reducedRate) {
        if (normalRate < 0 || reducedRate < 0) {
            throw new IllegalArgumentException("Rates must be non-negative.");
        }
        if (normalRate < reducedRate) {
            throw new IllegalArgumentException("Normal rate must be greater than or equal to reduced rate.");
        }
        if (normalRate > 10 && carParkKind == CarParkKind.STAFF) {
            throw new IllegalArgumentException("Normal rate for STAFF cannot exceed 10.");
        }
        if (hasOverlappingPeriods(reducedPeriods, normalPeriods) || hasOverlappingPeriodsWithinCategory(reducedPeriods) || hasOverlappingPeriodsWithinCategory(normalPeriods)) {
            throw new IllegalArgumentException("Periods cannot overlap within or between categories.");
        }
        if (hasInvalidPeriods(reducedPeriods) || hasInvalidPeriods(normalPeriods)) {
            throw new IllegalArgumentException("Periods must have valid start and end times.");
        }

        this.carParkKind = carParkKind;
        this.reducedPeriods = reducedPeriods;
        this.normalPeriods = normalPeriods;
        this.normalRate = normalRate;
        this.reducedRate = reducedRate;
    }

    // Check for overlaps between reduced and normal periods
    private boolean hasOverlappingPeriods(List<Period> reducedPeriods, List<Period> normalPeriods) {
        for (Period reduced : reducedPeriods) {
            for (Period normal : normalPeriods) {
                if (reduced.overlaps(normal)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check for overlaps within the same category (either reduced or normal periods)
    private boolean hasOverlappingPeriodsWithinCategory(List<Period> periods) {
        for (int i = 0; i < periods.size(); i++) {
            for (int j = i + 1; j < periods.size(); j++) {
                if (periods.get(i).overlaps(periods.get(j))) {
                    return true;
                }
            }
        }
        return false;
    }

    // Check if periods have valid times
    private boolean hasInvalidPeriods(List<Period> periods) {
        for (Period period : periods) {
            if (period.getStartHour() >= period.getEndHour()) {
                return true;
            }
        }
        return false;
    }
}
