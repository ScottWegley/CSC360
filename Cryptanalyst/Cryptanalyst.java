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
    }

    public static PotentialSolution[] bruteforceSubstitution(String cipherText, double sThreshold) {

        return new PotentialSolution[] {};
    }

    public static String[] shiftedAlphabet(int shift) {
        String[] sAlphabet = new String[BASE_ALPHABET.length];
        while(shift < 0){
            shift += 26;
        }
        while(shift > 26){
            shift -= 26;
        }
        System.out.println("Currently shifting by " + shift);
        return sAlphabet;
    }

}