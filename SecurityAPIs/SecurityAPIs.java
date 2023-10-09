// -- http://www.stellarbuild.com/blog/article/encrypting-and-decrypting-large-data-using-java-and-rsa

package SecurityAPIs;

import javax.crypto.*;
import java.security.*;


public class SecurityAPIs {

	public static void main(String[] args) 
	{
		FindProviders();
		MessageDigest();
		DigitalSignature();
		DESEncryption();
		AESEncryption();
		RSAEncryption();
		KeyExchange("thisisthekey");
	}
	
	public static void FindProviders ()
	{
		/*
		 * Security providers -- Since Java is designed to be object-code portable you must check your system to
		 *                       make sure an appropriate security provider is available
		 */
		System.out.println("Available Security Providers");
		Provider providers[] = Security.getProviders();
		System.out.println(providers.length + " Providers available");
		for (Provider p : providers ) {
			System.out.println("\t" + p.getName() + "  " + p.getVersion());
		}
		System.out.println("\n");		
	}
	
	/*
	 * --------------------------------------------------------------------- 
	 * Message Digests -- a message digest is a hash function that converts a block of text
	 *                    to a [nearly] unique number (sequence of numbers). It is not reversible.
	 * ---------------------------------------------------------------------
	 */
	public static void MessageDigest() {
		System.out.println("MD5 and SHA-1 message digesting");
		try {
			// -- create various types of message digest -- 3 different [at least] schemes are available
			MessageDigest md = MessageDigest.getInstance("MD5");
			MessageDigest sha_1 = MessageDigest.getInstance("SHA-1");
			MessageDigest sha_256 = MessageDigest.getInstance("SHA-256");

			// -- create a message
			String messageString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
			byte message[] = messageString.getBytes();

			// -- insert the message into the MessageDigest object
			md.update(message);
			sha_1.update(message);
			sha_256.update(message);

			// -- get the computed digest for the message
			byte mdDigest[] = md.digest();
			byte sha_1Digest[] = sha_1.digest();
			byte sha_256Digest[] = sha_256.digest();

			// -- print out the various digests
			System.out.println("The MD5 digest for \"ABCDEFGHIJKLMNOPQRSTUVWXYZ\"");
			for (byte b : mdDigest) {
				System.out.print(b + " ");
			}
			System.out.println();

			System.out.println("The SHA-1 digest for \"ABCDEFGHIJKLMNOPQRSTUVWXYZ\"");
			for (byte b : sha_1Digest) {
				System.out.print(b + " ");
			}
			System.out.println();

			System.out.println("The SHA-256 digest for \"ABCDEFGHIJKLMNOPQRSTUVWXYZ\"");
			for (byte b : sha_256Digest) {
				System.out.print(b + " ");
			}
			System.out.println();

			// -- change the message by a single character "A" -> "a" to see how the digest
			//    changes
			messageString = "aBCDEFGHIJKLMNOPQRSTUVWXYZ"; 
			message = messageString.getBytes();
			
			// -- send the message to the MessageDigest object
			md.update(message);
			sha_1.update(message);
			sha_256.update(message);

			// -- get the computed digest
			mdDigest = md.digest();
			sha_1Digest = sha_1.digest();
			sha_256Digest = sha_256.digest();

			System.out.println("The MD5 digest for \"aBCDEFGHIJKLMNOPQRSTUVWXYZ\"");
			for (byte b : mdDigest) {
				System.out.print(b + " ");
			}
			System.out.println();

			System.out.println("The SHA-1 digest for \"aBCDEFGHIJKLMNOPQRSTUVWXYZ\"");
			for (byte b : sha_1Digest) {
				System.out.print(b + " ");
			}
			System.out.println();

			System.out.println("The SHA-256 digest for \"aBCDEFGHIJKLMNOPQRSTUVWXYZ\"");
			for (byte b : sha_256Digest) {
				System.out.print(b + " ");
			}
			System.out.println();

			System.out.println("\n\n");

		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * --------------------------------------------------------------------- 
	 * Digital Signature -- used to guarantee that a message from sender to receiver
	 *                      has not been altered -- enforces Integrity
	 * ---------------------------------------------------------------------
	 */
	public static void DigitalSignature() {
		try {
			// -- signatures us asymmetric crytography and a message digest
			//    various combinations are available in the providers
			System.out.println("Digital signature MD5 with RSA");
			
			// -- instantiate a Signature object for signing
			//    if given a Signature object you can find out what algorithm
			//    it implements 
			Signature signature = Signature.getInstance("MD5withRSA");
			System.out.println(signature.getAlgorithm());

			// -- create data to be signed
			String messageString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
			byte signMessage[] = messageString.getBytes();

			// -- generate a pair of keys (public/private) for RSA
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
			KeyPair keypair = keygen.generateKeyPair();
			PrivateKey privatekey = keypair.getPrivate(); // -- get the private key
			PublicKey publickey = keypair.getPublic(); // -- get the public key

			// -- initialize the signature object with the private key
			signature.initSign(privatekey);

			// -- add the data to the signature
			signature.update(signMessage);

			// -- sign the data (with the private key) and print the signature
			byte signedMessage[] = signature.sign();
			System.out.println("signed message: " + "(" + signedMessage.length + ")");
			for (byte b : signedMessage) {
				System.out.print(b + " ");
			}
			System.out.println();

			// -- verify the signature using the public key
			// -- instantiate a Signature object for verifying the signature
			Signature signatureVerify = Signature.getInstance(signature.getAlgorithm());

			// -- initialize the signature object with the public key
			signatureVerify.initVerify(publickey);
			// -- add the data to the signature
			signatureVerify.update(signMessage);

			// -- verify the signature
			boolean verify = signatureVerify.verify(signedMessage);
			System.out.println("Signature is verified: " + verify);
			System.out.println();
			System.out.println();
		} 
		catch (SignatureException e) {
			e.printStackTrace();
		} 
		catch (InvalidKeyException e) {
			e.printStackTrace();
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * ---------------------------------------------------------------------
	 * Symmetric (private key) encryption with DES
	 * ---------------------------------------------------------------------
	 */
	public static void DESEncryption() {
		try {
			System.out.println("DES private key encrypt/decrypt");
			KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
			SecretKey secretkey = keygenerator.generateKey();

			// -- create an encryption object based on the secret key
			//    (Cipher Factory method)
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, secretkey);

			// -- create/display the original message
			byte messagetext[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes();
			System.out.print(" Plaintext(" + messagetext.length + "): ");
			for (byte b : messagetext) {
				System.out.print(b + " ");
			}
			System.out.println();

			// -- encrypt the message 
			//    message is padded to next factor of 8
			byte ciphertext[] = cipher.doFinal(messagetext);

			// -- display the encrypted message
			System.out.print("Ciphertext(" + ciphertext.length + "): ");
			for (byte b : ciphertext) {
				System.out.print(b + " ");
			}
			System.out.println();

			// -- create a decryption object based on the secret key
			cipher.init(Cipher.DECRYPT_MODE, secretkey);

			// -- decrypt the ciphertext
			byte plaintext[] = cipher.doFinal(ciphertext);

			// -- display the plaintext
			System.out.print(" Plaintext(" + plaintext.length + "): ");
			for (byte b : plaintext) {
				System.out.print(b + " ");
			}
			System.out.println("\n");
		} 
		catch (BadPaddingException e) {
			e.printStackTrace();
		} 
		catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} 
		catch (InvalidKeyException e) {
			e.printStackTrace();
		} 
		catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * ---------------------------------------------------------------------
	 * Symmetric (private key) encryption with AES
	 * ---------------------------------------------------------------------
	 */
	public static void AESEncryption() {
		try {
			System.out.println("AES private key encrypt/decrypt");
			KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
			SecretKey secretkey = keygenerator.generateKey();

			// -- create an encryption object based on the secret key
			//    (Cipher Factory method)
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretkey);

			// -- create/display the original message
			byte messagetext[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes();
			System.out.print(" Plaintext(" + messagetext.length + "): ");
			for (byte b : messagetext) {
				System.out.print(b + " ");
			}
			System.out.println();

			// -- encrypt the message
			//    message is padded to next factor of 16
			byte ciphertext[] = cipher.doFinal(messagetext);

			// -- display the encrypted message
			System.out.print("Ciphertext(" + ciphertext.length + "): ");
			for (byte b : ciphertext) {
				System.out.print(b + " ");
			}
			System.out.println();

			// -- create a decryption object based on the secret key
			cipher.init(Cipher.DECRYPT_MODE, secretkey);

			// -- decrypt the ciphertext
			byte plaintext[] = cipher.doFinal(ciphertext);

			// -- display the plaintext
			System.out.print(" Plaintext(" + plaintext.length + "): ");
			for (byte b : plaintext) {
				System.out.print(b + " ");
			}
			System.out.println("\n");
		} 
		catch (BadPaddingException e) {
			e.printStackTrace();
		} 
		catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} 
		catch (InvalidKeyException e) {
			e.printStackTrace();
		} 
		catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} 
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * ---------------------------------------------------------------------
	 * Asymmetric (public key) encryption with RSA
	 * ---------------------------------------------------------------------
	 */
	public static void RSAEncryption() {
		try {
			System.out.println("RSA private key encrypt, public key decrypt");
			// -- generate a pair of asymmetric keys
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
			KeyPair keypair = keygen.generateKeyPair();
			PrivateKey privatekey = keypair.getPrivate(); // -- get the private key
			PublicKey publickey = keypair.getPublic(); // -- get the public key

			// -- create an encryption object based on the secret key
			//    (Cipher Factory method)
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, privatekey);

			// -- create/display the original message
			byte messagetext[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes();
			System.out.print(" Plaintext(" + messagetext.length + "): ");
			for (byte b : messagetext) {
				System.out.print(b + " ");
			}
			System.out.println();

			// -- encrypt the message
			//    message is padded to next factor of 256
			byte ciphertext[] = cipher.doFinal(messagetext);

			// -- display the encrypted message
			System.out.print("Ciphertext(" + ciphertext.length + "): ");
			for (byte b : ciphertext) {
				System.out.print(b + " ");
			}
			System.out.println();

			// -- create a decryption object based on the public key
			cipher.init(Cipher.DECRYPT_MODE, publickey);

			// -- decrypt the ciphertext
			byte plaintext[] = cipher.doFinal(ciphertext);

			// -- display the plaintext
			System.out.print(" Plaintext(" + plaintext.length + "): ");
			for (byte b : plaintext) {
				System.out.print(b + " ");
			}
			System.out.println("\n");


			
			System.out.println("RSA public key encrypt, private key decrypt");

			// -- create an encryption object based on the public key
			//    (keys generated above)
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publickey);

			// -- create/display the original message
			messagetext = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes();
			System.out.print(" Plaintext(" + messagetext.length + "): ");
			for (byte b : messagetext) {
				System.out.print(b + " ");
			}
			System.out.println();

			// -- encrypt the message
			ciphertext = cipher.doFinal(messagetext);

			// -- display the encrypted message
			System.out.print("Ciphertext(" + ciphertext.length + "): ");
			for (byte b : ciphertext) {
				System.out.print(b + " ");
			}
			System.out.println();

			// -- create a decryption object based on the secret key
			cipher.init(Cipher.DECRYPT_MODE, privatekey);

			// -- decrypt the ciphertext
			plaintext = cipher.doFinal(ciphertext);

			// -- display the plaintext
			System.out.print(" Plaintext(" + plaintext.length + "): ");
			for (byte b : plaintext) {
				System.out.print(b + " ");
			}
			System.out.println("\n");

		} 
		catch (Exception e) {
			System.out.println("exception");

		}
	}
	
	public static void KeyExchange(String keytoexchange)
	{

		try {
			System.out.println("RSA private key encrypt, public key decrypt");
			// -- generate a pair of asymmetric keys for the Sender
			KeyPairGenerator keygenSender = KeyPairGenerator.getInstance("RSA");
			KeyPair keypairSender = keygenSender.generateKeyPair();
			PrivateKey privatekeySender = keypairSender.getPrivate(); // -- get the private key for Sender
			PublicKey publickeySender = keypairSender.getPublic(); // -- get the public key for Sender
	
			// -- generate a pair of asymmetric keys for the Receiver
			KeyPairGenerator keygenReceiver = KeyPairGenerator.getInstance("RSA");
			KeyPair keypairReceiver = keygenReceiver.generateKeyPair();
			PrivateKey privatekeyReceiver = keypairReceiver.getPrivate(); // -- get the private key for Sender
			PublicKey publickeyReceiver = keypairReceiver.getPublic(); // -- get the public key for Sender
	
			
			// -- create an encryption object based on the secret key
			//    (Cipher Factory method)
			Cipher cipher0 = Cipher.getInstance("RSA");
			cipher0.init(Cipher.ENCRYPT_MODE, privatekeySender);
	
			// -- create/display the original message
			byte messagetext[] = "keytoexchange".getBytes();
	
			// -- encrypt the message with the Sender private key
			//    message is padded to next factor of 256
			byte ciphertext[] = cipher0.doFinal(messagetext);
			
			// -- encrypt with the Receiver public key
			//    RSA encrypt returns 256 bytes but, being a block cipher,
			//    will only accept 128 bytes so split it into two messages
			byte splitcipher[][] = new byte[2][128];
			for (int i = 0; i < 2; ++i) {
				for (int j = 0; j < 128; ++j) {
					splitcipher[i][j] = ciphertext[i*128 + j];
				}
			}
			Cipher cipher1 = Cipher.getInstance("RSA");
			cipher1.init(Cipher.ENCRYPT_MODE, publickeyReceiver);
			byte exchangetext[][] = new byte[2][0];
			exchangetext[0] = cipher1.doFinal(splitcipher[0]);
			exchangetext[1] = cipher1.doFinal(splitcipher[1]);
			
			
	
			// -- decrypt the ciphertext with the Receiver private key
			//    two part as described above
			Cipher cipher2 = Cipher.getInstance("RSA");
			cipher2.init(Cipher.DECRYPT_MODE, privatekeyReceiver);
	
			byte plaintext[][] = new byte[2][0];
			plaintext[0] = cipher2.doFinal(exchangetext[0]);
			plaintext[1] = cipher2.doFinal(exchangetext[1]);

			// -- combine the two decryptions into a single text for final decryption
			byte ptext[] = new byte[2 * plaintext[0].length];
			for (int i = 0; i < 2; ++i) {
				for (int j = 0; j < plaintext[i].length; ++j) {
					ptext[i*plaintext[0].length + j] = plaintext[i][j];
				}
			}
			
			// -- decrypt with Sender public key
			Cipher cipher3 = Cipher.getInstance("RSA");
			cipher3.init(Cipher.DECRYPT_MODE, publickeySender);
			byte exchangedtext[] = cipher3.doFinal(ptext);
		
	
			// -- display the plaintext
			System.out.print(" Plaintext(" + exchangedtext.length + "): ");
			for (byte b : exchangedtext) {
				System.out.print((char)b);
			}
			System.out.println("\n");
	
		}
		catch (NoSuchPaddingException e) {
			System.out.println("No such padding");
		}
		catch (IllegalBlockSizeException e) {
			System.out.println("Illegal block size");
		}
		catch (BadPaddingException e) {
			System.out.println("Bad padding");
		}
		catch (InvalidKeyException e) {
			System.out.println("Invalid key");
		}
		catch (NoSuchAlgorithmException e) {
			System.out.println("RSA provider not found");
		}
		
	}
}
