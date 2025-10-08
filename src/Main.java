import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static boolean playGame = true;
    public static void main(String[] args) throws InterruptedException {

//        ------------manual test--------------
//        Pet ch = new Pet("Bob");
//        TestPet testPet = new TestPet("Poor lil thing");
//        testPet.checkStats();

//        ch = checkPets(ch);
//        ch.info();
//        ch.feed();
//
//        ch.checkStats();


//        -------------------------------------
        Pet ch1 = AUX_CLS.readFromBin();
        ch1 = checkPets(ch1);
        while (playGame) {
            chooseAction(ch1);
            ch1.checkStats();
        }
    }
        public static void chooseAction(Pet ch) throws InterruptedException {
            Thread.sleep(500);
            Scanner sc = new Scanner(System.in);
            System.out.println("============================");
            System.out.println("What do you want to do?");
            ch.getAppearance();

            if(ch.isSleeping()){
                printMessages(
                        "★ wake " + ch.getName() + " up ",
                        "★ " + ch.getName() + " info",
                        "★ quit the game",
                        "============================"
                );
                String answer = sc.nextLine().toLowerCase(Locale.ROOT);

                if (answer.toLowerCase().contains("wake")) {
                    ch.wakeUp();
                    AUX_CLS.writeToBin(ch);

                } else if (answer.toLowerCase().contains("info")) {
                    ch.info();
                    AUX_CLS.writeToBin(ch);

                } else if (answer.toLowerCase().contains("quit")) {
                    System.out.println("Bye bye");
                    AUX_CLS.writeToBin(ch);
                    playGame = false;

                } else {
                    AUX_CLS.writeToBin(ch);
                }


            } else {
                printMessages("★ feed " + ch.getName(),
                        "★ rename " + ch.getName(),
                        "★ sleep ",
                        "★ play",
                        "★ take a bath",
                        "★ " + ch.getName() + " info",
                        "",
                        "★ quit the game",
                        "============================");
                String answer = sc.nextLine().toLowerCase(Locale.ROOT);

                if (answer.toLowerCase().contains("feed")) {
                    ch.feed();
                    AUX_CLS.writeToBin(ch);

                } else if (answer.toLowerCase().contains("rename")) {
                    rename(ch);
                    AUX_CLS.writeToBin(ch);

                } else if (answer.toLowerCase().contains("sleep")) {
                    ch.sleep();
                    AUX_CLS.writeToBin(ch);

                } else if (answer.toLowerCase().contains("play")) {
                    ch.playWithPet();
                    AUX_CLS.writeToBin(ch);

                } else if (answer.toLowerCase().contains("bath")) {
                    ch.bath();
                    AUX_CLS.writeToBin(ch);

                } else if (answer.toLowerCase().contains("info")) {
                    ch.info();

                } else if (answer.toLowerCase().contains("quit")) {
                    System.out.println("Bye bye");
                    ch.lastPlayed();
                    AUX_CLS.writeToBin(ch);
                    playGame = false;

                } else {
                    System.out.println("huh...didn't get that, can you repeater yourself?");
                    AUX_CLS.writeToBin(ch);
                }

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
            if (ch == null) {
                ch = newPet();
            } else {
                printMessages("Hello, welcome back to tamagotchi!!");

                LocalDateTime offTime = ch.getOffTime();
                if (offTime != null) {
                    Duration duration = Duration.between(offTime, LocalDateTime.now());
                    long hours = duration.toHours();
                    long minutes = duration.toMinutes();

                    if (hours > 0) {
                        //test
                        ch.info();
                        lowerStats(ch,hours);
                        ch.info();
                        System.out.println("ako je proslo vise od sat trebala bi biti razlika na stats");


                        System.out.println("You last played " + hours + " hours " + (minutes) % 60 + " minutes ago.");
                    } else {
                        System.out.println("You last played " + minutes + " minutes ago.");
                    }

                    if (duration.toMinutes() > 15) {
                        Scanner sc = new Scanner(System.in);
                        printMessages(ch.getName() + " has been waiting so long to see you... where have you been? (mad)");
                    }
                }
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
         * {@link AUX_CLS#writeToBin(Pet)}.
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
            AUX_CLS.writeToBin(ch);
            return ch;
        }

        public static void  lowerStats(Pet ch, long amount) {
            ch.changeStats(amount);
        }

        public static void rename(Pet ch) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Are you sure you want to rename " + ch.getName());
            String answer = sc.nextLine().toLowerCase();

            if (answer.equals("yes") || answer.equals("2") ){
                System.out.println("What would you like to call your pet? :");
                answer = sc.nextLine();
                ch.changeName(answer);
                AUX_CLS.writeToBin(ch);
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