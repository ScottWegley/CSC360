import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
class PotentialSolution {

    private String text;
    private double accuracy;

    private static ArrayList<String> dictionary = null;
    public PotentialSolution(String _s) {
        text = _s.toUpperCase().trim();
        calculateAccuracy();
    }
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

    public double getAccuracy() {
        return accuracy;
    }
    public String getText() {
        return text;
    }
}