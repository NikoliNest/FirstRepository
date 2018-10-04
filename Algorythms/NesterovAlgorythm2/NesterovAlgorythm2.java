import java.util.Arrays;

public class NesterovAlgorythm2 {
    final static int A = 100000; // Количество элементов в массиве
    final static int B = 100; // От 0 до скольки изменяются случайные элементы масива

    public static void main(String[] args) {
        MyArray arrDemo = new MyArray(20, 10);
        arrDemo.info();
        arrDemo.add(7);
        arrDemo.info();
        for (int i = -9; i <= 9; i++) {
            System.out.println("Найти " + i + " : " + Arrays.toString(arrDemo.find(i)));
        }
        for (int i = - 9; i <= 9; i++) {
            System.out.println(" Удалить " + i + ": " + arrDemo.deleteValueFirst(i));
            arrDemo.info();
        }
        arrDemo.deleteValueEvery(5);
        arrDemo.info();
        arrDemo.deleteValueEvery(3);
        arrDemo.info();

        MyArray arr1 = new MyArray(A, B);   // Создаём массив на миллион элементов для пузырьковой сортировки.
        long t1 = System.currentTimeMillis();
        arr1.sortBubble();
        long t2 = System.currentTimeMillis();
        System.out.println(" Пузырьковая сортировка: " + (t2 - t1));

        MyArray arr2 = new MyArray(A, B);   // Создаём массив на миллион элементов для сортоировки выбором.
        long t3 = System.currentTimeMillis();
        arr2.sortSelect();
        long t4 = System.currentTimeMillis();
        System.out.println(" Cортировка выбором: " + (t4 - t3));

        MyArray arr3 = new MyArray(A, B);   // Создаём массив на миллион элементов для сортировки вставкой.
        long t5 = System.currentTimeMillis();
        arr3.sortInsert();
        long t6 = System.currentTimeMillis();
        System.out.println(" Cортировка вставкой: " + (t6 - t5));

        MyArray arr4 = new MyArray(A, B);   // Создаём массив на миллион элементов для пидженхола.
        long t7 = System.currentTimeMillis();
        arr4.pigeonHole();
        long t8 = System.currentTimeMillis();
        System.out.println(" Cортировка Pigeon hole: " + (t8 - t7));
    }
            }