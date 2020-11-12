package br.com.isaccanedo;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

/**
 * @author Isac Canedo
 */

public class EncryptDecryptString {

    private static final String cipherTransformation     = "AES/CBC/PKCS5PADDING";
    private static final String encryptionKey            = "ABCDEFGHIJKLMNOP";
    private static final String aesEncryptionAlgorithem  = "AES";
    private static final String characterEncoding        = "UTF-8";

    /**
     * Método para criptografar dados de uma string
     * @param plainText
     * @return encryptedText
     */

    public static String encrypt(String plainText) {

        String encryptedText = "";
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF-8"));
            Base64.Encoder encoder = Base64.getEncoder();
            encryptedText = encoder.encodeToString(cipherText);
        } catch (Exception E) {
            System.out.println("Exceção na criptografia: "+E.getMessage());
        }
        return encryptedText;
    }

    /**
     * Método para obter o texto criptografado e string fornecida descriptografada
     * @param encryptedText
     * @return decryptedText
     */

    public static String decrypt(String encryptedText) {

        String decryptedText = "";
        try {
            Cipher cipher = Cipher.getInstance(cipherTransformation);
            byte[] key = encryptionKey.getBytes(characterEncoding);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, aesEncryptionAlgorithem);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] cipherText = decoder.decode(encryptedText.getBytes("UTF-8"));
            decryptedText = new String(cipher.doFinal(cipherText), "UTF-8");

        } catch (Exception E) {
            System.err.println("Exceção na descriptografia: "+E.getMessage());
        }
        return decryptedText;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite uma String: ");
        String plainString = sc.nextLine();

        String encryptStr = encrypt(plainString);
        String decryptStr = decrypt(encryptStr);

        System.out.println("String simples          : " + plainString);
        System.out.println("String criptografada    : " + encryptStr);
        System.out.println("String descriptografada : " + decryptStr);

    }

}