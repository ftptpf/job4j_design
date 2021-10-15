package ru.job4j.inout.io.scanner;

import ru.job4j.inout.io.namedarguments.ArgsName;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * Читаем данные из CSV файла и выводим их.
 * В качестве входных данных задается путь к файлу path,
 * разделитель delimiter,
 * приемник данных out и фильтр по столбцам filter.
 * Ключ -out имеет только два допустимых значения stdout или путь к файлу, куда нужно вывести.
 * Например, если есть файл CSV
 * со столбцами name, age, birthDate, education, children и программа запускается таким образом:
 * java -jar target/csvReader.jar -path=file.csv -delimiter=";"  -out=stdout -filter=name,age
 * то программа должна прочитать файл по пути file.csv и вывести только столбцы name, age в консоль.
 * В качестве разделителя данных выступает ;
 */
public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String out = argsName.get("out");
        String delimiter = argsName.get("delimiter");
        String filter = argsName.get("filter");

        Scanner scanner = new Scanner(new FileInputStream(path)).useDelimiter(delimiter);
        List<String> firstString = new ArrayList<>();
        while (scanner.hasNext()) {
            firstString.add(scanner.next());
        }


        StringJoiner sj = new StringJoiner(delimiter);

    }
}
