package ru.job4j.ood.dip.wrong.sample1;

/**
 * Нарушение принципа DIP. Класс Shop зависит от объекта реализующего класс расчета скидки DiscountSchema.
 * Решением может быть выделение интерфейса чтобы зависимость была не от объекта, а от интерфейса расчета скидки.
 */
public class Shop {
    private static DiscountSchema discountSchema = new DiscountSchema();

    public static void main(String[] args) {
        Product product = new Product("Product 1", 98.05);
        double cost = discountSchema.discountCalculation(product);
        product.setCost(cost);
    }
}
