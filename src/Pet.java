import food.Food;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Pet implements Serializable {
    private int age;
    private String name;

    private String[] appearance;

    private double hunger;
    private double love;
    private double sleep;
    private double happiness;
    private double health;
    private double hygine;

    private LocalDateTime lastMeal;
    private LocalDateTime sleepStart;
    private boolean isSleeping;



    Random rand = new Random();
    public Pet(String name) {
        this.age = rand.nextInt(2);
        this.name = name;
        this.appearance = new String[]{" /\\_/\\", "( o.o )", " > ^ <"};  //default

        this.hunger = rand.nextDouble(20);
        this.lastMeal = null;
        this.love = rand.nextDouble(80,100);
        this.sleep = rand.nextDouble(60,100);
        this.happiness = rand.nextDouble(80,100);
        this.hygine = rand.nextDouble(80,100);
        this.health = rand.nextDouble(80,100);
    }

    public void feed(Food food){
        if(this.hunger == 0){
            System.out.println(getName() + " isn't hungry atm.");
        } else {
            this.hunger -= food.getValue();
            this.love += 15;
            if (this.hunger <= 0){
                this.hunger = 0;
                System.out.println(getName() + " is full now.");
            }
            this.lastMeal = LocalDateTime.now();
            System.out.println("+" + food.getValue()+", " + getName() + " ate, hunger level at: " + this.hunger);  //napravi getter za hunger
        }
    }
    public void sleep() {
        if (this.sleep == 100) {
            System.out.println(getName() + " isn't sleepy atm.");
        } else {
            if (isSleeping) {
                System.out.println(getName() + " is already sleeping  zZzZzzzZZzzZZzzz...");
                return;
            }
            this.sleepStart = LocalDateTime.now();
            this.isSleeping = true;
            System.out.println(getName() + " went to sleep mimimim");
        }
        }

    public void wakeUp() {
        if (!isSleeping) {
            System.out.println(getName() + " is awake already smh.");
            return;
        }
        long timeSlept = Duration.between(sleepStart, LocalDateTime.now()).toSeconds();

        if (timeSlept < 10) {
            System.out.println(getName() + " is still tired, let them sleep more...");
        } else {
            this.isSleeping = false;
            this.sleepStart = null;

            if ((this.sleep += timeSlept) >= 100){
                this.sleep = 100;
            } else {
                this.sleep += timeSlept;
            }
            System.out.println(getName() + " woke up, sleep: " + getSleep());
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

    public double getSleep() {
        return sleep;
    }

    public double getHunger() {
        return hunger;
    }

    public boolean isSleeping() {
        return isSleeping;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM HH:mm"); // dan/mesec/godina sat:min
        String lastMealStr = (lastMeal != null) ? lastMeal.format(formatter) : "never";

        return String.format(
                "%s is %d years old. Hunger: %.0f, Love: %.0f, Sleep: %.0f, Happiness: %.0f, Health: %.0f, Hygiene: %.0f., last meal: %s ",
                name, age, hunger, love, sleep, happiness, health, hygine,lastMealStr
        );
    }
}
