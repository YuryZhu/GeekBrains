package ru.yzhiharevich.marathon_lesson1;

import static ru.yzhiharevich.java3_lesson6.MainTesting.getSymbolsAfterFour;
import static ru.yzhiharevich.java3_lesson6.MainTesting.checkArrayWithOneAndFour;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {
    private int[] arr = new int[]{5, 3, 5, 7, 8, 4, 2, 3};
    private int[] arr1 = new int[]{5, 3, 5, 4, 8, 7, 2, 3};
    private int[] arr2 = new int[]{4, 3, 4, 7, 8, 9, 2, 3};
    private int[] arr3 = new int[]{5, 3, 5, 7, 8, 3, 2, 3};
    private int[] arrFO = new int[]{1, 4, 4, 1, 1, 4, 4, 1};
    private int[] arrFO1 = new int[]{1, 4, 4, 1, 1, 4, 4, 1};
    private int[] arrFO2 = new int[]{1, 4, 4, 4, 4, 1, 1, 1, 1};
    private int[] arrFO3 = new int[]{4, 4, 1, 1, 4, 4, 1};

    @Test
    public void test1() {
        int[] arrExcept1 = new int[]{2, 3};
        Assert.assertArrayEquals(arrExcept1, getSymbolsAfterFour(arr));
    }

    @Test
    public void test2() {
        int[] arrExcept2 = new int[]{8, 7, 2, 3};
        Assert.assertArrayEquals(arrExcept2, getSymbolsAfterFour(arr1));
    }

    @Test
    public void test3() {
        int[] arrExcept3 = new int[]{7, 8, 9, 2, 3};
        Assert.assertArrayEquals(arrExcept3, getSymbolsAfterFour(arr2));
    }

    @Test(expected = RuntimeException.class)
    public void test4() {
        getSymbolsAfterFour(arr3);
    }

    @Test
    public void testOneFour1() {
        Assert.assertTrue(checkArrayWithOneAndFour(arrFO));
    }

    @Test
    public void testOneFour2() {
        Assert.assertTrue(checkArrayWithOneAndFour(arrFO1));
    }

    @Test
    public void testOneFour3() {
        Assert.assertTrue(checkArrayWithOneAndFour(arrFO2));
    }

    @Test
    public void testOneFour4() {
        Assert.assertTrue(checkArrayWithOneAndFour(arrFO3));
    }
}
