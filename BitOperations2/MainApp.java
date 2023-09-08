package BitOperations2;
import Libraries.BitOperator;

public class MainApp {
    public static void main(String[] args) {
        byte b = (byte) 0b11011001;
        System.out.println("expander: ");
        System.out.println("\t" +
                BitOperator.int2binary(
                        BitOperations2.expander(b), 8));
        short key = 0b0110100101101001;
        System.out.println("key extractor: ");
        for (int pos = 0; pos <= 15; ++pos) {
            System.out.println("\t" +
                    BitOperator.int2binary(
                            BitOperations2.keyextractor(key, pos), 8));
        }
    }
}
