package Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    private final List<TestResults> testResults = new ArrayList<>();

    public void runTests() {
        try {
            // Hier kun je je testpakket of testklassen specificeren
            discoverAndRunTests();
            generateHtmlReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void discoverAndRunTests() {
        try {
            // Dynamisch de klasse laden op basis van het pakket
            Class<?>[] classes = TestClassFinder.findTestClasses();

            for (Class<?> testClass : classes) {
                if (isMainAnnotated(testClass)) {
                    runTestsForClass(testClass);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isMainAnnotated(Class<?> testClass) {
        Method[] methods = testClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Main.class)) {
                return true;
            }
        }
        return false;
    }

    private void runTestsForClass(Class<?> testClass) {
        SkylinePrinter.printASCIIArtFromFile();
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

        generateHtmlReport();
    }

    private void executeTest(Object testInstance, Method testMethod) {
        try {
            // Execute the actual test
            testMethod.invoke(testInstance);
            testResults.add(new TestResults(testMethod.getName(), "Passed", 0, "", ""));
            System.out.println(testMethod.getName() + " - Passed");
        } catch (Exception e) {
            testResults.add(new TestResults(testMethod.getName(), "Failed", 0, "", ""));
            System.out.println(testMethod.getName() + " - Failed");
        } finally {
            executeAfterEachMethod(testInstance, testInstance.getClass());
        }
    }

    private void executeBeforeEachMethod(Object testInstance, Class<?> testClass) {
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

    private void executeAfterEachMethod(Object testInstance, Class<?> testClass) {
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(AfterEach.class)) {
                try {
                    // Execute @BeforeEach method for each test
                    method.invoke(testInstance);
                } catch (Exception e) {
                    System.out.println("AfterEach method failed: " + method.getName() + " - " + e.getCause());
                }
            }
        }
    }

    private void executeBeforeTestMethod(Object testInstance, Class<?> testClass, String value) {
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

    private void generateHtmlReport() {
        HtmlGenerator htmlGenerator = new HtmlGenerator();
        htmlGenerator.generateHtmlToFile(testResults, "SkylineIndex.html");
    }
}
