package BlockChain;

import java.util.Date;

public class Block {
	
	public String hash;         // -- hash of this block
	public String previousHash; // -- hash of previous block in chain
	private String data;        // -- data will be a simple message.
	private long timeStamp;     // -- number of milliseconds since 1/1/1970.
	private int nonce;          // -- used in mining to adjust the hash to meet the required difficulty level
	
	
	public Block(String data, String previousHash ) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		
		// -- once the block is complete, compute the hash
		this.hash = calculateHash(); 
	}
	
	public String toString ()
	{
		String s = "";
		s += "previousHash: " + previousHash + "\n";
		s += "data: " + data + "\n";
		s += "timestamp: " + Long.toString(timeStamp) + "\n";
		s += "hash: " + hash + "\n";
		s += "nonce: " + nonce + "\n";
	
		return s;
	}

	public String getHash()
	{
		return hash;
	}
	
	public void setHash(String h) 
	{
		hash = h;
	}
	
	public String getPreviousHash()
	{
		return previousHash;
	}
	
	// -- Calculate new hash based on blocks contents
	public String calculateHash() {
		// -- the hash is a message digest on the string
		//    previous block hash
		//    time stamp from when block was constructed (above)
		//    the nonce value (obtained by mining the block, below)
		//    the string data
		String calculatedhash = HashFunction.applySha256( 
				previousHash +
				Long.toString(timeStamp) +
				Integer.toString(nonce) + 
				data 
				);
		return calculatedhash;
	}
	
	// -- Increases nonce value until hash target is reached.
	public void mineBlock(int difficulty) {
		String target = HashFunction.getDificultyString(difficulty); // -- Create a string with difficulty * "0" 
		
		// -- modify the value of nonce (increment it) until the front of the block hash matches 
		//    the difficulty string, that is: 
		//       increment nonce in the block
		//       recalculate the hash of the block (with modified nonce)
		//       check to see if the new hash has the proper number (difficulty) of 0's in the front
		
		// -- grab the first part of block hash, difficulty is the length (number of 0's being sought)
		String h = hash.substring( 0, difficulty );
		
		// -- as long as the front of the block hash does not have the proper number of 0's (difficulty)
		while(!h.equals(target)) {
			// -- increment the nonce
			nonce++;
			// -- recalculate the block hash
			hash = calculateHash();
			// -- grab the first part of the block hash, difficulty is the length (number of 0's being sought)
			h = hash.substring( 0, difficulty );
		}
		System.out.println("Block Mined!!! : " + hash + " nonce of " + nonce);
	}
	
}
