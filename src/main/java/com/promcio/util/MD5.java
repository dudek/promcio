package com.promcio.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public final class MD5 {

	 public static final String encodeString(String string) {

			byte[] digest;
			try {
				 digest = MessageDigest.getInstance("MD5").digest(string.getBytes());

				 Formatter formatter = new Formatter();

				 for (final byte digestByte : digest) {
						formatter.format("%02x", digestByte);
				 }

				 return formatter.toString();
			} catch (NoSuchAlgorithmException e) {
				 e.printStackTrace();
			}

			return null;
	 }
}