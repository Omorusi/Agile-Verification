import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OmorusiEdwardTestTask1 {
//  Testing the Period constructor
    @Test
    public void testStartHourNegative() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Period(-4, 6);  // Should throw IllegalArgumentException
        });

    }

    // Test Case 2: Valid input, valid start and end hours
    @Test
    public void testValidPeriod() {
        Period p = new Period(3, 5);  // Valid input
        assertNotNull(p);
    }

    // Test Case 3: Invalid input, start hour is blank
    @Test
    public void testStartHourBlank() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Period(' ', 12);  // Should throw IllegalArgumentException
        });

    }

    // Test Case : 3 Invalid startHour and endHour
    @Test
    public void testValidPeriodBlank() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Period(' ', ' ');   // Should throw IllegalArgumentException
        });

    }

    @Test
    public void testEndHourNegative() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Period(4, -4);  // Should throw IllegalArgumentException
        });

    }

    // Test Case 6: Invalid input, startHour == endHour == 0
    @Test
    public void testStartAndEndHourZero() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Period(0, 0);  // Should throw IllegalArgumentException
        });

    }

    // Test Case 7: Invalid input, startHour is greater than endHour
    @Test
    public void testStartHourGreaterThanEndHour() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Period(6, 4);  // Should throw IllegalArgumentException
        });

    }
    // Test Case 8: Invalid input, startHour == endHour
    @Test
    public void testStartHourEqualsEndHour() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Period(4, 4);  // Should throw IllegalArgumentException because startHour == endHour
        });

    }
   @Test
public  void testendHourNegative() {
   IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->  {
   new Period(7,-8);   // Should throw IllegalArgumentException because of a negative input in endHour
});
}
    // Test Case 10: Invalid input, startHour is a non-numeric string
    @Test
    public void testStartHourNonNumeric() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Period("abc", 4);  // Should throw IllegalArgumentException
        });
        assertEquals("Invalid hour values", thrown.getMessage());
    }
    // Test Case 11: Invalid input, startHour is a non-numeric string
    @Test
    public void testEndHourNonNumeric() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Period(4, "xvy");  // Should throw IllegalArgumentException
        });
        assertEquals("Invalid hour values", thrown.getMessage());
    }
    // Test Case 12: Invalid input, startHour is a non-numeric string
    @Test
    public void testStartHourAndEndHourNonNumeric() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            new Period("abc", "xvy");  // Should throw IllegalArgumentException
        });
        assertEquals("Invalid hour values", thrown.getMessage());
    }
    // Test Case 13: Invalid input, startHour is a non-numeric string
@Test
    public void testValidStartandendHour() {
   Period p =  new Period(0, 24);
    assertNotNull(p);

}

 // Testing duration Method
 @Test
 public void testValidDuration() {
     Period period = new Period(4, 7);
     assertEquals(3, period.getDuration());  // Duration = 7 - 4 = 3
 }

    // Test Case 4: StartHour = 0 and endHour = 5
    @Test
    public void testStartHourZeroEndHourFive() {
        Period period = new Period(0, 5);
        assertEquals(5, period.getDuration());  // Duration = 5 - 0 = 5
    }

    // Test Case 5: Half-day (6 to 12)
    @Test
    public void testHalfDay() {
        Period period = new Period(6, 12);
        assertEquals(6, period.getDuration());  // Duration = 12 - 6 = 6
    }

    // Test Case 6: Full-day (0 to 24)
    @Test
    public void testFullDay() {
        Period period = new Period(0, 24);
        assertEquals(24, period.getDuration());  // Duration = 24 - 0 = 24
    }

    // Test Case 7: One-hour duration (4 to 5)
    @Test
    public void testOneHourDuration() {
        Period period = new Period(4, 5);
        assertEquals(1, period.getDuration());  // Duration = 5 - 4 = 1
    }
}
