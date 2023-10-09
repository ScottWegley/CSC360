import Libraries.BitOperator;

public class EncodeDecode {
    static short encode12(short plain, int round, short key9) {
        byte key = keyextractor(key9, round);
        byte left = (byte) (0x3F & (BitOperator.urShift(plain, 6)));
        byte right = (byte) (0x3F & plain);
        byte fe = feistel(right, key);
        byte temp = (byte) (fe ^ left);
        short out = (short) (((short) (right) << 6) | (short) (temp));
        return out;
    }

    static short decode12(short cipher, int round, short key9) {
        byte key = keyextractor(key9, round);
        cipher = (short) ((((cipher >>> 6) & 0x003F) | ((cipher << 6) & 0xFC00)) & 0x0FFF);
        byte right = (byte) (cipher & 0x3F);
        byte left = (byte) ((BitOperator.urShift(cipher, 6)) & 0x3F);
        byte fe = feistel(right, key);
        byte temp = (byte) (fe ^ left);
        short out = (short) (((short) (right) << 6) | (short) (temp));
        out = (short) ((((out >>> 6) & 0x003F) | ((cipher << 6) & 0xFC00)) & 0x0FFF);
        return cipher;
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
        while (_pos > 11) {
            _pos -= 8;
        }
        byte outB;
        short max = (short) 0xFF00;
        max = (short) BitOperator.urShift(max, 6 + _pos);
        if (_pos <= 2) {
            return (byte) (BitOperator.urShift(max & _key, 2 - _pos));
        } else {
            outB = (byte) (((max & _key) << (_pos - 2)) & 0xFF);
            outB = (byte) (outB | (0xFF & BitOperator.urShift(_key, 11 - _pos)));
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

    public static void main(String[] args) {
        short[] datap = { 0b010000010100,
                0b001001000011,
                0b010001000100,
                0b010101000110 };
        short key = (short) (0b011001011);
        System.out.println(" Plaintext Decoded Plaintext Match");
        for (int i = 0; i < datap.length; ++i) {
            short cipher = encode12(datap[i], 1, key);
            System.out.print(BitOperator.int2binary(datap[i], 12) + " ");
            short plain = decode12(cipher, 1, key);
            System.out.print(BitOperator.int2binary(plain, 12));
            System.out.println(" " + (datap[i] == plain));
        }
        System.out.println();
    }
}
