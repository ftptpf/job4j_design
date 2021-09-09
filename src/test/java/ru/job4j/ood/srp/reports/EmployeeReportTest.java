package ru.job4j.ood.srp.reports;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.srp.reports.format.FormatTxt;
import ru.job4j.ood.srp.reports.format.OutputFormat;
import ru.job4j.ood.srp.reports.report.*;
import ru.job4j.ood.srp.reports.store.MemStore;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EmployeeReportTest {

    private MemStore store;
    private Employee employee1, employee2, employee3;
    EmployeeReport employeeReport;

    @Before
    public void initObjects() {
        store = new MemStore();
        employee1 = new Employee("Anna", Calendar.getInstance(), Calendar.getInstance(), 10000);
        employee2 = new Employee("Denis", Calendar.getInstance(), Calendar.getInstance(), 5000);
        employee3 = new Employee("Oleg", Calendar.getInstance(), Calendar.getInstance(), 7000);
        store.add(employee1);
        store.add(employee2);
        store.add(employee3);
        employeeReport = new EmployeeReport();
    }

    @Test
    public void reportForHr() {
        OutputFormat formatTxt = new FormatTxt();
        Report report = new ReportHr(store, formatTxt);
        employeeReport.setReport(report);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(employee1.getName()).append(";")
                .append(employee1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(employee3.getName()).append(";")
                .append(employee3.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(employee2.getName()).append(";")
                .append(employee2.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(expect.toString(), is(employeeReport.makeReport(employee -> employee.getSalary() >= 5000)));
    }

    @Test
    public void reportForAccounting() {
        double euroToday = 100;
        String currencyName = "Euro";
        Report report = new ReportAccounting(store, euroToday, currencyName);
        employeeReport.setReport(report);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary in Euro;")
                .append(employee1.getName()).append(";")
                .append(employee1.getHired()).append(";")
                .append(employee1.getFired()).append(';')
                .append(employee1.getSalary() / euroToday).append(";")
                .append(System.lineSeparator())
                .append(employee3.getName()).append(";")
                .append(employee3.getHired()).append(";")
                .append(employee3.getFired()).append(';')
                .append(employee3.getSalary() / euroToday).append(";")
                .append(System.lineSeparator());
        assertThat(expect.toString(), is(employeeReport.makeReport(employee -> employee.getSalary() >= 7000)));
    }

    @Test
    public void reportForHtml() {
        employeeReport.setReport(new ReportHtml(store));
        StringBuilder expect = new StringBuilder()
                .append("<table><tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr>")
                .append("<tr><td>Denis</td><td>")
                .append(employee2.getHired())
                .append("</td><td>")
                .append(employee2.getFired())
                .append("</td><td>5000.0</td></tr>")
                .append("<tr><td>Oleg</td><td>")
                .append(employee3.getHired())
                .append("</td><td>")
                .append(employee3.getFired())
                .append("</td><td>7000.0</td></tr>")
                .append("</table>");
        assertThat(expect.toString(), is(employeeReport.makeReport(employee -> employee.getSalary() <= 7000)));
    }

    @Test
    public void reportForJson() {
        employeeReport.setReport(new ReportJson(store));
        StringBuilder expect = new StringBuilder()
                .append("{\"employees\":")
                .append("[{\"name\":\"Denis\",")
                .append(employee2.getHired())
                .append("</td><td>")
                .append(employee2.getFired())
                .append("</td><td>5000.0</td></tr>")
                .append("<tr><td>Oleg</td><td>")
                .append(employee3.getHired())
                .append("</td><td>")
                .append(employee3.getFired())
                .append("</td><td>7000.0</td></tr>")
                .append("}");
        assertThat(expect.toString(), is(employeeReport.makeReport(employee -> employee.getSalary() <= 7000)));
    }
}
