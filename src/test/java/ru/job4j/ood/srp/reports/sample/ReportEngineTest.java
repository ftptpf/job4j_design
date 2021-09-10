package ru.job4j.ood.srp.reports.sample;

import org.junit.Test;
import ru.job4j.ood.srp.reports.Employee;
import ru.job4j.ood.srp.reports.report.Report;
import ru.job4j.ood.srp.reports.store.MemStore;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        LocalDate now = LocalDate.now();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}