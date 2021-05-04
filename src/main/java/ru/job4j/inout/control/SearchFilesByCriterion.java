package ru.job4j.inout.control;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Программа поиска файлов в заданном каталоге и подкаталогах.
 * Имя файла может задаваться целиком, по маске, по регулярному выражению(не обязательно).
 * Программа должна собираться в jar и запускаться через java -jar find.jar -d=c:/ -n=*.txt -t=mask -o=log.txt
 * Ключи:
 * -d - директория, в которой начинать поиск.
 * -n - имя файла, маска, либо регулярное выражение.
 * -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению.
 * -o - результат записать в файл.
 * Программа должна записывать результат в файл.
 * В программе должна быть валидация ключей и подсказка.
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
        ParamNames pr = ParamNames.of(args);
    }

/*
    public static void main(String[] args) {
        //Files.walkFileTree(Path.of("./"), )

    }*/

}
// ArgsName - Принимаем массив параметров и разбиваем их на пары: ключ, значение.

