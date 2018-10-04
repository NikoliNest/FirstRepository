import java.util.Arrays;
import java.util.Scanner;

public class NesterovAlgorythm1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        NesterovAlgorythm1 m = new NesterovAlgorythm1();
        System.out.print("Введите число: ");
        float a = sc.nextFloat();

        System.out.print("Введите показатель степени: ");
        int b = sc.nextInt();

        System.out.printf(" %.1f в степени %d равно %.1f \n ", a, b, m.toPower(a, b));
        System.out.printf(" %.1f в степени %d равно %.1f \n ", a, b, m.toPowerEvenOdd(a, b));

        System.out.println(" Массив случайных целых чисел: ");
        int v = (int) (Math.random() * 100);
        int[] arr = new int[v];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }
        System.out.println(Arrays.toString(arr));
        System.out.printf("Минимальный элемент: %d \n", m.minArray(arr));
        System.out.printf("Среднее арифметическое: %.2f \n", m.average(arr));
    }

    /*
     Алгоритм возведения в степень.
     O(n), т.к. число проходов равно показателю степени (линейный поиск).
     */

    float toPower(float a, int pow) {
        float b = 0;
        b = (a * a);
        for (int i = 3; i <= pow; i++) {
            b = b * a;
            System.out.println("" + a + " ^ " + i + " = " + b);
        }
        return b;
    }

     /*
    Алгоритм возведения в степень c учетом четности степени
    O(n/2), т.к. число проходов приближается к 1/2 от показателя степени (динейный поиск).
     */

    float toPowerEvenOdd(float a, int pow) {
        float b = (a * a);
        if (pow % 2 == 0) {
            for (int i = 4; i <= pow; i = i + 2) {
                b = b * (a * a);
                System.out.println("" + a + " ^ " + i + " = " + b);
            }
        } else {
            for (int i = 3; i < pow; i = i + 2) {
                b = b * (a * a);
                System.out.println("" + a + " ^ " + (i + 1) + " = " + b);
            }
            b = b * a;
            System.out.println("" + a + " ^ " + pow + " = " + b);
        }
        return b;
    }

    /*
    Алгоритм вычисления минимального элемента массива
    O(n), т.к. число проходов равно длине массива (линейный поиск).
    */

    int minArray(int[] arr) {
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return min;
    }

    /*
        Рекурсивный поиск не осилил :(
     */


    /*
    Алгоритм вычисления среднего арифметического.
    O(n), т.к. число проходов равно длине массива (линейный поиск)
     */
    private float average(int[] arr) {
        float average = 0;
        for (int i = 0; i < arr.length; i++) {
            average = average + arr[i];
        }
        average = average / arr.length;
        return average;
    }
}
