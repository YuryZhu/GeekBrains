package ru.yzhiharevich.marathon_lesson1;

public class Team {
    Competitor[] competitors;
    public Team() {
        createTeam();
    }

    public Competitor[] createTeam() {
        Competitor[] competitors = {new OldLady("Елена Петровна"), new OldMan("Виктор Петрович"), new SportsmanA1("Николай"), new Boy("Пашка")};
        this.competitors = competitors;
        return competitors;
    }

    public void showTeamMates(String teamName) {
        System.out.println("The name of the team is " + teamName);
        System.out.println("New Team was created and the new Members are " + competitors[0].getName() + " and " + competitors[1].getName()
                + " and " + competitors[2].getName() + " and " + competitors[3].getName());
    }
}
