package ru.job4j.inout.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

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
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) { // создаем сервер по умолчанию localhost порт 9000
            boolean closeServerWork = false; // признак закрытия сервера
            while (!server.isClosed()) { // до тех пор пока сервер не закрыт
                Socket socket = server.accept(); // переводим сервер в режим ожидания когда к нему обратиться клиент
                try (OutputStream out = socket.getOutputStream(); // создает поток который будут отправлять ответы от сервера
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) { // создаем поток на чтение данных от клиента
                    String str = "some text"; // строка сообщений от "клиента"
                    String answer = "Exit"; // контрольная строка

                    while (!Objects.requireNonNull(str = in.readLine()).isEmpty()) { // все прочитанные данные от клиента
/*                        str = in.readLine();
                        if (str == null) {
                            continue;
                        }*/
                        System.out.println(str); // выводим на консоль
                        if (str.startsWith("GET /?msg=") && str.contains("Exit")) { // если строка ответа начинается с "GET" и содержит "Exit"
                            closeServerWork = true;
                        } else if (str.startsWith("GET /?msg=") && str.contains("Hello")) { // // если строка ответа начинается с "GET" и содержит "Hello"
                            answer = "Hello";
                        } else if (str.startsWith("GET /?msg=")) { // в ином случае
                            answer = "What";
                        }
                    }
                    out.write("HTTP/1.1 200 OK".getBytes()); // записываем ответ - "HTTP/1.1 200 OK"
                    out.write(System.lineSeparator().getBytes()); // записываем перевод строки
                    out.write(System.lineSeparator().getBytes()); // записываем еще раз перевод строки
                    if (!answer.equals("Exit")) { // если контрольная строка не содержит "Exit"
                        out.write(answer.getBytes()); // выводим контрольное сообщение на консоль
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
