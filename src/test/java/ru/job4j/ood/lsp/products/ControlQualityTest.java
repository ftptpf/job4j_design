package ru.job4j.ood.lsp.products;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.lsp.products.product.Food;
import ru.job4j.ood.lsp.products.product.Meat;
import ru.job4j.ood.lsp.products.storage.Shop;
import ru.job4j.ood.lsp.products.storage.Storage;
import ru.job4j.ood.lsp.products.storage.Trash;
import ru.job4j.ood.lsp.products.storage.Warehouse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ControlQualityTest {
    private LocalDate today;
    private LocalDate meat1ExpiryDate, meat1CreateDate;
    private LocalDate meat2ExpiryDate, meat2CreateDate;
    private LocalDate meat3ExpiryDate, meat3CreateDate;
    private LocalDate meat4ExpiryDate, meat4CreateDate;

    private Food meatToWarehouse, meatToShop, meatToShopDiscount, meatToTrash;
    private Storage storageWarehouse, storageShop, storageTrash;
    private List<Storage> listStorage;
    private ControlQuality controlQuality;
    private List<Food> listFood;

    @Before
    public void initObjects() {
        today = LocalDate.now();
        meat1CreateDate = today.minusDays(1);
        meat1ExpiryDate = today.plusDays(10);
        meatToWarehouse = new Meat("very fresh meat - 9 %", meat1CreateDate, meat1ExpiryDate, new BigDecimal(50), 0);
        meat2CreateDate = today.minusDays(7);
        meat2ExpiryDate = today.plusDays(10);
        meatToShop = new Meat("good meat - 41 %", meat2CreateDate, meat2ExpiryDate, new BigDecimal(50), 0);
        meat3CreateDate = today.minusDays(5);
        meat3ExpiryDate = today.plusDays(1);
        meatToShopDiscount = new Meat("meat for quick sale - 83 %", meat3CreateDate, meat3ExpiryDate, new BigDecimal(50), 0);
        meat4CreateDate = today.minusDays(10);
        meat4ExpiryDate = today.minusDays(1);
        meatToTrash = new Meat("bad meat - expiration date has expired", meat4CreateDate, meat4ExpiryDate, new BigDecimal(50), 0);
        storageWarehouse = new Warehouse();
        storageShop = new Shop();
        storageTrash = new Trash();
        listStorage = new ArrayList<>();
        listStorage.add(storageWarehouse);
        listStorage.add(storageShop);
        listStorage.add(storageTrash);
        listFood = new ArrayList<>();
        listFood.add(meatToWarehouse);
        listFood.add(meatToShop);
        listFood.add(meatToShopDiscount);
        listFood.add(meatToTrash);
        controlQuality = new ControlQuality(listStorage);
        controlQuality.moveToStorage(listFood);
    }

    @Test
    public void moveToWarehouse() {
        StringBuilder expected = new StringBuilder()
                .append("[Food{name='")
                .append(controlQuality.getFoodListFromWarehouse().get(0).getName())
                .append("', expiryDate=")
                .append(controlQuality.getFoodListFromWarehouse().get(0).getExpiryDate())
                .append(", createDate=")
                .append(controlQuality.getFoodListFromWarehouse().get(0).getCreateDate())
                .append(", price=")
                .append(controlQuality.getFoodListFromWarehouse().get(0).getPrice())
                .append(", discount=")
                .append(controlQuality.getFoodListFromWarehouse().get(0).getDiscount())
                .append("}]");
        assertEquals(expected.toString(), controlQuality.getFoodListFromWarehouse().toString());
    }

    @Test
    public void moveToShop() {
        StringBuilder expected = new StringBuilder()
                .append("[Food{name='")
                .append(controlQuality.getFoodListFromShop().get(0).getName())
                .append("', expiryDate=")
                .append(controlQuality.getFoodListFromShop().get(0).getExpiryDate())
                .append(", createDate=")
                .append(controlQuality.getFoodListFromShop().get(0).getCreateDate())
                .append(", price=")
                .append(controlQuality.getFoodListFromShop().get(0).getPrice())
                .append(", discount=")
                .append(controlQuality.getFoodListFromShop().get(0).getDiscount())
                .append("}, ")
                .append("Food{name='")
                .append(controlQuality.getFoodListFromShop().get(1).getName())
                .append("', expiryDate=")
                .append(controlQuality.getFoodListFromShop().get(1).getExpiryDate())
                .append(", createDate=")
                .append(controlQuality.getFoodListFromShop().get(1).getCreateDate())
                .append(", price=")
                .append(controlQuality.getFoodListFromShop().get(1).getPrice())
                .append(", discount=")
                .append(controlQuality.getFoodListFromShop().get(1).getDiscount()) // discount=20.0
                .append("}]");
        assertEquals(expected.toString(), controlQuality.getFoodListFromShop().toString());
    }

    @Test
    public void moveToTrash() {
        StringBuilder expected = new StringBuilder()
                .append("[Food{name='")
                .append(controlQuality.getFoodListFromTrash().get(0).getName())
                .append("', expiryDate=")
                .append(controlQuality.getFoodListFromTrash().get(0).getExpiryDate())
                .append(", createDate=")
                .append(controlQuality.getFoodListFromTrash().get(0).getCreateDate())
                .append(", price=")
                .append(controlQuality.getFoodListFromTrash().get(0).getPrice())
                .append(", discount=")
                .append(controlQuality.getFoodListFromTrash().get(0).getDiscount())
                .append("}]");
        assertEquals(expected.toString(), controlQuality.getFoodListFromTrash().toString());
    }

    @Test
    public void resortAllProducts() {
        listFood = controlQuality.collectAllProducts();
        controlQuality.resort(listFood);
        moveToShop();
        moveToWarehouse();
        moveToTrash();
    }
}
