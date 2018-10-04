package NesterovJava6;

public abstract class Animal {

    String name;
    int age;
    private int distanceRun;
    private int distanceSwim;
    private int heightJump;
    int maxRun = 0;
    int maxSwim = 0;
    int maxJump = 0;

    Animal(){
        this.name = "Жывтоне";
        this.age = 0;
    }

    Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }
    boolean run(int distanceRun){
        this.distanceRun = distanceRun;
        if (this.distanceRun <= this.maxRun) {
            return true;
        } else return false;
    }

    boolean swim(int distanceSwim){
        this.distanceSwim = distanceSwim;
        if (this.distanceSwim <= this.maxSwim) {
            return true;
        } else return false;
    }

   boolean jump(int heightJump){
        this.heightJump = heightJump;
        if (this.heightJump <= this.maxJump) {
           return true;
       } else return false;
    }

    abstract void makeSound();

   void report() {
       System.out.printf("  %s.  Возраст: %d лет. Сколько бежит: %d метров. Сколько прыгает: %d метров. Сколько плывёт: %d метров. \n" , this.name, this.age, this.maxRun, this.maxJump, this.maxSwim);
       this.makeSound();
   }

}


