import java.util.Scanner;

public class MainThree {
    public static void main(String[] args) {
        Mfu mfu = new Mfu();
    }
}

class Mfu {
    int printed = 0;
    int scanned = 0;

    int toPrint = 0;
    int toScan = 0;

    public Mfu() {
        while (true) {
            UserInterFace ui = new UserInterFace();
            ui.ask();
            Thread t1 = new Thread() {
                @Override
                public void run() {


                    PrinterThread t2 = new PrinterThread();
                    t2.start();
                    ScannerThread t3 = new ScannerThread();
                    t3.start();
                }
            };
            t1.start();

            scanned = 0;
            printed = 0;
        }

    }

    class UserInterFace{
        public synchronized void ask(){
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите количество страниц для печати:");
            toPrint = sc.nextInt();

            System.out.println("Введите количество страниц для сканирования:");
            toScan = sc.nextInt();
        }
    }

    class PrinterThread extends Thread {
        @Override
        public synchronized void run() {

            for (int i = 0; i < toPrint; i++) {
                System.out.println("Отпечатано: " + ++printed);
                try {
                    wait(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(" страниц.");
        }
    }

    class ScannerThread extends Thread {
        @Override
        public synchronized void run() {
            for (int i = 0; i < toScan; i++) {
                System.out.println("Отсканировано:"  + ++scanned);
                try {
                    wait(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.print(" страниц.");
        }
    }

    void threads() {

    }
}
