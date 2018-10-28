package ru.yzhiharevich.yellowpages_lesson3;

public class LastNames {
    private String lastName;
    private String phone;

    public LastNames(String lastName, String phone) {
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getName() {
        return lastName;
    }

    public void setName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void info() {
        System.out.println("The last name is: " + lastName + ", and phone: +" + phone);
    }
}