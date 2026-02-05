package food;
import java.io.*;
import java.util.*;


/**
 * Manages a list of food items that a pet can eat.
 * Foods are stored in a file and loaded into memory at runtime.
 */
public class FoodItems {
    private static final String foodListFilePath = "foodList.txt";
    private Set<String> foodSet = new HashSet<>();

    public FoodItems() {
        loadFoodsFromFile();
    }

    /** Loads food items from the file into memory */
    private void loadFoodsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(foodListFilePath))) {
            String content = br.readLine();
            if (content != null) {
                String[] foods = content.split(",");
                for (String food : foods) {
                    foodSet.add(food.trim().toLowerCase());
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading food list: " + e.getMessage());
        }
    }

    /** Adds a new food to the file and in-memory set */
    private void addFoodToFile(String newFood) {
        try { StringBuilder sb = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(foodListFilePath))) {
                String content = br.readLine();
                if (content != null && !content.isEmpty()) {
                    sb.append(content);
                    sb.append(", ");
                }
            }
            sb.append(newFood.trim().toLowerCase());
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(foodListFilePath))) {
                bw.write(sb.toString());
            }
        } catch (IOException e) {
            System.out.println("Error adding food to file: " + e.getMessage());
        }
    }

    /**
     * Checks if food exists in the set.
     * @param foodName the name of the food
     * @return true if the food exists, false otherwise
     */
    public boolean checkFood(String foodName) {
        if (foodSet.contains(foodName.toLowerCase())) {
//            System.out.println(foodName + " is available.");
            return true;
        } else {
            System.out.println("Sorry, "+ foodName + " is unknown substance :(.");
            return false;
        }
    }

    /**
     * Prompts the user to add a new food to the list.
     * @param foodName the food to add
     * @return true if added, false otherwise
     */
    public boolean addFood(String foodName) {
        System.out.println("Is " + foodName + " food?, Do you want to add it to the list?");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine().toLowerCase();

        if(answer.toLowerCase().contains("yes")) {
            addFoodToFile(foodName);
            System.out.println("Added!! :33");
            return true;
        }
        if (answer.toLowerCase().contains("no")) {
            System.out.println("Okay, ever mind.");
        }
        return false;
    }
}
