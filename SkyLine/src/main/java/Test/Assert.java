package Test;

import java.util.logging.Logger;

public class Assert {
    private static final Logger LOGGER = Logger.getLogger(Assert.class.getName());
    public static void assertEquals(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            String errorMessage = "Assertion failed: expected " + expected + ", but got " + actual;
            LOGGER.info(errorMessage);
            throw new AssertionError(errorMessage);
        }
    }

    // Voeg andere assertiemethoden toe indien nodig
}
