package ru.job4j.inout.io.scanner;

import ru.job4j.inout.io.namedarguments.ArgsName;

/**
 * Запуск класса CSVReader с аргументами, которые мы получаем из командной строки.
 */
public class CSVReaderStart {

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        CSVReader csvReader = new CSVReader();
        csvReader.handle(argsName);
    }
}
