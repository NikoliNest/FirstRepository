import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class TextFileReader { // Класс представляет собой читалку текстовых файлов.

    final static int PAGE_LENGTH = 1800;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите имя текстового файла (пример - gigant.txt):  ");
        String file = sc.nextLine();
        RandomAccessFile in = null;
        try {
            in = new RandomAccessFile(file, "r");
            int page = 1;
            while (page != 0) {
                System.out.println("\nВведите номер страницы (0 - выход):  ");
                page = sc.nextInt();
                if (page == 0) break;

                int position = (PAGE_LENGTH * (page - 1) + 1);

                try {
                    in.seek(position);
                    for (int i = 0; i < PAGE_LENGTH; i++) {
                        System.out.print((char) in.read());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
