package cm;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface RateCalculationStrategy {
    BigDecimal calculate(Period periodStay, BigDecimal normalRate, BigDecimal reducedRate, ArrayList<Period> normal, ArrayList<Period> reduced);
}

