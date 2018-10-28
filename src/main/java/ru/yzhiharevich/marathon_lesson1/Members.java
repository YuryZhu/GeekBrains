package ru.yzhiharevich.geekb;

public class Members implements Competitor {

    String type;
    private String name;

    int maxRunDistance;
    int maxSwimDistance;
    int maxJumpHeight;

    boolean onDistance;

    public Members(String type, String name, int maxRunDistance, int maxSwimDistance, int maxJumpHeight) {
        this.type = type;
        this.setName(name);
        this.maxRunDistance = maxRunDistance;
        this.maxSwimDistance = maxSwimDistance;
        this.maxJumpHeight = maxJumpHeight;
        this.onDistance = true;
    }

    @Override
    public void run(int dist) {
        if (dist <= maxRunDistance) {
            System.out.println(type + " " + getName() + " справился с кроссом");
        } else {
            System.out.println(type + " " + getName() + " не справился с кроссом");
            onDistance = false;
        }
    }

    @Override
    public void swim(int dist) {
        if (dist <= maxSwimDistance) {
            System.out.println(type + " " + getName() + " справился с заплывом");
        } else {
            System.out.println(type + " " + getName() + " не справился с заплывом");
            onDistance = false;
        }
    }

    @Override
    public void jump(int height) {
        if (height <= maxJumpHeight) {
            System.out.println(type + " " + getName() + " справился с прыжком");
        } else {
            System.out.println(type + " " + getName() + " не справился с прыжком");
            onDistance = false;
        }
    }

    @Override
    public boolean isOnDistance() {
        return onDistance;
    }

    @Override
    public void info() {
        if (onDistance) {
            System.out.println("Учаастник " + getName() + " прошел дистанцию");
        } else {
            System.out.println("Учаастник " + getName() + " не прошел дистанцию");
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}