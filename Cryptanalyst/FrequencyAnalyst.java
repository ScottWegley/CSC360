package Cryptanalyst;

import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FrequencyAnalyst {

    static HashMap<Character, ArrayList<Character>> currFreq = null;
    static BigInteger TWENTY_SIX_FACTORIAL = new BigInteger("403291461126605635584000000");
    public static final String QUOTE_TWO = "B SMVE M HPEMJ RSMR LKE HMY RSBQ KMRBLK WBGG PBQE UN GBVE LUR RSE RPUE JEMKBKC LI BRQ TPEEH WE SLGH RSEQE RPURSQ RL AE QEGI EVBHEKR RSMR MGG JEK MPE TPEMREH EOUMG";
    public static final char[] BASE_ALPHABET = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    public static ArrayList<ArrayList<Character>> alphabets = new ArrayList<>();
    public static void main(String[] args) throws Exception {
    private static void generateAlphabets(char[] receive, int alphaIndex, ArrayList<Character> used, char... validChars)
            throws Exception {
        if (alphaIndex == BASE_ALPHABET.length) {
            ArrayList<Character> temp = new ArrayList<>();
            for (int i = 0; i < receive.length; i++) {
                temp.add(receive[i]);
            }
            alphabets.add(temp);
            return;
        }
        boolean valid = false;
        for (char c : validChars) {
            if (c == BASE_ALPHABET[alphaIndex]) {
                valid = true;
            }
        }
        if (!valid) {
            receive[alphaIndex] = '?';
            alphaIndex++;
            generateAlphabets(receive, alphaIndex, used, validChars);
        } else {
            ArrayList<Character> toTraverse = new ArrayList<>();
            for (char q : currFreq.get(BASE_ALPHABET[alphaIndex])) {
                if (used.contains(q)) {
                    continue;
                }
                toTraverse.add(q);

            }
            for (Character y : toTraverse) {
                receive[alphaIndex] = y;
                alphaIndex++;
                used.add(y);
                generateAlphabets(receive, alphaIndex--, used, validChars);
                used.remove(y);
            }

        }
        return;
    }

    public static void genOutput() throws Exception {
        File file = new File("C:\\Code\\CSC360\\Cryptanalyst\\dictionary.txt");
        Scanner scanner = new Scanner(file);
        File out = new File("C:\\Code\\CSC360\\Cryptanalyst\\outputs\\threeLetterAt1.txt");
        FileWriter writer = new FileWriter(out);
        ArrayList<Character> temp = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String in = scanner.nextLine();
            if (in.length() == 3) {
                System.out.println(in);
                if (!temp.contains(in.charAt(1))) {
                    temp.add(in.charAt(1));
                }
            }
        }

        for (Character t : temp) {
            writer.write(t + "  \n");
        }

        scanner.close();
        writer.close();
    }

    /**
     * Loads the FreqTest file into a HashMap.
     */
    private static void loadFrequencyTest() throws Exception {
        currFreq = new HashMap<>();
        Scanner scanner = new Scanner(new File("C:\\Code\\CSC360\\Cryptanalyst\\FreqTest.txt"));
        while (scanner.hasNextLine()) {
            String in = scanner.nextLine();
            char header = in.charAt(1);
            char[] possible = in.substring(in.indexOf(':') + 1).toCharArray();
            ArrayList<Character> out = new ArrayList<Character>();
            for (int i = 0; i < possible.length; i++) {
                out.add(possible[i]);
            }
            currFreq.put(header, out);

        }
        scanner.close();
    }

    public static void printFreqTestReport(int threshold) throws Exception {

        if (currFreq == null) {
            loadFrequencyTest();
        }
        HashMap<Character, Integer> FreqAnalysis = new HashMap<>();
        if (threshold != 0) {
            for (char c : BASE_ALPHABET) {
                FreqAnalysis.put(c, (int) QUOTE_TWO.chars().filter(ch -> ch == c).count());
            }
        }

        BigInteger perms = BigInteger.ONE;
        System.out.println("=======REPORT=======");
        for (Character c : currFreq.keySet()) {
            if (FreqAnalysis.size() != 0) {
                if (FreqAnalysis.get(c) < threshold) {
                    continue;
                }
            }
            System.out.print("_" + c + ": ");
            for (Character q : currFreq.get(c)) {
                System.out.print(q);
            }
            perms = perms.multiply(new BigInteger(String.valueOf(currFreq.get(c).size())));
            System.out.print(" || " + currFreq.get(c).size() + "\n");
        }
        System.out.println("Current Worst Permutations: " + perms.toString());
        System.out.println("Worst Case Possible: " +
                TWENTY_SIX_FACTORIAL.toString());

    }

    public static void printFreqTestReport(char... v) throws Exception {

        if (currFreq == null) {
            loadFrequencyTest();
        }

        BigInteger perms = BigInteger.ONE;
        System.out.println("=======REPORT=======");
        for (Character c : currFreq.keySet()) {
            boolean exists = false;
            for (char d : v) {
                if (c == d) {
                    exists = true;
                }
            }
            if (!exists) {
                continue;
            }
            System.out.print("_" + c + ": ");
            for (Character q : currFreq.get(c)) {
                System.out.print(q);
            }
            perms = perms.multiply(new BigInteger(String.valueOf(currFreq.get(c).size())));
            System.out.print(" || " + currFreq.get(c).size() + "\n");
        }
        System.out.println("Current Worst Permutations: " + perms.toString());
        System.out.println("Worst Case Possible:        " +
                TWENTY_SIX_FACTORIAL.toString());

    }
}
