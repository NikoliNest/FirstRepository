package nesterovJava7;

public class Plate {
    private int foodMax;
    private int food;

    Plate() {  // Конструктор пустой тарелки
        this.food = 0;
        this.foodMax = 100;
    }

    public Plate(int foodMax) { // Конструктор полной тарелки
        this.foodMax = foodMax;
        this.food = foodMax;
    }

    public Plate(int foodMax, int food) {  // Конструктор тарелки с заданными параметрами
        this.foodMax = foodMax;
        this.food = food;
    }

    void setFull() { // Заполнить тарелку
        this.food = this.foodMax;
    }

    void decreaseFood(Cat cat, Plate plate) { //
        while (this.food < cat.appetite) {
            cat.cantEatFromPlate(plate, cat);
        }
        this.food -= cat.appetite;
        System.out.printf(" \n Кот %s (аппетит - %d)  поел из тарелки, кот сыт.", cat.number, cat.appetite);
        cat.satiety = true;
        System.out.printf("\n В тарелке осталось %d процентов еды.", this.food);
    }

    void fillPlate() {
        System.out.println(" Сколько еды положить в тарелку, в процентах от полной?  ( 0 - выход.) ");
        int a = Main.scanner.nextInt();
        if (a == 0) {
            System.exit(0);
        } else if (a < 0 || a > foodMax) {
            System.out.println(" Введите корректное число от 1 до 100.");
        } else {
            food += a;
        }
    }
}
