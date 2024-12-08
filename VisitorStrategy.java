package cm;

import java.math.BigDecimal;
import java.util.ArrayList;

public class VisitorStrategy implements RateCalculationStrategy {
    @Override
    public BigDecimal calculate(Period periodStay, BigDecimal normalRate, BigDecimal reducedRate, ArrayList<Period> normal, ArrayList<Period> reduced) {
        int normalRateHours = periodStay.occurences(normal);
        int reducedRateHours = periodStay.occurences(reduced);

        // Calculate the total cost
        BigDecimal normalCost = normalRate.multiply(BigDecimal.valueOf(normalRateHours));
        BigDecimal reducedCost = reducedRate.multiply(BigDecimal.valueOf(reducedRateHours));
        BigDecimal totalCost = normalCost.add(reducedCost);

        // Apply the 50% reduction for the visitor after the first 10.00
        if (totalCost.compareTo(BigDecimal.valueOf(10)) > 0) {
            totalCost = totalCost.subtract(BigDecimal.valueOf(10)).multiply(BigDecimal.valueOf(0.5)).add(BigDecimal.valueOf(10));
        }

        return totalCost;
    }
}

