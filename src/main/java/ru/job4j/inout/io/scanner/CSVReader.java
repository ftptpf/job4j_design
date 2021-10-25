package ru.job4j.inout.io.scanner;

import ru.job4j.inout.io.namedarguments.ArgsName;

import java.io.*;
import java.util.*;

/**
 * Читаем данные из CSV файла и выводим их.
 * В качестве входных данных задается путь к файлу path,
 * разделитель delimiter, приемник данных out и фильтр по столбцам filter.
 * Ключ -out имеет только два допустимых значения stdout или путь к файлу, куда нужно вывести.
 * Например, если есть файл CSV со столбцами name, age, birthDate, education, children
 * и программа запускается таким образом:
 * java -jar target/csvReader.jar -path=file.csv -delimiter=";"  -out=stdout -filter=name,age
 * то программа должна прочитать файл по пути file.csv и вывести только столбцы name, age в консоль.
 * В качестве разделителя данных выступает ;
 */
public class CSVReader {
    /**
     * Читаем данные исходного файла.
     * Последовательно запуская вспомогательные методы, делаем выборку и построчно выводим информацию.
     * @param argsName путь к исходному файлу, путь к файлу куда будем записывать информацию, фильтр по столбцам и разделитель
     * @throws Exception
     */
    public void handle(ArgsName argsName) throws Exception {
        validate(argsName);
        String path = argsName.get("path");
        String out = argsName.get("out");
        String delimiter = argsName.get("delimiter");
        String delimiterFilter = ",";
        String filter = argsName.get("filter");
        String[] filterArray = filterArray(filter, delimiterFilter);
        List<Integer> index = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream(path));
             PrintStream psToFile = new PrintStream(new FileOutputStream(out, true))) {
            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                String[] array = string.split(delimiter);
                if (index.isEmpty()) {
                    index = findFilterIndex(array, filterArray);
                }
                String resultString = filter(array, index, delimiter);
                write(resultString, out, psToFile);
            }
        }
    }

    /**
     * Выполняем валидацию что в аргументах которые к нам поступают содержаться ключи
     * path, out, delimiter, filter
     * @param argsName путь к исходному файлу, путь к файлу куда будем записывать информацию, фильтр по столбцам и разделитель
     */
    public void validate(ArgsName argsName) {
        Optional<String> path = Optional.ofNullable(argsName.get("path"));
        Optional<String> out = Optional.ofNullable(argsName.get("out"));
        Optional<String> delimiter = Optional.ofNullable(argsName.get("delimiter"));
        Optional<String> filter = Optional.ofNullable(argsName.get("filter"));
        if (path.isEmpty() || out.isEmpty() || delimiter.isEmpty() || filter.isEmpty()) {
            throw new IllegalArgumentException("Check you arguments. Some or all of them is wrong.");
        }
    }

    /**
     * Разбиваем строку по разделителю на массив строк.
     * @param filter исходная строка
     * @param delimiter разделитель
     * @return массив строк
     */
    private String[] filterArray(String filter, String delimiter) {
        return filter.split(delimiter);
    }

    /**
     * Находим совпадения в двух массивах строк и возвращаем индексы первого массива по которым были совпадения.
     * @param array массив строк первого массива в котором будем искать совпадения
     * @param filterArray  второй массив строк для сравнения
     * @return лист индексов первого массива у которого значения совпали со значениями второго массива
     */
    private List<Integer> findFilterIndex(String[] array, String[] filterArray) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (String fl : filterArray) {
                if (fl.equals(array[i])) {
                    result.add(i);
                }
            }
        }
        return result;
    }

    /**
     * Из массива строк делаем выборку нужных значений по индексам и формируем, используя разделители, строку.
     * @param array массив строк
     * @param index массив индексов по которым выполняем отбор
     * @param delimiter разделитель строк
     * @return
     */
    private String filter(String[] array, List<Integer> index, String delimiter) {
        StringBuilder sb = new StringBuilder();
        StringJoiner sj = new StringJoiner(delimiter);
        for (int i : index) {
            sj.add(array[i]);
        }
        sb.append(sj);
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    /**
     * Выполняем вывод информации.
     * @param resultString строка которую нужно вывести
     * @param out если приходит - "stdout" - данные выводим на консоль,
     *            в ином случае в параметрах должен прийти путь к файлу в который записываем информацию.
     * @param psToFile поток записи в файл
     */
    private void write(String resultString, String out, PrintStream psToFile) {
        String console = "stdout";
        if (out.equals(console)) {
            System.out.print(resultString);
        } else {
            psToFile.print(resultString);
        }
    }
}
