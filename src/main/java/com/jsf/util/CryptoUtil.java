package com.jsf.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

public class CryptoUtil {

	public static String IV = "AAAAAAAAAAAAAAAA";
	public static String chaveEncriptacao = "0123456789abcdef";

	public static String encryptAES(String textopuro) {

		try {
			SecretKeySpec key = new SecretKeySpec(CryptoUtil.chaveEncriptacao.getBytes("UTF-8"), "AES");

			Cipher encripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
			encripta.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));
			final byte[] criptografado = encripta.doFinal(textopuro.getBytes("UTF-8"));

			String textoCriptografado = StringUtils.trim(Base64.encodeBase64String(criptografado));

			return textoCriptografado;
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}

	}

	public static String decryptAES(String textoencriptado) {

		try {
			SecretKeySpec key = new SecretKeySpec(CryptoUtil.chaveEncriptacao.getBytes("UTF-8"), "AES");

			Cipher decripta = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
			decripta.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV.getBytes("UTF-8")));

			String texto = new String(decripta.doFinal(Base64.decodeBase64(textoencriptado)), "UTF-8");

			return texto;
		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}

	}

}
