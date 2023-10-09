// -- https://medium.com/programmers-blockchain/create-simple-blockchain-java-tutorial-from-scratch-6eeed3cb03fa

package BlockChain;
import java.util.ArrayList;

public class TestBlockChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 3;

	public static void main(String[] args)	
	{		
		// -- add our blocks to the blockchain ArrayList:
		Block block;
		
		System.out.println("Trying to Mine block 0... ");
		// -- create the block, this is the head of the chain (coin base in bitcoin, it has
		//    no predecessor block so the previous hash doesn't matter)
		block = new Block("This is block 0", "0");
		// -- add block to the chain (this will also mine the block)
		addBlock(block);
		
		System.out.println("Trying to Mine block 1... ");
		// -- create the block
		block = new Block("This is block 1", blockchain.get(blockchain.size()-1).hash);
		// -- add block to the chain (this will also mine the block)
		addBlock(block);
		
		System.out.println("Trying to Mine block 2... ");
		// -- create the block
		block = new Block("This is block 2", blockchain.get(blockchain.size()-1).hash);
		// -- add block to the chain (this will also mine the block)
		addBlock(block);
		
		// -- print the block chain and verify its validity (true)
		for (Block b : blockchain) {
			System.out.println(b);
		}
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		System.out.println();
		
		// -- modify then print the block chain and verify its validity (false)
		blockchain.get(1).setHash("012345");
		for (Block b : blockchain) {
			System.out.println(b);
		}
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		System.out.println();
		
	}
	
	public static Boolean isChainValid() {
		Block currentBlock; 
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		// -- loop through blockchain to check hashes:
		for(int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
			
			// -- compare registered hash and calculated hash:
			if(!currentBlock.hash.equals(currentBlock.calculateHash()) ){
				System.out.println("Current Hashes not equal");			
				return false;
			}
			// -- compare previous hash and registered previous hash
			if(!previousBlock.hash.equals(currentBlock.previousHash) ) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			// -- check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
			
		}
		return true;
	}
	
	public static void addBlock(Block newBlock) 
	{
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
	}
}
