import food.Food;

import java.net.StandardSocketOptions;

public class Charachter {
    private int age;
    private String name;

    private double hunger;
    private double love;
    private double sleep;
    private double happiness;
    private double health;
    private double hygine;

    public Charachter(String name) {
        this.age = 0;
        this.name = name;
        this.hunger = 10;
        this.love = 100;
        this.sleep = 90;
        this.happiness = 100;
        this.hygine = 100;
        this.health = 100;
    }

    public void feed(Food food){
    }

    @Override
    public String toString() {
        return "Charachter{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", hunger=" + hunger +
                ", love=" + love +
                ", sleep=" + sleep +
                ", happiness=" + happiness +
                ", health=" + health +
                ", hygine=" + hygine +
                '}';
    }
}
