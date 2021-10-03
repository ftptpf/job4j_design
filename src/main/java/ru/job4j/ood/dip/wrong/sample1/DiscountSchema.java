package ru.job4j.ood.dip.wrong.sample1;

public class DiscountSchema {

    public double discountCalculation(Product product) {
        double cost = product.getCost();
        if (cost > 100.00) {
            return cost / 2;
        } else {
            return cost / 1.2;
        }
    }
}
