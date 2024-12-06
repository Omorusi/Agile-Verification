package cm;


import java.math.BigDecimal;
import java.util.ArrayList;

public class ManagementStrategy implements RateCalculationStrategy {
    @Override
    public BigDecimal calculate(Period periodStay, BigDecimal normalRate, BigDecimal reducedRate, ArrayList<Period> normal, ArrayList<Period> reduced) {
        int normalRateHours = periodStay.occurences(normal);
        int reducedRateHours = periodStay.occurences(reduced);

        // Calculate the total cost
        BigDecimal normalCost = normalRate.multiply(BigDecimal.valueOf(normalRateHours));
        BigDecimal reducedCost = reducedRate.multiply(BigDecimal.valueOf(reducedRateHours));
        BigDecimal totalCost = normalCost.add(reducedCost);

        // Ensure minimum payable of 4.00
        if (totalCost.compareTo(BigDecimal.valueOf(4)) < 0) {
            totalCost = BigDecimal.valueOf(4);
        }

        return totalCost;
    }
}
