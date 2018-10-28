package ru.yzhiharevich.marathon_lesson1;

public class Cross extends Obstacle {
    int distance;

    public Cross() {
        this.distance = new Randomiser().randomiser(42);
        System.out.println("The distance of the marathon = " + distance + " kilometers");
    }

    @Override
    public void doit(Competitor competitor) {
        competitor.run(distance);
    }
}
