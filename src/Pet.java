import com.sun.source.tree.ForLoopTree;
import food.Food;
import food.FoodItems;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Pet implements Serializable {
    private int age;
    private String name;

    private String[] appearance;

    private double hunger;        // 0 good 100 bad
    private double love;          // 100 good 0 bad
    private double sleep;         // 100 good 0 bad
    private double happiness;     // 100 good 0 bad
    private double health;       // 100 good 0 bad
    private double hygiene;      // 100 good 0 bad
    private double boredom;      // 0 good 100 bad

    private LocalDateTime lastMeal;
    private LocalDateTime sleepStart;
    private LocalDateTime offTime;

    private boolean isSleeping;



    Random rand = new Random();

    public Pet(String name) {
        this.age = rand.nextInt(2);
        this.name = name;
        this.appearance = new String[]{" /\\_/\\", "( o.o )", " > ^ <"};  //default

        this.hunger = rand.nextDouble(10);
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

            FoodItems foodItems = new FoodItems();
            List<Food> foods = foodItems.availableFood();

            for (Food f : foods) {
                System.out.println(f);
            }

            Scanner sc = new Scanner(System.in);
            System.out.println("Choose what do you want to feed " + getName() + ":");
            String choice = sc.nextLine();


            setHunger(getHunger() - 6);  //promjeni poslaje na value od food
            setLove(getLove() + 3);
            setHappiness(getHappiness() + 10);
            setHealth(getHealth() + 2);  //napravi da ovisi o hrani

            this.lastMeal = LocalDateTime.now();
            System.out.println(getName() + " ate, hunger level at: " + (int)getHunger());
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

            setSleep(getSleep() + timeSlept);
            setHealth(getHealth() + (timeSlept*2));
            setBoredom(getBoredom() + (timeSlept*2));
            setHunger(getHunger() + (timeSlept*2));
            setHygiene(getHygiene() - (timeSlept*2));

            System.out.println(getName() + " woke up, sleep: " + (int)getSleep());
        }
    }

    public void bath() {
        Random rand = new Random();
        int interaction = rand.nextInt(3);
        switch(interaction) {
            case 0 -> System.out.println(getName() + " is enjoying the bath...splish splash :3");
            case 1 -> System.out.println(getName() + " is trying to escape the bath, slippery little guy");
            case 2 -> System.out.println(getName() + " is singing in the shower");
        }

        setHygiene(getHygiene()+15);
        setHappiness(getHappiness()+10);
        setHealth(getHealth()+3);
        setBoredom(getBoredom()-4);
        setLove(getLove()+2);
    }
    public void playWithPet() {
        Random rand = new Random();
        int game = rand.nextInt(3);

        switch(game) {
            case 0:
            {
                System.out.println(getName() + " is playing fetch.");
                setHappiness(getHappiness() + 10);
                setBoredom(getBoredom() - 15);
            }
            case 1:
            {
                System.out.println(getName() + " is chasing its tail.");
                setHappiness(getHappiness() + 5);
                setBoredom(getBoredom() - 10);
                setHunger(getHunger() + 2);
            }
            case 2:
            {
                System.out.println(getName() + " is rolling on the floor.");
                setHappiness(getHappiness() + 8);
                setBoredom(getBoredom() - 12);
                setHealth(getHealth() + 2);
            }
        }
    }

    public void changeName(String newName) {
        setName(newName);
        System.out.println("Name successfully changed!");
    }

    public void info(){
        System.out.println(toString());
    }






    public void checkStats() {
        boolean alert = false;

        if (getHunger() > 90) {
            System.out.println("- Warning: " + getName() + " is very hungry!");
            alert = true;
        }
        if (getSleep() < 20) {
            System.out.println("- Warning: " + getName() + " is extremely sleepy!");
            alert = true;
        }
        if (getHealth() < 10) {
            System.out.println("- Alert: " + getName() + "'s health is low!");
            alert = true;
        }
        if (getHappiness() < 20) {
            System.out.println("- Warning: " + getName() + " seems sad.");
            alert = true;
        }
        if (getBoredom() > 90) {
            System.out.println("- Warning: " + getName() + " is very bored!");
            alert = true;
        }
        if (getHygiene() < 10) {
            System.out.println("- Reminder: " + getName() + " needs a bath.");
            alert = true;
        }

        if (alert) {
            info();
        }
    }

    public void lastPlayed() {
        this.offTime = LocalDateTime.now();
        AUX_CLS.writeToBin(this);

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

        if (getSleep() < 20) {
            double amount = 20 - getSleep();

            setHealth(getHealth() - amount);
            setBoredom(getBoredom() - amount);
            setHappiness(getHappiness() - amount);
        }
    }

    public double getHunger() {
        return hunger;
    }
    public void setHunger(double hunger) {
        if (hunger < 0) this.hunger = 0;
        else if (hunger > 100) this.hunger = 100;
        else this.hunger = hunger;

        if (getHunger() > 90) {
            double amount =getHunger() - 90;

            setHealth(getHealth() - amount);
            setHappiness(getHappiness() - amount);
            setLove(getLove() - amount);
        }
    }

    public double getHealth() {
        return health;
    }
    public void setHealth(double health) {
        if (health < 0) this.health = 0;
        else if (health > 100) this.health = 100;
        else this.health = health;

        if (getHealth() < 10) {
            double amount = 10 - getSleep();

            setLove(getLove() - amount);
            setHappiness(getHappiness() - amount);
        }
    }


    public double getLove() {
        return love;
    }
    public void setLove(double love) {
        if (love < 0) this.love = 0;
        else if (love > 100) this.love = 100;
        else this.love = love;
    }


    public double getHappiness() {
        return happiness;
    }
    public void setHappiness(double happiness) {
        if (happiness < 0) this.happiness = 0;
        else if (happiness > 100) this.happiness = 100;
        else this.happiness = happiness;

        if (getHappiness() < 20) {
            double amount = 20 - getHappiness();

            setLove(getLove() - amount);
        }
    }


    public double getHygiene() {
        return hygiene;
    }
    public void setHygiene(double hygiene) {
        if (hygiene < 0) this.hygiene = 0;
        else if (hygiene > 100) this.hygiene = 100;
        else this.hygiene = hygiene;

        if (getHygiene() < 10) {
            double amount = 10 - getHygiene();

            setHealth(getHealth() - amount);
            setHappiness(getHappiness() - amount);
            setLove(getLove() - amount);
        }
    }


    public double getBoredom() {
        return boredom;
    }
    public void setBoredom(double boredom) {
        if (boredom < 0) this.boredom = 0;
        else if (boredom > 100) this.boredom = 100;
        else this.boredom = boredom;

        if (getBoredom() > 90) {
            double amount =getBoredom() - 90;

            setHappiness(getHappiness() - amount);
            setLove(getLove() - amount);
        }
    }
    public LocalDateTime getOffTime() {
        return offTime;
    }


    public void getAppearance() {
        for (String line : this.appearance) {
            System.out.println(line);
        }
    }

//    private void maxAllStats(Pet ch) {
//        this.hunger = 100;
//        this.lastMeal = LocalDateTime.now();
//        this.love = 100;
//        this.sleep = 100;
//        this.happiness = 100;
//        this.health = 100;
//        this.hygiene = 100;
//        this.boredom = 100;
//    }
//    private void lowerAllStats(Pet ch) {
//        this.hunger = 0;
//        this.love = 0;
//        this.sleep = 0;
//        this.happiness = 0;
//        this.health = 0;
//        this.hygiene = 0;
//        this.boredom = 0;
//    }

    protected void changeStats(double amount) {
        this.hunger += amount;
        this.love -= amount;
        this.sleep -= amount;
        this.happiness -= amount;
        this.health -= amount;
        this.hygiene -= amount;
        this.boredom += amount;
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
