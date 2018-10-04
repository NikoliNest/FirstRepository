package NesterovJava6;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Dog dog = new Dog("Жучка", 3);
        Cat cat = new Cat("Барсик", 2);

        /* Тест 1 */

        dog.report();
        cat.report();

        /* Тест 2 */

        System.out.println(" Сколько пробежать коту?");
        int a = scanner.nextInt();
        System.out.println(cat.run(a));

        System.out.println(" Сколько проплыть собаке?");
        int b = scanner.nextInt();
        System.out.println(dog.swim(b));

        System.out.println(" На сколько прыгнуть собаке?");
        int c = scanner.nextInt();
        System.out.println(dog.jump(c));

        System.out.println(" Сколько проплыть коту?");
        int d = scanner.nextInt();
        System.out.println(cat.swim(d));
    }
}
