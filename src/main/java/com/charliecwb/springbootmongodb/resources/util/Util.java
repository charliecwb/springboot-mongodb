package com.charliecwb.springbootmongodb.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class Util {
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

	public static String decryptPassword(String password) throws NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
		SecretKey myDesKey = keygenerator.generateKey();

		// Creating object of Cipher
		Cipher desCipher;
		desCipher = Cipher.getInstance("DES");

		// Creating byte array to store string
		byte[] text = password.getBytes("UTF8");

		// Encrypting text
		desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
		byte[] textEncrypted = desCipher.doFinal(text);

		// Converting encrypted byte array to string
		return new String(textEncrypted);
	}

	public static String encryptPassword(String password) throws NoSuchAlgorithmException, NoSuchPaddingException,
			UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
		SecretKey myDesKey = keygenerator.generateKey();

		// Creating object of Cipher
		Cipher desCipher;
		desCipher = Cipher.getInstance("DES");

		// Creating byte array to store string
		byte[] text = password.getBytes("UTF8");

		// Encrypting text
		desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
		byte[] textEncrypted = desCipher.doFinal(text);

		// Converting encrypted byte array to string
		return new String(textEncrypted);
	}
}
