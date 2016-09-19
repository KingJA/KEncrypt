package com.kingja.kencrypt;

import org.apache.commons.codec.binary.Base64;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DES {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String enString=encrypt("amigoxie","A1B2C3D4E5F60708");
		System.out.println(enString);
		System.out.println(decrypt(enString,"A1B2C3D4E5F60708"));

	}

	public static String encrypt(String content, String key) {
		byte[] result = null;
		try {
			Key secretKey = getKey(key);
			
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			result = cipher.doFinal(content.getBytes());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Base64.encodeBase64String(result);

	}

	
	public static String decrypt(String content, String key) {
		byte[] result = null;
		try {
			Key secretKey = getKey(key);
			
			Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			result = cipher.doFinal(Base64.decodeBase64(content));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new String(result);

	}
	private static Key getKey(String key) throws InvalidKeyException,
			NoSuchAlgorithmException, InvalidKeySpecException {
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
		SecretKeyFactory secretKeyFactory = SecretKeyFactory
				.getInstance("DES");
		Key secretKey = secretKeyFactory
				.generateSecret(desKeySpec);
		return secretKey;
	}

}
