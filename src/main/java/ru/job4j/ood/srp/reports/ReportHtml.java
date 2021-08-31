package ru.job4j.ood.srp.reports;

import java.util.function.Predicate;

/**
 * Отчет для программистов в формате html.
 */
public class ReportHtml implements Report {
    private Store store;

    public ReportHtml(Store store) {
        this.store = store;
    }
    @Override
    public String generate(Predicate<Employee> filter) {
        return null;
    }
}
