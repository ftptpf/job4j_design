package ru.job4j.inout.serialization;

import java.io.*;
import java.nio.file.Files;

public class Contact implements Serializable {
    // serialVersionUID - уникальный идентификатор версии сериализованного класса,
    // необходим для обеспечения механизмов версионности, т.е. нужен JVM для понимания,
    // что сериализованный объект при десериализации имеет те же члены класса, методы и пр.
    // Если значения не совпадают, будет выброшено исключение java.io.InvalidClassException.
    // Для наглядности в примере мы задаем значение поля вручную,
    // в реальной разработке лучше использовать штатный механизм Java генерации serialVersionUID или разработать свой.
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone
                + '\'' + '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Contact contact = new Contact(123456, "+7 (111) 111-11-11");

        /* Запись объекта в файл */
        File tempFile = Files.createTempFile(null, null).toFile();
        try (FileOutputStream fos = new FileOutputStream(tempFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                oos.writeObject(contact); // writeObject - используется для сериализации объектов в поток
        }

        /* Чтение объекта из файла */
        try (FileInputStream fis = new FileInputStream(tempFile);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            final Contact contactFromFile = (Contact) ois.readObject(); // readObject - используется для для чтения из потока
            System.out.println(contactFromFile);
        }
    }
}
