package ru.job4j.ood.srp.reports;

import java.util.function.Predicate;

public class EmployeeReport {
    Report report;

    public void setReport(Report report) {
        this.report = report;
    }

    public String makeReport(Predicate<Employee> filter) {
        return report.generate(filter);
    }
}
