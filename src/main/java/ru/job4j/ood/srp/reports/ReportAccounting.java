package ru.job4j.ood.srp.reports;

import java.util.function.Predicate;

/**
 * Отчет для бухгалтерии с измененным видом зарплаты.
 */
public class ReportAccounting implements Report {
    private Store store;

    public ReportAccounting(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return null;
    }
}
