package DataPreparation;

import Libraries.BitOperator;

public class DataPreparation {

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

    public static void main(String[] args) {
        short[] data12;
        byte[] data8;

        data12 = preprocess("A");
        for (int i = 0; i < data12.length; ++i) {
            System.out.print(BitOperator.int2binary(data12[i], 12) + " ");
        }
        System.out.println();
        data8 = postprocess(data12);
        for (int i = 0; i < data8.length; ++i) {
            System.out.print(BitOperator.int2binary(data8[i], 8) + " ");
        }
        System.out.println();
        System.out.println();

        data12 = preprocess("AB");
        for (int i = 0; i < data12.length; ++i) {
            System.out.print(BitOperator.int2binary(data12[i], 12) + " ");
        }
        System.out.println();
        data8 = postprocess(data12);
        for (int i = 0; i < data8.length; ++i) {
            System.out.print(BitOperator.int2binary(data8[i], 8) + " ");
        }
        System.out.println();
        System.out.println();

        data12 = preprocess("ABC");
        for (int i = 0; i < data12.length; ++i) {
            System.out.print(BitOperator.int2binary(data12[i], 12) + " ");
        }
        System.out.println();
        data8 = postprocess(data12);
        for (int i = 0; i < data8.length; ++i) {
            System.out.print(BitOperator.int2binary(data8[i], 8) + " ");
        }
        System.out.println();
        System.out.println();

        data12 = preprocess("ABCD");
        for (int i = 0; i < data12.length; ++i) {
            System.out.print(BitOperator.int2binary(data12[i], 12) + " ");
        }
        System.out.println();
        data8 = postprocess(data12);
        for (int i = 0; i < data8.length; ++i) {
            System.out.print(BitOperator.int2binary(data8[i], 8) + " ");
        }
        System.out.println();
        System.out.println();

        data12 = preprocess("ABCDEF");
        for (int i = 0; i < data12.length; ++i) {
            System.out.print(BitOperator.int2binary(data12[i], 12) + " ");
        }
        System.out.println();
        data8 = postprocess(data12);
        for (int i = 0; i < data8.length; ++i) {
            System.out.print(BitOperator.int2binary(data8[i], 8) + " ");
        }
        System.out.println();
        System.out.println();

    }
}
