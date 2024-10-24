import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OmorusiEdwardRateTests {
    // Test case 1
    @Test
    public void testValidRateStaff() {
        // Test case 1: Valid Input
        Rate rate = new Rate(Rate.CarParkKind.STAFF, Arrays.asList(new Period(7, 10)), Arrays.asList(new Period(10, 15)), 8, 5);
        assertNotNull(rate);
    }
    // Test case 2
    @Test
    public void testInvalidRateNormalLessThanReduced() {
        // Test case 2: Invalid Input, normal rate < reduced rate
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.STUDENT, Arrays.asList(new Period(6, 8)), Arrays.asList(new Period(8, 10)), 5, 6);
        });
    }
    // Test case 3
    @Test
    public void testInvalidOverlappingPeriods() {
        // Test case 3: Invalid Input, periods overlap
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.VISITOR, Arrays.asList(new Period(9, 12)), Arrays.asList(new Period(11, 14)), 9, 5);
        });
    }
    // Test case 4
    @Test
    public void testInvalidRateForStaff() {
        // Test case 4: Invalid Input, normal rate exceeds maximum for STAFF
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.STAFF, Arrays.asList(new Period(7, 10)), Arrays.asList(new Period(10, 15)), 12, 5);
        });
    }
    // Test case 5
    @Test
    public void testInvalidReducedRateNegative() {
        // Test case 5: Invalid Input, reduced rate is negative
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.STUDENT, Arrays.asList(new Period(8, 10)), Arrays.asList(new Period(10, 12)), 7, -1);
        });
    }
    // Test case 6
    @Test
    public void testValidBoundaryCaseAdjacentPeriods() {
        // Test case 6: Valid Input, adjacent reduced and normal periods
        Rate rate = new Rate(Rate.CarParkKind.VISITOR, Arrays.asList(new Period(8, 12)), Arrays.asList(new Period(12, 16)), 10, 6);
        assertNotNull(rate);
    }
    // Test case 7
    @Test
    public void testValidManagementPeriodSeparation() {
        // Test case 7: Valid Input, reduced period earlier, normal period later
        Rate rate = new Rate(Rate.CarParkKind.MANAGEMENT, Arrays.asList(new Period(6, 9)), Arrays.asList(new Period(9, 13)), 7, 4);
        assertNotNull(rate);
    }
    // Test case 8
    @Test
    public void testInvalidOverlappingPeriodsInSameCategory() {
        // Test case 8: Invalid Input, overlapping periods within reduced category
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.STUDENT, Arrays.asList(new Period(8, 11), new Period(10, 12)), Arrays.asList(new Period(13, 16)), 6, 3);
        });
    }
    // Test case 9
    @Test
    public void testValidNoPeriodsDefined() {
        // Test case 9: Valid Input, no periods defined
        Rate rate = new Rate(Rate.CarParkKind.VISITOR, Arrays.asList(), Arrays.asList(), 8, 5);
        assertNotNull(rate);
    }
    // Test case 10
    @Test
    public void testValidNormalRateZero() {
        // Test case 10: Valid Input, normal rate is zero
        Rate rate = new Rate(Rate.CarParkKind.STAFF, Arrays.asList(new Period(6, 9)), Arrays.asList(new Period(9, 13)), 7, 4);
        assertNotNull(rate);
    }

    // Test case 11: Valid Input, reduced and normal periods fully adjacent
    @Test
    public void testValidFullyAdjacentPeriods() {
        Rate rate = new Rate(Rate.CarParkKind.MANAGEMENT, Arrays.asList(new Period(5, 8)), Arrays.asList(new Period(8, 12)), 9, 4);
        assertNotNull(rate);
    }

    // Test case 12: Valid Input, only reduced periods defined
    @Test
    public void testValidOnlyReducedPeriods() {
        Rate rate = new Rate(Rate.CarParkKind.VISITOR, Arrays.asList(new Period(8, 12)), Arrays.asList(), 8, 6);
        assertNotNull(rate);
    }

    // Test case 13: Valid Input, only normal periods defined
    @Test
    public void testValidOnlyNormalPeriods() {
        Rate rate = new Rate(Rate.CarParkKind.STUDENT, Arrays.asList(), Arrays.asList(new Period(6, 12)), 7, 0);
        assertNotNull(rate);
    }

    // Test case 14: Invalid Input, period exceeds 24 hours
    @Test
    public void testInvalidPeriodExceeds24Hours() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.MANAGEMENT, Arrays.asList(new Period(8, 12)), Arrays.asList(new Period(12, 25)), 9, 5);
        });
    }

    // Test case 15: Invalid Input, reduced period starts after it ends
    @Test
    public void testInvalidPeriodStartAfterEnd() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.STUDENT, Arrays.asList(new Period(12, 8)), Arrays.asList(new Period(10, 15)), 8, 5);
        });
    }

    // Test case 16: Invalid Input, reduced and normal periods are equal
    @Test
    public void testInvalidEqualPeriods() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.STAFF, Arrays.asList(new Period(9, 12)), Arrays.asList(new Period(9, 12)), 10, 6);
        });
    }

    // Test case 17: Invalid Input, normal rate less than reduced rate
    @Test
    public void testInvalidNormalRateLessThanReducedRate() {
        // New Test Case: Normal rate is less than reduced rate
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Rate(Rate.CarParkKind.STUDENT, Arrays.asList(new Period(8, 12)), Arrays.asList(new Period(12, 16)), 4, 5); // Normal rate < Reduced rate
        });
        assertEquals("Normal rate must be greater than or equal to reduced rate.", thrown.getMessage());
    }

    // Test case 18: Valid Input, normal rate equals reduced rate (boundary case)
    @Test
    public void testValidNormalRateEqualReducedRate() {
        // New Test Case: Normal rate is equal to reduced rate
        Rate rate = new Rate(Rate.CarParkKind.STAFF, Arrays.asList(new Period(7, 9)), Arrays.asList(new Period(9, 12)), 5, 5); // Normal rate == Reduced rate
        assertNotNull(rate);
    }

}
