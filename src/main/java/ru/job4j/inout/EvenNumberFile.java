package ru.job4j.inout;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream fileInputStream = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            do {
                read = fileInputStream.read(); // считываем в байтах значение записанное в файле
                if (text.length() > 0 &&  (char) read == '\r' || read == -1) { // если в text есть записанные значения и последнее считанное значение "символ возврат каретки" или мы уже дошли до последнего значения файла
                    if (Integer.parseInt(text.toString()) % 2 == 0) { // приводим text к числу и проверяем на четность
                        System.out.println(text + " - четное число"); // выводим значение text на консоль с комментариями
                        text.setLength(0); // удаляем всю ранее записанную информацию из text
                    } else if (Integer.parseInt(text.toString()) % 2 != 0) { // приводим text к числу и проверяем на четность
                        System.out.println(text + " - нечетное число"); // выводим значение text на консоль с комментариями
                        text.setLength(0); // удаляем всю ранее записанную информацию из text
                    }
                }
                if ((char) read == '\r') { // если в символах встречается "символ возврат каретки"
                    continue; // переходим в начало цикла while и считываем новое значение
                }
                if ((char) read == '\n') { // если в символах встречается "символ перехода на новую строку"
                    continue; // переходим в начало цикла while и считываем новое значение
                }
                text.append((char) read); // приводим значение к "char" и записываем в text
            }
            while (read != -1); //{ // выполняем действия пока не дойдем до конца файла

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
