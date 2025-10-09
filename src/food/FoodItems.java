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
    private void addFoodToFile(String filePath, String newFood) {
        try { StringBuilder sb = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                String content = br.readLine();
                if (content != null && !content.isEmpty()) {
                    sb.append(content);
                    sb.append(",");
                }
            }
            sb.append(newFood.trim().toLowerCase());
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
                bw.write(sb.toString());
            }
        } catch (IOException e) {
            System.out.println("Error adding food to file: " + e.getMessage());
        }
    }


    public boolean checkFood(String foodName) {
        if (foodSet.contains(foodName.toLowerCase())) {
//            System.out.println(foodName + " is available.");
            return true;
        } else {
            System.out.println("Sorry, "+ foodName + " is unknown substance :(.");
            return false;
        }
    }
    public void addFood(String foodName) {
        System.out.println("Is " + foodName + " food?, Do you want to add it to the list?");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();

        if(answer.toLowerCase().contains("yes")) {
            addFoodToFile("foodList.txt",foodName);
            System.out.println("Added!! :33");
        }
        if (answer.toLowerCase().contains("no")) {
            System.out.println("Okay, ever mind.");
        }
    }
}
