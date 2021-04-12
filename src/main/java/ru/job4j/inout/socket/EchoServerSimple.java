package ru.job4j.inout.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Сервер к которому можно обращаться с командной строки, например
 * curl -i http://localhost:9000/?msg=Hello
 * Чтобы закрыть сервер отправляем команду
 * curl -i http://localhost:9000/?msg=Bye
 */
public class EchoServerSimple {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean closeServerWork = false; // признак закрытия сервера
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.startsWith("GET") && str.contains("Bye")) {
                            closeServerWork = true;
                        }
                    }
                    out.write(("HTTP/1.1 200 OK\r\n\r\n").getBytes());
                    if (closeServerWork) {
                        server.close();
                        closeServerWork = false;
                    }
                }
            }
        }
    }
}
