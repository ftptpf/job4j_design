package ru.job4j.ood.lsp.products.product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Food {
    private String name;
    private LocalDate createDate;
    private LocalDate expiryDate;
    private BigDecimal price;
    private double discount;

    public Food(String name, LocalDate createDate, LocalDate expiryDate, BigDecimal price, double discount) {
        this.name = name;
        this.createDate = createDate;
        this.expiryDate = expiryDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return name.equals(food.name) && expiryDate.equals(food.expiryDate) && createDate.equals(food.createDate) && price.equals(food.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expiryDate, createDate, price);
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", expiryDate=" + expiryDate
                + ", createDate=" + createDate
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
