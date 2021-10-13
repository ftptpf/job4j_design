package ru.job4j.inout.serialization.pojo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class CarPojoConvert {
    /**
     * Получаем контекст для доступа к АПИ. Создаем сериализатор. Указываем, что нам нужно форматирование.
     * Сериализуем. Для десериализации нам нужно создать десериализатор. Десериализуем.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        CarPojo car = new CarPojo("Toyota", 2008, true, new EnginePojo(220), "Sergey", "Dmitriy");
        JAXBContext context = JAXBContext.newInstance(CarPojo.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(car, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader stringReader = new StringReader(xml)) {
            CarPojo carResult = (CarPojo) unmarshaller.unmarshal(stringReader);
            System.out.println(carResult);
        }
    }
}
