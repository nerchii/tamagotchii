package food;

import java.io.*;
import java.util.*;

public class FoodItems {
    private Set<String> foodSet = new HashSet<>();

    public FoodItems(String filePath) {
        loadFoodsFromFile(filePath);
    }

    private void loadFoodsFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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

    public FoodItems checkFood(String foodName) {
        if (foodSet.contains(foodName.toLowerCase())) {
            System.out.println(foodName + " is available.");
        } else {
            System.out.println("Sorry, "+ foodName + " is not in the food list.");
        }
        return null;
    }
}
