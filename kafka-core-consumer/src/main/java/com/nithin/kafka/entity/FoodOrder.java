package com.nithin.kafka.entity;

public class FoodOrder {

    private String foodName;

    private int amount;

    public FoodOrder(){

    }

    public FoodOrder(String foodName, int amount) {
        this.foodName = foodName;
        this.amount = amount;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "FoodOrder{" +
                "foodName='" + foodName + '\'' +
                ", amount=" + amount +
                '}';
    }
}
