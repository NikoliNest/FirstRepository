package First;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MainTest {

    private Main main;

    @Before
    public void startTest() {
        Main main = new Main();
    }

    @Test
    public void mainMethodTest1() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        Assert.assertArrayEquals(new int[]{5, 6, 7, 8}, main.mainMethod(arr));
    }

    @Test
    public void mainMethodTest2() {
        int[] arr = {1, 4, 4, 4, 5, 6, 4, 7, 8};
        Assert.assertArrayEquals(new int[]{7, 8}, main.mainMethod(arr));
    }

    @Test(expected = RuntimeException.class)
    public void mainMethodTest3() {
        int[] arr = {1, 5, 6, 3, 7, 8};
        main.mainMethod(arr);
    }

    @Test
    public void onlyFoursTest1(){
        int[] arr = {1, 4, 4, 4, 1, 4, 4, 1, 4};
        Assert.assertEquals(true, main.onlyFours(arr));
    }

    @Test
    public void onlyFoursTest2(){
        int[] arr = {1, 4, 4, 5, 1, 4, 4, 1, 4};
        Assert.assertEquals(false, main.onlyFours(arr));
    }

    @Test
    public void onlyFoursTest3(){
        int[] arr = {1, 2, 4, 5, 1, 4, 4, 3, 4};
        Assert.assertEquals(false, main.onlyFours(arr));
    }
}