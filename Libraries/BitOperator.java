package Libraries;

public class BitOperator {
    public static String int2binary(long a, int bits) {
        long q = a;
        long r = 0;
        String result = "";
        for (int i = 0; i < bits; i++) {
            r = q & 0x01;
            result = r + result;
            q = urShift(q, 1);
        }
        return result;
    }

    public static String num2binary(long a, int bits) {
        String out = Long.toBinaryString(a);
        while (out.length() < bits) {
            out = "0" + out;
        }
        return out;
    }

    public static String num2binary(int a, int bits) {
        String out = Integer.toBinaryString(a);
        while (out.length() < bits) {
            out = "0" + out;
        }
        return out;
    }

    public static long urShift(long a, int bits) {
        for (int i = 0; i < bits; i++) {
            a = a >>> 1 & 0x7FFFFFFFFFFFFFFFL;
        }
        return a;
    }

    public static int urShift(int a, int bits) {
        for (int i = 0; i < bits; i++) {
            a = a >>> 1 & 0x7FFFFFFF;
        }
        return a;
    }

    public static short urShift(short a, int bits) {
        for (int i = 0; i < bits; i++) {
            a = (short) (a >>> 1 & 0x7FFF);
        }
        return a;
    }

    public static byte urShift(byte a, int bits) {
        for (int i = 0; i < bits; i++) {
            a = (byte) (a >>> 1 & 0x7F);
        }
        return a;
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

    public static void main(String[] args) {
        byte b = 0x7F;
        short s = 0x7FFF;
        int i = 0x7FFFFFFF;
        long t = 0x7FFFFFFFFFFFFFFFL;
        System.out.println(int2binary(b, 8));
        System.out.println(int2binary(urShift(b, 3), 8));
        System.out.println(int2binary(s, 16));
        System.out.println(int2binary(urShift(s, 3), 16));
        System.out.println(int2binary(i, 32));
        System.out.println(int2binary(urShift(i, 3), 32));
        System.out.println(int2binary(t, 64));
        System.out.println(int2binary(urShift(t, 3), 64));

        // System.out.println(int2binary((byte)(-100),8));
    }
}
