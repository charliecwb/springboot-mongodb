package com.charliecwb.springbootmongodb.resources.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Cipher;

public class Util {
	public static final String ALGORITHM = "RSA";
	public static final String PATH_PRIVATE_KEY = "./keys/private.key";
	public static final String PATH_PUBLIC_KEY = "./keys/public.key";

	public static String decodeParam(String param) {
		try {
			return URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static Date convertDate(String textDate, Date defaultDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return sdf.parse(textDate);
		} catch (ParseException e) {
			return defaultDate;
		}
	}
	
	private static PublicKey getPublicKey() throws ClassNotFoundException, IOException {
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATH_PUBLIC_KEY));
	    return (PublicKey) inputStream.readObject();
	}
	
	private static PrivateKey getPrivateKey() throws IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATH_PRIVATE_KEY));
		return (PrivateKey) inputStream.readObject();
	}
	
	public static String decryptPassword(String password) {
	    byte[] decryptedText = null;

	    try {
	      Cipher cipher = Cipher.getInstance(ALGORITHM);
	      cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());
	      decryptedText = cipher.doFinal(Base64.getDecoder().decode(password));

	    } catch (Exception ex) {
	      ex.printStackTrace();
	    }

	    return new String(decryptedText);
	}

	public static String encryptPassword(String password) {
	    byte[] textEncrypted = null;

	    try {
	      Cipher cipher = Cipher.getInstance(ALGORITHM);
	      cipher.init(Cipher.ENCRYPT_MODE, getPublicKey());
	      textEncrypted = cipher.doFinal(password.getBytes());
	    } catch (Exception e) {
	      e.printStackTrace();
	    }

		return Base64.getEncoder().encodeToString(textEncrypted);
	}
}
