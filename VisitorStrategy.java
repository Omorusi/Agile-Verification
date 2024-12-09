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

        // Apply visitor discount logic: Free up to $10, then 50% on the remaining
        if (totalCost.compareTo(BigDecimal.valueOf(10)) > 0) {
            BigDecimal excess = totalCost.subtract(BigDecimal.valueOf(10)); // Calculate excess over $10
            totalCost = excess.multiply(BigDecimal.valueOf(0.5)); // Apply 50% discount on excess
        } else {
            totalCost = BigDecimal.ZERO; // If total cost is $10 or less, it's free
        }

        return totalCost;
    }

}

