package NesterovJavaDZ;

public class Dog extends Animal {
    public Dog(String name) {
        super("Пес", name, 500, 50, 20);
    }

    public Dog(String name, int maxJumpHeight, int maxRunDistance, int maxSwimDistance) {
        super("Пёс", name,  maxRunDistance, maxJumpHeight, maxSwimDistance);
    }
}
