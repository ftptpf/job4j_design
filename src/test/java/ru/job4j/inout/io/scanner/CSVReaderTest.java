package ru.job4j.inout.io.scanner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.inout.io.namedarguments.ArgsName;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;

import static org.junit.Assert.*;

public class CSVReaderTest {

    String data;

    {
        data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
    }

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void whenFilterTwoFirstColumns() throws Exception {
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;age",
                "Tom;20",
                "Jack;25",
                "William;30"
        ).concat(System.lineSeparator());
        CSVReader csvReader = new CSVReader();
        csvReader.handle(argsName);
        assertEquals(expected, Files.readString(target.toPath()));
    }

    @Test
    public void whenFilterTwoLastColumns() throws Exception {
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=last_name,education"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "last_name;education",
                "Smith;Bachelor",
                "Johnson;Undergraduate",
                "Brown;Secondary special"
        ).concat(System.lineSeparator());
        CSVReader csvReader = new CSVReader();
        csvReader.handle(argsName);
        assertEquals(expected, Files.readString(target.toPath()));
    }

    @Test
    public void whenFilterTwoLastColumnsToConsole() throws Exception {
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=stdout", "-filter=last_name,education"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "last_name;education",
                "Smith;Bachelor",
                "Johnson;Undergraduate",
                "Brown;Secondary special"
        ).concat(System.lineSeparator());
        try (PrintStream ps = new PrintStream(new FileOutputStream(target, true))) {
            System.setOut(ps);
            CSVReader csvReader = new CSVReader();
            csvReader.handle(argsName);
        }
        assertEquals(expected, Files.readString(target.toPath()));
        System.setOut(System.out);
    }

    @Test (expected =  IllegalArgumentException.class)
    public void whenFilterTwoFirstColumnsWrongFirstArgs() throws Exception {
        File file = temporaryFolder.newFile("source.csv");
        File target = temporaryFolder.newFile("target.csv");
        ArgsName argsName = ArgsName.of(new String[]{
                "-pathWrong=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;age",
                "Tom;20",
                "Jack;25",
                "William;30"
        ).concat(System.lineSeparator());
        CSVReader csvReader = new CSVReader();
        csvReader.handle(argsName);
        assertEquals(expected, Files.readString(target.toPath()));
    }
}