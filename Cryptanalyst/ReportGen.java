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
        String TESTONE = "ADEIKNORSTV"; // W
        String TESTTWO = "BCDEFGILMNOPRST"; // Q
        String TESTTHREE = "ADEGHLMNOPRSTWXY"; // X
        String TESTFOUR = "BCDEFGHKLMNPQRSTVWY"; // B
        String TESTFIVE = ""; // J
        String TESTSIX = ""; // Z
        String TESTSEVEN = ""; // K
        String TESTEIGHT = ""; // M
        String TESTNINE = ""; // F

        while (scanner.hasNextLine()) {
            String in = scanner.nextLine();
            if (temp.size() == 26) {
                break;
            }
            if (in.length() == 6 && TESTONE.indexOf(in.toUpperCase().charAt(4)) != -1
                    && TESTTWO.indexOf(in.toUpperCase().charAt(2)) != -1
                    && TESTTHREE.indexOf(in.toUpperCase().charAt(5)) != -1
                    && TESTFOUR.indexOf(in.toUpperCase().charAt(0)) != -1/*
                    && TESTFIVE.indexOf(in.toUpperCase().charAt(4)) != -1
                    && TESTSIX.indexOf(in.toUpperCase().charAt(5)) != -1
                    && TESTSEVEN.indexOf(in.toUpperCase().charAt(6)) != -1
                    && TESTEIGHT.indexOf(in.toUpperCase().charAt(7)) != -1
                    && TESTNINE.indexOf(in.toUpperCase().charAt(8)) != -1*/
                    && in.charAt(2) == in.charAt(3)) {
                if (!temp.contains(in.charAt(1))) {
                    temp.add(in.charAt(1));
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
