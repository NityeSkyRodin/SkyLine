package Test;

public class CodeTest {

    private static int x = 1;

    private static String string = "";

    @BeforeEach
    public static void beforeEach() {
        x = 1;
    }

    @BeforeTest("test2")
    public static void beforeTest() {
        x = 2-1;
    }

    @BeforeTest("test3")
    public static void beforeTest2(){
        string = "test";
        x = 10;
    }

    @AfterEach
    public static void afterEach() {
        x --;
    }

    @UnitTest
    public static void test1() {
        Assert.assertEquals(1, x);
    }

    @UnitTest
    public static void test2() {
        Assert.assertEquals(1, x);
    }

    @UnitTest
    public static void test3(){
        Assert.assertEquals("test", string);
    }
}
