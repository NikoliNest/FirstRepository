import java.io.*;
import java.util.ArrayList;


public class FileTestApp {

    public static void formRandomByteFile(int length, String fileName) {
        byte[] arr1 = new byte[length];
        for (int i = 0; i < length; i++) {
            byte b = (byte) (Math.random() * 128);
            arr1[i] = b;
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            out.write(arr1);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // Метод создаёт файл со случайной последовательностью байт

    public static void formRandomCharFile(int length, String fileName) {
        char[] arr1 = new char[length];
        for (int i = 0; i < length; i++) {
            char b = (char) ((Math.random() * 27) + 64); // В основном -  заглавные латинские буквы
            if (b == '@') b = ' ';
            arr1[i] = b;
        }
        OutputStream out = null;
        OutputStreamWriter outW = null;
        try {
            out = new FileOutputStream(fileName);
            outW = new OutputStreamWriter(out);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            outW.write(arr1);
            outW.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    } // Метод создаёт файл  со случайной последовательностью символов

    public static ArrayList<Byte> fileReadBytes(String fileName) {
        ArrayList<Byte> buf = new ArrayList<>();

        FileInputStream in = null;

        try {
            in = new FileInputStream(fileName);
            int i = 0;
            byte b = (byte) in.read();
            while (b != -1) {
                buf.add(b);
                b = (byte) in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buf;
    } // Метод выводит файл с последовательностью байт в консоль

    public static ArrayList<Character> fileReadChar(String fileName) {
        ArrayList<Character> buf = new ArrayList<>();

        FileInputStream in = null;

        try {
            in = new FileInputStream(fileName);
            int b = in.read();
            while (b != -1) {
                buf.add((char) b);
                b = in.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buf;
    } // Метод выводит в консоль файл с последовательносью символов

    public static void main(String[] args) {

        final int LENGTH = 50;

        formRandomByteFile(LENGTH, "byte1.txt"); // Заготовка для первого задания - создаётся файл со случайной послдовательностью байт.

        System.out.println(fileReadBytes("byte1.txt").toString()); // Выполнение первого задания - прочитать файл в консоль.

        formRandomCharFile(LENGTH, "char_file.txt"); // Заготовка для второго задания

        System.out.println(fileReadChar("char_file.txt").toString()); // Заготовка для  второго задания

        formRandomCharFile(20000000, "gigant.txt"); // Заготовка для третьего задания
    }
}
