package ru.job4j.ood.srp.reports.report;

import com.google.gson.Gson;
import ru.job4j.ood.srp.reports.Employee;
import ru.job4j.ood.srp.reports.store.Store;

import java.util.function.Predicate;

public class ReportJson implements Report {
    private Store store;

    public ReportJson(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        Gson gson = new Gson();
        return gson.toJson(store.findBy(filter));
    }
}
