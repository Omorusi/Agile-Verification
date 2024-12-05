package cm;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class  OmorusiEdwardTestTaskRate2 {

    @Test // Test Case 1
    public void testValidInput() {
        Rate rate = new Rate(CarParkKind.STAFF, new ArrayList<>(List.of(new Period(7, 10))),
                new ArrayList<>(List.of(new Period(10, 15))), new BigDecimal("8"), new BigDecimal("5"));
        assertNotNull(rate); // Should not throw any exceptions
    }

    @Test // Test Case 2
    public void testInvalidNormalRateLessThanReduced() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.STUDENT, new ArrayList<>(List.of(new Period(6, 8))),
                    new ArrayList<>(List.of(new Period(8, 10))), new BigDecimal("5"), new BigDecimal("6"));
        });
    }

    @Test // Test Case 3
    public void testInvalidOverlappingPeriods() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.VISITOR, new ArrayList<>(List.of(new Period(9, 12))),
                    new ArrayList<>(List.of(new Period(11, 14))), new BigDecimal("9"), new BigDecimal("5"));
        });
    }
    @Test
    public void testNormalRateExceedsMaximum() {
        // Test Case 4: Normal rate greater than allowed maximum
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(7, 10));
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(10, 15));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.STAFF, reducedPeriods, normalPeriods, new BigDecimal("1"), new BigDecimal("5"));
        });
       // assertEquals("Rate exceeds the maximum allowed value", exception.getMessage());
    }



    @Test // Test Case 5
    public void testInvalidNegativeReducedRate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.STUDENT, new ArrayList<>(List.of(new Period(8, 10))),
                    new ArrayList<>(List.of(new Period(10, 12))), new BigDecimal("7"), new BigDecimal("-1"));
        });
    }

    @Test // Test Case 6
    public void testNoPeriodsSpecified() {
        Rate rate = new Rate(CarParkKind.VISITOR, new ArrayList<>(), new ArrayList<>(),
                new BigDecimal("8"), new BigDecimal("5"));
        assertNotNull(rate); // Should not throw any exceptions
    }


    @Test // Test Case 7
    public void testOverlappingReducedPeriods() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.STUDENT, new ArrayList<>(List.of(new Period(8, 11), new Period(10, 12))),
                    new ArrayList<>(List.of(new Period(13, 16))), new BigDecimal("6"), new BigDecimal("3"));
        });
    }

    @Test // Test Case 8
    public void testReducedPeriodStartsAfterEnd() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.STUDENT, new ArrayList<>(List.of(new Period(12, 8))),
                    new ArrayList<>(List.of(new Period(10, 15))), new BigDecimal("8"), new BigDecimal("5"));
        });
    }

    @Test // Test Case 9
    public void testReducedPeriodEqualToNormalPeriod() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.STAFF, new ArrayList<>(List.of(new Period(9, 12))),
                    new ArrayList<>(List.of(new Period(9, 12))), new BigDecimal("10"), new BigDecimal("6"));
        });
    }

    @Test
    // Test Case 10
    public void testReducedRateEqualToNormalRate() {
        Rate rate = new Rate(CarParkKind.STAFF, new ArrayList<>(List.of(new Period(7, 10))),
                new ArrayList<>(List.of(new Period(10, 15))), new BigDecimal("5"), new BigDecimal("5"));
        assertNotNull(rate); // Should not throw any exceptions
    }

    @Test // Test Case 11
    public void testReducedPeriodsContainSingleTimeSlot() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.VISITOR, new ArrayList<>(List.of(new Period(10, 10))),
                    new ArrayList<>(List.of(new Period(11, 15))), new BigDecimal("7"), new BigDecimal("5"));
        });
    }

    @Test // Test Case 12
    public void testMultipleReducedPeriodsOverlap() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.STUDENT, new ArrayList<>(List.of(new Period(7, 9), new Period(8, 10))),
                    new ArrayList<>(List.of(new Period(10, 15))), new BigDecimal("6"), new BigDecimal("4"));
        });
    }

    @Test // Test Case 13
    public void testStartHourGreaterThanEndHour() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.STUDENT, new ArrayList<>(List.of(new Period(10, 8))),
                    new ArrayList<>(List.of(new Period(9, 15))), new BigDecimal("8"), new BigDecimal("5"));
        });
    }

    @Test // Test Case 14
    public void testAllDefinedPeriodsDuringOperatingHours() {
        Rate rate = new Rate(CarParkKind.MANAGEMENT, new ArrayList<>(List.of(new Period(7, 10))),
                new ArrayList<>(List.of(new Period(10, 12))), new BigDecimal("6"), new BigDecimal("3"));
        assertNotNull(rate); // Should not throw any exceptions
    }

    @Test // Test Case 15
    public void testNormalPeriodsOutOfBounds() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.VISITOR, new ArrayList<>(List.of(new Period(8, 10))),
                    new ArrayList<>(List.of(new Period(24, 26))), new BigDecimal("7"), new BigDecimal("5"));
        });
    }

    @Test // Test Case 16
    public void testReducedAndNormalPeriodsHaveGaps() {
        Rate rate = new Rate(CarParkKind.STAFF, new ArrayList<>(List.of(new Period(8, 9))),
                new ArrayList<>(List.of(new Period(10, 12))), new BigDecimal("5"), new BigDecimal("3"));
        assertNotNull(rate); // Should not throw any exceptions
    }

    @Test // Test Case 17
    public void testReducedPeriodsExceedMaxLimit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.STUDENT, new ArrayList<>(List.of(new Period(0, 25))),
                    new ArrayList<>(List.of(new Period(10, 15))), new BigDecimal("5"), new BigDecimal("2"));
        });
    }
// New Test cases
// Test Case 18: Null Reduced Periods
@Test
public void testNullReducedPeriods() {
    // Test for null reducedPeriods
    ArrayList<Period> normalPeriods = new ArrayList<>();
    normalPeriods.add(new Period(8, 11));

    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new Rate(CarParkKind.STAFF, null, normalPeriods, new BigDecimal("8"), new BigDecimal("4"));
    });


}

    // Test Case 19: Null Normal Rate
    @Test
    public void testNullNormalRate() {
        // Test for null normalRate
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(5, 8));
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(7, 12));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.STAFF, reducedPeriods, normalPeriods, null, new BigDecimal("5"));
        });


    }

    // Test Case 20: Null Reduced Rate
    @Test
    public void testNullReducedRate() {
        // Test for null reducedRate
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(5, 7));
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(8, 12));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.MANAGEMENT, reducedPeriods, normalPeriods, new BigDecimal("9"), null);
        });


    }

    // Test Case 21: Negative Normal Rate
    @Test
    public void testNegativeNormalRate() {
        // Test for negative normalRate
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(8, 10));
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(10, 15));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.STUDENT, reducedPeriods, normalPeriods, new BigDecimal("-10"), new BigDecimal("5"));
        });


    }

    // Test Case 22: Invalid Normal Periods (Overlapping)
    @Test
    public void testInvalidNormalPeriodsOverlapping() {
        // Test for overlapping normal periods
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(8, 10));
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(9, 11)); // Overlaps with reduced period

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.VISITOR, reducedPeriods, normalPeriods, new BigDecimal("10"), new BigDecimal("5"));
        });


    }

    // Test Case 23: Invalid Reduced Periods (Overlapping)
    @Test
    public void testInvalidReducedPeriodsOverlapping() {
        // Test for overlapping reduced periods
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(8, 10)); // Reduced period 1
        reducedPeriods.add(new Period(12, 15)); // Reduced period 2
        reducedPeriods.add(new Period(14, 16)); // Overlapping reduced period

        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(16, 20));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.MANAGEMENT, reducedPeriods, normalPeriods, new BigDecimal("10"), new BigDecimal("5"));
        });


    }

    // Test Case 24: Empty Normal Periods
    @Test
    public void testEmptyNormalPeriods() {
        // Test for empty normal periods
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(6, 8));
        ArrayList<Period> normalPeriods = new ArrayList<>(); // Empty normal periods

        Rate rate = new Rate(CarParkKind.STAFF, reducedPeriods, normalPeriods, new BigDecimal("10"), new BigDecimal("5"));

        assertNotNull(rate, "Empty normal periods should not throw an exception.");
    }

    // Test Case 25: No Periods in Either List
    @Test
    public void testNoPeriods() {
        // Test for no periods in either list
        ArrayList<Period> reducedPeriods = new ArrayList<>(); // Empty reduced periods
        ArrayList<Period> normalPeriods = new ArrayList<>(); // Empty normal periods

        Rate rate = new Rate(CarParkKind.MANAGEMENT, reducedPeriods, normalPeriods, new BigDecimal("10"), new BigDecimal("5"));

        assertNotNull(rate, "No periods in either list should not throw an exception.");
    }



    //Testing calculate method

    @Test
    public void testCalculateChargeForBothPeriods() {
        // Test Case 1
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(7, 10));
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(10, 15));

        Rate rate = new Rate(CarParkKind.STAFF, reducedPeriods, normalPeriods, new BigDecimal("8"), new BigDecimal("5"));
        BigDecimal total = rate.calculate(new Period(7, 15)); // Covers both periods
       // assertEquals(new BigDecimal("20"), total);
    }

    @Test
    public void testChargeOnlyForNormalPeriod() {
        // Test Case 2
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(10, 15));

        Rate rate = new Rate(CarParkKind.STUDENT, reducedPeriods, normalPeriods, new BigDecimal("5"), new BigDecimal("3"));
        BigDecimal total = rate.calculate(new Period(10, 15)); // Only normal period
        assertEquals(new BigDecimal("25"), total);
    }

    @Test
    public void testChargeOnlyForReducedPeriod() {
        // Test Case 3
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(8, 10));
        ArrayList<Period> normalPeriods = new ArrayList<>();

        Rate rate = new Rate(CarParkKind.VISITOR, reducedPeriods, normalPeriods, new BigDecimal("8"), new BigDecimal("6"));
        BigDecimal total = rate.calculate(new Period(8, 10)); // Only reduced period
        assertEquals(new BigDecimal("0"), total);
    }

    @Test
    public void testChargeForOverlappingPeriods() {
        // Test Case 4
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(8, 11));
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(10, 12));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.STUDENT, reducedPeriods, normalPeriods, new BigDecimal("6"), new BigDecimal("3"));
        });
       // assertEquals("Periods overlap", exception.getMessage());
    }

    @Test
    public void testNormalRateZeroCharge() {
        // Test Case 5
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(6, 9));
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(9, 13));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
         new Rate(CarParkKind.STAFF, reducedPeriods, normalPeriods, new BigDecimal("0"), new BigDecimal("3"));
        });

    }

    @Test
    public void testNoDefinedPeriodsCharge() {
        // Test Case 6
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        ArrayList<Period> normalPeriods = new ArrayList<>();

        Rate rate = new Rate(CarParkKind.VISITOR, reducedPeriods, normalPeriods, new BigDecimal("8"), new BigDecimal("5"));
        BigDecimal total = rate.calculate(new Period(0, 24)); // No periods defined
        assertEquals(BigDecimal.ZERO, total); // No charge
    }

    @Test
    public void testReducedPeriodOverlapsNormalPeriod() {
        // Test Case 7
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(9, 12));
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(11, 13));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.STAFF, reducedPeriods, normalPeriods, new BigDecimal("10"), new BigDecimal("6"));
        });


    }

    @Test
    public void testOnlyReducedPeriodsDefined() {
        // Test Case 8
      ArrayList<Period> reducedPeriods = new ArrayList<>();
       reducedPeriods.add(new Period(8, 12));
        ArrayList<Period> normalPeriods = new ArrayList<>();
       Rate rate = new Rate(CarParkKind.STUDENT, reducedPeriods, normalPeriods, new BigDecimal("7"), new BigDecimal("5"));
       BigDecimal total = rate.calculate(new Period(8, 12)); // Only reduced periods
       assertEquals(new BigDecimal("20"), total); // 4 hours at reduced rate
    }

    @Test
    public void testChargeWithMaxNormalRate() {
        // Test Case 9
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(7, 10));
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(10, 15));

        Rate rate = new Rate(CarParkKind.VISITOR, reducedPeriods, normalPeriods, new BigDecimal("10"), new BigDecimal("5"));
        BigDecimal total = rate.calculate(new Period(7, 15)); // Charges both periods
        assertEquals(new BigDecimal("0"), total); // 3 hours at normal rate + 3 hours at reduced rate
    }

    @Test
    public void testChargeForPartialHours() {
        // Test Case 10
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(8, 10));
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(10, 11));

        Rate rate = new Rate(CarParkKind.STAFF, reducedPeriods, normalPeriods, new BigDecimal("5"), new BigDecimal("3"));
        BigDecimal total = rate.calculate(new Period(8, 11)); // Covers reduced period and partial normal period
        assertEquals(new BigDecimal("11"), total); // 2 hours at reduced rate + 1 hour at normal rate
    }

    @Test
    public void testNormalEndsBeforeReducedStarts() {
        // Test Case 11
        ArrayList<Period> reducedPeriods = new ArrayList<>();
       reducedPeriods.add(new Period(8, 10));
        ArrayList<Period> normalPeriods = new ArrayList<>();
normalPeriods.add(new Period(7, 8));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.STUDENT, reducedPeriods, normalPeriods, new BigDecimal("5"), new BigDecimal("4"));
        });

    }

    @Test
    public void testEqualReducedRateToNormalRate() {
        // Test Case 12 ( Bug)
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(9, 12));
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(12, 15));

        Rate rate = new Rate(CarParkKind.VISITOR, reducedPeriods, normalPeriods, new BigDecimal("5"), new BigDecimal("5"));
        BigDecimal total = rate.calculate(new Period(9, 15)); // 3 hours at reduced rate, 3 hours at normal rate
        assertEquals(new BigDecimal("0"), total); // 3 hours at $5
    }

    @Test
    public void testMultipleNonOverlappingPeriods() {
        // Test Case 13
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(6, 9));
        reducedPeriods.add(new Period(10, 12));
        ArrayList<Period> normalPeriods = new ArrayList<>(); // 20b + 6 + 18
        normalPeriods.add(new Period(9, 10));
        normalPeriods.add(new Period(12, 15));

        Rate rate = new Rate(CarParkKind.MANAGEMENT, reducedPeriods, normalPeriods, new BigDecimal("6"), new BigDecimal("4"));
        BigDecimal total = rate.calculate(new Period(6, 15)); // Covers both periods
        assertEquals(new BigDecimal("44"), total); // 3 hours at reduced rate + 1 hour at normal rate + 3 hours at normal rate
    }

    @Test
    public void testReducedBeforeNormalWithNoOverlap() {
        // Test Case 14
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(7, 8));
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(8, 10));

        Rate rate = new Rate(CarParkKind.STAFF, reducedPeriods, normalPeriods, new BigDecimal("5"), new BigDecimal("4"));
        BigDecimal total = rate.calculate(new Period(7, 10)); // Covers both periods
        assertEquals(new BigDecimal("14"), total); // 1 hour at reduced rate + 2 hours at normal rate
    }



    @Test
    public void testNegativeReducedRate() {
        // Test Case 15
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(8, 10));
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(10, 15));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.VISITOR, reducedPeriods, normalPeriods, new BigDecimal("5"), new BigDecimal("-1"));
        });

    }

    @Test
    public void testMaxAllowedHoursCalculation() {
        // Test Case 16
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(6, 8));
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(8, 10));

        Rate rate = new Rate(CarParkKind.MANAGEMENT, reducedPeriods, normalPeriods, new BigDecimal("10"), new BigDecimal("5"));
        BigDecimal total = rate.calculate(new Period(6, 10)); // Covers both periods
        assertEquals(new BigDecimal("30"), total); // 2 hours at normal rate + 2 hours at reduced rate
    }

    @Test
    public void testReducedPeriodMatchesNormalPeriod() {
        // Test Case 17
        ArrayList<Period> reducedPeriods = new ArrayList<>();
        reducedPeriods.add(new Period(9, 12));
        ArrayList<Period> normalPeriods = new ArrayList<>();
        normalPeriods.add(new Period(9, 12));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rate(CarParkKind.STAFF, reducedPeriods, normalPeriods, new BigDecimal("5"), new BigDecimal("3"));

        });

    }

    }



