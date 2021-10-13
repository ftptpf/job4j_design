package ru.job4j.inout.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Сервер.
 * При отправке сообщения браузером-клиентом: http://localhost:9000/?msg=Hello
 * Сервер отвечает:HTTP/1.1 200 OK
 * И выводит в браузере Hello.
 * При отправке любого сообщения под звездочками (кроме Exit ) браузером-клиентом: http://localhost:9000/?msg=***
 * Сервер отвечает:HTTP/1.1 200 OK
 * И выводит в браузере What.
 * При отправке сообщения клиентом: http://localhost:9000/?msg=Exit
 * Сервер отвечает:HTTP/1.1 200 OK
 * и завершает работу.
 */
public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    /**
     * Создаем сервер по умолчанию localhost порт 9000.
     * closeServerWork - признак закрытия сервера
     * До тех пор пока сервер не закрыт, переводим сервер в режим ожидания когда к нему обратиться клиент.
     * Создает поток который будут отправлять ответы от сервера.
     * Создаем поток на чтение данных от клиента.
     * str - строка сообщений от "клиента"
     * answer - контрольная строка
     * @param args
     */
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean closeServerWork = false;
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = "some text";
                    String answer = "Exit";

                    while (str != null && !str.isEmpty()) {
                        if (str.startsWith("GET /?msg=") && str.contains("Exit")) {
                            closeServerWork = true;
                        } else if (str.startsWith("GET /?msg=") && str.contains("Hello")) {
                            answer = "Hello";
                        } else if (str.startsWith("GET /?msg=")) {
                            answer = "What";
                        }
                        str = in.readLine();
                        System.out.println(str);
                    }
                    out.write(("HTTP/1.1 200 OK\r\n\r\n").getBytes());

                    if (!answer.equals("Exit")) {
                        out.write(answer.getBytes());
                    }
                    if (closeServerWork) {
                        server.close();
                        closeServerWork = false;
                    }
                }
            }
        } catch (Exception e) {
            LOG.error("Exception information: ", e);
        }
    }
}
