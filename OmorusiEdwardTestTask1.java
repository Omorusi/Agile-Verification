import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OmorusiEdwardTestTask1 {

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


}