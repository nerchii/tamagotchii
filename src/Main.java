import food.FoodItems;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    static boolean playGame = true;
    public static void main(String[] args) {

//        ------------manual test--------------
//        Pet ch = new Pet("Bob");
//        ch = checkPets(ch);
//        chooseAction(ch);

//        -------------------------------------

        Pet ch1 = AUX.readFromBin();
        ch1 = checkPets(ch1);
        while (playGame) {
            chooseAction(ch1);
        }














        }

        public static void chooseAction(Pet ch) {
            Scanner sc = new Scanner(System.in);
            ch.getAppearance();
            printMessages("What do you want to do?",
                    "★ feed " + ch.getName(),
                    "★ rename " + ch.getName(),
                    "★ sleep " + "(" + ch.getName() + " is sleeping: " + ch.isSleeping() + ")",
                    "★ wake up " + "(" + ch.getName() + " is awake: " + !ch.isSleeping() + ")",
                    "★ " + ch.getName() + " info",
                    "★ quit the game");
            String answer = sc.nextLine().toLowerCase(Locale.ROOT);

            switch (answer) {
                case "feed":
                    ch.feed(FoodItems.SPINACH);
                    AUX.writeToBin(ch);
                    break;
                case "rename":
                    rename(ch);
                    AUX.writeToBin(ch);
                    break;
                case "sleep":
                    ch.sleep();
                    AUX.writeToBin(ch);
                    break;
                case "wake up":
                    ch.wakeUp();
                    AUX.writeToBin(ch);
                case "info":
                    ch.info();
                    break;
                case "quit":
                    System.out.println("Bye bye");
                    AUX.writeToBin(ch);
                    playGame = false;
                default:
                    AUX.writeToBin(ch);
            }
        }






        /**
         * Checks whether a Tamagotchi pet exists and handles the appropriate game sequence.
         * <p>
         * If the provided {@link Pet} object {@code ch} is {@code null}, this method
         * initiates the creation of a new pet by calling {@link #newPet()}.
         * If {@code ch} is not {@code null}, it welcomes the returning pet and interacts
         * with the user through the console, prompting for input and responding accordingly.
         * </p>
         * <p>
         * This method is intended to be called at the start of the game to determine whether
         * the player needs to create a new Tamagotchi or continue with an existing one.
         * </p>
         *
         * @param ch the existing {@link Pet} object representing the player's Tamagotchi;
         *           may be {@code null} if no pet exists yet
         */
        public static Pet checkPets(Pet ch) {
            if (ch == null){
                ch = newPet();
            } else {
                printMessages("Hello, welcome back to tamagotchi!!");
//                ch.getAppearance();
//                Scanner sc = new Scanner(System.in);
//                printMessages(ch.getName() + " has been waiting so loong to see you... where have you been? (mad)");
//                String answer = sc.nextLine();
//                System.out.println("Mhm,, okay, I wouldn't say " + answer.toLowerCase() + " is a valid excuse but whatever.");
            }
            return ch;
        }


        /**
         * Starts the sequence for creating a new Tamagotchi pet.
         * <p>
         * This method is intended to be called when the game starts and no existing
         * pet is found. It interacts with the user through the console, asking them
         * to name their new Tamagotchi. Once a name is entered, a new
         * {@link Pet} object is created and saved to a binary file using
         * {@link AUX#writeToBin(Pet)}.
         * </p>
         * <p>
         * <b>Note:</b> This method does <em>not</em> check whether a pet already exists.
         * If a pet already exists (i.e., the binary save file is present), calling this
         * method will create a new pet and overwrite any existing data.
         * </p>
         */
        public static Pet newPet() {
            Scanner sc = new Scanner(System.in);
            printMessages(
                    "Hello, welcome to Tamagotchi!!",
                    "I see that you are petless... wanna fix that?",
                    "psss... look over here, don't tell anyone about this;",
                    "...",
                    "* gets closer to you and opens up a box *",
                    "What do you wanna name it? "
            );
            String chName = sc.nextLine();
            Pet ch = new Pet(chName);
            printMessages("Greattt, good choice, surely " + chName + " is an uh * interesting * name but okay heh I won't police you.");
            AUX.writeToBin(ch);
            return ch;
        }

        public static void rename(Pet ch) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Are you sure you want to rename " + ch.getName());
            String answer = sc.nextLine().toLowerCase();

            if (answer.equals("yes") || answer.equals("2") ){
                System.out.println("What would you like to call your pet? :");
                answer = sc.nextLine();
                ch.changeName(answer);
                AUX.writeToBin(ch);
            }  else {
                System.out.println("OkeyDokey, gonna ignore that.");
            }
        }


        public static void printMessages(String... messages) {
            for (String msg : messages) {
                System.out.println(msg);
            }
        }




}
//}