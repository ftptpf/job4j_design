package ru.job4j.inout.control;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * Программа поиска файлов в заданном каталоге и подкаталогах.
 * Имя файла может задаваться целиком, по маске, по регулярному выражению(не обязательно).
 * Программа собирается в jar и запускаться через java -jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt
 * Ключи:
 * -d - директория, в которой начинать поиск.
 * -n - имя файла, маска, либо регулярное выражение.
 * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
 * -o - результат записать в файл.
 * Программа записывает результат в файл. В программе реализована валидация ключей и подсказки.
 * Для работы с входными аргументами (-d=C:\projects\job4j_design\resources -n=*.txt -t=mask -o=.\resources\logSearchFiles.txt)
 * которые прописываем в Run Configurations в Intellije IDEA, используем класс ParamNames.
 */
public class SearchFilesByCriterion {
    /**
     * Поиск файлов по заданным условиям.
     * @param rootPath директрия начала поиска
     * @param predicate условия поиска
     * @return лист с найденными файлами
     * @throws IOException
     */
    public List<Path> findFiles(Path rootPath, Predicate<Path> predicate) throws IOException {
        if (!rootPath.toFile().exists()) {
            throw new IllegalArgumentException("The wrong root directory path.");
        }
        FileFind ff = new FileFind(predicate);
        Files.walkFileTree(rootPath, ff);
        return ff.getPaths();
    }

    /**
     * Сохранение листа найденных файлов в файл
     * @param sourcesList лист с найденными файлами
     * @param target файл куда сохраняем информацию о найденных файлах
     */
    public void saveFiles(List<Path> sourcesList, Path target) {
        if (sourcesList.size() == 0) {
            throw new IllegalArgumentException("Empty source list. Add data to it.");
        }
        File file = target.toFile();
        try (PrintWriter out = new PrintWriter(file)) {
            for (Path list : sourcesList) {
                out.println(list);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Predicate<Path> predicateChoice(String[] args) {
        ParamNames pr = ParamNames.of(args);
        Predicate<Path> predicate;
        if (pr.get("t").equals(""))
        if (pr.get("t").equals("mask")) {
            predicate = p -> p.toFile().getName().matches();
        }
    }


    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Your parameters is empty. Add it.");
        }
        ParamNames pr = ParamNames.of(args); // создаем map (ключ-значение) из входных параметров
        Predicate<Path> predicate = p -> p.toFile().getName().matches(pr.get("e")); // условие поиска файлов по регулярному выражению
        Path rootPath = Paths.get(pr.get("d")); // директория с которой будем начинать поиск
        Path targetPath = Paths.get(pr.get("o")); // файл в который будут записаны результаты поиска
        Predicate<Path> predicateType = p -> p.toFile().getName().equals(pr.get("t")); // условие определиния типа поиска
        SearchFilesByCriterion sfc = new SearchFilesByCriterion();
        List<Path> sourcesList = sfc.findFiles(rootPath, predicate);
        sfc.saveFiles(sourcesList, targetPath);
    }
}
