public class GenericTest<T> {
    T[] obj;
    public GenericTest(T[] obj) {
        this.obj = obj;
    }
    public void change(int a, int b) {   // Меняет местами два элемента массива
        T temp;
        temp = this.obj[a];
        this.obj[a] = this.obj[b];
        this.obj[b] = temp;
    }
    public void info() {
        for (int i=0; i< this.obj.length; i++){
            System.out.print(obj[i]);
        }
        System.out.println();
    }
}

class One {
    public static void main(String[] args) {
        String[] arr = {"I ", "like ", "to ", "eat ", "apples "};
        GenericTest<String> first = new GenericTest<>(arr);
        first.info();
        first.change(0, 4);
        first.info();
    }
}