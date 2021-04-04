package ru.job4j.inout.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) { // создаем сервер по умолчанию localhost порт 9000
            while (!server.isClosed()) { // до тех пор пока сервер не закрыт
                Socket socket = server.accept(); // переводим сервер в режим ожидания когда к нему обратиться клиент
                try (OutputStream out = socket.getOutputStream(); // создает поток от сервера
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) { // создаем поток на чтение данных от клиента
                    String str;
                    while (!(str = in.readLine()).isEmpty()) { // все прочитанные данные от клиента
                        System.out.println(str); // выводим на консоль
                        if (str.startsWith("GET") && str.contains("Bye")) { // если строка ответа начинается с "GET" и содержит "Bye"
                            server.close(); // закрываем сервер
                        }
                    }
                    out.write("HTTP/1.1 200 OK".getBytes()); // записываем ответ - "HTTP/1.1 200 OK"
                    out.write(System.lineSeparator().getBytes()); // записываем перевод строки
                }
            }
        }
    }
}
