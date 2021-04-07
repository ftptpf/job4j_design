package ru.job4j.inout.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Сервер.
 * При отправке сообщения клиентом: http://localhost:9000/?msg=Hello
 * Сервер отвечает:HTTP/1.1 200 OK
 * При отправке сообщения клиентом: http://localhost:9000/?msg=Bye
 * Сервер отвечает:HTTP/1.1 200 OK
 * и завершает работу.
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) { // создаем сервер по умолчанию localhost порт 9000
            boolean closeServerWork = false; // признак закрытия сервера
            while (!server.isClosed()) { // до тех пор пока сервер не закрыт
                Socket socket = server.accept(); // переводим сервер в режим ожидания когда к нему обратиться клиент
                try (OutputStream out = socket.getOutputStream(); // создает поток который будут отправлять ответы от сервера
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) { // создаем поток на чтение данных от клиента
                    String str;
                    String answer = "Exit";

                    while (!(str = in.readLine()).isEmpty()) { // все прочитанные данные от клиента
                        System.out.println(str); // выводим на консоль
                        if (str.startsWith("GET") && str.contains("Exit")) { // если строка ответа начинается с "GET" и содержит "Bye"
                            closeServerWork = true;
                        } else if (str.startsWith("GET") && str.contains("Hello")) {
                            answer = "Hello";
                        } else {
                            answer = "What";
                        }
                    }
                    out.write("HTTP/1.1 200 OK".getBytes()); // записываем ответ - "HTTP/1.1 200 OK"
                    out.write(System.lineSeparator().getBytes()); // записываем перевод строки
                    if (!answer.equals("Exit")) {
                        out.write(answer.getBytes());
                    }

                    if (closeServerWork) { // делаем проверку требуется ли закрыть сервер
                        server.close(); // закрываем сервер
                        closeServerWork = false;
                    }
                }
            }
        }
    }
}
