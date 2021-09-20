package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.Percentage;
import ru.job4j.ood.lsp.products.product.Food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {

    List<Food> storageWarehouse = new ArrayList<>();

    @Override
    public void setFoodList(Food food) {
        if (Percentage.getPercentage(food) < 25) {
            storageWarehouse.add(food);
        }
    }

    @Override
    public List<Food> getFoodList() {
        return storageWarehouse;
    }
}
