package NesterovJavaDZ;

public class Human implements Competitor {
    String name;

    int maxRunDistance;
    int maxJumpHeight;
    int maxSwimDistance;

    boolean active;

    public Human(String name, int maxJumpHeight, int maxRunDistance, int maxSwimDistance) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.maxSwimDistance = maxSwimDistance;
        this.active = true;
    }


    @Override
    public boolean isOnDistance() {
        return active;
    }

    public Human(String name) {
        this.name = name;
        this.maxRunDistance = 5000;
        this.maxJumpHeight = 70;
        this.maxSwimDistance = 200;
        this.active = true;
    }

    @Override
    public void run(int dist) {
        if (dist <= maxRunDistance) {
            System.out.println(name + " хорошо справился с кроссом " + dist + " метров.");
        } else {
            System.out.println(name + " не справился с кроссом " + dist + " метров.");
            active = false;
        }
    }

    @Override
    public void jump(int height) {
        if (height <= maxJumpHeight) {
            System.out.println(name + " удачно перепрыгнул через стену " + height + " см.");
        } else {
            System.out.println(name + " не смог перепрыгнуть стену " + height + " см.");
            active = false;
        }
    }

    @Override
    public void swim(int dist) {
        if (dist <= maxSwimDistance) {
            System.out.println(name + " отлично проплыл " + dist + " метров.");
        } else {
            System.out.println(name + " не смог проплыть" + dist + " метров.");
            active = false;
        }
    }

    @Override
    public void info() {
        System.out.println(name + " - " + active);
    }
}
