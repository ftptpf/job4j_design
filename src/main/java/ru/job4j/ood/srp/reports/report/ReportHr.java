package ru.job4j.ood.srp.reports.report;

import ru.job4j.ood.srp.reports.Employee;
import ru.job4j.ood.srp.reports.store.Store;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
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
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        Comparator<Employee> salaryComparator = (o2, o1) -> Double.compare(o1.getSalary(), o2.getSalary());
        Set<Employee> employeeSet = new TreeSet<>(salaryComparator);
        employeeSet.addAll(store.findBy(filter));
        for (Employee employee : employeeSet) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
