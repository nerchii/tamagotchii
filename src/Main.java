import food.FoodItems;

public class Main {
    public static void main(String[] args) {
        Charachter ch1 = new Charachter("Bob");
        ch1.feed(FoodItems.BREAD);
        System.out.println(ch1);

    }
}