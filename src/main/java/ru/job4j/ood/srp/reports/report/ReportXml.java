package ru.job4j.ood.srp.reports.report;

import ru.job4j.ood.srp.reports.Employee;
import ru.job4j.ood.srp.reports.store.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.function.Predicate;

public class ReportXml  implements Report {
    private Store store;

    public ReportXml(Store store) {
        this.store = store;
    }

    /**
     * Получаем контекст для доступа к API. Создаем сериализатор.
     * Указываем, что нам нужно форматирование. Сериализуем.
     * @param filter
     * @return
     */
    @Override
    public String generate(Predicate<Employee> filter) {
        try {
            JAXBContext context = JAXBContext.newInstance(Employee.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            String xml = "";
            try (StringWriter writer = new StringWriter()) {
                for (Employee employee : store.findBy(filter)) {
                    marshaller.marshal(employee, writer);
                    xml = writer.getBuffer().toString();
                }
            }
            return xml;
        } catch (IOException | JAXBException e) {
            throw new RuntimeException("The problem in serialization XML report", e);
            }
    }
}
