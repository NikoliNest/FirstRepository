package First;

import org.junit.Test;

import java.util.Arrays;

public class Main {

   static int[] mainMethod(int[] intArray) {
        int[] newArray;
        int begin = 0;
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] == 4) begin = (i + 1);
        }
        if (begin == 0) {
            throw new RuntimeException("В исходном массиве нет четвёрок.");
        }
        newArray = Arrays.copyOfRange(intArray, begin, intArray.length);
        return newArray;
    }

    static boolean onlyFours(int[] intArray){
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] != 4 && intArray[i] != 1) return false;
        }
        return true;
    }

    public static void main(String[] args) {
//        int[] arr = {1, 4, 4, 4, 1, 4, 4, 1, 4};
//        System.out.println(onlyFours(arr));

//        int[] arr = { 1, 2, 3,  5, 6, 7, 8};
//        int[] arr2 = mainMethod(arr);
//        System.out.println(Arrays.toString(arr2));
    }
}



