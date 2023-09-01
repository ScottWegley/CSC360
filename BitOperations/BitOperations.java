package BitOperations;

public class BitOperations {
    public static byte left(byte _b) {
        return (byte) ((_b >>> 4) & 0b00001111);
    }

    public static byte right(byte _b) {
        return (byte) (_b & 0b00001111);
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
