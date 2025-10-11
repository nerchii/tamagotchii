import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static boolean playGame = true;
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {

//        ------------manual test--------------
//        Pet ch = new Pet("Bob");
//        TestPet testPet = new TestPet("Poor lil thing");
//        testPet.checkStats();

//        ch = checkPets(ch);
//        ch.info();
//        ch.feed();
//        ch.checkStats();
//        -------------------------------------
        Pet ch1 = AUX_CLS.readFromBin();
        ch1 = initializePet(ch1);

        while (playGame) {
            chooseAction(ch1);
            AUX_CLS.writeToBin(ch1);
            ch1.checkStats();
        }
    }


        public static void chooseAction(Pet ch) throws InterruptedException {
            Thread.sleep(500);
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
                } else if (answer.toLowerCase().contains("info")) {
                    ch.info();
                } else if (answer.toLowerCase().contains("quit")) {
                    System.out.println("Bye bye");
                    playGame = false;
                } else {
//                    AUX_CLS.writeToBin(ch);
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
                } else if (answer.toLowerCase().contains("rename")) {
                    rename(ch);
                } else if (answer.toLowerCase().contains("sleep")) {
                    ch.sleep();
                } else if (answer.toLowerCase().contains("play")) {
                    ch.playWithPet();
                } else if (answer.toLowerCase().contains("bath")) {
                    ch.bath();
                } else if (answer.toLowerCase().contains("info")) {
                    ch.info();
                } else if (answer.toLowerCase().contains("quit")) {
                    System.out.println("Bye bye");
                    ch.lastPlayed();
                    playGame = false;
                } else {
                    System.out.println("huh...didn't get that, can you repeater yourself?");
                }
            }
        }

        public static Pet initializePet(Pet ch) {
            if (ch == null) {
                ch = createNewPet();
            } else {
                printMessages("Hello, welcome back to tamagotchi!!");
                returnFromOffLine(ch);
            }
            return ch;
        }

        public static void returnFromOffLine(Pet ch){
            LocalDateTime offTime = ch.getOffTime();
            if (offTime != null) {
                Duration duration = Duration.between(offTime, LocalDateTime.now());
                long hours = duration.toHours();
                long minutes = duration.toMinutes();

                if (hours > 0) {
                    ch.info();
                    lowerStats(ch,hours);
                    ch.info();

                    System.out.println("You last played " + hours + " hours " + (minutes) % 60 + " minutes ago.");
                } else {
                    System.out.println("You last played " + minutes + " minutes ago.");
                }

                if (duration.toMinutes() > 15) {
                    printMessages(ch.getName() + " has been waiting so long to see you... where have you been? (mad)");
                    String answer = sc.nextLine();
                    printMessages("Idk if " + answer + " is a valid reason but ok...");
                }
            }
        }

        public static Pet createNewPet() {
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