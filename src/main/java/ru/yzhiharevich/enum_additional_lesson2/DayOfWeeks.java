package ru.yzhiharevich.enumlesson;

public enum DayOfWeeks {
    MONDAY("Понедельник", 40), Tuesday("Вторник", 32),
    Wednesday("Среда", 24), Thursday("Четверг", 16),
    Friday("Пятница", 8),Saturday("Суббота", 0),
    Sunday("Воскресение", 0);

    private String rus;

    public String getRus() {
        return rus;
    }

    private int stillWork;

    public int getWorkingHours() {
        return stillWork;
    }

    DayOfWeeks (String rus, int stillWork) {
        this.rus = rus;
        this.stillWork = stillWork;
    }
}
