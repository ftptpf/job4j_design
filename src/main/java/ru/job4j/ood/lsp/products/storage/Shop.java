package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.Percentage;
import ru.job4j.ood.lsp.products.product.Food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {

    List<Food> storageShop = new ArrayList<>();
    double discount = 20;

    @Override
    public void setFoodList(Food food) {
        if (Percentage.getPercentage(food) >= 25 && Percentage.getPercentage(food) <= 75) {
            storageShop.add(food);
        } else if (Percentage.getPercentage(food) > 75 && Percentage.getPercentage(food) <= 100) {
            food.setDiscount(discount);
            storageShop.add(food);
        }
    }

    @Override
    public List<Food> getFoodList() {
        return storageShop;
    }
}
