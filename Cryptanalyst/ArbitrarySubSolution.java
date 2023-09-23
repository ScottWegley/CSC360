package Cryptanalyst;

import java.util.ArrayList;

public class ArbitrarySubSolution extends PotentialSolution{

    private ArrayList<Character> alphabet;

    public ArbitrarySubSolution(String _s, ArrayList<Character> _a) {
        super(_s);
        alphabet = _a;
    }

    public ArrayList<Character> getAlphabet(){
        return alphabet;
    }
    
}
