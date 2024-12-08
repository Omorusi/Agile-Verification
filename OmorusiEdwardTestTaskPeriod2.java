package cm;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OmorusiEdwardTestTaskPeriod2 {  @Test
public void testStartHourNegative() {
    IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
        new Period(-4, 6);  // Should throw IllegalArgumentException
    });

}

    // Test Case 2: Valid input, valid start and end hours
    @Test
    public void testValidPeriod() {
        Period p = new Period(3, 5);  // Valid input
        Assertions.assertNotNull(p);
    }

    // Test Case 3: Invalid input, start hour is blank
    @Test
    public void testStartHourBlank() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Period(' ', 12);  // Should throw IllegalArgumentException
        });

    }

    // Test Case : 3 Invalid startHour and endHour
    @Test
    public void testValidPeriodBlank() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Period(' ', ' ');   // Should throw IllegalArgumentException
        });

    }

    @Test
    public void testEndHourNegative() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Period(4, -4);  // Should throw IllegalArgumentException
        });

    }

    // Test Case 6: Invalid input, startHour == endHour == 0
    @Test
    public void testStartAndEndHourZero() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Period(0, 0);  // Should throw IllegalArgumentException
        });

    }

    // Test Case 7: Invalid input, startHour is greater than endHour
    @Test
    public void testStartHourGreaterThanEndHour() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Period(6, 4);  // Should throw IllegalArgumentException
        });

    }
    // Test Case 8: Invalid input, startHour == endHour
    @Test
    public void testStartHourEqualsEndHour() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Period(4, 4);  // Should throw IllegalArgumentException because startHour == endHour
        });

    }

    // test case 9
    @Test
    public  void testendHourNegative() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () ->  {
            new Period(7,-8);   // Should throw IllegalArgumentException because of a negative input in endHour
        });
    }
    // Test Case 10: Invalid input, startHour is a non-numeric string

    @Test
    public void testValidStartandendHour() {
        Period p =  new Period(0, 24);
        Assertions.assertNotNull(p);

    }

    // Test Case 11: Invalid startHour (startHour == 25)
    @Test
    public void testInvalidStartHourExceedsTwentyFour() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Period period =  new Period(25, 6);  // Invalid startHour (greater than 24)
        });

    }

    // Test Case 12: Invalid endHour (endHour > 24)
    @Test
    public void testInvalidEndHourExceedsTwentyFour() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Period period =    new Period(4, 25);  // Invalid endHour (greater than 24)
        });

    }

    // Test Case 13: Valid input (startHour == 23, endHour == 24)
    @Test
    public void testValidPeriodTwentyThreeToTwentyFour() {
        Period period = new Period(23, 24);  // Valid case
        Assertions.assertNotNull(period);  // Ensure the object is created successfully
    }

    // Test Case 14: Invalid startHour (-1)
    @Test
    public void testInvalidNegativeStartHour() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Period period =   new Period(-1, 22);  // Invalid startHour
        });
    }

    // Test Case 15: Invalid startHour == 24
    @Test
    public void testInvalidStartHourEqualTwentyFour() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Period period =  new Period(24, 5);  // Invalid startHour == 24
        });

    }

    // Test Case 16: Invalid period (startHour > endHour)
    @Test
    public void testInvalidStartGreaterThanEnd() {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Period period =  new Period(22, 20);  // Invalid period
        });

    }

    // Test Case 17: Valid period (startHour == 0, endHour == 1)
    @Test
    public void testValidSmallDuration() {
        Period period = new Period(0, 1);  // Valid period
        Assertions.assertNotNull(period);  // Ensure the object is created successfully
    }
    


    // Testing duration Method
    @Test
    public void testValidDuration() {
        Period period = new Period(4, 7);
        Assertions.assertEquals(3, period.duration());  // Duration = 7 - 4 = 3
    }

    // Test Case 4: StartHour = 0 and endHour = 5
    @Test
    public void testStartHourZeroEndHourFive() {
        Period period = new Period(0, 5);
        Assertions.assertEquals(5, period.duration());  // Duration = 5 - 0 = 5
    }

    // Test Case 5: Half-day (6 to 12)
    @Test
    public void testHalfDay() {
        Period period = new Period(6, 12);
        Assertions.assertEquals(6, period.duration());  // Duration = 12 - 6 = 6
    }

    // Test Case 6: Full-day (0 to 24)
    @Test
    public void testFullDay() {
        Period period = new Period(0, 24);
        Assertions.assertEquals(24, period.duration());  // Duration = 24 - 0 = 24
    }

    // Test Case 7: One-hour duration (4 to 5)
    @Test
    public void testOneHourDuration() {
        Period period = new Period(4, 5);
        Assertions.assertEquals(1, period.duration());  // Duration = 5 - 4 = 1
    }

    //  TESTING THE OVERLAPS METHOD
    // Test Case 1: Periods completely overlap
    @Test
    public void testCompletelyOverlappingPeriods() {
        Period period1 = new Period(3, 6);
        Period period2 = new Period(4, 5);
        Assertions.assertTrue(period1.overlaps(period2));
    }

    // Test Case 2: Periods partially overlap
    @Test
    public void testPartiallyOverlappingPeriods() {
        Period period1 = new Period(2, 5);
        Period period2 = new Period(4, 7);
        Assertions.assertTrue(period1.overlaps(period2));
    }

    // Test Case 3: Periods are consecutive (no overlap)
    @Test
    public void testConsecutivePeriods() {
        Period period1 = new Period(3, 5);
        Period period2 = new Period(5, 7);
        Assertions.assertFalse(period1.overlaps(period2));
    }

    // Test Case 4: Periods do not overlap at all
    @Test
    public void testNonOverlappingPeriods() {
        Period period1 = new Period(1, 3);
        Period period2 = new Period(4, 6);
        Assertions.assertFalse(period1.overlaps(period2));
    }

    // Test Case 5: One period is fully within another
    @Test
    public void testFullyWithinAnotherPeriod() {
        Period period1 = new Period(2, 8);
        Period period2 = new Period(3, 5);
        Assertions.assertTrue(period1.overlaps(period2));
    }

    // Test Case 6: Same start hour, different end hour
    @Test
    public void testSameStartDifferentEnd() {
        Period period1 = new Period(5, 9);
        Period period2 = new Period(5, 7);
        Assertions.assertTrue(period1.overlaps(period2));
    }

    // Test Case 7: Periods are identical
    @Test
    public void testIdenticalPeriods() {
        Period period1 = new Period(3, 6);
        Period period2 = new Period(3, 6);
        Assertions.assertTrue(period1.overlaps(period2));
    }

    // Test Case 8: Boundary overlap (touching exactly at one end)
    @Test
    public void testBoundaryOverlap() {
        Period period1 = new Period(3, 5);
        Period period2 = new Period(5, 7);
        Assertions.assertFalse(period1.overlaps(period2));
    }



      }


