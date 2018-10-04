package NesterovJavaDZ;

public class Cat extends Animal {
    public Cat(String name) {
        super("Кот", name, 200, 30, 0);
    }

    public Cat(String name, int maxHeight, int maxRun) {
                super("Кот", name,  maxHeight, maxRun, 0 );
    }

}
