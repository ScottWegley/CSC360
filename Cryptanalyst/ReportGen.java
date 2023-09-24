package Cryptanalyst;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
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
        // LOAD DICTIONARY
        ArrayList<String> dictionary = new ArrayList<>();
        File file = new File("C:\\Code\\CSC360\\Cryptanalyst\\dictionary.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            dictionary.add(scanner.nextLine());
        }
        scanner.close();
        // LOAD DICTIONARY
        HashMap<Integer, Integer> dupeMap = new HashMap<>();
        String chars = "SW";
        // GEN DUPE MAP
        for (int i = 0; i < chars.length() - 1; i++) {
            for (int j = i + 1; j < chars.length(); j++) {
                if (chars.charAt(i) == chars.charAt(j)) {
                    dupeMap.put(i, j);
                }
            }
        }
        for (Integer yo : dupeMap.keySet()) {
            System.out.println("Must match " + yo + " to " + dupeMap.get(yo));
        }
        // GEN DUPE MAP
        ArrayList<String> reports = new ArrayList<>();
        for (int CHAR_TO_REPORT = 0; CHAR_TO_REPORT < chars.length(); CHAR_TO_REPORT++) {
            ArrayList<Character> temp = new ArrayList<>();
            for (String in : dictionary) {
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

                        if (in.chars().filter(c -> c == in.charAt(_i)).count() != chars.chars()
                                .filter(c -> c == chars.charAt(_i)).count()) {
                            skip = true; // For every letter if the word we're checking, the count should match the
                                         // count of the corresponding letter count in our cipher word
                        }
                    }
                    for (Integer FIRST_INDEX : dupeMap.keySet()) {
                        if(in.charAt(FIRST_INDEX) != in.charAt(dupeMap.get(FIRST_INDEX))){
                            skip = true;
                        }
                    }

                    if (skip) {
                        continue;
                    }
                    if (!temp.contains(in.charAt(CHAR_TO_REPORT))) {
                        temp.add(in.charAt(CHAR_TO_REPORT));
                        System.out.println(in);
                    }
                }
            }
            Collections.sort(temp);
            String report = chars.charAt(CHAR_TO_REPORT) + ": ";
            for (Character t : temp) {
                report = report + Character.toUpperCase(t);
            }
            reports.add(report);
        }
        scanner.close();
        for (String s : reports) {
            System.out.println(s);
        }
    }
}
