import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListFromArray<T> { // Преобразует массив в ArrayList
    ArrayList<T> arrayList;
    public ArrayListFromArray(T[] arr){
        this.arrayList = new ArrayList<T>();
        for (int i = 0; i< arr.length; i++){
            arrayList.add(arr[i]);
        }
    }
}

class Two {
    public static void main(String[] args) {
        String[] arr = {"I ", "like ", "to ", "eat ", "apples "};
        System.out.println(Arrays.toString(arr));

        ArrayListFromArray<String> arrl = new ArrayListFromArray<String>(arr);
        System.out.println(arrl.arrayList.toString());
    }
}