package lab4;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public final class Lab4Collection {
    private static final int keyCount;

    private HashMap<String, String> map;

    static {
        keyCount = 3;
    }

    {
        map = new HashMap<>();
    }

    Lab4Collection() {
    }

    public void requestCollection(PrintStream out, Scanner scn) {
        out.println("Введите элементы коллекции, пары ключ-значение, разделитель - пробел");
        for (int i = 0; i < keyCount; ++i) {
            String line;
            for (;;) {
                out.print(i + ": ");
                try {
                    line = scn.nextLine();
                } catch (InputMismatchException e) {
                    out.println("Недопустимый ввод!");
                    continue;
                }
                var parts = line.split(" ");
                if (parts.length != 2) {
                    out.println("Необходима пара элементов!");
                    continue;
                }
                this.map.put(parts[0], parts[1]);
                break;
            }
        }
    }

    public void printKeys(PrintStream out) {
        out.println("Ключи коллекции:");
        Iterator<String> it = this.map.keySet().iterator();
        while (it.hasNext()) {
            out.println(it.next());
        }
    }

    public void printValues(PrintStream out) {
        out.println("Значения коллекции:");
        Iterator<String> it = this.map.values().iterator();
        while (it.hasNext()) {
            out.println(it.next());
        }
    }

    public void printItems(PrintStream out) {
        out.println("Пары ключ-значение:");
        Iterator<HashMap.Entry<String, String>> it = this.map.entrySet().iterator();
        while (it.hasNext()) {
            var entry = it.next();
            out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public void saveToFile(String filename) {
        try {
            var filestream = new FileOutputStream(filename, false);

            for (var entry : this.map.entrySet()) {
                String line = entry.getKey() + " " + entry.getValue() + "\n";
                byte[] bytes = line.getBytes("UTF-8");
                filestream.write(bytes);
            }

            filestream.close();

        } catch (FileNotFoundException e) {
            System.err.println("Непредвиденная ошибка при открытии файла!");
        } catch (IOException e) {
            System.err.println("Непредвиденная ошибка при записи в файл!");
        }
    }

}
