package ru.job4j.ood.lsp.products.product;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Meat extends Food {
    public Meat(String name, LocalDate createDate, LocalDate expiryDate, BigDecimal price, double discount) {
        super(name, createDate, expiryDate, price, discount);
    }
}
