package ru.job4j.ood.srp.reports.report;

import ru.job4j.ood.srp.reports.Employee;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.function.Predicate;

/**
 * Интерфейс генерации отчетов.
 */
public interface Report {
    String generate(Predicate<Employee> filter) throws JAXBException, IOException;
}
