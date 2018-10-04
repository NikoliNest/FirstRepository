package nesterovJava7;

public class Cat {
    int number = 1;
    boolean satiety = false;
    int appetite = (int) (Math.random() * 20 + 3);

    Cat(int i) {
        this.number = i;
    }

    void eatFromPlate(Plate plate) {
        plate.decreaseFood(this, plate);
    }

    void cantEatFromPlate(Plate plate, Cat cat) {
        System.out.printf("\n Кот %s (аппетит - %d) не поел из тарелки, там мало еды.", cat.number, cat.appetite);
        plate.fillPlate();
    }
}



