package ru.job4j.ood.srp.reports;

import java.util.function.Predicate;

/**
 * Отчет для программистов в формате html.
 */
public class ReportHtml implements Report {
    private Store store;

    public ReportHtml(Store store) {
        this.store = store;
    }
    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<table>");
        text.append("<tr><th>Name</th><th>Hired</th><th>Fired</th><th>Salary</th></tr>");
        for (Employee employee : store.findBy(filter)) {
            text.append("<tr><td>")
                    .append(employee.getName())
                    .append("</td><td>")
                    .append(employee.getHired())
                    .append("</td><td>")
                    .append(employee.getFired())
                    .append("</td><td>")
                    .append(employee.getSalary())
                    .append("</td></tr>");
        }
        text.append("</table>");
        return text.toString();
    }
}
