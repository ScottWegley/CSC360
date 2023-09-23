package Cryptanalyst;

import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class FrequencyAnalyst {

    static BigInteger TWENTY_SIX_FACTORIAL = new BigInteger("403291461126605635584000000");
    public static final String QUOTE_TWO = "B SMVE M HPEMJ RSMR LKE HMY RSBQ KMRBLK WBGG PBQE UN GBVE LUR RSE RPUE JEMKBKC LI BRQ TPEEH WE SLGH RSEQE RPURSQ RL AE QEGI EVBHEKR RSMR MGG JEK MPE TPEMREH EOUMG";
    public static final char[] BASE_ALPHABET = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    public static ArrayList<ArrayList<Character>> alphabets = new ArrayList<>();
    public static void main(String[] args) throws Exception {
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
    }
}
