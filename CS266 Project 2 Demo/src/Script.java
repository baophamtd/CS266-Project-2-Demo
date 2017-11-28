import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Script {
	
	public ArrayList<String> multisign() throws NoSuchAlgorithmException {
		final int keySize = 1024;
		ArrayList<String> pubKeyList = new ArrayList<String>();
		for(int i = 0; i<3; i++) {
	        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
	        keyPairGenerator.initialize(keySize);  
	        String pubKey = keyPairGenerator.generateKeyPair().getPublic().toString();
	        pubKeyList.add(pubKey);
		}
		return null;
	}
	
	public String buildMultisignOut(ArrayList<String> pubKeyList) {
		String multiHashValue = "";
		for (String pubKey: pubKeyList) {
			int hashValue = pubKey.hashCode();
			multiHashValue.concat(Integer.toString(hashValue));
		}
		return multiHashValue;
	}
	
	public boolean evaluate(String priKey) {
		return true;
	}
}