public class MainOne {
    public static void main(String[] args) {
        PrintLetter pl1 = new PrintLetter();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    pl1.printA();
                }
            }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    pl1.printB();
                }
            }
        };
        Thread t3 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    pl1.printC();
                }

            }
        };
//        t1.setPriority(1);
//        t2.setPriority(2);
//        t3.setPriority(3);
        t1.start();
        t2.start();
        t3.start();
    }
}

class PrintLetter {
    int flag = 3;

    synchronized void printA() {
        //      System.out.print("a");
        if (flag == 3) {
            System.out.print(" A");
            flag = 1;
        }
        try {
            //      System.out.println(" " + Thread.currentThread().getName() + " wait ");
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notify();
    }

    synchronized void printB() {
        //      System.out.print( "b");
        if (flag == 1) {
            System.out.print("B");
            flag = 2;
        }
        notify();
        try {
            //      System.out.println(" " + Thread.currentThread().getName() + " wait ");
            wait();
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void printC() {
        //      System.out.print("c");
        if (flag == 2) {
            System.out.print("C");
            flag = 3;
        }
        notify();
        try {
            // System.out.println(" " + Thread.currentThread().getName() + " wait ");
            wait();
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
    }
}

