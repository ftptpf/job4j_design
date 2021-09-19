package ru.job4j.ood.lsp.products.storage;

import ru.job4j.ood.lsp.products.product.Food;

import java.util.ArrayList;
import java.util.List;

public class Trash implements Storage {

    List<Food> storageTrash = new ArrayList<>();

    @Override
    public void setProductList(Food food) {

    }

    @Override
    public List<Food> getProductList() {
        return storageTrash;
    }
}
