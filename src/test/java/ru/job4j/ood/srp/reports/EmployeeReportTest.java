package ru.job4j.ood.srp.reports;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EmployeeReportTest {

    @Test
    @Ignore
    public void reportForHr() {
        MemStore store = new MemStore();
        Employee employee1 = new Employee("Anna", Calendar.getInstance(), Calendar.getInstance(), 10000);
        Employee employee2 = new Employee("Denis", Calendar.getInstance(), Calendar.getInstance(), 5000);
        Employee employee3 = new Employee("Oleg", Calendar.getInstance(), Calendar.getInstance(), 7000);
        store.add(employee1);
        store.add(employee2);
        store.add(employee3);
        EmployeeReport employeeReport = new EmployeeReport();
        employeeReport.setReport(new ReportHr(store));
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(employee1.getName()).append(";")
                .append(employee1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(employee3.getName()).append(";")
                .append(employee3.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(employee2.getName()).append(";")
                .append(employee2.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(expect.toString(), is(employeeReport.makeReport(employee -> employee.getSalary() >= 10000)));
    }
}