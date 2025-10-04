package food;

import java.util.ArrayList;
import java.util.List;

public class FoodItems {
    private List<Food> foodList = new ArrayList<>();

    public FoodItems() {
        foodList.add((new Food("Apple", 3)));
        foodList.add((new Food("Banana", 5)));
        foodList.add((new Food("Burgir", 7)));
        foodList.add((new Food("Orange juice", 2)));
    }

    public List<Food> availableFood (){
        List<Food> available = new ArrayList<>();
        for (Food food : foodList) {
            available.add((food));
        }
        return available;
    }
}
