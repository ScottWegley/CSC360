package BitOperations2;

import Libraries.BitOperator;

public class BitOperations2 {
    public static byte keyextractor(short _key, int _pos) {
        return 0;
    }

    public static byte expander(byte _byte) {
        byte outB = (byte) (0b00110000 & _byte);
        outB = (byte) (outB << 2);
        outB = (byte) (outB | (_byte & 0b00000011));
        byte temp = (byte) (((0b00000100 & _byte) << 1) | ((0b00001000 & _byte) >>> 1));
        outB = (byte) ((outB | temp) | (temp << 2));
        return outB;
    }

    public static void main(String[] args) {
        System.out.println(BitOperator.int2binary(expander((byte) 0b11011001), 8));
    }

}
