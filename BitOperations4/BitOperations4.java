package BitOperations4;

import Libraries.BitOperator;

public class BitOperations4 {
    public static void main(String[] args) {
        byte K = (byte) (0b10101010 & 0xFF);
        System.out.println("\t S Feistel");
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 8; ++j) {
                // -- set up index to S boxes from i and
                byte Sindex = (byte) (((i << 3) | j) & 0x0F);
                // -- combine 3 bits from S1 and S2 into a 6 bits
                byte R = (byte) ((S1(Sindex) << 3) |
                        S2((byte) (Sindex)));
                // -- send S values to Feistel function with the key
                byte f = feistel(R, K);
                System.out.println(
                        BitOperator.int2binary((byte) (((i << 3) | j) & 0x0F), 4) +
                                ": " +
                                BitOperator.int2binary(R, 6) + " " +
                                BitOperator.int2binary(f, 6));
            }
        }

        // feistel((byte) 0b10110111, (byte) 0xFF);
    }

    public static byte feistel(byte R, byte K) {
        R = expander(R);
        R = (byte) (R ^ K);
        K = right(R);
        R = left(R);
        R = S1(R);
        K = S2(K);
        R = (byte) (R | (K << 3));
        return R;
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

    public static byte keyextractor(short _key, int _pos) {
        byte outB;
        short max = (short) 0xFF00;
        max = (short) BitOperator.urShift(max, _pos);
        if (_pos < 9) {
            return (byte) (BitOperator.urShift((max & _key), (8 - _pos)));
        } else {
            outB = (byte) ((max & _key) << _pos - 8);
            _key = (short) (_key >>> (16 - (_pos - 8)));
            outB = (byte) (outB | _key);
            return outB;
        }
    }

    public static byte expander(byte _byte) {
        byte outB = (byte) (0b00110000 & _byte);
        outB = (byte) (outB << 2);
        outB = (byte) (outB | (_byte & 0b00000011));
        byte temp = (byte) (((0b00000100 & _byte) << 1) | BitOperator.urShift((0b00001000 & _byte), 1));
        outB = (byte) ((outB | temp) | (temp << 2));
        return outB;
    }

    public static byte left(byte _b) {
        return (byte) ((_b >>> 4) & 0b00001111);
    }

    public static byte right(byte _b) {
        return (byte) (_b & 0b00001111);
    }

    public static byte sixbits(byte _b) {
        return (byte) ((_b >> 2) & 0b00111111);
    }

    public static short lrswap(short _b) {
        short l = (short) ((_b >>> 8) & 0b0000000011111111);
        short r = (short) (_b << 8);
        return (short) ((0b1111111111111111 & r) | l);
    }
}
