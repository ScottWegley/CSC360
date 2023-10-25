package SimpleDES;

import Libraries.BitOperator;

class SimpleDES {

    public static final int ROUNDS = 3;

    public static void main(String[] args) {
        
    }

    byte[] encrypt(String pText, short key) {
        short[] arr = preprocess(pText);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < ROUNDS; j++) {
                arr[i] = encode12(arr[i], j, key);
            }
        }
    }

    String decrypt(byte[] cipherText, short key) {

    }

    static short encode12(short plain, int round, short key9) {
        byte key = keyextractor(key9, round);
        byte left = (byte) (0x3F & (plain >>> 6));
        byte right = (byte) (0x3F & plain);
        byte fe = feistel(right, key);
        byte temp = (byte) (fe ^ left);
        short out = (short) (((right) << 6) | (temp));
        return out;
    }

    static short decode12(short cipher, int round, short key9) {
        byte key = keyextractor(key9, round);
        cipher = lrswap(cipher);
        byte right = (byte) (cipher & 0x3F);
        byte left = (byte) ((cipher >>> 6) & 0x3F);
        byte fe = feistel(right, key);
        byte temp = (byte) (fe ^ left);
        short out = (short) (((short) (right) << 6) | (short) (temp));
        out = lrswap(out);
        return out;
    }

    public static short[] preprocess(String s) {
        int _l = s.getBytes().length;
        while (_l % 3 != 0) {
            _l++;
        }
        byte[] input = new byte[_l];
        int i = 0;
        while (i < s.getBytes().length) {
            input[i] = s.getBytes()[i++];
        }
        while (i < _l) {
            input[i++] = 0b0;
        }

        short[] output = new short[_l / 3 * 2];
        int shortIndex = 0;
        for (int j = 0; j < _l / 3; j++) {
            output[shortIndex++] = (short) ((short) (((short) input[j]) << 4) | (short) ((input[j + 1] >>> 4) & 0x0f));
            output[shortIndex++] = (short) (((short) (input[j + 1]) << 8) | input[j + 2]);
        }
        return output;
    }

    public static byte[] postprocess(short[] s) {
        int byteIndex = 0;
        byte[] output = new byte[s.length / 2 * 3];
        for (int i = 0; i < s.length / 2; i++) {
            output[byteIndex++] = (byte) ((s[i] >>> 4) & 0xFF);
            output[byteIndex++] = (byte) (((byte) (s[i] & 0x0F) << 4) | (byte) ((s[i + 1] >>> 8) & 0x0F));
            output[byteIndex++] = (byte) (s[i + 1] & 0xFF);
        }
        return output;
    }

    static short lrswap(short _in) {
        short swap = 0;
        swap = (short) (((_in & 0x003F) << 6 | ((_in & 0x0FC0) >> 6)));
        return swap;
    }

    public static byte feistel(byte R, byte K) {
        R = expander(R);
        R = (byte) (R ^ K);
        K = right(R);
        R = left(R);
        R = S1(R);
        K = S2(K);
        R = (byte) (R << 3 | (K & 0b111));
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
}