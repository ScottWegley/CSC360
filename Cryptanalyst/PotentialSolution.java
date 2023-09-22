package Cryptanalyst;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class to store the attempted decryption of a text and what percentage of
 * the words are real words.
 */
abstract class PotentialSolution {

    /** The potentially decrypted text. */
    private String text;
    /**
     * The accuracy of the decrypted text. (The percentage of words present in the
     * {@link #dictionary}.)
     */
    private double accuracy;

    /**
     * List of the ~10000 most common english words.
     */
    private static ArrayList<String> dictionary = null;

    /**
     * Creates a {@link PotentialSolution} from the specified string.
     * 
     * @param _s The text of the potential solution.
     */
    public PotentialSolution(String _s) {
        text = _s.toUpperCase().trim();
        calculateAccuracy();
    }

    /**
     * Checks the words in {@link #text} against the ~10000 most
     * common english words to provide a percentage accuracy. If the dictionary has
     * not been initialized, this initializes it w/ {@link #initializeDictionary()}.
     */
    private void calculateAccuracy() {
        if (dictionary == null) {
            initializeDictionary();
        }
        String[] toCheck = getText().split(" ");
        int totalWords = toCheck.length;
        int realWords = 0;
        for (String s : toCheck) {
            if (dictionary.contains(s)) {
                realWords++;
            }
        }
        accuracy = (double) realWords / (double) totalWords;
    }

    /**
     * Reads the ~10000 most common english words from a file and loads them
     * into {@link #dictionary} which we check against.
     */
    private static void initializeDictionary() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("C:\\Code\\CSC360\\Cryptanalyst\\dictionary.txt"));
            dictionary = new ArrayList<>();
            while (scanner.hasNextLine()) {
                dictionary.add(scanner.nextLine().toUpperCase());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        scanner.close();
    }

    /**
     * @return {@link #accuracy}
     */
    public double getAccuracy() {
        return accuracy;
    }

    /**
     * @return {@link #text}
     */
    public String getText() {
        return text;
    }
}