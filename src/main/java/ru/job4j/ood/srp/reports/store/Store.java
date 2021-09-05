package ru.job4j.ood.srp.reports.store;

import ru.job4j.ood.srp.reports.Employee;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    List<Employee> findBy(Predicate<Employee> filter);
}
