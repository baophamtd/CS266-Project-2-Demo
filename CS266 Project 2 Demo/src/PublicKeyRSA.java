import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class PublicKeyRSA {
	
    public static void main(String [] args) throws Exception 
    {
    	long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
    	long time1 = System.nanoTime();
    	
        KeyPair keyPair = buildKeyPair();
        PublicKey pubKey = keyPair.getPublic();
        PrivateKey prKey = keyPair.getPrivate();
        String baseMessage;
        
        baseMessage = "Sample message to be encrypted.";
        System.out.println(new String(baseMessage));
        System.out.println();
        
        byte [] encryptedMessage = encrypt(prKey, baseMessage);     
        System.out.println(new String(encryptedMessage));
        System.out.println();
        
        byte[] decryptedMessage = decrypt(pubKey, encryptedMessage);                                 
        System.out.println(new String(decryptedMessage));
        System.out.println();
        
        long time2 = System.nanoTime();
        long timeTaken = time2 - time1;
        System.out.println("Time Taken: " + timeTaken + " nanoseconds.");
        System.out.println();
        
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed=afterUsedMem-beforeUsedMem;
        System.out.println("Memory Used: " + actualMemUsed + " bytes.");
        System.out.println();
        System.out.println();
        
        baseMessage = "Second sample message for test.";
        System.out.println(new String(baseMessage));
        System.out.println();
        
        byte [] encryptedMessage2 = encrypt(prKey, baseMessage);     
        System.out.println(new String(encryptedMessage2));
        System.out.println();
        
        byte[] decryptedMessage2 = decrypt(pubKey, encryptedMessage2);                                 
        System.out.println(new String(decryptedMessage2));
        System.out.println();
        
        long time3 = System.nanoTime();
        long timeTaken2 = time3 - time1;
        System.out.println("Time Taken: " + timeTaken2 + " nanoseconds.");
        System.out.println();
        
        long afterUsedMem2=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed2=afterUsedMem2-beforeUsedMem;
        System.out.println("Memory Used: " + actualMemUsed2 + " bytes.");
        System.out.println();
        System.out.println();
        
        baseMessage = "Third and final sample message for this project.";
        System.out.println(new String(baseMessage));
        System.out.println();
        
        byte [] encryptedMessage3 = encrypt(prKey, baseMessage);     
        System.out.println(new String(encryptedMessage3));
        System.out.println();
        
        byte[] decryptedMessage3 = decrypt(pubKey, encryptedMessage3);                                 
        System.out.println(new String(decryptedMessage3));
        System.out.println();
        
        long time4 = System.nanoTime();
        long timeTaken3 = time4 - time1;
        System.out.println("Time Taken: " + timeTaken3 + " nanoseconds.");
        System.out.println();
        
        long afterUsedMem3=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed3=afterUsedMem3-beforeUsedMem;
        System.out.println("Memory Used: " + actualMemUsed3 + " bytes.");

    }

    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException 
    {
        final int keySize = 2048;
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keySize);      
        return keyPairGenerator.genKeyPair();
    }

    public static byte[] encrypt(PrivateKey prKey, String message) throws Exception 
    {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.ENCRYPT_MODE, prKey);  
        return cipher.doFinal(message.getBytes());  
    }
    
    public static byte[] decrypt(PublicKey pubKey, byte [] encrypted) throws Exception 
    {
        Cipher cipher = Cipher.getInstance("RSA");  
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        return cipher.doFinal(encrypted);
    }
    
}