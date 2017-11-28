import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MerkleP2SH {

	public static void main(String [] args) throws NoSuchAlgorithmException 
	  {
		// Start timer and record memory
		long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
	  	long time1 = System.nanoTime();

	  	// Hash scripts
	  	ScriptHashOut hashedScriptNode1 = new ScriptHashOut();
	  	ScriptHashOut hashedScriptNode2 = new ScriptHashOut();
	  	ScriptHashOut hashedScriptNode3 = new ScriptHashOut();
	  	ScriptHashOut hashedScriptNode4 = new ScriptHashOut();
	  	ScriptHashOut hashedScriptNode5 = new ScriptHashOut();
	  	ScriptHashOut hashedScriptNode6 = new ScriptHashOut();
	  	ScriptHashOut hashedScriptNode7 = new ScriptHashOut();
	  	ScriptHashOut hashedScriptNode8 = new ScriptHashOut();
	  	
	  	// Put into string format
	  	String hashNode1 = hashedScriptNode1.toString();
	  	String hashNode2 = hashedScriptNode2.toString();
	  	String hashNode3 = hashedScriptNode3.toString();
	  	String hashNode4 = hashedScriptNode4.toString();
	  	String hashNode5 = hashedScriptNode5.toString();
	  	String hashNode6 = hashedScriptNode6.toString();
	  	String hashNode7 = hashedScriptNode7.toString();
	  	String hashNode8 = hashedScriptNode8.toString();
	  
	 // Put hashed nodes in list for merkle tree
	    List<String> merkleNodes = new ArrayList<String>();
	    merkleNodes.add(hashNode1);
	    merkleNodes.add(hashNode2);
	    merkleNodes.add(hashNode3);
	    merkleNodes.add(hashNode4);
	    merkleNodes.add(hashNode5);
	    merkleNodes.add(hashNode6);
	    merkleNodes.add(hashNode7);
	    merkleNodes.add(hashNode8);
	    
	    // Create/Initialize Merkle Tree and print out root
	    MerkleTree treeOne = new MerkleTree(merkleNodes);
	    treeOne.mTree();
	    System.out.println("root : " + treeOne.getRoot());
	    System.out.println();
	    
	    // Get time it takes to perform the tests
	    long time2 = System.nanoTime();
	    long timeTaken = time2 - time1;
	    System.out.println("Time Taken: " + timeTaken + " nanoseconds.");
	    System.out.println();
	    
	    // Get memory usage for tests
	    long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
	    long actualMemUsed=afterUsedMem-beforeUsedMem;
	    System.out.println("Memory Used: " + actualMemUsed + " bytes.");
	  }
	  
	  // Get hash value of nodes
	  public static String getHash(String str) {
	      byte[] cipher_byte;
	      try{
	          MessageDigest md = MessageDigest.getInstance("SHA-256");
	          md.update(str.getBytes());
	          cipher_byte = md.digest();
	          StringBuilder sb = new StringBuilder(2 * cipher_byte.length);
	          for(byte b: cipher_byte) {
	            sb.append(String.format("%02x", b&0xff) );
	          }
	          return sb.toString();
	      } catch (Exception e) {
	              e.printStackTrace();
	      }
	      
	      return "";
	  }
	  
	  // Compare merkle tree root and node final hash
	  public static void checkHashRoot (MerkleTree tree, String checkNodeFinal)
	  {
		   if (tree.getRoot().equals(checkNodeFinal))
		    {
		    	System.out.println("Accept the node, the hash and root are equal. Continue.");
		    }
		    else
		    {
		    	System.out.println("Hash and root are not equal. Do not accept.");
		    }
		   
	  }
}
