package Cryptanalyst;

import java.util.ArrayList;

class Cryptanalyst {

    /** The standard 26 letter english alphabet. */
    public static final String[] BASE_ALPHABET = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };


    public static final String QUOTE_ONE = "EUA IGT LUUR GRR ZNK VKUVRK YUSK UL ZNK ZOSK GTJ YUSK UL ZNK VKUVRK GRR ZNK ZOSK HAZ EUA IGTTUZ LUUR GRR ZNK VKUVRK GRR UL ZNK ZOSK";
    public static final String QUOTE_TWO = "B SMVE M HPEMJ RSMR LKE HMY RSBQ KMRBLK WBGG PBQE UN GBVE LUR RSE RPUE JEMKBKC LI BRQ TPEEH WE SLGH RSEQE RPURSQ RL AE QEGI EVBHEKR RSMR MGG JEK MPE TPEMREH EOUMG";
    public static final String QUOTE_THREE = "KMS FW IO BLQQWX KILYJZKMF KFA MWV XUKV OWHY ZWHMVYO ZKM SW BWY OWH KFA XUKV OWH ZKM SW BWY OWHY ZWHMVYO";
    public static final String QUOTE_FOUR = "DQGJRGVDLGOHWWKHUHEHOLJKWDQGWKHUHZDVOLJKW";

    public static void main(String[] args) {
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