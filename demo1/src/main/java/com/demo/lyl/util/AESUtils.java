package com.demo.lyl.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 *报文体AES对称加密
 *
 *
 */
public class AESUtils {

    public static final String CHARACTER = "utf-8";
    public static final String ALGORITHM = "AES";

    /**
     * BASE64解密
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception{
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key)throws Exception{
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    // 加密
    public static String Encrypt(String sSrc, String sKey) throws Exception {
//        if (sKey == null) {
//            System.out.print("Key为空null");
//            return null;
//        }
//        // 判断Key是否为16位
//        if (sKey.length() != 16) {
//            System.out.print("Key长度不是16位");
//            return null;
//        }
//        byte[] raw = sKey.getBytes(CHARACTER);
        Key key = toKey(decryptBASE64(sKey));
        byte[] raw = key.getEncoded();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(CHARACTER));
        return encryptBASE64(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
//        return RSAUtils.bASE64StrsubString(encryptBASE64(encrypted),2)
    }
    /**
     * <p>
     * 转换密钥
     * </p>
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static Key toKey(byte[] key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
        return secretKey;
    }

    // 解密
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
//            // 判断Key是否正确
//            if (sKey == null) {
//                System.out.print("Key is null");
//                return null;
//            }
//            // 判断Key是否为16位
//            if (sKey.length() != 16) {
//                System.out.print("The length of the key is not 16 bits!!");
//                return null;
//            }
//            byte[] raw = sKey.getBytes(CHARACTER);
            Key key = toKey(decryptBASE64(sKey));
            byte[] raw = key.getEncoded();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, ALGORITHM);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            //byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
            byte[] encrypted1 = decryptBASE64(sSrc);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,CHARACTER);
                return originalString;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.toString());
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        /*
         * 此处使用AES-128-ECB加密模式，key需要为16位。
         */


        String cKey = "1234567890$%F456";

        System.out.println(encryptBASE64(cKey.getBytes())+"==========");
        // 需要加密的字串
        String cSrc = "www.gowhere.sosasdadsadsa";
        System.out.println(cSrc);
        // 加密
        String enString = AESUtils.Encrypt(cSrc, cKey);
        System.out.println("加密后的字串是：" + enString);
        System.out.println("加密后的字串是：" + enString.length());
        // 解密
        String DeString = AESUtils.Decrypt(enString, cKey);
        System.out.println("解密后的字串是：" + DeString);
    }
}

//    //源代码片段来自云代码http://yuncode.netey(decryptBASE64(key));
//    //Key k = toKey(key.getBytes(CHARACTER));
//    byte[] raw = k.getEncoded();
//    SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITHM);
//    Cipher cipher = Cipher.getInstance(TRANSFORMATION);
//            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
//                    CipherInputStream cin = new CipherInputStream(in, cipher);
//                    byte[] cache = new byte[CACHE_SIZE];
//                    int nRead = 0;
//                    while ((nRead = cin.read(cache)) != -1) {
//                    out.write(cache, 0, nRead);
//                    out.flush();
//                    }
//                    out.close();
//                    cin.close();
//                    in.close();
//                    }
//                    }
//
///**
// * <p>
// * 文件解密
// * </p>
// *
// * @param key
// * @param sourceFilePath
// * @param destFilePath
// * @throws Exception
// */
//public static void decryptFile(String key, String sourceFilePath, String destFilePath) throws Exception {
//        File sourceFile = new File(sourceFilePath);
//        File destFile = new File(destFilePath);
//        if (sourceFile.exists() && sourceFile.isFile()) {
//        if (!destFile.getParentFile().exists()) {
//        destFile.getParentFile().mkdirs();
//        }
//        destFile.createNewFile();
//        FileInputStream in = new FileInputStream(sourceFile);
//        FileOutputStream out = new FileOutputStream(destFile);
//        //Key k = toKey(Base64Utils.decode(key));
//        Key k = toKey(decryptBASE64(key));
//        //Key k = toKey(key.getBytes(CHARACTER));
//        byte[] raw = k.getEncoded();
//        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITHM);
//        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
//        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
//        CipherOutputStream cout = new CipherOutputStream(out, cipher);
//        byte[] cache = new byte[CACHE_SIZE];
//        int nRead = 0;
//        while ((nRead = in.read(cache)) != -1) {
//        cout.write(cache, 0, nRead);
//        cout.flush();
//        }
//        cout.close();
//        out.close();
//        in.close();
//        }
//        }
//
//
//
///**
// * <p>
// * 转换密钥
// * </p>
// *
// * @param key
// * @return
// * @throws Exception
// */
//private static Key toKey(byte[] key) throws Exception {
//        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
//        return secretKey;
//        }
//
//public static byte[] encrypt(byte[] fileData,String key) throws Exception {
//        Key k = toKey(decryptBASE64(key));
//        byte[] raw = k.getEncoded();
//        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITHM);
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
//        return cipher.doFinal(fileData);
//        }
//
//public static byte[] decrypt(byte[] encData,String key) throws Exception {
//        Key k = toKey(decryptBASE64(key));
//        byte[] raw = k.getEncoded();
//        SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITHM);
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
//        return cipher.doFinal(encData);
//        }
//public static void main(String[] args) throws Exception {
//        /*
//         * 报文加解密
//         */
//        //此处使用AES-128-ECB加密模式，key需要为16位。
//        String cKey = "1234567890$%F456";
//
//        String encryptBASE64 = encryptBASE64(cKey.getBytes(CHARACTER));
//        /*System.out.println("密钥base64转码后："+encryptBASE64);
//        // 需要加密的字串
//        String cSrc = "www.gowhere.sosasdadsadsa";
//        System.out.println(cSrc);
//        // 加密
//        String enString = AESUtils.Encrypt(cSrc, encryptBASE64);
//        System.out.println("加密后的字串是：" + enString);
//        // 解密
//        String DeString = AESUtils.Decrypt(enString, encryptBASE64);
//        System.out.println("解密后的字串是：" + DeString);
//*/
//        /*
//         * 报文加解密
//         */
//        //文件加密
//        //AESUtils.decryptFile(encryptBASE64(cKey.getBytes()), "E:\\temp\\20190312\\Noname6.java", "E:\\temp\\20190312\\AES-Noname6.java");
//        //文件解密
//        //AESUtils.encryptFile(encryptBASE64, "C:\\Users\\卡中心\\Desktop\\11\\fuwu_20190322.txt.tmp", "C:\\Users\\卡中心\\Desktop\\11\\fuwu_20190322.txt");
//        //AESUtils.decryptFile(encryptBASE64,"C:\\Users\\卡中心\\Desktop\\11\\fuwu_20190322.txt","C:\\Users\\卡中心\\Desktop\\11\\fuwu_20190322.dec");
//        //处理顺序
//        //1.获取文件字节
//        byte[] fileData = FileUtils.getFileByte("C:\\Users\\卡中心\\Desktop\\11\\fuwu_20190322.txt.tmp");
//        //2.加密
//        byte[] data = encrypt(fileData,encryptBASE64);
//        //3.base64转码
//        String dataStr = encryptBASE64(data);
//        //4.生成数据文件
//        FileUtils.exportTxt(dataStr,"C:\\Users\\卡中心\\Desktop\\11\\fuwu_20190322.txt","GBK");
//        //5.压缩文件
//        String[] files = {"C:\\Users\\卡中心\\Desktop\\11\\fuwu_20190322.txt"};
//        TarUtils.zipFiles(files,"C:\\Users\\卡中心\\Desktop\\11\\fuwu_20190322.zip",Charsets.GBK);
//
//        //解密
//        File file = new File("C:\\Users\\卡中心\\Desktop\\11\\fuwu_20190322.zip");
//        //1.解压
//        TarUtils.unZipFiles(file,file.getParent(),"GBK");
//        String content = FileUtils.getFile("C:\\Users\\卡中心\\Desktop\\11\\fuwu_20190322.txt","GBK");
//        //2.转码、解密
//        byte [] decData = decrypt(decryptBASE64(content),encryptBASE64);
//        FileUtils.exportByteTxt(decData,"C:\\Users\\卡中心\\Desktop\\11\\fuwu_20190322.dec");
//
//        }
//        }
