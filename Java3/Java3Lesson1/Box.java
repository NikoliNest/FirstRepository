import java.util.ArrayList;

public class Box<T extends Fruit> {
    T whatFor;
    ArrayList<T> fruitsInBox;
    int number;
    public Box(T whatFor, int number) {
        this.number = number;
        this.whatFor = whatFor;
        this.fruitsInBox = new ArrayList<>(10);
    }
    float getWeight() { // Измерение веса коробки
        float weightOfBox = 0.0f;
        for (int i = 0; i < fruitsInBox.size(); i++) {
            weightOfBox = weightOfBox + fruitsInBox.get(i).weight;
        }
        return weightOfBox;
    }
    boolean compare(Box<? extends Fruit> anotherBox) { // Метод сравнения двух коробок по весу
        if (this.getWeight() == anotherBox.getWeight()) {
            System.out.println("1 коробка: " + this.getWeight() + "  2 коробка:  " + anotherBox.getWeight());
            return true;
        } else return false;
    }
    void addFruit(Fruit fruit) { // Метод добавления фрукта в коробку
        if (fruit.getClass().equals(this.whatFor.getClass())) {
            fruitsInBox.add((T) fruit);
        } else
            System.out.printf("Невозможно поместить фрукт %s в коробку для %s. \n", fruit.getClass().toString(), whatFor.getClass().toString());
    }
    private void justPour(Box<T> anotherBox) { // Метод пересыпает фрукты из коробки в коробку.
        for (int i = 0; i < this.fruitsInBox.size(); i++) {
            T tempFruit = this.fruitsInBox.get(i);
            anotherBox.fruitsInBox.add(tempFruit);
        }
        for (int i = this.fruitsInBox.size() - 1; i >= 0; i--) {
            this.fruitsInBox.remove(i);
        }
    }
    void pour(Box<? extends Fruit> anotherBox) { // Проверка соответствия типов фруктов
        if (anotherBox.whatFor.getClass().equals(this.whatFor.getClass())) {
            justPour((Box<T>) anotherBox);
        } else
            System.out.printf("Невозможно пересыпать фрукты %s в коробку для %s. \n",
                    anotherBox.whatFor.getClass().toString(), whatFor.getClass().toString());
    }
    @Override
    public String toString() {
        return fruitsInBox.toString();
    }
}
class Fruit {
    float weight;

    public float getWeight() {
        return this.weight;
    }
    @Override
    public String toString() {
        return this.getClass().toString();
    }
}
class Apple extends Fruit {
    String sort;
    public Apple(String sort) {
        weight = 1.0f;
        this.sort = sort;
    }
    @Override
    public String toString() {
        return this.sort;
    }
}
class Orange extends Fruit {
    String sort;
    public Orange(String sort) {
        weight = 1.5f;
        this.sort = sort;
    }
    @Override
    public String toString() {
        return this.sort;
    }
}
class Three {
    public static void main(String[] args) {
        Apple appleSign = new Apple("Этикетка с яблоком");
        Box<Apple> appleBox1 = new Box<>(appleSign, 1);
        Apple apple0 = new Apple("Антоновка");
        Apple apple1 = new Apple("Голден");
        Apple apple2 = new Apple("Симиренко");
        Apple apple3 = new Apple("Грени Смит");
        Apple apple4 = new Apple("Антоновка");
        Apple apple5 = new Apple("Грени Смит");
        appleBox1.addFruit(apple0);
        appleBox1.addFruit(apple1);
        appleBox1.addFruit(apple2);
        appleBox1.addFruit(apple3);
        appleBox1.addFruit(apple4);
        appleBox1.addFruit(apple5);
        System.out.println("Коробка с яблоками 1 " + appleBox1.toString());

        Orange orangeSign = new Orange("Этикетка с апельсином");
        Box<Orange> orangeBox1 = new Box<>(orangeSign, 2);
        Orange orange0 = new Orange("Марокко");
        Orange orange1 = new Orange("Израиль");
        Orange orange2 = new Orange("Испания");
        Orange orange3 = new Orange("Абхазия");
        Orange orange4 = new Orange("Абхазия");
        orangeBox1.addFruit(orange0);
        orangeBox1.addFruit(orange1);
        orangeBox1.addFruit(orange2);
        orangeBox1.addFruit(orange3);
        System.out.println("Коробка с апельсинами 1 " + orangeBox1.toString());

        System.out.println("Сравним коробки:" + appleBox1.compare(orangeBox1));

        System.out.print("Попробуем положить апельсин к яблокам: ");
        appleBox1.addFruit( orange4);

        Box<Apple> appleBox2 = new Box<>(appleSign, 3);
        System.out.println("Пересыпем яблоки в новую коробку: ");
        appleBox1.pour(appleBox2);
        System.out.println("Коробка с яблоками 1 " + appleBox1.toString());
        System.out.println("Коробка с яблоками 2 " + appleBox2.toString());

        System.out.print("Попробуем пересыпать апельсины в коробку из-под яблок: ");
        appleBox2.pour(orangeBox1);
    }
}