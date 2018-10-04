import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class MainClass {

    public static final int CARS_COUNT = 4;

    static CountDownLatch onStart = new CountDownLatch(CARS_COUNT + 1);  // Счётчик машин, вышедших на старт
    static Semaphore sm = new Semaphore( ((CARS_COUNT / 2) - 1) ); // Семафор в тоннеле
    static int inTunnel = 0; // Вспомогательная переменная для учёта количества машин в тоннеле
    static boolean getWinner = false;
    static String winnerName = "";
    static CountDownLatch onFinish = new CountDownLatch(CARS_COUNT + 1); // Счётчик машин, пришедших к финишу

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (
                int i = 0;
                i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (
                int i = 0;
                i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        while (MainClass.onStart.getCount() != 1) {     //Ждёт выхода на старт всех машин
        }
        MainClass.onStart.countDown();      // Подаёт сигнал начала гонки
        try {
            MainClass.onStart.await();      //  В этом месте основной поток ждёт начала гонки
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread tpw = new Thread(new PrintWinner());     // Отслеживание победителя
        tpw.start();
        while (MainClass.onFinish.getCount() != 1) {     //Ждёт прихода к финишу всех машин
        }
        MainClass.onFinish.countDown();      // Подаёт сигнал окончания гонки
        try {
            MainClass.onStart.await();      //  В этом месте основной поток ждёт окончания гонки
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        System.out.println("ПОВТОРЯЕМ: победитель - " + winnerName);
    }
}

class PrintWinner extends Thread { // Отслеживает появление победителя
    @Override
    public void run() {
        while (true) {
            if (MainClass.getWinner) {
                System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ: победитель - " + MainClass.winnerName);
                break;
            }
        }
    }
}

class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
        } catch (Exception e) {
            e.printStackTrace();
        }
        MainClass.onStart.countDown();        //  Подаёт сигнал о том, что машина вышла на старт
        try {
            MainClass.onStart.await();        // Здесь поток-машинка ждёт сигнала начала гонки
            Thread.sleep(50);           // Пришлось добавить задержку, иначе иногда происходит технический фальстарт.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        if (!MainClass.getWinner) {           // В случае прихода машинки к финишу первой, регистрирует ее как победителя.
            MainClass.getWinner = true;
            MainClass.winnerName = this.getName();
        }
        MainClass.onFinish.countDown();       // Подаёт сигнал о том, что машина пришла к финишу.
    }
}

abstract class Stage {
    protected int length;
    protected String description;

    public String getDescription() {
        return description;
    }

    public abstract void go(Car c);
}

class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Tunnel extends Stage {

    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                MainClass.sm.acquire();                                                 // Регистрирует машину на семафору у входа в тоннель
                System.out.println(c.getName() + " начал этап: " + description);
                MainClass.inTunnel++;
                System.out.println("В тоннеле машин: " + MainClass.inTunnel);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                MainClass.sm.release();
                MainClass.inTunnel--;
                System.out.println("В тоннеле машин: " + MainClass.inTunnel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Race {
    private ArrayList<Stage> stages;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}