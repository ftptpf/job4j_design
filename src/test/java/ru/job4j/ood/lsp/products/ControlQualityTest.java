package ru.job4j.ood.lsp.products;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.lsp.products.product.Food;
import ru.job4j.ood.lsp.products.product.Meat;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class ControlQualityTest {
    private LocalDate today;
    private LocalDate meat1ExpiryDate, meat1CreateDate;
    private LocalDate meat2ExpiryDate, meat2CreateDate;
    private LocalDate meat3ExpiryDate, meat3CreateDate;
    private LocalDate meat4ExpiryDate, meat4CreateDate;

    private Food meatToWarehouse, meatToShop, meatToShopDiscount, meatToTrash;
    private ControlQuality controlQuality;

    @Before
    public void initObjects() {
        today = LocalDate.now();
        meat1CreateDate = today.minusDays(1);
        meat1ExpiryDate = today.plusDays(10);
        meatToWarehouse = new Meat("red", meat1CreateDate, meat1ExpiryDate, new BigDecimal(50), 0);
        meat2CreateDate = today.minusDays(10);
        meat2ExpiryDate = today.plusDays(10);
        meatToShop = new Meat("red", meat2CreateDate, meat2ExpiryDate, new BigDecimal(50), 0);
        meat3CreateDate = today.minusDays(4);
        meat3ExpiryDate = today.plusDays(10);
        meatToShopDiscount = new Meat("red", meat3CreateDate, meat3ExpiryDate, new BigDecimal(50), 0);
        meat4CreateDate = today.minusDays(10);
        meat4ExpiryDate = today.minusDays(1);
        meatToTrash = new Meat("red", meat4CreateDate, meat4ExpiryDate, new BigDecimal(50), 0);
        controlQuality = new ControlQuality();
    }


    @Test
    public void moveToWarehouse() {
        controlQuality.moveToStorage(meatToWarehouse);
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
        assertTrue(controlQuality.getFoodListFromShop().isEmpty());
        assertTrue(controlQuality.getFoodListFromTrash().isEmpty());
    }

    @Test
    public void moveToShop() {
        controlQuality.moveToStorage(meatToShop);
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
                .append("}]");
        assertEquals(expected.toString(), controlQuality.getFoodListFromShop().toString());
        assertTrue(controlQuality.getFoodListFromWarehouse().isEmpty());
        assertTrue(controlQuality.getFoodListFromTrash().isEmpty());
    }

    @Test
    public void moveToShopDiscount() {
        controlQuality.moveToStorage(meatToShopDiscount);
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
                .append("}]");
        assertEquals(expected.toString(), controlQuality.getFoodListFromShop().toString());
        assertTrue(controlQuality.getFoodListFromWarehouse().isEmpty());
        assertTrue(controlQuality.getFoodListFromTrash().isEmpty());
    }

    @Test
    public void moveToTrash() {
        controlQuality.moveToStorage(meatToTrash);
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
        assertTrue(controlQuality.getFoodListFromWarehouse().isEmpty());
        assertTrue(controlQuality.getFoodListFromShop().isEmpty());
    }
}