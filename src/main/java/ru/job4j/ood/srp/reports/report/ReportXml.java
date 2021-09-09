package ru.job4j.ood.srp.reports.report;

import ru.job4j.ood.srp.reports.Employee;
import ru.job4j.ood.srp.reports.store.Store;

import java.util.function.Predicate;

public class ReportXml  implements Report {
    private Store store;

    @Override
    public String generate(Predicate<Employee> filter) {

        return null;
    }
}
