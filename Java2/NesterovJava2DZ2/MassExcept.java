import java.io.EOFException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyException;

public class MassExcept {

    public static void main(String[] args) {
        Exception[] exceptions = new Exception[10];
        for (int i = 0; i < 10; i++) {
            int z = (int) ((Math.random() * 10) + 1);       // Создаёт случайное исключение из 10 вариантов
            switch (z) {
                case 1:
                    try {
                        throw new ArithmeticException();
                    } catch (ArithmeticException a) {
                        a.printStackTrace();
                        exceptions[i] = a;
                    }
                    break;
                case 2:
                    try {
                        throw new EOFException();
                    } catch (EOFException b) {
                        b.printStackTrace();
                        exceptions[i] = b;
                    }
                    break;
                case 3:
                    try {
                        throw new IOException();
                    } catch (IOException c) {
                        c.printStackTrace();
                        exceptions[i] = c;
                    }
                    break;
                case 4:
                    try {
                        throw new RuntimeException();
                    } catch (RuntimeException d) {
                        d.printStackTrace();
                        exceptions[i] = d;
                    }
                    break;
                case 5:
                    try {
                        throw new RuntimeException();
                    } catch (RuntimeException e) {
                        e.printStackTrace();
                        exceptions[i] = e;
                    }
                    break;
                case 6:
                    try {
                        throw new ClassCastException();
                    } catch (ClassCastException f) {
                        f.printStackTrace();
                        exceptions[i] = f;
                    }
                    break;
                case 7:
                    try {
                        throw new NullPointerException();
                    } catch (NullPointerException g) {
                        g.printStackTrace();
                        exceptions[i] = g;
                    }
                    break;
                case 8:
                    try {
                        throw new KeyException();
                    } catch (KeyException h) {
                        h.printStackTrace();
                        exceptions[i] = h;
                    }
                    break;
                case 9:
                    try {
                        throw new GeneralSecurityException();
                    } catch (GeneralSecurityException k) {
                        k.printStackTrace();
                        exceptions[i] = k;
                    }
                    break;
                case 10:
                    try {
                        throw new ArrayIndexOutOfBoundsException();
                    } catch (ArrayIndexOutOfBoundsException k) {
                        k.printStackTrace();
                        exceptions[i] = k;
                    }
                    break;
            }
        }

        System.out.println("\n\n");                 // Выводит в консоль массив исключений
        for (int i = 0; i< 10; i++) {
            try {
                Exception a = exceptions[i];
                throw a;
            } catch (Exception a) {
                System.out.println(a);
            }
        }
    }
}
