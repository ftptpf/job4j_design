package ru.job4j.ood.srp.reports;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.ood.srp.reports.report.*;
import ru.job4j.ood.srp.reports.store.MemStore;

import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EmployeeReportTest {

    private MemStore store;
    private Employee employee1, employee2, employee3;
    Report report;

    @Before
    public void initObjects() {
        store = new MemStore();
        employee1 = new Employee("Anna", Calendar.getInstance(), Calendar.getInstance(), 10000);
        employee2 = new Employee("Denis", Calendar.getInstance(), Calendar.getInstance(), 5000);
        employee3 = new Employee("Oleg", Calendar.getInstance(), Calendar.getInstance(), 7000);
        store.add(employee1);
        store.add(employee2);
        store.add(employee3);
    }

    @Test
    public void reportForHr() {
        report = new ReportHr(store);
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
        assertThat(report.generate(employee -> employee.getSalary() >= 5000), is(expect.toString()));
    }

    @Test
    public void reportForAccounting() {
        double euroToday = 100;
        String currencyName = "Euro";
        report = new ReportAccounting(store, euroToday, currencyName);
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
        assertThat(report.generate(employee -> employee.getSalary() >= 7000), is(expect.toString()));
    }

    @Test
    public void reportForHtml() {
        report = new ReportHtml(store);
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
        assertThat(report.generate(employee -> employee.getSalary() <= 7000), is(expect.toString()));
    }

    @Test
    public void reportForJson() {
        report = new ReportJson(store);
        StringBuilder expect = new StringBuilder()
                .append("[{\"name\":\"Denis\",\"hired\":")
                .append("{\"year\":")
                .append(employee2.getHired().get(Calendar.YEAR))
                .append(",\"month\":")
                .append(employee2.getHired().get(Calendar.MONTH))
                .append(",\"dayOfMonth\":")
                .append(employee2.getHired().get(Calendar.DATE))
                .append(",\"hourOfDay\":")
                .append(employee2.getHired().get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":")
                .append(employee2.getHired().get(Calendar.MINUTE))
                .append(",\"second\":")
                .append(employee2.getHired().get(Calendar.SECOND))
                .append("},\"fired\":")
                .append("{\"year\":")
                .append(employee2.getFired().get(Calendar.YEAR))
                .append(",\"month\":")
                .append(employee2.getFired().get(Calendar.MONTH))
                .append(",\"dayOfMonth\":")
                .append(employee2.getFired().get(Calendar.DATE))
                .append(",\"hourOfDay\":")
                .append(employee2.getFired().get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":")
                .append(employee2.getFired().get(Calendar.MINUTE))
                .append(",\"second\":")
                .append(employee2.getFired().get(Calendar.SECOND))
                .append("},\"salary\":5000.0},")
                .append("{\"name\":\"Oleg\",\"hired\":")
                .append("{\"year\":")
                .append(employee3.getHired().get(Calendar.YEAR))
                .append(",\"month\":")
                .append(employee3.getHired().get(Calendar.MONTH))
                .append(",\"dayOfMonth\":")
                .append(employee3.getHired().get(Calendar.DATE))
                .append(",\"hourOfDay\":")
                .append(employee3.getHired().get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":")
                .append(employee3.getHired().get(Calendar.MINUTE))
                .append(",\"second\":")
                .append(employee3.getHired().get(Calendar.SECOND))
                .append("},\"fired\":")
                .append("{\"year\":")
                .append(employee3.getFired().get(Calendar.YEAR))
                .append(",\"month\":")
                .append(employee3.getFired().get(Calendar.MONTH))
                .append(",\"dayOfMonth\":")
                .append(employee3.getFired().get(Calendar.DATE))
                .append(",\"hourOfDay\":")
                .append(employee3.getFired().get(Calendar.HOUR_OF_DAY))
                .append(",\"minute\":")
                .append(employee3.getFired().get(Calendar.MINUTE))
                .append(",\"second\":")
                .append(employee3.getFired().get(Calendar.SECOND))
                .append("},\"salary\":7000.0}]");
        assertThat(report.generate(employee -> employee.getSalary() <= 7000), is(expect.toString()));
    }

    @Test
    public void reportForXml() {
        report = new ReportXml(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employee>\n    ")
                .append("<name>")
                .append(employee2.getName())
                .append("</name>\n    ")
                .append("<salary>")
                .append(employee2.getSalary())
                .append("</salary>\n")
                .append("</employee>\n");
        assertEquals(expect.toString(), report.generate(employee -> employee.getSalary() == 5000));
    }
}
