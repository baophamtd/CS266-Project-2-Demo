import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class RunMerkle {

  public static void main(String [] args) 
  {
	long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
  	long time1 = System.nanoTime();
  	
  	String node1 = "a";
  	String node2 = "b";
  	String node3 = "c";
  	String node4 = "d";
  	
  	String sha2HexValueA = getSHA2HexValue(node1);
  	String sha2HexValueB = getSHA2HexValue(node2);
  	String sha2HexValueC = getSHA2HexValue(node3);
  	String sha2HexValueD = getSHA2HexValue(node4);
  	
    List<String> merkleNodes = new ArrayList<String>();
    merkleNodes.add(sha2HexValueA);
    merkleNodes.add(sha2HexValueB);
    merkleNodes.add(sha2HexValueC);
    merkleNodes.add(sha2HexValueD);
     
    MerkleTree treeOne = new MerkleTree(merkleNodes);
    treeOne.mTree();
    System.out.println("root : " + treeOne.getRoot());
    System.out.println();
    
    System.out.println("Same nodes and path Test");
    String checkNode1 = sha2HexValueA;
    String checkNode2 = getSHA2HexValue(sha2HexValueA + sha2HexValueB);
    String checkNode3 = getSHA2HexValue(sha2HexValueC + sha2HexValueD);
    String checkNodeFinal = getSHA2HexValue(checkNode2 + checkNode3);
    System.out.println((checkNodeFinal));
    System.out.println();
    
    checkHashRoot(treeOne, checkNodeFinal);
    System.out.println();
    
    System.out.println("Different node and same path Test");
    String sha2HexValueE = getSHA2HexValue("e");
    String checkChangedNode3 = getSHA2HexValue(sha2HexValueC + sha2HexValueE);
    String checkNodeFinal2 = getSHA2HexValue(checkNode2 + checkChangedNode3);
    System.out.println((checkNodeFinal2));
    System.out.println();
    
    checkHashRoot(treeOne, checkNodeFinal2);
    System.out.println();
    
    long time2 = System.nanoTime();
    long timeTaken = time2 - time1;
    System.out.println("Time Taken: " + timeTaken + " ns.");
    System.out.println();
    
    long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
    long actualMemUsed=afterUsedMem-beforeUsedMem;
    System.out.println("Memory Used: " + actualMemUsed);
  }
  
  public static String getSHA2HexValue(String str) {
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
  
  public static void checkHashRoot (MerkleTree tree, String checkNodeFinal)
  {
	   if (tree.getRoot().equals(checkNodeFinal))
	    {
	    	System.out.println("Accept the node, the has and root are equal. Continue.");
	    }
	    else
	    {
	    	System.out.println("Hash and root are not equal. Do not accept.");
	    }
	   
  }
}