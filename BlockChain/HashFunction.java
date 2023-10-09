package BlockChain;
import java.security.MessageDigest;


public class HashFunction {
	
	// -- Applies Sha256 to a string and returns the result. 
	public static String applySha256(String input){		
		try {
			// -- construct a message digest with the SHA-256 protocol
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			// -- apply the digest to the input string
			byte[] hash = digest.digest(input.getBytes("UTF-8"));

			/// -- build a string out of the hex digits from the digest
			String hexString = "";
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if (hex.length() == 1) hexString += '0';
				hexString += hex;
			}
			return hexString;
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}	
	
	// -- Returns difficulty string target, to compare to hash. eg difficulty of 5 will return "00000"  
	public static String getDificultyString(int difficulty) {
		return new String(new char[difficulty]).replace('\0', '0');
	}
	
	
}
