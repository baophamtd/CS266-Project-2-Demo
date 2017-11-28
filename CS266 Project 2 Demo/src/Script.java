import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Script {
	private ArrayList<String> priKeyList;
	public ArrayList<String> multisign(int numOfNodes) throws NoSuchAlgorithmException {
		final int keySize = 1024;
		priKeyList = new ArrayList<>();
		ArrayList<String> pubKeyList = new ArrayList<String>();
		for(int i = 0; i<numOfNodes; i++) {
	        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
	        keyPairGenerator.initialize(keySize);  
	        String pubKey = keyPairGenerator.generateKeyPair().getPublic().toString();
	        pubKeyList.add(pubKey);
	        String priKey = keyPairGenerator.generateKeyPair().getPrivate().toString();
	        priKeyList.add(priKey);
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
		if(priKeyList.contains(priKey)) {
			return true;
		}else return false;
	}
}