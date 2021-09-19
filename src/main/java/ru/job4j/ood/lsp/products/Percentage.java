package ru.job4j.ood.lsp.products;

import ru.job4j.ood.lsp.products.product.Food;

import java.time.LocalDate;
import java.time.Period;

public class Percentage {

    public static float getPercentage(Food food) {
        Period periodProductValidity = Period.between(food.getCreateDate(), food.getExpiryDate());
        int daysFromCreateToExpiry = periodProductValidity.getDays();
        Period periodFromCreateTillNow = Period.between(food.getCreateDate(), LocalDate.now());
        int daysFromCreateTillNow = periodFromCreateTillNow.getDays();
        return (float) (daysFromCreateTillNow * 100 / daysFromCreateToExpiry);
    }
}
