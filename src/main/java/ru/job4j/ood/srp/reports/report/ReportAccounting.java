package ru.job4j.ood.srp.reports.report;

import ru.job4j.ood.srp.reports.Employee;
import ru.job4j.ood.srp.reports.store.Store;

import java.util.function.Predicate;

/**
 * Отчет для бухгалтерии с измененным видом зарплаты. Зарплата в евро. 1 евро = 90,05 руб.
 */
public class ReportAccounting implements Report {
    private double currency;
    private Store store;
    private String currencyName;

    public ReportAccounting(Store store, double currency, String currencyName) {
        this.store = store;
        this.currency = currency;
        this.currencyName = currencyName;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary in " + currencyName + ";");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append((employee.getSalary() / currency)).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
