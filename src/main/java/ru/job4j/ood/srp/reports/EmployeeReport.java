package ru.job4j.ood.srp.reports;

import java.util.function.Predicate;

public class EmployeeReport {
    Predicate<Employee> predicate;
    Report report;

    public void setReport(Report report) {
        this.report = report;
    }

    public void setPredicate(Predicate<Employee> predicate) {
        this.predicate = predicate;
    }

    public void makeReport() {
        report.generate(predicate);
    }
}
