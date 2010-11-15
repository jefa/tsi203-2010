package partuzabook.servicioDatos.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptUtils {
	
	public static String encript(String text) throws NoSuchAlgorithmException{
		
        MessageDigest algorithm = null;

        algorithm = MessageDigest.getInstance("MD5");
               
        byte[] defaultBytes = text.getBytes();
        algorithm.reset();
        algorithm.update(defaultBytes);
        byte messageDigest[] = algorithm.digest();
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < messageDigest.length; i++)
        {
            String hex = Integer.toHexString(0xFF & messageDigest[i]);
            if (hex.length() == 1)
            {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();

	}

	public static void main(String[] args) {
		try {
			for(String arg: args)
				System.out.println(CryptUtils.encript(arg));
		} catch (NoSuchAlgorithmException e) {}
	}
}
