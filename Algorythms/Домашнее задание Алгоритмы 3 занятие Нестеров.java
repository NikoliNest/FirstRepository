import java.util.Arrays;
import java.util.Scanner;


public class ReverseString {   // Программа, переворачивающая строки

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str;
        str = sc.nextLine();
        System.out.println(reverse(str));
    }

    public static String reverse(String str) {
        char ch;
        int size = str.length();
        char[] tmp = new char[size];
        for (int i = 0; i < size; i++) {
            ch = str.charAt(i);
            tmp[size - i - 1] = ch;
        }
        str = String.valueOf(tmp);
        return str;
    }
}

import java.util.Arrays;

public class Deque {      //   Дек   |-------T+++++++H--------|


    int items = 0;
    int maxSize = 10;
    int[] deque;
    int tail;
    int head;

    Deque() {
        this.tail = (maxSize / 2);
        this.head = ((maxSize / 2) + items);
        deque = new int[maxSize];
    }

    public void insertRight(int a) {
        if (head == (maxSize - 1)) {
            doubleDequeRight();
        }
        deque[head] = a;
        head++;
        items++;
    }

    private void doubleDequeRight() {
        int newMaxSize = maxSize + 10;
        int[] temp = new int[newMaxSize];
        System.arraycopy(deque, 0, temp, 0, maxSize);
        deque = temp;
        maxSize = newMaxSize;
    }

    public int removeRight() throws RuntimeException {
        if (isEmpty()) throw new RuntimeException("Дек пуст");
        int a = deque[head];
        deque[head] = 0;
        items--;
        head--;
        return a;
    }

    public void insertLeft(int a) {
        if (tail <= 1) {
            doubleDequeLeft();
        }
        tail--;
        deque[tail] = a;
        items++;
    }

    private void doubleDequeLeft() {
        int newMaxSize = maxSize + 10;
        int[] temp = new int[newMaxSize];
        System.arraycopy(deque, 0, temp, newMaxSize - (maxSize), maxSize);
        deque = temp;
        maxSize = newMaxSize;
        head = (head + 10);
        tail = (head - items - 1);
    }

    public int removeLeft() {
        if (isEmpty()) throw new RuntimeException("Дек пуст");
        int a = deque[tail];
        deque[tail] = 0;
        items--;
        tail++;
        return a;
    }

    private boolean isEmpty() {
        if (head < tail) return true;
        return false;
    }
    public void info() {
        System.out.println(Arrays.toString(deque));
    }

    public int getTail() {
        return deque[tail];
    }

    public int getHead() {
        return deque[head];
    }
}

public class PriorityQueue { // Приоритетная очередь

    int maxSize = 5;
    int[] priorArray;
    int items;

    PriorityQueue() {
        priorArray = new int[maxSize];
        items = 0;
    }

    public void add(int a) {    // Добавляет в очередь
        int tmp;
        if (isFull()) {
            doubleArr();
            info();
        }
        if (isEmpty()) {
            items++;
            priorArray[maxSize - 1] = a;

        } else {
            for (int i = maxSize - 1; i >= 0; i--) {
                if (a >= priorArray[i]) {
                    for (int j = 1; j <= i; j++) {
                        tmp = priorArray[0];
                        priorArray[j - 1] = priorArray[j];
                        priorArray[maxSize - (items + 1)] = tmp;
                    }
                    priorArray[i] = a;
                    items++;
                    break;
                }
            }
        }
    }

    public int remove() {   // Удаляет из очереди
        if (isEmpty()){
            throw new RuntimeException("Очередь пуста.");
        }
        int tmp = priorArray[maxSize - 1];
        for (int i = (maxSize-1); i > 0; i--){
            priorArray[i] = priorArray[i-1];
        }
        items--;
        return tmp;
    }
    private void doubleArr() {  // Увеличивает максимальный размер очереди
        maxSize *= 2;
        int[] tmpArr = new int[maxSize];
        System.arraycopy(priorArray, 1, tmpArr, (maxSize - items), items);
        priorArray = tmpArr;
    }

    public boolean isFull() {
        return (items == (maxSize - 1));
    }

    public boolean isEmpty() {
        return (items == 0);
    }

    public void info() {        // Выводит очередь в консоль
        System.out.println("Items: " + items + "  " + Arrays.toString(priorArray)) ;
    }
}