package ru.yzhiharevich.geekb;

public class Course {

    Obstacle[] obstacles = {new Cross(), new Wall(), new Water()};

    public void doIt(Team team) {
        Competitor[] Competitor = team.createTeam();
        for (Competitor c : Competitor) {
            for (Obstacle o : obstacles) {
                o.doit(c);
                if (!c.isOnDistance()) {
                    break;
                }
            }
        }
        for (Competitor c : Competitor) {
            c.info();
        }
    }
}
