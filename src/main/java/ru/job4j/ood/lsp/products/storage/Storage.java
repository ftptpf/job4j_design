package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.product.Food;

import java.util.List;

public interface Storage {
    public void setProductList(Food food);
    public List<Food> getProductList();
}
