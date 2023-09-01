package BitOperations;

public class BitOperationsTest {
	
	public static String int2binary(int b, int nbits)
	{
		int q = b; // -- note that variables are initialized
		int r = 0;
		String result = "";
		for (int i = 0; i < nbits; ++i) {
			r = q & 0x01; // -- isolate least significant bit
			result = r + result; // -- string concatenation
			q = q >>> 1;  // -- shift for next least significant bit
		}
		return result;
	}
	
	public static void main (String[] args)
	{
		byte b = (byte)0b10101111;
		System.out.println(int2binary(b, 8));
		System.out.println(int2binary(BitOperations.left(b), 8));
		System.out.println(int2binary(BitOperations.right(b), 8));
		System.out.println(int2binary(BitOperations.sixbits(b), 8));
		System.out.println(int2binary(BitOperations.lrswap((short)0b1010101011111111), 16));
	}
}
