package NesterovJavaDZ;

public class Animal implements Competitor {
    String type;
    String name;

    int maxRunDistance;
    int maxJumpHeight;
    int maxSwimDistance;

    boolean onDistance;

    @Override
    public boolean isOnDistance() {
        return onDistance;
    }

    public Animal(String type, String name, int maxRunDistance, int maxJumpHeight, int maxSwimDistance) {
        this.type = type;
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.maxSwimDistance = maxSwimDistance;
        this.onDistance = true;
    }

    @Override
    public void run(int dist) {
        if (dist <= maxRunDistance) {
            System.out.println(type + " " + name + " хорошо справился с кроссом " + dist + " метров.");
        } else {
            System.out.println(type + " " + name + " не справился с кроссом " + dist + " метров.");
            onDistance = false;
        }
    }

    @Override
    public void jump(int height) {
        if (height <= maxJumpHeight) {
            System.out.println(type + " " + name + " удачно перепрыгнул через стену "  + height + " см.");
        } else {
            System.out.println(type + " " + name + " не смог перепрыгнуть стену " + height + " см.");
            onDistance = false;
        }
    }

    @Override
    public void swim(int dist) {
        if (maxSwimDistance == 0) {
            System.out.println(type + " " + name + " не умеет плавать");
            onDistance = false;
            return;
        }
        if (dist <= maxSwimDistance) {
            System.out.println(type + " " + name + " отлично проплыл " + dist + " метров.");
        } else {
            System.out.println(type + " " + name + " не смог проплыть "  + dist + " метров.");
            onDistance = false;
        }
    }

    @Override
    public void info() {
        System.out.println(type + " " + name + " - " + onDistance);
    }
}
