package lab4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var out = System.out;
        var scn = new Scanner(System.in);

        Lab4Collection coll = new Lab4Collection();

        coll.requestCollection(System.out, scn);
        coll.printItems(System.out);

        String filename;
        for (;;) {
            out.print("Введите название файла для записи коллекции: ");
            try {
                filename = scn.nextLine();
            } catch (InputMismatchException e) {
                out.println("Недопустимый ввод!");
                continue;
            }
            if (filename.isEmpty()) {
                out.println("Необходимо непустое название!");
                continue;
            }
            break;
        }

        coll.saveToFile(filename);

    }
}
