package ru.yzhiharevich.marathon_lesson1;

public class App {
    public static void main(String[] args) {
        Course c = new Course(); // Создаем полосу препятствий
        Team team = new Team(); // Создаем команду
        team.showTeamMates("Спортсмены 80");
        c.doIt(team); // Просим команду пройти полосу
    }
}
