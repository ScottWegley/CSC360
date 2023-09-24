package Cryptanalyst;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReportGen {

    // KILYJZKMF
    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Code\\CSC360\\Cryptanalyst\\dictionary.txt");
        Scanner scanner = new Scanner(file);
        File out = new File("C:\\Code\\CSC360\\Cryptanalyst\\outputs\\CUSTOM.txt");
        FileWriter writer = new FileWriter(out);
        ArrayList<Character> temp = new ArrayList<>();
        String TESTONE = "BIMU"; // Z
        String TESTTWO = "ADEINORSTV"; // W
        String TESTTHREE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // H
        String TESTFOUR = "ELNT"; // M
        String TESTFIVE = "ABCDEGILMNOPRSTUVWXYZ"; // V
        String TESTSIX = "CFIZ"; // Y
        String TESTSEVEN = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // O
        String TESTEIGHT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // M
        String TESTNINE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // F

        while (scanner.hasNextLine()) {
            String in = scanner.nextLine();
            if (temp.size() == 26) {
                break;
            }
            if (in.length() == 7 && TESTONE.indexOf(in.toUpperCase().charAt(0)) != -1
                    && TESTTWO.indexOf(in.toUpperCase().charAt(1)) != -1
                    && TESTTHREE.indexOf(in.toUpperCase().charAt(2)) != -1
                    && TESTFOUR.indexOf(in.toUpperCase().charAt(0)) != -1
                    && TESTFIVE.indexOf(in.toUpperCase().charAt(1)) != -1
                    && TESTSIX.indexOf(in.toUpperCase().charAt(5)) != -1
                    && TESTSEVEN.indexOf(in.toUpperCase().charAt(6)) != -1/*
                    && TESTEIGHT.indexOf(in.toUpperCase().charAt(7)) != -1
                    && TESTNINE.indexOf(in.toUpperCase().charAt(8)) != -1
                    && in.charAt(0) == in.charAt(6)*/) {
                if (in.chars().filter(c -> c == in.charAt(0)).count() > 1
                        || in.chars().filter(c -> c == in.charAt(1)).count() > 1
                        || in.chars().filter(c -> c == in.charAt(2)).count() > 1
                        || in.chars().filter(c -> c == in.charAt(3)).count() > 1
                        || in.chars().filter(c -> c == in.charAt(4)).count() > 1
                        || in.chars().filter(c -> c == in.charAt(5)).count() > 1
                        || in.chars().filter(c -> c == in.charAt(6)).count() > 1/*
                        || in.chars().filter(c -> c == in.charAt(7)).count() > 1
                        || in.chars().filter(c -> c == in.charAt(8)).count() > 1*/) {
                    continue;
                }
                int i = 2;
                if (!temp.contains(in.charAt(i))) {
                    temp.add(in.charAt(i));
                    System.out.println(in);
                }
            }

        }
        for (Character t : temp) {
            writer.write(t + "\n");
        }
        scanner.close();
        writer.close();
    }
}
