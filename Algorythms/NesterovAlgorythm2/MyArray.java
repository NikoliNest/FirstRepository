import java.util.Arrays;

class MyArray {
    private int[] arr;
    private int size;

    MyArray() { // Конструктор пустого массива нулевой длины, для последующего заполнения случайными числами.
        this.size = 0;
        this.arr = new int[size];
    }

    MyArray(int size) { // Конструктор массива заданной длины.
        this.size = size;
        this.arr = new int[size];
    }

    MyArray(int size, int a) { // Конструктор массива заданной длины, заполненного случайными числами от -a до a.
        this.arr = new int[0];
        for (int i = 0; i < size; i++) {
            int b = (int) ((Math.random() * a * 2) - a);
            add(b);
        }
    }

    void add(int a) {            // Добавляет значение в правый край массива.
        int[] temp = new int[(this.size + 1)];
        temp[(size)] = a;
        System.arraycopy(arr, 0, temp, 0, size);
        size++;
        arr = temp;
    }

    void info() {     // Выводит массив в консоль.
        System.out.println(Arrays.toString(arr));
    }

    void deletePosition(int position) {         //Удаляет элемент массива по номеру.
        if (position >= 0 && position <= size) {
            for (int i = position; i < (size - 1); i++) {
                this.arr[i] = this.arr[i + 1];
            }
            size--;
            int[] temp = new int[this.size];
            System.arraycopy(arr, 0, temp, 0, size);
            arr = temp;
        } else System.out.println("Такой позиции в массиве нет.");
    }

    int[] find(int a) { // Возвращает массив с номерами всех найденных позиций с искомым числом.
        MyArray temp = new MyArray(0);
        for (int i = 0; i < size; i++) {
            if (arr[i] == a) {
                temp.add(i);
            }
        }
        return temp.arr;
    }

    boolean deleteValueFirst(int value) {   // Удаляет первый попавшийся подходящий элемент массива.
        for (int i = 0; i < size; i++) {
            if (arr[i] == value) {
                deletePosition(i);
                return true;
            }
        }
        return false;
    }

    void deleteValueEvery(int value) {    //  Удаляет все подходящие элементы массива. Линейный поиск.
        for (int i = 0; i < size; i++) {
            if (arr[i] == value) {
                deletePosition(i);
            }
        }
    }

    void sortBubble() { // Сортировка пузырьковая
        int in;
        int out;
        for (out = (size - 1); out > 1; out--) {
            for (in = 0; in < out; in++) {
                if (arr[in] > arr[(in + 1)]) {
                    change(in, (in + 1));
                }
            }
        }
    }

    private void change(int i, int j) {  // Вспомогательный метод перестановки двух элементов массива
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;

    }


    void sortSelect() {  // Сортировка выбором.
        int out, in, mark;
        for (out = 0; out < size; out++) {
            mark = out;
            for (in = out + 1; in < size; in++) {
                if (arr[in] < arr[mark]) {
                    mark = in;
                }
            }
            change(out, mark);
        }
    }

    void sortInsert() { // Сортировка вставкой.
        int in, out;
        for (out = 1; out < size; out++) {
            int temp = arr[out];
            in = out;
            while (in > 0 && arr[in - 1] >= temp) {
                arr[in] = arr[in - 1];
                --in;
            }
            arr[in] = temp;
        }
    }

    void pigeonHole() {         //  Сортировка "Pigeon Hole"
        int min;
        int max;
        min = arr[0];
        max = arr[0];
        for (int i = 0; i < size; i++) { // Находим минимальное и максимальное значения
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int[] tmp = new int[(max - min + 1)]; // Создаём частотный массив.
        for (int i = 0; i < tmp.length; i++) {
            int l = find((min + i)).length;
            tmp[i] = l;
        }
        MyArray tmpArr = new MyArray(size);     // Заполняем временный массив отсортированными по частоте элементами.
        int count = 0;
        for (int value = min; value <= max; value++) {
            for (int freq = 0; freq < tmp[value]; freq++) {
                tmpArr.arr[count] = value;
                count++;
            }
        }
        this.arr = tmpArr.arr;      // Заполняем исходный массив отсортированным.
    }
}