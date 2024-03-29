package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.Percentage;
import ru.job4j.ood.lsp.products.product.Food;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage {

    List<Food> storageWarehouse = new ArrayList<>();

    @Override
    public void setFoodList(Food food) {
        float percentage = Percentage.getPercentage(food);
        if (percentage < 25.0) {
            storageWarehouse.add(food);
        }
    }

    @Override
    public List<Food> getFoodList() {
        return storageWarehouse;
    }

    @Override
    public void clear() {
        storageWarehouse.clear();
    }
}
