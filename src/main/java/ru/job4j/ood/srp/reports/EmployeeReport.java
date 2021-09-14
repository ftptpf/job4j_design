package ru.job4j.ood.srp.reports;

import ru.job4j.ood.srp.reports.report.Report;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.function.Predicate;

public class EmployeeReport {
    Report report;

    public void setReport(Report report) {
        this.report = report;
    }

    public String makeReport(Predicate<Employee> filter) throws JAXBException, IOException {
        return report.generate(filter);
    }
}
