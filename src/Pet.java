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
    private double hygiene;
    private double boredom;

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
        this.health = rand.nextDouble(80,100);
        this.hygiene = rand.nextDouble(80,100);
        this.boredom = rand.nextDouble(20);

    }

    public void feed(){
        if(getHunger() == 0){
            System.out.println(getName() + " isn't hungry atm.");
        } else {
            setHunger(getHunger() - 6);  //promjeni poslaje na value od food
            setLove(getLove() + 3);
            setHappiness(getHappiness() + 10);
            setHealth(getHealth() + 2);  //napravi da ovisi o hrani

            this.lastMeal = LocalDateTime.now();
            System.out.println("+" + 6 +", " + getName() + " ate, hunger level at: " + (int)getHunger());
        }
    }
    public void sleep() {
        if (getSleep() == 100) {
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

            setSleep(this.sleep + timeSlept);
            setHealth(this.health + (timeSlept*2));
            setBoredom(this.boredom - (timeSlept*2));
            setHunger(this.hunger - (timeSlept*2));
            setHygiene(this.hygiene - (timeSlept*2));

            System.out.println(getName() + " woke up, sleep: " + (int)getSleep());
        }
    }


    public void changeName(String newName) {
        setName(newName);
        System.out.println("Name successfully changed!");
    }

    public void info(){
        System.out.println(toString());
    }









    public boolean isSleeping() {
        return isSleeping;
    }



    // getters/setters
    public String getName() {
        return name;
    }
    private void setName(String name) {
        this.name = name;
    }


    public double getSleep() {
        return sleep;
    }
    public void setSleep(double sleep) {
        if (sleep < 0) this.sleep = 0;
        else if (sleep > 100) this.sleep = 100;
        else this.sleep = sleep;
    }

    public double getHunger() {
        return hunger;
    }
    public void setHunger(double hunger) {
        if (hunger < 0) this.hunger = 0;
        else if (hunger > 100) this.hunger = 100;
        else this.hunger = hunger;
    }

    public double getHealth() {
        return health;
    }
    public void setHealth(double health) {
        if (health < 0) this.health = 0;
        else if (health > 100) this.health = 100;
        else this.health = health;
    }


    public double getLove() {
        return love;
    }
    public void setLove(double love) {
        this.love = love;
    }


    public double getHappiness() {
        return happiness;
    }
    public void setHappiness(double happiness) {
        if (happiness < 0) this.happiness = 0;
        else if (happiness > 100) this.happiness = 100;
        else this.happiness = happiness;
    }


    public double getHygiene() {
        return hygiene;
    }
    public void setHygiene(double hygiene) {
        if (hygiene < 0) this.hygiene = 0;
        else if (hygiene > 100) this.hygiene = 100;
        else this.hygiene = hygiene;
    }


    public double getBoredom() {
        return boredom;
    }
    public void setBoredom(double boredom) {
        if (boredom < 0) this.boredom = 0;
        else if (boredom > 100) this.boredom = 100;
        else this.boredom = boredom;
    }

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
                "%s is %d years old. Hunger: %.0f, Love: %.0f, Sleep: %.0f, Happiness: %.0f, Health: %.0f, Hygiene: %.0f., last meal: %s, Boredom: %.0f ",
                name, age, hunger, love, sleep, happiness, health, hygiene,lastMealStr,boredom
        );
    }
}
