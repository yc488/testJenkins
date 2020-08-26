package com.yc.articlemongon;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDhSzPPnFn41iaz+t4tI4kbaXNu\n" +
                "NFOsI8hFeCYtlwPFKRbETHbBS10bMvUbOWLFtRgZV3L924GQ9orbomEmJ1nWyaSO\n" +
                "8iBbZAyiWUP5PJJh/b9kHj1MMwG712bGfYYPdjkRprNpzU9w4UBzUMKKUoHU4c/G\n" +
                "bb4XeBK9LNTPWQL4YwIDAQAB";
        String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOFLM8+cWfjWJrP6\n" +
                "3i0jiRtpc240U6wjyEV4Ji2XA8UpFsRMdsFLXRsy9Rs5YsW1GBlXcv3bgZD2itui\n" +
                "YSYnWdbJpI7yIFtkDKJZQ/k8kmH9v2QePUwzAbvXZsZ9hg92ORGms2nNT3DhQHNQ\n" +
                "wopSgdThz8Ztvhd4Er0s1M9ZAvhjAgMBAAECgYEAxwNLTUXsJGfn4Gzm/jC52MEZ\n" +
                "+mu2zgT90IAGGZeg+PUG63gwHyeXo4MsCVRz7/m8xAX/ykew+IEQwFt8Pdvc+rrs\n" +
                "5yml4gOBPfhpau5QaI75xNjnyH7UA3mbRCZeyZrvuKqtY/f8pCgzy3EBWnRpkcsq\n" +
                "eE6bsOQrD45mltr+0QECQQDynvhKEh+hD5xBpF/DIP8Fp6fizexHdA6+aZT/gLaF\n" +
                "A4XgZ9HEDDBhvNdadyYUNOLWhkxRHv6CkT5azfLXsJEhAkEA7begtbBCDXDf1+DR\n" +
                "h3j2S8zcv6+utYgcpjvxZqjbPi6UIWXLxI80PIwQ0uouHCUMjikBA6VX9vTbw9TZ\n" +
                "/IelAwJBAKI3W7baiz86mrTg3A4w/5GeWQexuurDVCBHo5F5U493nYk+oOe9ZpPS\n" +
                "mQIpa9JS0d+xB1GtsWlHBzPbQySnL0ECQA/btCjqvT1QTl6EbPXwp92eqQtQmQMb\n" +
                "NW4RiaUjlpyrVs5zkAho1T9EyMqJPNI71n6VVa/8k8WxyAdkZ7ZlBikCQEkNe1+s\n" +
                "AKnh+AFGCJ+6WAq1J2RuIgcA6bVL3ip7F2NHdE+N+tR9JqWw3JNCweWmAlzKIGs6\n" +
                "eKSVD5egzKaLXss=";
        try {

            //获取密钥
            Map<String, String> rsaKey = getKeyPair();
            System.out.println("公钥："+rsaKey.get("publicKey"));
            System.out.println("私钥："+rsaKey.get("privateKey"));
            String key = "3c777519-4f95-438d-9168-8a388457f000|2020-8-14 13:37:20";
            String encrypt = encrypt(key, publicKey);
            System.out.println("加密后："+encrypt);

            String s = decrypt(encrypt, privateKey);
            System.out.println("解密后："+s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 随机生成密钥对
     * @throws NoSuchAlgorithmException
     */
    public static Map<String,String> getKeyPair() throws NoSuchAlgorithmException {
        HashMap<String,String> map = new HashMap<>();
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        map.put("publicKey",publicKeyString);
        map.put("privateKey",privateKeyString);
        return map;
    }

    /**
     * RSA公钥加密
     *
     * @param str  加密字符串
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
     private static String encrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return Base64.encodeBase64String(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * RSA私钥解密
     * @param str 加密字符串
     * @param privateKey   私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    private static String decrypt(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes(StandardCharsets.UTF_8));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }
}
