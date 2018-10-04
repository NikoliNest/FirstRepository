import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {
    static HashMap<String, ArrayList<String>> phoneBook = new HashMap<>();  // Проще оказалось вынести в класс.

    public static void main(String[] args) {
        add("Nik" ,"921765");
        add("Alex", "911453");
        add("Ivan", "906897");
        add("Alex", "812232");
        add("Peter", "495987");
        add("Tihon", "777544");

        System.out.println("Весь справочник:" + phoneBook);
        System.out.println();

        get("Alex");
        get("Grigory");   // Тест
        get("Nik");
    }

    static void add(String name, String phone){
        ArrayList<String> a = addPhone(name, phone);
        phoneBook.put(name, a);
    }

    static ArrayList<String> addPhone(String name, String phone) {
        if ((phoneBook.get(name) == null)) {
            ArrayList<String> phones = new ArrayList<>();
            phones.add(phone);
            return phones;
        } else {
            ArrayList<String> phones = (ArrayList<String>) phoneBook.get(name);
            phones.add(phone);
            return phones;
        }
    }

    static void get(String name) {
        ArrayList<String> phones = (ArrayList<String>) phoneBook.get(name);
        System.out.println(name + "  " + " " + phones + " \n");

    }
}