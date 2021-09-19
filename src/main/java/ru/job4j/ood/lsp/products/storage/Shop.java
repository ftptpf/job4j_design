package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.product.Food;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage {

    List<Food> storageShop = new ArrayList<>();

    @Override
    public void setProductList(Food food) {
    }

    @Override
    public List<Food> getProductList() {
        return storageShop;
    }
}
