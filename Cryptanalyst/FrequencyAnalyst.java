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

    private static char[] testArray = { 'B', 'Q', 'M', 'R', 'S', 'G', 'W', 'L', 'A', 'E' };

    public static void main(String[] args) throws Exception {
        try {
            // printFreqTestReport(3);
            applyFrequencyTest(QUOTE_TWO, 0.90, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void applyFrequencyTest(String encoded, double threshold, int alphaThreshold) throws Exception {
        if (currFreq == null) {
            loadFrequencyTest();
        }
        String s = "";
        for (Character c : currFreq.keySet()) {
            if ((int) QUOTE_TWO.chars().filter(ch -> ch == c).count() >= alphaThreshold) {
                s = s + c;
            }
        }
        applyFrequencyTest(encoded, threshold, s.toCharArray());
    }

    /**
     * Applies the current frequency test with limitations specified in the
     * parameters.
     * 
     * @param encoded   The encoded text we are applying our frequency test to.
     * @param threshold The percentage of "real words" that must be present to
     *                  consider
     *                  the result.
     * @param c         An array of characters to use in the thresholding.
     *                  Characters not
     *                  in this array will not be solved for.
     * @throws Exception
     */
    public static void applyFrequencyTest(String encoded, double threshold, char... c) throws Exception {
        if (currFreq == null) {
            loadFrequencyTest();
        }
        char[] template = new char[26];
        generateAlphabets(template, 0, new ArrayList<>(), c);
        double highestAccuracy = 0;
        for (int i = 0; i < alphabets.size(); i++) {
            // Unccomment the below lines if you'd like
            // to print the alphabets out.
            // System.out.print((i + 1) + ": ");
            // for (char q : alphabets.get(i)) {
            // System.out.print(q);
            // }
            // System.out.print('\n');
            // Above is only for printing alphabets.
            String decoded = "";
            for (int j = 0; j < encoded.length(); j++) {
                if (encoded.charAt(j) == ' ') {
                    decoded = decoded + ' ';
                    continue;
                }
                decoded = decoded + alphabets.get(i).get(encoded.charAt(j) - 65);
            }
            ArbitrarySubSolution _s = new ArbitrarySubSolution(decoded, alphabets.get(i));
            if (_s.getAccuracy() >= threshold) {
                if (_s.getAccuracy() > highestAccuracy) {
                    highestAccuracy = _s.getAccuracy();
                }
                System.out.println("Testing Alphabet #" + ++i + " || Accuracy: " + _s.getAccuracy() * 100 + "%");
                System.out.print("===");
                for (char d : BASE_ALPHABET) {
                    System.out.print(d);
                }
                System.out.print("===\n");
                System.out.print("===");
                for (char d : _s.getAlphabet()) {
                    System.out.print(d);
                }
                System.out.print("===\n");
                System.out.println(_s.getText());
            }
        }
        System.out.println("Highest Accuracy: " + highestAccuracy * 100 + "%");
    }

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
