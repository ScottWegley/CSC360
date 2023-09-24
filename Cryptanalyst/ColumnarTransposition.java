package Cryptanalyst;

import java.util.ArrayList;
import java.util.HashMap;

public class ColumnarTransposition {
    public static final String QUOTE_FIVE = "THULWOPIIWAKNIBEKWOALOVDENEITDTAWEHMIREONWWNKHODLARIETLNLYDTIOSHTUOETAHSLRIKEEGYSXHXTXXXAXXXRXXX";

    public static void bruteForce(){
        HashMap<Integer,ArrayList<String>> outputStorage = new HashMap<>();
        for (int i = 2; i <= 96; i++) {
            if(96 % i != 0){
                continue;
            }
            for (int counter = 0; counter < i; counter++) {
                if(!outputStorage.keySet().contains(i)){
                    outputStorage.put(i,new ArrayList<>());
                }
                int readPosition = 0;
                String row = "";
                while(readPosition < QUOTE_FIVE.length()){
                    row = row + QUOTE_FIVE.charAt(readPosition);
                    readPosition += i;
                }
                outputStorage.get(i).add(row);
            }
        }
        for (Integer i : outputStorage.keySet()) {
            System.out.println("=======Using Spacing " + i + "=======");
            for (String s : outputStorage.get(i)) {
                System.out.println(s);
            }
        }
    }
    public static void main(String[] args) {
        bruteForce();
    }
}
