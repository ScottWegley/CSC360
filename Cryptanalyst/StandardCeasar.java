package Cryptanalyst;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class StandardCeasar {

    /** The standard 26 letter english alphabet. */
    public static final char[] BASE_ALPHABET = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    /** The standard 26 letter alphabet sorted by frequency */
    public static ArrayList<Character> FREQUENCY_TABLE = new ArrayList<>() {
        {
            add('e');
            add('t');
            add('a');
            add('o');
            add('i');
            add('n');
            add('s');
            add('h');
            add('r');
            add('d');
            add('l');
            add('u');
            add('c');
            add('m');
            add('f');
            add('w');
            add('y');
            add('g');
            add('p');
            add('b');
            add('v');
            add('k');
            add('q');
            add('j');
            add('x');
            add('z');
        }
    };

    public static final String QUOTE_ONE = "EUA IGT LUUR GRR ZNK VKUVRK YUSK UL ZNK ZOSK GTJ YUSK UL ZNK VKUVRK GRR ZNK ZOSK HAZ EUA IGTTUZ LUUR GRR ZNK VKUVRK GRR UL ZNK ZOSK";
    public static final String QUOTE_THREE = "KMS FW IO BLQQWX KILYJZKMF KFA MWV XUKV OWHY ZWHMVYO ZKM SW BWY OWH KFA XUKV OWH ZKM SW BWY OWHY ZWHMVYO";
    public static final String QUOTE_FOUR = "DQGJRGVDLGOHWWKHUHEHOLJKWDQGWKHUHZDVOLJKW";

    public static void main(String[] args) throws Exception {
        StandardCeasarSolution[] ONE_SOLS = bruteforceCeaser(QUOTE_ONE, .3);
        for (StandardCeasarSolution s : ONE_SOLS) {
        System.out.println("================================================");
        for (int i = 0; i < shiftedAlphabet(s.getShift()).length; i++) {
        System.out.print(shiftedAlphabet(s.getShift())[i]);
        }
        System.out.print(" || Shifted by " + s.getShift());
        System.out.print('\n');
        System.out.println("Accuracy: " + (s.getAccuracy() * 100) + "%");
        System.out.println(s.getText());
        }
    }

    /**
     * This function brute-forces Ceaser Ciphers to return the decrypted message.
     * (Will work on any cipher where the order of letters is constant with the
     * english alphabet)
     * 
     * @param cipherText The encrypted text you wish to brute force.
     * @param sThreshold A number between 0 and 1 representing the percentage of
     *                   real words (checked against the ~10000 most commmon english
     *                   words) required
     *                   to be considered a {@link PotentialSolution}.
     * @return
     */
    public static StandardCeasarSolution[] bruteforceCeaser(String cipherText, double sThreshold) {
        if (sThreshold > 1) {
            sThreshold = 1;
        }
        if (sThreshold < 0) {
            sThreshold = 0;
        }
        ArrayList<StandardCeasarSolution> sols = new ArrayList<>();
        for (int i = 0; i < BASE_ALPHABET.length; i++) {
            char[] sAlpha = shiftedAlphabet(i);
            String decoded = "";
            for (int j = 0; j < cipherText.length(); j++) {
                if (cipherText.charAt(j) == ' ') {
                    decoded = decoded + ' ';
                    continue;
                }
                decoded = decoded + sAlpha[cipherText.charAt(j) - 65];
            }
            StandardCeasarSolution p = new StandardCeasarSolution(decoded, i);
            if (p.getAccuracy() >= sThreshold) {
                sols.add(p);
            }
        }
        StandardCeasarSolution[] out = new StandardCeasarSolution[sols.size()];
        for (int i = 0; i < out.length; i++) {
            out[i] = sols.get(i);
        }
        return out;
    }

    /**
     * Returns an an array with the characters from the standard english alphabet.
     * 
     * @param shift The difference between each letter's position in this shifted
     *              alphabet and it's natural order.
     *              Shift is normalized to a minimum value of zero and maximum value
     *              of 26. This handles negative shift amounts
     *              and shifting by more than the length of the alphabet.
     * @return An alphabet with each letter shifted by the specified amount.
     */
    public static char[] shiftedAlphabet(int shift) {
        char[] sAlphabet = new char[BASE_ALPHABET.length];
        while (shift < 0) {
            shift += 26;
        }
        while (shift >= 26) {
            shift -= 26;
        }
        for (int i = 0; i < BASE_ALPHABET.length; i++) {
            sAlphabet[(i + shift > 25 ? i + shift - 26 : i + shift)] = BASE_ALPHABET[i];
        }
        return sAlphabet;
    }

}