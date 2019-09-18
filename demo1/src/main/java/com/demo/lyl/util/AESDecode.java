package com.demo.lyl.util;

import java.net.URLDecoder;
import java.security.interfaces.RSAPublicKey;

public class AESDecode {

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
//        // -------------------服务端验SGIN和解密过程-------------
        String aesKey = "1234567890$%F456";
        String aesKeyBASE64 = RSAUtils.encryptBASE64(aesKey.getBytes(CHARACTER));
        String url_sing_byte = "lJAxm7v0YMKSnecUaZTvExRUnNpnS7t9TLDXJUcch%2BiFJqLFWJYl8vTTKY6Dxoc40AwI%2FjfWxSBP%0D%0AC5gnEPPNNjwKLmmv%2FWQAkJCVk5aZ8lZNocdyBT5QtHQwJc14gF2yDezB8Ubs%2BDUSxzvqA2JpxBD0%0D%0ACahpD8oouPa54ZtF7u3l9bd5jA9tDcXAGm6JR7AynTAgfYli7sa33U6J%2FNMKMhu2hgjlIfAFsRiL%0D%0AUlksCPMmEVA4UFAbEIUTMW0oM7WROf%2FqQnQpB1r1QF5GTpmTfJ0VXTSy7IGt00Whh6HCdT4jpOK1%0D%0A3zLtLFAqupzJeQxaMdbFfxXFY643%2FRstqJTANQ%3D%3D%0D%0A";
//        url_sing_byte = URLDecoder.decode(url_sing_byte,"utf-8");
        String URLciphertext = "6kFHqF%2BdjjapheAQ42Hum7kR558NriJEIuV8ISRtiKRN%2FHCOUolfmvzOlGq2p5mrehGfMc07JPef%0D%0A2wSSFFm2Jg%3D%3D%0D%0A";
        URLciphertext = URLDecoder.decode(URLciphertext,"utf-8");
//        URLDecoder.decode(URLciphertext,"utf-8");
         //获得公钥
        String pubKey = RSAUtils.getpublickey();
        RSAPublicKey rsaPublicKey = RSAUtils.getRSAPublidKeyBybase64(RSAUtils.getFormatString(pubKey,true,false));
        String sing_byte = URLDecoder.decode(url_sing_byte,"utf-8");
        //验签
        RSAUtils.verifySign(rsaPublicKey, URLciphertext,sing_byte);
        //解密
        String decrypt = AESUtils.Decrypt(URLciphertext, aesKeyBASE64);
        System.out.println("解密后：" + decrypt);
        System.out.println("原文是：" + PLAIN_TEXT);

    }
}