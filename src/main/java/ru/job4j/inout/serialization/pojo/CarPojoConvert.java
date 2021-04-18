package ru.job4j.inout.serialization.pojo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class CarPojoConvert {
    public static void main(String[] args) throws Exception {
        CarPojo car = new CarPojo("Toyota", 2008, true, new EnginePojo(220), "Sergey", "Dmitriy");
        // Получаем контекст для доступа к АПИ
        JAXBContext context = JAXBContext.newInstance(CarPojo.class);
        // Создаем сериализатор
        Marshaller marshaller = context.createMarshaller();
        // Указываем, что нам нужно форматирование
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            // Сериализуем
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        // Для десериализации нам нужно создать десериализатор
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader stringReader = new StringReader(xml)) {
            // десериализуем
            CarPojo carResult = (CarPojo) unmarshaller.unmarshal(stringReader);
            System.out.println(carResult);
        }
    }
}
