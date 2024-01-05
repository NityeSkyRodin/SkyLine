import java.lang.reflect.Method;

public class TestRunner {

    public static void runTests(Class<?> testClass) {
        SkylinePrinter.printASCIIArtFromFile("Logo.txt");
        System.out.println("------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Running tests for " + testClass.getName() + "\n");

        // Create an instance of the test class to invoke non-static methods
        Object testInstance;
        try {
            testInstance = testClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.out.println("Failed to create test instance: " + e.getMessage());
            return;
        }

        // Execute @BeforeEach method for each test
        executeBeforeEachMethod(testInstance, testClass);

        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(UnitTest.class) || method.isAnnotationPresent(EndToEndTest.class)) {
                String value = method.getName();
                executeBeforeTestMethod(testInstance, testClass, value);
                executeTest(testInstance, method);
            }
        }
    }

    private static void executeTest(Object testInstance, Method testMethod) {
        try {
            // Execute the actual test
            testMethod.invoke(testInstance);
            System.out.println(testMethod.getName() + " - Passed");
        } catch (Exception e) {
            System.out.println(testMethod.getName() + " - Failed");
        }
    }

    private static void executeBeforeEachMethod(Object testInstance, Class<?> testClass) {
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BeforeEach.class)) {
                try {
                    // Execute @BeforeEach method for each test
                    method.invoke(testInstance);
                } catch (Exception e) {
                    System.out.println("BeforeEach method failed: " + method.getName() + " - " + e.getCause());
                }
            }
        }
    }

    private static void executeBeforeTestMethod(Object testInstance, Class<?> testClass, String value) {
        for (Method method : testClass.getDeclaredMethods()) {

            BeforeTest beforeTest = method.getAnnotation(BeforeTest.class);
            if (beforeTest == null) {
                continue;
            }

            if (method.isAnnotationPresent(BeforeTest.class)) {
                if (beforeTest.value().equals(value)) {

                    try {
                        // Execute @BeforeEach method for each test
                        method.invoke(testInstance);
                    } catch (Exception e) {
                        System.out.println("BeforeTest method failed: " + method.getName() + " - " + e.getCause());
                    }
                }
            }
        }
    }
}
