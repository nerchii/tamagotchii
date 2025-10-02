import food.Food;
import java.io.Serializable;
import java.util.Arrays;

public class Charachter implements Serializable {
    private int age;
    private String name;

    private String[] appearance;

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
        this.appearance = new String[]{" /\\_/\\", "( o.o )", " > ^ <"};  //default
    }

    public void feed(Food food){
        if(this.hunger == 0){
            System.out.println(this.name + " isn't hungry atm.");
        } else {
            this.hunger -= food.getValue();
            if (this.hunger <= 0){
                this.hunger = 0;
                System.out.println(this.name + " is full now.");
            }
            System.out.println(this.name + " ate, hunger level at: " + this.hunger);
        }
    }
    public void changeName(String newName) {
        setName(newName);
        System.out.println("Name successfully changed!");
    }

    public void info(){
        System.out.println(toString());
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

//    private void setAppearance() {
//        this.appearance = new String[]{"/\\_/\\", "( o.o )", " > ^ <"};
//    }


    public void getAppearance() {
        for (String line : this.appearance) {
            System.out.println(line);
        }
    }


    @Override
    public String toString() {
        return String.format(
                "%s is %d years old. Hunger: %.0f, Love: %.0f, Sleep: %.0f, Happiness: %.0f, Health: %.0f, Hygiene: %.0f.",
                name, age, hunger, love, sleep, happiness, health, hygine
        );
    }
}
