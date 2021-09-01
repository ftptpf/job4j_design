package ru.job4j.ood.srp.reports;

import java.util.function.Predicate;

/**
 * Отчет для бухгалтерии с измененным видом зарплаты. Зарплата в евро. 1 евро = 90,05 руб.
 */
public class ReportAccounting implements Report {
    private double euro;
    private Store store;

    public ReportAccounting(Store store, double euro) {
        this.store = store;
        this.euro = euro;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary in Euro;");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired()).append(";")
                    .append(employee.getFired()).append(";")
                    .append((employee.getSalary() / euro)).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
