package ru.job4j.inout.io.encoding;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 'Консольный чат':
 * - пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
 * - программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
 * - если пользователь вводит слово «продолжить», программа снова начинает отвечать.
 * - при вводе слова «закончить» программа прекращает работу.
 * - запись диалога, включая слова-команды стоп/продолжить/закончить должны быть записаны в текстовый лог.
 */
public class ConsoleChat {
    private final String path; // имя файла, в который будет записан весь диалог между ботом и пользователем
    private final String botAnswer; // имя файла в котором находятся строки с ответами, которые будет использовать бот
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private List<String> answerList = new ArrayList<>();

    public ConsoleChat(String path, String botAnswer) {
        this.path = path;
        this.botAnswer = botAnswer;
    }
    public void run() {
        arrayBotAnswerCollect();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String str;
            do {
                str = br.readLine();
                //int index =
                System.out.println(answerList.get(1));
            } while (!str.equals(STOP));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * Метод считываем ответы бота из файла и заносит их в лист.
     * @return
     */
    public void arrayBotAnswerCollect() {
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswer))) {
            answerList.add(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("resources/logConsoleChat.txt", "resources/botAnswer.txt");
        cc.run();
    }
}
