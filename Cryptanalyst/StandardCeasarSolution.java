package Cryptanalyst;

public class StandardCeasarSolution extends PotentialSolution {

    private int shift;

    public StandardCeasarSolution(String _s, int i) {
        super(_s);
        shift = i;
    }

    public int getShift() {
        return shift;
    }

}
