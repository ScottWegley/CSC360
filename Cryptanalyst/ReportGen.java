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
        String TESTONE = "ACD"; // K
        String TESTTWO = "LHA"; // I
        String TESTTHREE = "IEU"; // L
        String TESTFOUR = "GM"; // Y
        String TESTFIVE = "NIH"; // J
        String TESTSIX = "MCT"; // Z
        String TESTSEVEN = "EA"; // K
        String TESTEIGHT = "NLR"; // M
        String TESTNINE = "TS"; // F
        while (scanner.hasNextLine()) {
            String in = scanner.nextLine();
            if (temp.size() == 26) {
                break;
            }
            if (in.length() == 9 && TESTONE.indexOf(in.toUpperCase().charAt(0)) != -1
                    && TESTTWO.indexOf(in.toUpperCase().charAt(1)) != -1
                    && TESTTHREE.indexOf(in.toUpperCase().charAt(2)) != -1
                    && TESTFOUR.indexOf(in.toUpperCase().charAt(3)) != -1 
                    && TESTFIVE.indexOf(in.toUpperCase().charAt(4)) != -1
                    && TESTSIX.indexOf(in.toUpperCase().charAt(5)) != -1
                    && TESTSEVEN.indexOf(in.toUpperCase().charAt(6)) != -1
                    && TESTEIGHT.indexOf(in.toUpperCase().charAt(7)) != -1
                    && TESTNINE.indexOf(in.toUpperCase().charAt(8)) != -1) {
                if (!temp.contains(in.charAt(0))) {
                    temp.add(in.charAt(0));
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
    // ZWHMVYO
}
