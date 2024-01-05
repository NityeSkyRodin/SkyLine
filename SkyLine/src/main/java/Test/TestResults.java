package Test;

public class TestResults {
    private String testName;
    private String success;
    private long duration;
    private String expectedResult;
    private String actualResult;

    public TestResults(String testName, String success, long duration, String expectedResult, String actualResult) {
        this.testName = testName;
        this.success = success;
        this.duration = duration;
        this.expectedResult = expectedResult;
        this.actualResult = actualResult;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getActualResult() {
        return actualResult;
    }

    public void setActualResult(String actualResult) {
        this.actualResult = actualResult;
    }
}
