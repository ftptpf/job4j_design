package ru.job4j.ood.srp.reports;

import java.util.function.Predicate;

/**
 * Отчет для HR c выводом сотрудников в порядке убывания зарплаты и без полей даты найма и увольнения.
 */
public class ReportHr implements Report {
    private Store store;

    public ReportHr(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return null;
    }
}
