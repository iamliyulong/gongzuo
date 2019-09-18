package com.demo.lyl.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";
    public static final int KEY_SIZE = 2048;
    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    public static final String ENCODE_ALGORITHM = "SHA-256";
    /**
     * 乐牙    FWS02001	GYS0000001PublicKey	GYS0000001PrivateKey
     */
//   private static String PUBLIC_KEY_FILE = "E:\\RSA\\FWS02001\\GYS0000001PublicKey";
//    private static String PRIVATE_KEY_FILE = "E:\\RSA\\FWS02001\\GYS0000001PrivateKey";
    /**
     * 至尊租车 FWS01001	GYS0000002PublicKey	GYS0000002PrivateKey
     */
    private static String PUBLIC_KEY_FILE = "D:\\data\\GYS0000002PublicKey";
    private static String PRIVATE_KEY_FILE = "D:\\data\\GYS0000002PrivateKey";
    /**
     * 龙腾出行 FWS01002	GYS0000003PublicKey	GYS0000003PrivateKey
     */
//    private static String PUBLIC_KEY_FILE = "E:\\RSA\\FWS01002\\GYS0000003PublicKey";
//    private static String PRIVATE_KEY_FILE = "E:\\RSA\\FWS01002\\GYS0000003PrivateKey";
    /*private static String PUBLIC_KEY_FILE = CFGHolder.getString("Third_Public_Key");
    *//**
     * 指定私钥存放文件
     *//*
    private static String PRIVATE_KEY_FILE = CFGHolder.getString("Vasp_Private_key");*/
    public static final String CHARACTER = "utf-8";

    /**
     * 生成密钥对
     *
     * @return
     */
    public static void generateKeyBytes() {
        FileWriter PUBLIC_KEY_Write = null;
        FileWriter PRIVATE_KEY_Write = null;
        try {
            //返回生成指定算法的 public/private 密钥对的 KeyPairGenerator 对象
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            //初始化确定密钥大小的密钥对生成器，使用默认的参数集合，并使用以最高优先级安装的提供者的 SecureRandom 实现作为随机源。
            keyPairGenerator.initialize(KEY_SIZE);
            //生成一个密钥对。
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
           // encryptBASE64(privateKey.getEncoded());
            String publicKeyStr =  encryptBASE64(publicKey.getEncoded());
            String privateKeyStr =  encryptBASE64(privateKey.getEncoded());
            System.out.println("publicKeyStr:"+publicKeyStr);
            System.out.println("privateKeyStr:"+privateKeyStr);
            /*
             * 用对象流将生成的密钥写入文件
             */
            PUBLIC_KEY_Write = new FileWriter(PUBLIC_KEY_FILE,false);
            PUBLIC_KEY_Write.write(publicKeyStr);
            PRIVATE_KEY_Write = new FileWriter(PRIVATE_KEY_FILE,false);
            PRIVATE_KEY_Write.write(privateKeyStr);
           /* oos1 = new ObjectOutputStream(
                    new FileOutputStream(PUBLIC_KEY_FILE));
            oos2 = new ObjectOutputStream(
                    new FileOutputStream(PRIVATE_KEY_FILE));
            oos1.writeChars();
            oos2.writeChars(encryptBASE64(privateKey.getEncoded()));
            //oos1.writeObject(publicKey);
            //oos2.writeObject(privateKey);
            *//** 清空缓存，关闭文件输出流 */

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // return null;
        catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (PRIVATE_KEY_Write != null){
                try {
                    PRIVATE_KEY_Write.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (PUBLIC_KEY_Write != null){
                try {
                    PUBLIC_KEY_Write.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 将文件中的私钥对象读出
     */
//    public static RSAPrivateKey getprivatekey(String vaspPrivateKey) throws Exception {
//        FileInputStream fileInputStream = new FileInputStream(PRIVATE_KEY_FILE + vaspPrivateKey);
//        ObjectInputStream ois = new ObjectInputStream(
//                new FileInputStream(PRIVATE_KEY_FILE + vaspPrivateKey));
//        RSAPrivateKey privatekey = (RSAPrivateKey) ois.readObject();
//        if (ois != null) {
//            ois.close();
//        }
//        if (fileInputStream != null) {
//            fileInputStream.close();
//        }
//        return privatekey;
//    }

    /** 将文件中的私钥对象读出 */
    public static String  getprivatekey() throws Exception
    {
        return readFileByLines(PRIVATE_KEY_FILE);

    }

    /**
     * 将文件中的公钥对象读出
     */
//    public static RSAPublicKey getpublickey(String publicKey) throws Exception {
//        FileInputStream fileInputStream = new FileInputStream(PUBLIC_KEY_FILE + publicKey);
//        ObjectInputStream ois = new ObjectInputStream(fileInputStream);
//        RSAPublicKey publickey = (RSAPublicKey) ois.readObject();
//        if (ois != null) {
//            ois.close();
//        }
//        if (fileInputStream != null) {
//            fileInputStream.close();
//        }
//        return publickey;
//    }

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);

//        return bASE64StrsubString((new BASE64Encoder()).encodeBuffer(key),2);

    }

    /**
     * 去掉字符串尾部空行
     * @param aesKeyBASE64
     * @param i
     * @return
     */
    public static String bASE64StrsubString(String aesKeyBASE64, int i) {
        if(aesKeyBASE64 != null && aesKeyBASE64.length()>2){
            if(aesKeyBASE64.endsWith("\n")){
                aesKeyBASE64 = aesKeyBASE64.substring(0,aesKeyBASE64.length()-i);
            }
        }
        return aesKeyBASE64;
    }

    /**
     * 签名
     *
     * @param privateKey 私钥
     * @param plain_text 明文
     * @return
     * @throws Exception
     */
    public static String sign(PrivateKey privateKey, String plain_text)
            throws Exception {
        byte[] outputDigest_sign = plain_text.getBytes(CHARACTER);
        //初始化实现SHA256withRSA签名算法的 Signature 对象。
        Signature Sign = Signature.getInstance(SIGNATURE_ALGORITHM);
        //使用私钥初始化Signature对象
        Sign.initSign(privateKey);
        Sign.update(outputDigest_sign);
        byte[] signed = Sign.sign();
        return encryptBASE64(signed);
//        return RSAUtils.bASE64StrsubString(encryptBASE64(signed),2);

    }

    /**
     * 验签
     *
     * @param publicKey  公钥
     * @param plain_text 明文
     * @param signed     签名
     * @throws Exception
     */
    public static boolean verifySign(PublicKey publicKey, String plain_text,
                                     String signed) throws Exception {
        byte[] decryptBASE64 = decryptBASE64(signed);
        boolean SignedSuccess = false;
        byte[] outputDigest_verify = plain_text.getBytes(CHARACTER);
        Signature verifySign = Signature.getInstance(SIGNATURE_ALGORITHM);
        verifySign.initVerify(publicKey);
        verifySign.update(outputDigest_verify);
        SignedSuccess = verifySign.verify(decryptBASE64);
        System.out.println("Verification signature ？---" + SignedSuccess);
        return SignedSuccess;
    }

    /**
     * 对密钥对字符串进行格式化
     * @param key 密钥对字符串
     * @param isPubKey 密钥类型。true：公钥，false：私钥
     * @return
     * @author wp Jul 4, 2017 12:04:25 PM
     */
    public static String getFormatString(String key, boolean isPubKey, boolean prefix){
        StringBuilder sb = new StringBuilder();
        if(key != null && key.length() >0){
            if(prefix){
                sb.append(isPubKey ? "-----BEGIN PUBLIC KEY-----\n" : "-----BEGIN RSA PRIVATE KEY-----\n");
            }
            int num = key.length()%64 == 0 ? key.length()/64 : key.length()/64 + 1;
            for(int i = 0; i<num; i++){
                if(i == num-1){
                    sb.append(key.substring(i*64, key.length())+"\n");
                }else{
                    sb.append(key.substring(i*64, (i+1)*64)+"\n");
                }
            }
            if(prefix){
                sb.append(isPubKey ? "-----END PUBLIC KEY-----" : "-----END RSA PRIVATE KEY-----");
            }
            return sb.toString();
        }else{
            return null;
        }
    }
    public static RSAPrivateKey getRSAPrivateKeyBybase64(String base64s) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(AESUtils.decryptBASE64(base64s));
        // PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(base64s.getBytes());
        RSAPrivateKey privateKey = null;
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        try {
            privateKey = (RSAPrivateKey)keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException var4) {
            var4.printStackTrace();
            System.out.println("base64编码=" + base64s + "转RSA私钥失败"+ var4.getMessage());
        }
        return privateKey;
    }
    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String  readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sb = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            sb = new StringBuffer();
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                sb.append(tempString);
            }
            reader.close();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return null;
    }

    /** 将文件中的公钥对象读出 */
    public static String getpublickey() throws Exception
    {
        return readFileByLines(PUBLIC_KEY_FILE);
    }

    public static RSAPublicKey getRSAPublidKeyBybase64(String base64s) throws Exception {
        byte[] decryptBASE64 = AESUtils.decryptBASE64(base64s);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(AESUtils.decryptBASE64(base64s));
        RSAPublicKey publicKey = null;
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        try {
            publicKey = (RSAPublicKey)keyFactory.generatePublic(keySpec);
        } catch (InvalidKeySpecException var4) {
            System.out.println("base64编码=" + base64s + "转RSA公钥失败"+ var4);
            var4.printStackTrace();
        }
        return publicKey;
    }

}