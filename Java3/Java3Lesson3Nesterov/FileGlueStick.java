import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class FileGlueStick { // Класс склеивает 5 файлов, содержащих последовательности символов, в 1 файл, и выводит его в консоль в виде массива.

    public static void main(String[] args) {

        final int LENGTH = 100;

        FileTestApp.formRandomCharFile(LENGTH, "test1.txt");
        FileTestApp.formRandomCharFile(LENGTH, "test2.txt");
        FileTestApp.formRandomCharFile(LENGTH, "test3.txt");
        FileTestApp.formRandomCharFile(LENGTH, "test4.txt");
        FileTestApp.formRandomCharFile(LENGTH, "test5.txt");

        FileInputStream in1 = null;
        FileInputStream in2 = null;
        FileInputStream in3 = null;
        FileInputStream in4 = null;
        FileInputStream in5 = null;
        SequenceInputStream seq = null;
        FileOutputStream out = null;

        try {
            in1 = new FileInputStream("test1.txt");
            in2 = new FileInputStream("test2.txt");
            in3 = new FileInputStream("test3.txt");
            in4 = new FileInputStream("test4.txt");
            in5 = new FileInputStream("test5.txt");

            ArrayList<FileInputStream> al = new ArrayList<>();
            al.add(in1);
            al.add(in2);
            al.add(in3);
            al.add(in4);
            al.add(in5);
            Enumeration<FileInputStream> enumeration = Collections.enumeration(al);

            seq = new SequenceInputStream(enumeration);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out = new FileOutputStream("result.txt");
            byte rb = (byte) seq.read();
            while (rb != -1) {
                out.write(rb);
                rb = (byte) seq.read();
            }
        } catch (IOException c) {
            c.printStackTrace();
        } finally {
            try {
                in1.close();
                in2.close();
                in3.close();
                in4.close();
                in5.close();
                out.close();
            } catch (IOException b) {
                b.printStackTrace();
            }
        }
        System.out.println(FileTestApp.fileReadChar("result.txt"));
    }
}

