package com.demo.lyl.util;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class AESEncryption {

    //原始报文
    public static final String PLAIN_TEXT = "{\"resultCode\":\"0005\",\"resultMsg\":\"联机交易内部异常\"}";
    // public static final String PLAIN_TEXT ="123abc";
    public static final String CHARACTER = "utf-8";
    private static String PUBLIC_KEY_FILE = "E:/RSA/FWS02001/GYS0000002PublicKey";
    private static String PRIVATE_KEY_FILE = "E:/RSA/FWS02001/GYS0000002PrivateKey";
    public static void main(String[] args) throws Exception {

        /**
         *
         * 一、双方生成 RSA秘钥对，双方交换公钥。
         *     注意：公钥为base64转码后的值。
         * 二、权益管理系统生成 AES堆成秘钥并同步给服务商系统。
         * 三、客户端服务端双方交换报文流程如下：
         *  客户端操作：
         *      1. 原始报文通过AES加密得到报文密文ciphertext。
         *      2. 对报文密文ciphertext进行url编码得的URLciphertext。
         *      3. 通过URLciphertext和私钥rsaPrivateKey得到签名sing_byte，对sing_byte进行URL编码得到url_sing_byte。
         *      4. 报文传输时需要对URLciphertext再进行一次URL编码得到URL2ciphertext，传输报文格式为：
         *       {
         *          "sign":"url2_sing_byte",
         *          "data":"URL2ciphertext",
         *          "commCode":"服务商编码文明"
         *        }
         *  服务端操作：
         *      1. 通过URL解码得到URLciphertext和sing_byte。
         *      2. 通过URLciphertext、sing_byte和公钥验证签名。
         *      2. 验证签名通过，通过AES对ciphertext进行解密.
         */
        /**
         * 生成签名需要使用的RSA公私钥对,密钥对只需要生成一次。
         */
        //RSAUtils.generateKeyBytes();
        /*
         * ----------客户端报文加密和生成sgin过程--------
         */
        //AES加密过程；
        String aesKey = "1234567890$%F456";
        String aesKeyBASE64 = RSAUtils.encryptBASE64(aesKey.getBytes(CHARACTER));
        System.out.println("密钥base64转码后：" + aesKeyBASE64);
        System.out.println("明文:" + PLAIN_TEXT);
        String ciphertext = AESUtils.Encrypt(PLAIN_TEXT, aesKeyBASE64);
        System.out.println("加密后的字串是：" + ciphertext);
        String URLciphertext = URLEncoder.encode(ciphertext);
        // 签名
        String priKey = RSAUtils.getprivatekey();
        RSAPrivateKey rsaPrivateKey = RSAUtils.getRSAPrivateKeyBybase64(RSAUtils.getFormatString(priKey, false, false));
        String sing_byte = RSAUtils.sign(rsaPrivateKey, URLciphertext);
        System.out.println("签名是：" + sing_byte);
        System.out.println("签名URL编码1次的结果是：" + URLEncoder.encode(sing_byte, "utf-8"));
        System.out.println("签名URL编码2次的结果是：" + URLEncoder.encode(URLEncoder.encode(sing_byte, "utf-8"), "utf-8"));
        System.out.println("加密后的字串URL编码1次的结果是：" + URLciphertext);
        System.out.println("加密后的字串URL编码2次的结果是：" + URLEncoder.encode(URLciphertext));
    }
}