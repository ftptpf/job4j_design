package ru.job4j.ood.ocp.wrong;

public class EuroConverter {
    private static final double EURO = 90;
    private double ruble;

    public EuroConverter(double ruble) {
        this.ruble = ruble;
    }

    public double converter() {
        return ruble / EURO;
   }
}
