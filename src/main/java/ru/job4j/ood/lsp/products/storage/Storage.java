package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.product.Food;

import java.util.List;

public interface Storage {
    public void setFoodList(Food food);
    public List<Food> getFoodList();
}
