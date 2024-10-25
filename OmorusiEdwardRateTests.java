import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OmorusiEdwardRateTests {
    @Test
    public void testValidPeriodsAndRatesWithinAllowedRange() { // Test 1
        Rate rate = new Rate(Rate.CarParkKind.STAFF,
                Arrays.asList(new Period(7, 10)),
                Arrays.asList(new Period(10, 15)),
                8, 5);
        assertNotNull(rate); // Should create a valid rate object
    }

    @Test
    public void testNormalRateLessThanReducedRate() { // Test 2
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.STUDENT,
                    Arrays.asList(new Period(6, 8)),
                    Arrays.asList(new Period(8, 10)),
                    5, 6);
        });
    }

    @Test
    public void testReducedAndNormalPeriodsOverlap() { // Test 3
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.VISITOR,
                    Arrays.asList(new Period(9, 12)),
                    Arrays.asList(new Period(11, 14)),
                    9, 5);
        });
    }

    @Test
    public void testNormalRateGreaterThanAllowedMaximum() { // Test 4
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.STAFF,
                    Arrays.asList(new Period(7, 10)),
                    Arrays.asList(new Period(10, 15)),
                    12, 5);
        });
    }

    @Test
    public void testReducedRateIsNegative() { // Test 5
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.STUDENT,
                    Arrays.asList(new Period(8, 10)),
                    Arrays.asList(new Period(10, 12)),
                    7, -1);
        });
    }

    @Test
    public void testNoPeriodsSpecified() { // Test 6
        Rate rate = new Rate(Rate.CarParkKind.VISITOR,
                Arrays.asList(),
                Arrays.asList(),
                8, 5);
        assertNotNull(rate); // Should create a valid rate object
    }

    @Test
    public void testOnlyReducedPeriodsDefined() { // Test 7
        Rate rate = new Rate(Rate.CarParkKind.VISITOR,
                Arrays.asList(new Period(8, 12)),
                Arrays.asList(),
                8, 6);
        assertNotNull(rate); // Should create a valid rate object
    }

    @Test
    public void testOverlappingPeriodsWithinSameCategory() { // Test 8
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.STUDENT,
                    Arrays.asList(new Period(8, 11), new Period(10, 12)),
                    Arrays.asList(new Period(13, 16)),
                    6, 3);
        });
    }

    @Test
    public void testReducedPeriodStartsAfterEnd() { // Test 9
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.STUDENT,
                    Arrays.asList(new Period(12, 8)),
                    Arrays.asList(new Period(10, 15)),
                    8, 5);
        });
    }

    @Test
    public void testReducedPeriodEqualToNormalPeriod() { // Test 10
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.STAFF,
                    Arrays.asList(new Period(9, 12)),
                    Arrays.asList(new Period(9, 12)),
                    10, 6);
        });
    }

    @Test
    public void testReducedRateEqualToNormalRate() { // Test 11
        Rate rate = new Rate(Rate.CarParkKind.STAFF,
                Arrays.asList(new Period(7, 10)),
                Arrays.asList(new Period(10, 15)),
                5, 5);
        assertNotNull(rate); // Should create a valid rate object
    }

    @Test
    public void testReducedPeriodsContainSingleTimeSlot() { // Test 12
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.VISITOR,
                    Arrays.asList(new Period(10, 10)),
                    Arrays.asList(new Period(11, 15)),
                    7, 5);
        });
    }

    @Test
    public void testMultipleReducedPeriodsOverlap() { // Test 13
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.STUDENT,
                    Arrays.asList(new Period(7, 9), new Period(8, 10)),
                    Arrays.asList(new Period(10, 15)),
                    6, 4);
        });
    }



    @Test
    public void testStartHourOfReducedPeriodIsGreaterThanEnd() { // Test 14
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.STUDENT,
                    Arrays.asList(new Period(10, 8)),
                    Arrays.asList(new Period(9, 15)),
                    8, 5);
        });
    }

    @Test
    public void testAllDefinedPeriodsDuringOperatingHours() { // Test 15
        Rate rate = new Rate(Rate.CarParkKind.MANAGEMENT,
                Arrays.asList(new Period(7, 10)),
                Arrays.asList(new Period(10, 12)),
                6, 3);
        assertNotNull(rate); // Should create a valid rate object
    }

    @Test
    public void testNormalPeriodsOutOfBounds() { // Test 16
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.VISITOR,
                    Arrays.asList(new Period(8, 10)),
                    Arrays.asList(new Period(24, 26)),
                    7, 5);
        });
    }


    @Test
    public void testReducedAndNormalPeriodsHaveGaps() { // Test 17
        Rate rate = new Rate(Rate.CarParkKind.STAFF,
                Arrays.asList(new Period(8, 9)),
                Arrays.asList(new Period(10, 12)),
                5, 3);
        assertNotNull(rate); // Should create a valid rate object
    }

    @Test
    public void testReducedPeriodsDefinedButExceedMaximumLimit() { // Test 18
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.STUDENT,
                    Arrays.asList(new Period(0, 25)),
                    Arrays.asList(new Period(10, 15)),
                    5, 2);
        });
    }
}
