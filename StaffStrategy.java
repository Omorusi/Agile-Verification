package cm;

import java.math.BigDecimal;
import java.util.ArrayList;

public class StaffStrategy implements RateCalculationStrategy {
    @Override
    public BigDecimal calculate(Period periodStay, BigDecimal normalRate, BigDecimal reducedRate, ArrayList<Period> normal, ArrayList<Period> reduced) {
        int normalRateHours = periodStay.occurences(normal);
        int reducedRateHours = periodStay.occurences(reduced);

        // Calculate the total cost
        BigDecimal normalCost = normalRate.multiply(BigDecimal.valueOf(normalRateHours));
        BigDecimal reducedCost = reducedRate.multiply(BigDecimal.valueOf(reducedRateHours));
        BigDecimal totalCost = normalCost.add(reducedCost);

        // Ensure maximum payable of 16.00
        if (totalCost.compareTo(BigDecimal.valueOf(16)) > 0) {
            totalCost = BigDecimal.valueOf(16);
        }

        return totalCost;
    }
}
