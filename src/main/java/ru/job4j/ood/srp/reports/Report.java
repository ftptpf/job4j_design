package ru.job4j.ood.srp.reports;

import java.util.function.Predicate;

/**
 * Интерфейс генерации отчетов.
 */
public interface Report {
    String generate(Predicate<Employee> filter);
}
