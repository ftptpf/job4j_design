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
     * Читаем данные исходного файла, собираем их в лист.
     * Последовательно запуская вспомогательные методы, делаем выборку и выводим информацию.
     * @param argsName путь к исходному файлу, путь к файлу куда будем записывать информацию, фильтр по столбцам и разделитель
     * @throws Exception
     */
    public void handle(ArgsName argsName) throws Exception {
        validate(argsName);
        String path = argsName.get("path");
        String out = argsName.get("out");
        String delimiter = argsName.get("delimiter");
        String filter = argsName.get("filter");
        List<List<String>> listAllTable = new ArrayList<>();
        List<List<String>> resultList;

        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            while (scanner.hasNextLine()) {
                String string = scanner.nextLine();
                String[] arrayString = string.split(";");
                List<String> listString = new ArrayList<>(Arrays.asList(arrayString));
                listAllTable.add(listString);
            }
        }
        resultList = listFilter(listAllTable, filter);
        String resultString = convertFromListToString(resultList, delimiter);
        writeToFile(resultString, out);
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
     * Из общей таблицы значений делает выборку по определенным столбцам.
     * filterFirstStringList - лист слов-фильтров первой(титульной) строки
     * titleStringList - лист первой строка таблицы
     * indexTitleStringList - лист индексов слов-фильтров первой строки
     * stringList - лист всех значений одной строки
     * stringIndexList - лист значений строки у которых индексы совпали с индексами слов-фильтров первой строки
     * @param list вся таблица собранная в лист
     * @param filter название столбцов, разделенный запятой по которым будет происходить отбор
     * @return лист только с указанными в фильтре столбцами
     */
    private List<List<String>> listFilter(List<List<String>> list, String filter) {
        List<List<String>> result = new ArrayList<>();
        String[] arrayString = filter.split(",");
        List<String> filterFirstStringList = new ArrayList<>(Arrays.asList(arrayString));
        List<String> titleStringList = list.get(0);
        List<Integer> indexTitleStringList = new ArrayList<>();

        for (String str : filterFirstStringList) {
            for (int i = 0; i < titleStringList.size(); i++) {
                if (str.equals(titleStringList.get(i))) {
                    indexTitleStringList.add(i);
                }
            }
        }
        result.add(new ArrayList<>(filterFirstStringList));

        for (int i = 1; i < list.size(); i++) {
            List<String> stringList = list.get(i);
            List<String> stringIndexList = new ArrayList<>();
            for (int j = 0; j < stringList.size(); j++) {
                for (int index : indexTitleStringList) {
                    if (index == j) {
                        stringIndexList.add(stringList.get(j));
                    }
                }
            }
            result.add(stringIndexList);
        }
        return result;
    }

    /**
     * Выполняем вывод информации.
     * @param resultString строка которую нужно вывести
     * @param out если приходит - "stdout" - данные выводим на консоль,
     *            в ином случае в параметрах должен прийти путь к файлу в который записываем информацию.
     * @throws FileNotFoundException
     */
    private void writeToFile(String resultString, String out) throws FileNotFoundException {
        String console = "stdout";
        if (out.equals(console)) {
            PrintStream ps = new PrintStream(System.out);
            ps.print(resultString);
        } else {
            try (PrintStream ps = new PrintStream(new FileOutputStream(out))) {
                ps.print(resultString);
            }
        }
    }

    /**
     * Выполняем конвертацию из листа в строку.
     * @param resultList лист
     * @param delimiter разделитель
     * @return строка
     */
    private String convertFromListToString(List<List<String>> resultList, String delimiter) {
        StringBuilder sb = new StringBuilder();
        for (List<String> listString : resultList) {
            StringJoiner sj = new StringJoiner(delimiter);
            for (String str : listString) {
                sj.add(str);
            }
            sb.append(sj);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
