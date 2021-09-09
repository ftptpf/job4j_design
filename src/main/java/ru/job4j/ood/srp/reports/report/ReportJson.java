package ru.job4j.ood.srp.reports.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.ood.srp.reports.Employee;
import ru.job4j.ood.srp.reports.store.MemStore;
import ru.job4j.ood.srp.reports.store.Store;

import java.util.function.Predicate;

public class ReportJson implements Report {
    private Store store;

    public ReportJson(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        //StringBuilder text = new StringBuilder();
        MemStore afterFilterStore = new MemStore();
        for (Employee employee : store.findBy(filter)) {
            afterFilterStore.add(employee);
        }
        Gson gsonEmployee = new GsonBuilder().create();
        String result = gsonEmployee.toJson(afterFilterStore);
        return result;
    }
}
