import food.FoodItems;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TamagotchiApp extends Application {
    private Pet pet;
    private Label nameLabel;
    private ProgressBar hungerBar;
    private ProgressBar happinessBar;
    private ProgressBar healthBar;
    private TextArea terminal;

    private boolean waitingForInput = false;
    private Runnable pendingAction;
    private String lastInput;


    @Override
    public void start(Stage stage) {
        Font.loadFont(getClass().getResourceAsStream("resources/PressStart2P-Regular.ttf"), 10);

        pet = AUX_CLS.readFromBin();
        if (pet == null) pet = new Pet("Fluffy"); //prompt za novi pet

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);

        HBox topPane = new HBox(15);
        topPane.setAlignment(Pos.CENTER);

        VBox petDisplay = new VBox(5);
        petDisplay.setAlignment(Pos.CENTER);
        petDisplay.setPrefWidth(150);

        String petAscii =
                " ♪　∧＿∧∩\n" +
                "　　（ ´・ω・)ﾉ\n" +
                "　⊂l⌒i　 /　　　♪\n" +
                "　　（＿） )　 ☆\n" +
                "　　((（＿）☆";

        Label petPlaceholder = new Label(petAscii);
        petPlaceholder.setId("pet-name");
        petPlaceholder.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 20px; -fx-text-fill: black;");
        petDisplay.getChildren().add(petPlaceholder);

        VBox statusBars = new VBox(8);
        statusBars.setAlignment(Pos.TOP_LEFT);

        Label hungerLabel = new Label("HUNGER");
        hungerLabel.getStyleClass().add("status-label");
        hungerBar = new ProgressBar(pet.getHunger() / 100);
        hungerBar.getStyleClass().add("hunger");

        Label happinessLabel = new Label("HAPPINESS");
        happinessLabel.getStyleClass().add("status-label");
        happinessBar = new ProgressBar(pet.getHappiness() / 100);
        happinessBar.getStyleClass().add("happiness");

        Label healthLabel = new Label("HEALTH");
        healthLabel.getStyleClass().add("status-label");
        healthBar = new ProgressBar(pet.getHealth() / 100);
        healthBar.getStyleClass().add("health");

        statusBars.getChildren().addAll(
                hungerLabel, hungerBar,
                happinessLabel, happinessBar,
                healthLabel, healthBar
        );


        topPane.getChildren().addAll(petDisplay, statusBars);

        // ----- BUTTONS -----
        HBox buttonContainer = new HBox(8);
        buttonContainer.setAlignment(Pos.CENTER);

        Button feedBtn = new Button("FEED");
        Button playBtn = new Button("PLAY");
        Button sleepBtn = new Button("SLEEP");
        Button bathBtn = new Button("BATH");

//        feedBtn.setOnAction(e -> performFeed());
//        playBtn.setOnAction(e -> performPlay());
//        sleepBtn.setOnAction(e -> performSleep());
//        bathBtn.setOnAction(e -> performBath());
//        buttonContainer.getChildren().addAll(feedBtn, playBtn, sleepBtn, bathBtn);


        // ----- TERMINAL -----
        terminal = new TextArea();
        terminal.setEditable(true);
//        terminal.setWrapText(true);
        terminal.setPrefRowCount(15);
        terminal.setStyle("-fx-font-family: 'Courier New'; -fx-font-size: 12px;");
        terminal.setId("terminal");
        terminalBootUp();

        terminal.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER -> {
                    event.consume(); // prevent new line
                    String text = terminal.getText();
                    int lastPrompt = text.lastIndexOf(">>> ") + 4;
                    lastInput = text.substring(lastPrompt).trim();

                    if (waitingForInput) {
                        waitingForInput = false;   // reset waiting state
                        if (pendingAction != null) pendingAction.run(); // run action
                        pendingAction = null;
                    } else {
                        processCommand(lastInput); // normal command
                    }

                    terminal.appendText("\n>>> "); // new prompt
                }
            }
        });


        root.getChildren().addAll(
                topPane,
                buttonContainer,
                terminal
        );

        Scene scene = new Scene(root, 400, 500);
        scene.setCursor(Cursor.HAND);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setScene(scene);
        stage.setTitle("Digital Tamagotchi");
        stage.show();
    }
    
    
    
    private void processCommand(String command) {
        switch (command.toLowerCase()) {
            case "feed" -> performFeed();
            case "play" -> performPlay();
            case "sleep" -> performSleep();
            case "bath" -> performBath();
            default -> logToTerminal(">>> Unknown command: " + command);
        }
    }

    private void terminalBootUp() {
        logToTerminal(">>> Initializing Tamagotchi...");
        logToTerminal(">>> Loading pet data...");
        logToTerminal(">>> Tamagotchi Boot Complete");
        logToTerminal(">>> Welcome, " + pet.getName() + "!");
    }

    private void logToTerminal(String message) {
        terminal.appendText(message + "\n");
    }






    private void performFeed() {
        feedInteractive();
        updateBars();
        AUX_CLS.writeToBin(pet);
        logToTerminal(">>> Fed " + pet.getName() + "! Yummy!");
    }

    private void performPlay() {
        pet.playWithPet();
        updateBars();
        AUX_CLS.writeToBin(pet);
        logToTerminal(">>> Played with " + pet.getName() + "! So fun!");
    }

    private void performSleep() {
        pet.sleep();
        updateBars();
        AUX_CLS.writeToBin(pet);
        logToTerminal(">>> " + pet.getName() + " is sleeping... Zzz");
    }

    private void performBath() {
        pet.bath();
        updateBars();
        AUX_CLS.writeToBin(pet);
        logToTerminal(">>> Gave " + pet.getName() + " a bath! So clean!");
    }

    private void updateBars() {
        hungerBar.setProgress(pet.getHunger() / 100);
        happinessBar.setProgress(pet.getHappiness() / 100);
        healthBar.setProgress(pet.getHealth() / 100);
        nameLabel.setText(pet.getName());
    }



    public void feedInteractive() {
        if (pet.getHunger() == 0) {
            logToTerminal(pet.getName() + " isn't hungry atm.");
            return;
        }

        FoodItems foodItems = new FoodItems();
        logToTerminal("What do you want to feed " + pet.getName() + "?");
        waitingForInput = true;
        pendingAction = new Runnable() {
            @Override
            public void run() {
                String choice = lastInput.trim();
                boolean exists = foodItems.checkFood(choice);
                if (exists) {
                    pet.giveFood();
                    logToTerminal(pet.getName() + " ate the " + choice + "!");
                } else {
                    boolean added = foodItems.addFood(choice);
                    if (added) {
                        pet.giveFood();
                        logToTerminal(pet.getName() + " enjoyed the new " + choice + "!");
                    } else {
                        logToTerminal(choice + " cannot be added.");
                    }
                }
            }
        };

    }


    public static void main(String[] args) {
        launch(args);
    }
}