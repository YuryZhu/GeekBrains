package ru.yzhiharevich.geekb;

public class Wall extends Obstacle {
    int height;

    public Wall() {
        this.height = new Randomiser().randomiser(5);
        System.out.println("The height of the obstacle = " + height + " meters");
    }

    @Override
    public void doit(Competitor competitor) {
        competitor.jump(height);
    }
}
