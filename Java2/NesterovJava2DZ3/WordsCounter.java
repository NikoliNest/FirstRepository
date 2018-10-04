import java.util.*;

public class WordsCounter {
    public static void main(String... args) {

        ArrayList<String> words = new ArrayList<String>();
        words.add("apple");
        words.add("car");
        words.add("book");
        words.add("crow");
        words.add("hedgehog");
        words.add("roof");
        words.add("apple");
        words.add("car");
        words.add("book");          // 2
        words.add("crow");          // 2
        words.add("hedgehog");      // 2
        words.add("roof");          // 2
        words.add("car");           // 3
        words.add("apple");         // 3
        words.add("orange");        // 1
        System.out.println(" Исходный массив слов: " + words);      // 15 элементов, из них 7 уникальных

        HashSet<String> set = new HashSet<String>();
        Iterator<String> iter = words.iterator();
        while (iter.hasNext()) {
            //System.out.println(iter.next());       // Выдает ошибку NoSuchElementException. Почему?
            set.add(iter.next());
        }
        System.out.println("Уникальные элементы массива: " + set);

        HashMap<String, Integer> hm = new HashMap<>();
        Integer a = 1;
        for (String s: words) {
 //           System.out.println(s + "  "+ hm.get(s));    // Отладка
            if (!(hm.get(s)==null)) {
                a = (hm.get(s) + 1);
//                 System.out.println("Найден повтор: " + s + "  " + hm.get(s));          // Отладка
            }
            hm.put(s, a);
            a = 1;
        }
        System.out.println("Количество повторов слов в массиве:" + hm);
    }
}