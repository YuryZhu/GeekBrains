package ru.yzhiharevich.java3_lesson1;

public class Fruit {

    String name;
    float WEIGHT;
    int amount;
    public Fruit(int amount,float WEIGHT,String name){
        setAmount(amount);
        setWEIGHT(WEIGHT);
        setName(name);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getWEIGHT() {
        return WEIGHT;
    }

    public void setWEIGHT(float WEIGHT) {
        this.WEIGHT = WEIGHT;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void info() {
        System.out.println(name);
    }
}
