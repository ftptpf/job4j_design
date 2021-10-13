package ru.job4j.inout.io.encoding;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 'Консольный чат':
 * - пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
 * - программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
 * - если пользователь вводит слово «продолжить», программа снова начинает отвечать.
 * - при вводе слова «закончить» программа прекращает работу.
 * - запись диалога, включая слова-команды стоп/продолжить/закончить должны быть записаны в текстовый лог.
 * path - имя файла, в который будет записан весь диалог между ботом и пользователем
 * botAnswer - имя файла в котором находятся строки с ответами, которые будет использовать бот
 * answerList - лист с ответами бота
 * logList - в лист собираем переписку с ботом перед записью её в файл
 * pauseStop - признак начала и окончания паузы в ответах бота
 */
public class ConsoleChat {
    private final String path;
    private final String botAnswer;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private List<String> answerList = new ArrayList<>();
    private List<String> logList = new ArrayList<>();
    private boolean pauseStop = false;

    public ConsoleChat(String path, String botAnswer) {
        this.path = path;
        this.botAnswer = botAnswer;
    }

    /**
     * Метод читает данные которые пользователь вводит в командной строке
     * и формирует ответ пользователю в командной строке,
     * случайным образов беря строку с ответ из листа answerList.
     * Вся переписка записывается в StringBuilder log файл.
     */
    public void run() {
        arrayBotAnswerCollect();
        if (answerList.size() == 0) {
            throw new IllegalArgumentException("Пустой лист с ответами бота.");
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            String str;
            do {
                str = br.readLine();
                logList.add("Я: --- " + str + " " + System.lineSeparator());
                pause(str);
                if (!str.equals(OUT)) {
                    if (!pauseStop) {
                        String answer = answerList.get(new Random().nextInt(answerList.size()));
                        System.out.println(answer);
                        logList.add("Бот: - " + answer + " " + System.lineSeparator());
                    }
                }
            } while (!str.equals(OUT));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод записывает собранный в List<String> logList диалог с ботом в файл.
     */
    public void writeLogToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            for (String logString : logList) {
                bw.write(logString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод проверяет введение слов "стоп" - "продолжить",
     * которые соответственно ставят на паузу либо повторно запускают,
     * ответы бота из листа.
     * @param str
     * @return
     */
    public void pause(String str) {
        if (str.equals(STOP)) {
            pauseStop = true;
        } else if (str.equals(CONTINUE)) {
            pauseStop = false;
        }
    }

    /**
     * Метод считываем ответы бота из файла и заносит их в лист.
     * @return
     */
    public void arrayBotAnswerCollect() {
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswer))) {
            br.lines().forEach(answerList::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat(
                Paths.get("resources", "logConsoleChat.txt").toString(),
                Paths.get("resources", "botAnswer.txt").toString());
        cc.run();
        cc.writeLogToFile();
    }
}
