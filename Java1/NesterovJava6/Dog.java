package NesterovJava6;

public class Dog extends Animal{

    @Override
    void makeSound() {
        System.out.println("Woof, woof!");
    }

    public Dog() {
        this.name = "Собака";
        this.age = 0;
    }
    Dog(String name, int age){
        super(name, age);
        maxRun =  (int)(Math.random() * 500);
        maxSwim = (int)(Math.random() * 100);
        maxJump = (int)(Math.random() * 2);
    }

}