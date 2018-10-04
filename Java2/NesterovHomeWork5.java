public class NesterovHomeWork5 {
    public static void main(String[] args) {
        NesterovHomeWork5 e = new NesterovHomeWork5();
       e.method1();
        e.method2();
    }

    public void method1() {
        System.out.println("\n Mетод 1 \n");
        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        // System.out.println("Время1: " + a);
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long b = System.currentTimeMillis();
        // System.out.println("Контрольное время 2: " + b);
        long c = (System.currentTimeMillis() - a);
        System.out.printf("Время выполнения первого метода  %d миллисекунд \n" , c);
    }

    public void method2() {
        System.out.println("\n Mетод 2 \n");

        final int size = 10000000;
        final int h = size / 2;
        float[] arr = new float[size];
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }
        float[] a1 = new float[h];
        float[] a2 = new float[h];
        long a = System.currentTimeMillis();
        // System.out.println("Время 1: " + a);
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        long d = System.currentTimeMillis();
        System.out.println(" Время разбивки массивов на 2 :" + (d - a));

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    arr[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
                long e = (System.currentTimeMillis() - d);
                System.out.println("Время просчета первого массива: " + e);
            }


        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    arr[i] = (float) (a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
                long e = (System.currentTimeMillis() - d);
                System.out.println("Время просчета второго массива: " + e);
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long f = System.currentTimeMillis();

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        long b = System.currentTimeMillis();

        System.out.println(" Время склейки 2 массивов: " + (b - f));
        // System.out.println("Контрольное время 2: " + b);
        long c = (System.currentTimeMillis() - a);
        System.out.printf("Время выполнения второго метода  %d миллисекунд \n" , c);
    }
}
