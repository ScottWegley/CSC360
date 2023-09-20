package BitOperations3;

import Libraries.BitOperator;

public class BitOperations3 {
    public static void main(String[] args) {

        System.out.println("S1\t\tS2");
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 8; ++j) {
                System.out.println(
                        BitOperator.int2binary((byte) (((i << 3) | j) & 0x0F), 4) + ": " +
                                BitOperator.int2binary(S1((byte) (((i << 3) | j) & 0x0F)), 3) + "\t" +
                                BitOperator.int2binary(S2((byte) (((i << 3) | j) & 0x0F)), 3));
            }
        }

    }

    private static byte S2(byte _b) {
        byte arr[][] = { { 0b00000100, 0b00000101 }, { 0b00000000, 0b00000011 }, { 0b00000110, 0b00000000 },
                { 0b00000101, 0b00000111 }, { 0b00000111, 0b00000110 }, { 0b00000001, 0b00000010 },
                { 0b00000011, 0b00000001 }, { 0b00000010, 0b00000100 } };
        _b = (byte) (_b & 0x0F);
        return arr[_b & 0x07][(_b >>> 3)];
    }

    private static byte S1(byte _b) {
        byte arr[][] = { { 0b00000101, 0b00000001 }, { 0b00000010, 0b00000100 }, { 0b00000001, 0b00000110 },
                { 0b00000110, 0b00000010 }, { 0b00000011, 0b00000000 }, { 0b00000100, 0b00000111 },
                { 0b00000111, 0b00000101 }, { 0b00000000, 0b00000011 } };
        _b = (byte) (_b & 0x0F);
        return arr[_b & 0x07][(_b >>> 3)];
    }
}
