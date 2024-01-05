package HTML;

// TestResult.java
public class TestResult {

    private final String testName;
    private final boolean success;
    private final long duration;
    private final String expectedResult;
    private final String actualResult;

    public TestResult(String testName, boolean success, long duration, String expectedResult, String actualResult) {
        this.testName = testName;
        this.success = success;
        this.duration = duration;
        this.expectedResult = expectedResult;
        this.actualResult = actualResult;
    }

    public String getTestName() {
        return testName;
    }

    public boolean isSuccess() {
        return success;
    }

    public long getDuration() {
        return duration;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public String getActualResult() {
        return actualResult;
    }
}
