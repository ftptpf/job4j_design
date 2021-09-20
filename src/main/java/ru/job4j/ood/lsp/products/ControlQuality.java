package ru.job4j.ood.lsp.products;

import ru.job4j.ood.lsp.products.product.Food;
import ru.job4j.ood.lsp.products.storage.Shop;
import ru.job4j.ood.lsp.products.storage.Storage;
import ru.job4j.ood.lsp.products.storage.Trash;
import ru.job4j.ood.lsp.products.storage.Warehouse;

import java.util.List;


public class ControlQuality {
    private Storage storageWarehouse = new Warehouse();
    private Storage storageShop = new Shop();
    private Storage storageTrash = new Trash();


    public void moveToStorage(Food food) {
        storageWarehouse.setFoodList(food);
        storageShop.setFoodList(food);
        storageTrash.setFoodList(food);
    }

    public List<Food> getFoodListFromWarehouse() {
        return storageWarehouse.getFoodList();
    }

    public List<Food> getFoodListFromShop() {
        return storageShop.getFoodList();
    }

    public List<Food> getFoodListFromTrash() {
        return storageTrash.getFoodList();
    }
}
