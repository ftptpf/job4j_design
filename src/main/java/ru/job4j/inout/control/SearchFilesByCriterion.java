package ru.job4j.inout.control;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
     * @param n имя файла, маска, либо регулярное выражение
     * @param t mask - искать по маске, name - по полному совпадение имени, regex - по регулярному выражению.
     * @return лист с найденными файлами
     * @throws IOException
     */
    public List<Path> findFiles(Path rootPath, String n, String t) throws IOException {
        if (!rootPath.toFile().exists()) {
            throw new IllegalArgumentException("The wrong root directory path.");
        }
        if (n.length() < 1) {
            throw new IllegalArgumentException("The wrong parameter in \"-n\"");
        }
        if (t.length() < 4) {
            throw new IllegalArgumentException("The wrong length of \"-t\" parameter");
        }
        FileFind ff = new FileFind(n, t);
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
            throw new IllegalArgumentException("Empty source list. Could be data in it.");
        }
        if (target.toString().isEmpty()) {
            throw new IllegalArgumentException("The wrong file name where we save information.");
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

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Your parameters is empty. Check and add it.");
        }
        ParamNames pr = ParamNames.of(args); // создаем map (ключ-значение) из входных параметров

        Path rootPath = Paths.get(pr.get("d")); // директория с которой будем начинать поиск
        Path targetPath = Paths.get(pr.get("o")); // файл в который будут записаны результаты поиска

        String n = pr.get("n");
        String t = pr.get("t");

        SearchFilesByCriterion sfc = new SearchFilesByCriterion();
        List<Path> sourcesList = sfc.findFiles(rootPath, n, t);
        sfc.saveFiles(sourcesList, targetPath);
    }
}
