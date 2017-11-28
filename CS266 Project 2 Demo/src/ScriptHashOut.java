import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

public class ScriptHashOut {
	
	public static String hashScript() throws Exception{
  InputStream fis =  new FileInputStream("/Users/baopham/Downloads/CS266-Project-2-Demo-master/CS266 Project 2 Demo/src/Script.java");

  byte[] buffer = new byte[1024];
  MessageDigest complete = MessageDigest.getInstance("MD5");
  int numRead;
  do {
   numRead = fis.read(buffer);
   if (numRead > 0) {
     complete.update(buffer, 0, numRead);
     }
   } while (numRead != -1);
  fis.close();
  return getHexString(complete.digest());
}
	
	public static String getHexString(byte[] b) throws Exception {
		  String result = "";
		  for (int i=0; i < b.length; i++) {
		    result +=
		          Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
		  }
		  System.out.println(result);
		  return result;
		}

	public static void main(String args[]) throws Exception {
	 hashScript();
	}
}