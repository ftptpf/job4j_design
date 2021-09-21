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
        float percentage = Percentage.getPercentage(food);
        if (percentage >= 25.0 && percentage <= 75.0) {
            storageShop.add(food);
        } else if (percentage > 75.0 && percentage <= 100.0) {
            food.setDiscount(discount);
            storageShop.add(food);
        }
    }

    @Override
    public List<Food> getFoodList() {
        return storageShop;
    }
}
