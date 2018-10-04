import java.io.*;

public class MainTwo {
    public static void main(String[] args) {
        FilePrinter fp1 = new FilePrinter();
        fp1.threads();// Вот этот метод!

        try {
            fp1.sw.close();
            fp1.out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class FilePrinter {
    File file;
    FileOutputStream out;
    OutputStreamWriter sw;
    int flag = 3;

    FilePrinter() {
        this.file = new File("outPrint.txt");
        {
            try {
                this.out = new FileOutputStream(file);
                System.out.println(file.toString());
            } catch (
                    FileNotFoundException e)

            {
                e.printStackTrace();
            }

        }

        this.sw = new OutputStreamWriter(out);
    }


    public void threads() {

        CopyThread t1 = new CopyThread(1);

        CopyThread t2 = new CopyThread(2);

        CopyThread t3 = new CopyThread(3);


        t1.setPriority(2);
        t2.setPriority(2);
        t3.setPriority(2);
        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    class CopyThread extends Thread {
        int flag;

        CopyThread(int flag) {
            this.flag = flag;
        }

        synchronized void outPrintOne(int i) {
            FilePrinter.this.flag = 1;
            String str = ("\n This string has been pasted by " + Thread.currentThread().getName() + " and has got the number " + (i + 1) + '\uFFFF' + " \n");
            try {
                sw.write(str);
                System.out.println("File updated: " + str);
            } catch (IOException e) {
                e.printStackTrace();
            }

            notify();
            try {
                System.out.println(" " + Thread.currentThread().getName() + " waits 20 ms");
                wait(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        synchronized void outPrintTwo(int i) {
            FilePrinter.this.flag = 2;
            String str = ("\n This string has been pasted by " + Thread.currentThread().getName() + " and has got the number " + (i + 1) + '\uFFFF' + " \n");
            try {
                sw.write(str);
                System.out.println("File updated: " + str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            notify();
            try {
                System.out.println(" " + Thread.currentThread().getName() + " waits 20 ms");
                wait(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        synchronized void outPrintThree(int i) {
            FilePrinter.this.flag = 3;
            String str = ("\n This string has been pasted by " + Thread.currentThread().getName() + " and has got the number " + (i + 1) + '\uFFFF' + " \n");
            try {
                sw.write(str);
                System.out.println("File updated: " + str);
            } catch (IOException e) {
                e.printStackTrace();
            }
            notify();
            try {
                System.out.println(" " + Thread.currentThread().getName() + " waits 20 ms");
                wait(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        void outPrint(int flag, int i) {
            System.out.println(" Works thread:  " + Thread.currentThread().getName() + " | iteration : " + i);
            switch (flag) {
                case 3: {
                    outPrintOne(i);
                    break;
                }
                case 1: {
                    outPrintTwo(i);
                    break;
                }
                case 2: {
                    outPrintThree(i);
                    break;
                }
            }
        }

        @Override
        public void run() {
            System.out.println(" Запущен тред " + Thread.currentThread().getName());
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                outPrint(flag, i);
            }
        }
    }

}