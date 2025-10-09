package food;

public class Food {
    private String foodName;
    private int value;

    protected void chooseFood(){}


    protected Food(String foodName, int value) {
        this.foodName = foodName;
        this.value = value;
    }

    public String getFoodName() {
        return foodName;
    }
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }


    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return getFoodName();
    }
}
