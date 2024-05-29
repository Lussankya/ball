import org.example.WaterBilling;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class WaterBillingTest {

    @Test
    void testUsageWithinFirstTier() {
        assertEquals(48083.4, WaterBilling.calculateWaterBill("445", "452"), 0.01);
    }

    @Test
    void testUsageSpanningFirstAndSecondTiers() {
        assertEquals(104739.6, WaterBilling.calculateWaterBill("0", "15"), 0.01);
    }

    @Test
    void testUsageSpanningThreeTiers() {
        assertEquals(191394.75, WaterBilling.calculateWaterBill("0", "25"), 0.01);
    }

    @Test
    void testUsageSpanningAllTiers() {
        assertEquals(326243.7, WaterBilling.calculateWaterBill("0", "35"), 0.01);
    }

    @Test
    void testBoundaryAt10m3() {
        assertEquals(65619.15, WaterBilling.calculateWaterBill("0", "10"), 0.01);
    }

    @Test
    void testBoundaryAt20m3() {
        assertEquals(143887.5, WaterBilling.calculateWaterBill("0", "20"), 0.01);
    }

    @Test
    void testBoundaryAt30m3() {
        assertEquals(239334.0, WaterBilling.calculateWaterBill("0", "30"), 0.01);
    }

    @Test
    void testBoundaryAbove30m3() {
        assertEquals(259728.0, WaterBilling.calculateWaterBill("0", "31"), 0.01);
    }

    @Test
    void testNegativeUsage() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            WaterBilling.calculateWaterBill("445", "440");
        });
        assertEquals("Invalid water usage: Water usage cannot be negative.", exception.getMessage());
    }

    @Test
    void testExcessivelyHighUsage() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            WaterBilling.calculateWaterBill("0", "10001");
        });
        assertEquals("Unrealistic water usage: Please verify your input.", exception.getMessage());
    }

    @Test
    void testNegativeReading() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            WaterBilling.calculateWaterBill("-1", "10");
        });
        assertEquals("Reading cannot be negative.", exception.getMessage());
    }

    @Test
    void testNegativeCurrentReading() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            WaterBilling.calculateWaterBill("10", "-1");
        });
        assertEquals("Reading cannot be negative.", exception.getMessage());
    }

    @Test
    void testNonNumericPreviousReading() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            WaterBilling.calculateWaterBill("abc", "10");
        });
        assertEquals("Both readings must be valid integers.", exception.getMessage());
    }

    @Test
    void testNonNumericCurrentReading() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            WaterBilling.calculateWaterBill("10", "xyz");
        });
        assertEquals("Both readings must be valid integers.", exception.getMessage());
    }
}
