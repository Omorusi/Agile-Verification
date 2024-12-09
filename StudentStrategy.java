package cm;

import java.math.BigDecimal;
import java.util.ArrayList;

public class StudentStrategy implements RateCalculationStrategy {
    @Override
    public BigDecimal calculate(Period periodStay, BigDecimal normalRate, BigDecimal reducedRate, ArrayList<Period> normal, ArrayList<Period> reduced) {
        int normalRateHours = periodStay.occurences(normal);
        int reducedRateHours = periodStay.occurences(reduced);

        // Calculate the total cost
        BigDecimal normalCost = normalRate.multiply(BigDecimal.valueOf(normalRateHours));
        BigDecimal reducedCost = reducedRate.multiply(BigDecimal.valueOf(reducedRateHours));
        BigDecimal totalCost = normalCost.add(reducedCost);

        // Apply the 25% reduction for students above 5.50
        if (totalCost.compareTo(BigDecimal.valueOf(5.50)) > 0) {
            BigDecimal excess = totalCost.subtract(BigDecimal.valueOf(5.50));
            BigDecimal reducedExcess = excess.multiply(BigDecimal.valueOf(0.75)); // 25% reduction
            totalCost = BigDecimal.valueOf(5.50).add(reducedExcess);
        }

        return totalCost;
    }
}
