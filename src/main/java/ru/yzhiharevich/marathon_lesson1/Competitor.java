package ru.yzhiharevich.marathon_lesson1;

public interface Competitor {
    void run(int dist);
    void swim(int dist);
    void jump(int height);
    boolean isOnDistance();
    void info();
    String getName();
}
