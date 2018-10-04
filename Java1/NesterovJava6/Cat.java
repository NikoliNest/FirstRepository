package NesterovJava6;

public class Cat extends Animal{

    @Override
    void makeSound() {
        System.out.println("Meow, meow!");
    }

    public Cat() {
        this.name = "Кошка";
        this.age = 0;
    }

    Cat(String name, int age) {
        super(name, age);
        maxRun =  (int)(Math.random() * 300);
        maxSwim = 0;
        maxJump = (int)(Math.random() * 1);
    }
}
