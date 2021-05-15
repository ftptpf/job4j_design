package ru.job4j.inout.control;


import java.nio.file.Path;
import java.nio.file.Paths;
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
    public void findFiles() {

    }

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Your parameters is empty. Add it.");
        }
        ParamNames pr = ParamNames.of(args); // создаем map (ключ-значение) из входных параметров
        Predicate<Path> predicate = p -> p.toFile().getName().endsWith(pr.get("n")); // условие поиска файлов по имени
        Path rootPath = Paths.get(pr.get("d")); // директория с которой будем начинать поиск
        Path targetPath = Paths.get(pr.get("o")); // файл в который будут записаны результаты поиска
        Predicate<Path> predicateType = p -> p.toFile().getName().equals(pr.get("t")); // условие определиния типа поиска
    }


}


