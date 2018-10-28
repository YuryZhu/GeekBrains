package ru.yzhiharevich.marathon_lesson1;


public class Water extends Obstacle {
    int distance;

    public Water() {
        this.distance = new Randomiser().randomiser(10);
        System.out.println("Swim distance = " + distance + " kilometers");
    }

    @Override
    public void doit(Competitor competitor) {
        competitor.swim(distance);
    }
}
