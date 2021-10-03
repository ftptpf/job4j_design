package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.product.Food;

import java.util.List;

public interface Storage {
    void setFoodList(Food food);
    List<Food> getFoodList();
    void clear();
}
