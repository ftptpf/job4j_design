package ru.job4j.ood.lsp.products;

import ru.job4j.ood.lsp.products.product.Food;
import ru.job4j.ood.lsp.products.storage.Storage;

import java.util.List;


public class ControlQuality {
    private List<Storage> listStorage;

    public ControlQuality(List<Storage> listStorage) {
        this.listStorage = listStorage;
    }

    public void moveToStorage(List<Food> listFood) {
        for (Food food : listFood) {
            for (Storage storage : listStorage) {
                storage.setFoodList(food);
            }
        }
    }

    public List<Food> getFoodListFromWarehouse() {
        return listStorage.get(0).getFoodList();
    }

    public List<Food> getFoodListFromShop() {
        return listStorage.get(1).getFoodList();
    }

    public List<Food> getFoodListFromTrash() {
        return listStorage.get(2).getFoodList();
    }
}
