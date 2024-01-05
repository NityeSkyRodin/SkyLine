public class CodeTest {

    private static int x = 0;

    private static String string = "";

    @BeforeEach
    public static void beforeEach() {
        x = 1;
    }

    @BeforeTest("test1")
    public static void beforeTest() {
        x = 2-1;
    }

    @BeforeTest("test3")
    public static void beforeTest2(){
        string = "test";
        x = 10;
    }

    @UnitTest
    public static void test1() {
        Assert.assertEquals(1, x);
    }

    @UnitTest
    public static void test2() {
        Assert.assertEquals(1, 1);
    }

    @UnitTest
    public static void test3(){
        Assert.assertEquals("test", string);
    }
}
