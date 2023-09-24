package Cryptanalyst;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ReportGen {

    // KILYJZKMF
    public static void main(String[] args) throws Exception {
        // LOAD FREQUENCY TABLE
        File freqTxt = new File("C:\\Code\\CSC360\\Cryptanalyst\\FreqTestQ3.txt");
        Scanner freqScan = new Scanner(freqTxt);
        HashMap<Character, String> currFreq = new HashMap<>();
        while (freqScan.hasNextLine()) {
            String in = freqScan.nextLine();
            char header = in.charAt(1);
            char[] possible = in.substring(in.indexOf(':') + 1).toCharArray();
            String out = "";
            for (int i = 0; i < possible.length; i++) {
                out = out + possible[i];
            }
            currFreq.put(header, out);

        }
        freqScan.close();
        // LOAD FREQUENCY TABLE
        File file = new File("C:\\Code\\CSC360\\Cryptanalyst\\dictionary.txt");
        Scanner scanner = new Scanner(file);
        File out = new File("C:\\Code\\CSC360\\Cryptanalyst\\outputs\\CUSTOM.txt");
        FileWriter writer = new FileWriter(out);
        ArrayList<Character> temp = new ArrayList<>();
        String chars = "SW";
        int[] occur = { 1, 1 };
        if (occur.length != chars.length()) {
            throw new Exception();
        }

        while (scanner.hasNextLine()) {
            String in = scanner.nextLine();
            if (temp.size() == 26) {
                break;
            }
            // Only check the word if it's the same length as the word we're using as a mask
            // (chars).
            if (in.length() == chars.length()) {
                boolean skip = false;
                for (int i = 0; i < in.length() && !skip; i++) {
                    if (currFreq.get(chars.charAt(i)).indexOf(in.toUpperCase().charAt(i)) == -1) {
                        skip = true; // For every character in our mask, make sure the corresponding character in
                                     // this word is in our frequency list.
                    }

                    final int _i = i;

                    if (in.chars().filter(c -> c == in.charAt(_i)).count() > occur[i]) {
                        skip = true;
                    }
                }
                if (skip) {
                    continue;
                }
                int i = 1;
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
