package BitOperations2;

import Libraries.BitOperator;

public class BitOperations2 {
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
    // 11111111
    public static void main(String[] args) {
        // keyextractor((short) 0b0110100101101001, 11);
        System.out.println(BitOperator.int2binary(keyextractor((short) 0b0110100101101001, 10), 16));
        // System.out.println(BitOperator.int2binary(expander((byte) 0b00011001), 8));
    }

}
