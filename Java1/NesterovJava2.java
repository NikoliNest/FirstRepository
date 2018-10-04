import java.util.Arrays;
import java.util.Scanner;
public class NesterovJava2 {
    private static Scanner scanner;
    public static void main(String[] args) throws java.io.IOException {
        scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println();
            System.out.println("Выберите номер задания: ");
            System.out.println("1. Заменить 0 на 1, 1 на 0");
            System.out.println("2. Массив с шагом 3");
            System.out.println("3. Массив с умножением на 2 чисел меньше 6");
            System.out.println("4. Двумерный массив с единицами по диагонали");
            System.out.println("5. Минимальный и максимальный члены массива");
            System.out.println("6. Есть ли у массива середина");
            System.out.println("7. Сдвиг одномерного массива");
            System.out.println("8. Выход.");
            int answer = scanner.nextInt();
            switch (answer) {
                case 1:
                    int[] array1 = new int[]{1, 0, 0, 1, 1, 0, 0, 0, 1};
                    zeroToOne(array1);
                    break;
                case 2:
                    int[] array2 = new int[8];
                    array2 = cycleFillArrayStep3(array2);
                    System.out.println("Массив с шагом 3:" + Arrays.toString(array2));
                    break;
                case 3:
                    int[] array3 = new int[]{1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
                    System.out.println("Исходный массив: " + Arrays.toString(array3));
                    array3 = lessSixDouble(array3);
                    System.out.println("Массив с умножением на 2 чисел меньше 6: " + Arrays.toString(array3));
                    break;
                case 4:
                    System.out.println("Введите размер каждого измерения массива:");
                    int a = scanner.nextInt();
                    int[][] array4 = new int[a][a];
                    array4 = twoDimenseOnes(array4);
                    System.out.println("Двумерный массив с единицами по диагонали: \n");
                    for (int j = 0; j < a; j++) {
                        for (int k = 0; k < a; k++) {
                            System.out.print(array4[j][k] + "\t");
                        }
                        System.out.println();
                    }
                    break;
                case 5:
                    int[] array5 = buildIntArray();//Метод создания целочисленного одномерного массива заданной пользователем длины
                    intArrayDisplay(array5); // Массив выводится в виде таблички по 10 столбцов
                    System.out.println("\n" + "Минимальный член массива: " + minArray(array5));
                    System.out.println("Максимальный член массива: " + maxArray(array5));
                    break;
                case 6:
                    int[] array6 = buildIntArray(); // Не зря метод написал
                    // int[] array6 = new int[]{1, 10, 1, 10, 1, 10, 1, 10, 1, 10, 1, 10, 1, 10, 1, 10}; // тест
                    intArrayDisplay(array6);
                    boolean b = checkBalance(array6); // магия 0_o
                    System.out.println("\nЕсть ли у массива \"середина\"? - " + b);
                    break;
                case 7:
                    int[] array7 = buildIntArray();// Снова пригодился
                    // int[] array7 = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}; // тест
                    intArrayDisplay(array7);
                    System.out.println("\nВведите количество позиций для сдвига (положительное число - вправо, отрицательное - влево.");
                    int push = scanner.nextInt();
                    array7 = pushArray(array7, push);
                    intArrayDisplay(array7);
                    break;
                case 8:
                    exit = true;
                    break;
            }
        }
    }
    private static void zeroToOne(int[] arr) {
         /* Задать целочисленный массив, состоящий из элементов 0 и 1 . Например: [ 1 , 1 , 0, 0, 1 , 0, 1 , 1 ,
        0, 0 ]. С помощью цикла и условия заменить 0 на 1 , 1 на 0; */
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length; i++) {
            int c = arr[i];
            switch (c) {
                case 1:
                    c = 0;
                    break;
                case 0:
                    c = 1;
                    break;
                default:
                    break;
            }
            arr[i] = c;
        }
        System.out.println(Arrays.toString(arr));
    }
    private static int[] cycleFillArrayStep3(int[] arr) {
        /* Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его
        значениями 0 3 6 9 1 2 1 5 1 8 21  */
        int a = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = a;
            a += 3;
        }
        return arr;
    }
    private static int[] lessSixDouble(int[] arr) {
       /* Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6
       умножить на 2 */
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) arr[i] *= 2;
        }
        return arr;
    }
    private static int[][] twoDimenseOnes(int[][] arr) {
      /*  Создать квадратный двумерный целочисленный массив (количество строк и столбцов
    одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами; */
        for (int i = 0; i < arr.length; i++) {
            arr[i][i] = 1;
        }
        return arr;
    }
    private static int minArray(int arr[]) {
        /* Задать одномерный массив и найти в нем минимальный элемент
    (без помощи интернета)*/
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) min = arr[i];
        }
        return min;
    }
    private static int maxArray(int arr[]) {
        /* Задать одномерный массив и найти в нем максимальный элемент
    (без помощи интернета)*/
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }
    private static int arrayLengthInput() { // Ввод размера создаваемого массива
        while (true) {
            System.out.println("Введите размер массива: ");
            int s = (int) scanner.nextInt();
            return s;
        }
    }
    private static void intArrayDisplay(int[] array){ // Выводит в консоль одномерный целочисленный массив в виде таблички по 10 столбцов.
        int i = 0;
        for (int c: array) {
            if (i % 10 == 0 && i != 0) System.out.println();
            System.out.print(/*i + " \\ " + */ c + "\t \t \t");
            i++;
        }
    }
    private static int[] buildIntArray() { // Создать одномерный целочисленный массив (из случайных одно- или двузначных положительных чисел)
        int l = arrayLengthInput();
        int[] arr = new int[l];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        return arr;
    }
    private static boolean checkBalance(int[] arr){
        /* Написать метод, в который передается не пустой одномерный целочисленный массив,
        метод должен вернуть true если в массиве есть место, в котором сумма левой и правой части
        массива равны */
        boolean b = false;
        int[] fromLeft = new int[arr.length]; // Сумма слева будет лежать здесь
        int[] fromRight = new int[arr.length];// Сумма справа будет лежать здесь
        int sumLeft = 0, sumRight = 0;
        for (int i = 0; i < arr.length ; i++) {
            sumLeft += arr[i];
            fromLeft[i] = sumLeft;
        }
        ///System.out.println("\n" + Arrays.toString(fromLeft));
        for (int i = arr.length - 1; i >= 0 ; i--) {
            sumRight += arr[i];
            fromRight[arr.length - 1 -  i] = sumRight;
        }
        //System.out.println("\n" + Arrays.toString(fromRight));

        for(int i = 0; i < arr.length; i++){
            int ost = fromLeft[arr.length - 1] - fromLeft[i]; // Разница между полной суммой элементов массива и суммой слева
            for (int j = 0; j < arr.length; j++) {
                if ((fromLeft[i] == fromRight[j]) && (j == arr.length - 2 -  i) && (fromRight[j] == ost)){
                    // Алгоритм проверки выведен методом подбора :)
                    // Почему там двойка, а не единичка? Потому что нумерация массива начинается с 0.
                    // Протестировано на массиве вида:
                    // int[] array6 = new int[]{1, 10, 1, 10, 1, 10, 1, 10, 1, 10, 1, 10, 1, 10, 1, 10.......};
                    System.out.println("\n Позиция (счёт с нуля): " + i + " слева;  " + j + " справа " +  "    " + " Середина - " + fromRight[j]) ;
                    b = true;
                }
            }
        }
        return b;
    }
    private static int[] pushArray(int[] arr, int n){
        /*  Написать метод, которому на вход подаётся одномерный массив и число n (может быть
      положительным, или отрицательным), при этом метод должен сместить все элементы
       массива на n позиций. Для усложнения задачи нельзя пользоваться вспомогательными
        массивами   */
        if (n > 0) {  //Сдвиг вправо.
            for (int i = 0; i < arr.length; i++){
                if ((arr.length - n - i - 1) >= 0) {
                    int a = arr[arr.length  - n - i - 1];
                    arr[(arr.length - i - 1)] = a;
                } else arr[(arr.length - i - 1)] = 0;
            }
        } else if (n < 0) {  //Сдвиг влево.
           for (int i = 0; i < arr.length; i++){
                if (( - n + i) < arr.length ) {
                    int a = arr[(- n + i)];
                    arr[( i )] = a;
               } else arr[i] = 0;
            }
        }
        return arr;
        }
    }