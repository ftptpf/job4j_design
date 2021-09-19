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
        storageWarehouse.setProductList(food);
        storageShop.setProductList(food);
        storageTrash.setProductList(food);
    }

    public List<Food> getFromWarehouse() {
        return storageWarehouse.getProductList();
    }

    public List<Food> getFromShop() {
        return storageShop.getProductList();
    }

    public List<Food> getFromTrash() {
        return storageTrash.getProductList();
    }





/*    private float getPercentage(Food food) {
        Period periodProductValidity = Period.between(food.getCreateDate(), food.getExpiryDate());
        int daysFromCreateToExpiry = periodProductValidity.getDays();
        Period periodFromCreateTillNow = Period.between(food.getCreateDate(), LocalDate.now());
        int daysFromCreateTillNow = periodFromCreateTillNow.getDays();
        return (float) (daysFromCreateTillNow * 100 / daysFromCreateToExpiry);

    }*/
}
