package nesterovJava7;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Plate plate = new Plate();
        plate.setFull(); // Наполняет тарелку

        System.out.println(" Введите количество котов.");
        int catNumber = scanner.nextInt();
        Cat cats[] = new Cat[catNumber];
        System.out.println(catNumber);
        int i = 0;
        do {
            cats[i] = new Cat(i);
            i++;
        } while (i < cats.length);

        for (Cat cat : cats) {
            cat.eatFromPlate(plate);
        }
    }
}