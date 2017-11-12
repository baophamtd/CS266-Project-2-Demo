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
   
        
        baseMessage = "Sample message to be encrypted,";
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
        System.out.println("Time Taken: " + timeTaken + " ns.");
        System.out.println();
        
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        long actualMemUsed=afterUsedMem-beforeUsedMem;
        System.out.println("Memory Used: " + actualMemUsed);

    }

    public static KeyPair buildKeyPair() throws NoSuchAlgorithmException 
    {
        final int keySize = 1024;
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