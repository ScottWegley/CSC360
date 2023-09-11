package Libraries;

public class BitOperator {
    public static String int2binary(int a, int bits) {
        int q = a;
        int r = 0;
        String result = "";
        for (int i = 0; i < bits; i++) {
            r = q & 0x01;
            result = r + result;
            q = urShift(q, 1);
        }
        return result;
    }

    public static int urShift(int a, int bits) {
        for (int i = 0; i < bits; i++) {
            a = a >>> 1 & 0x7FFFFFFF;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(int2binary(0x00FF, 16));
    }
}
