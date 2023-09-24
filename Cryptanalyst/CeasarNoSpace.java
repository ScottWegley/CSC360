package Cryptanalyst;

import java.util.ArrayList;
import java.util.Collections;

public class CeasarNoSpace {

    /** The standard 26 letter english alphabet. */
    public static final char[] BASE_ALPHABET = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    public static final String QUOTE_FOUR = "DQGJRGVDLGOHWWKHUHEHOLJKWDQGWKHUHZDVOLJKW";

    public static void main(String[] args) {
        ArbitrarySubSolution[] ONE_SOLS = bruteforceCeaser(QUOTE_FOUR);
        for (ArbitrarySubSolution s : ONE_SOLS) {
            System.out.println("================================================");
            String p1 = "===";
            String p2 = "===";
            for (int i = 0; i < BASE_ALPHABET.length; i++) {
                p1 = p1 + BASE_ALPHABET[i];
                p2 = p2 + s.getAlphabet().get(i);
            }
            System.out.print(p1+"===\n");
            System.out.print(p2+"===\n");
            System.out.println(s.getText());
        }
    }

    public static ArbitrarySubSolution[] bruteforceCeaser(String cipherText) {
        ArrayList<ArbitrarySubSolution> sols = new ArrayList<>();
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
            ArrayList<Character> temp = new ArrayList<>();
            for (char c : sAlpha) {
                temp.add(c);
            }
            ArbitrarySubSolution p = new ArbitrarySubSolution(decoded, temp);
            sols.add(p);
        }
        ArbitrarySubSolution[] out = new ArbitrarySubSolution[sols.size()];
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
