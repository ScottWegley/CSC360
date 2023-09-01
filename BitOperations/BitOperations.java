package BitOperations;

public class BitOperations {
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

    public static String int2binary(int a, int bits) {
        int q = a;
        int r = 0;
        String result = "";
        for (int i = 0; i < bits; i++) {
            r = q & 0x01;
            result = r + result;
            q = q >>> 1;
        }
        return result;
    }
}
