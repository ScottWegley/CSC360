package Cryptanalyst;

import java.util.ArrayList;

class Cryptanalyst {

    /** The standard 26 letter english alphabet. */
    public static final String[] BASE_ALPHABET = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };

    public static void main(String[] args) {
        shiftedAlphabet(27);
    }

    public static StandardCeasarSolution[] bruteforceCeaser(String cipherText, double sThreshold) {
        if (sThreshold > 1) {
            sThreshold = 1;
        }
        if (sThreshold < 0) {
            sThreshold = 0;
        }
        ArrayList<StandardCeasarSolution> sols = new ArrayList<>();
        for (int i = 0; i < BASE_ALPHABET.length; i++) {
            String[] sAlpha = shiftedAlphabet(i);
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

    public static PotentialSolution[] bruteforceSubstitution(String cipherText, double sThreshold) {

        return new PotentialSolution[] {};
    }

    public static String[] shiftedAlphabet(int shift) {
        String[] sAlphabet = new String[BASE_ALPHABET.length];
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