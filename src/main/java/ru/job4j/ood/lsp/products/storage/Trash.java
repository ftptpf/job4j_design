package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.Percentage;
import ru.job4j.ood.lsp.products.product.Food;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage {

    List<Food> storageTrash = new ArrayList<>();

    @Override
    public void setFoodList(Food food) {
        float percentage = Percentage.getPercentage(food);
        if (percentage > 100.0) {
            storageTrash.add(food);
        }
    }

    @Override
    public List<Food> getFoodList() {
        return storageTrash;
    }
}
