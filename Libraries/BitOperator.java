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

    public static void main(String[] args) {
        /* byte b = 0x7F;
        short s = 0x7FFF;
        int i = 0x7FFFFFFF;
        long t = 0x7FFFFFFFFFFFFFFFL;
        System.out.println(int2binary(b, 8));
        System.out.println(int2binary(urShift(b, 3), 8));
        System.out.println(int2binary(urShift(s, 3), 16));
        System.out.println(int2binary(s, 16));
        System.out.println(int2binary(urShift(i, 3), 32));
        System.out.println(int2binary(i, 32));
        System.out.println(int2binary(urShift(t, 3), 64));
        System.out.println(int2binary(t, 64)); */
        System.out.println(int2binary((byte)(-100),8));
    }
}
