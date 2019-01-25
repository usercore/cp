package com.bc.encrypt;


import org.apache.commons.codec.binary.Base64;  

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;


public class RsaUtil {
    // 数字签名，密钥算法
    private static final String RSA_KEY_ALGORITHM = "RSA";

    // 数字签名签名/验证算法
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    // RSA密钥长度
    private static final int KEY_SIZE = 1024;

    private static final String PUBLIC_KEY = "publicKey";
    private static final String PRIVATE_KEY = "privateKey";

    public static final String pub_key="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCEDmmUI4eqXHbhWrcdgOauoLLvvMz9XDW5IbdoprAxSwW9mkycfcDYO6QWQjAnx/pSlSKCeGrQcy4e/oAIsS9YxbQ+EMgPnQltGq3RiNc4CnUNKoinSEkSINC+R5aQrfxRIzj4I91pB47a4v4xOP1gC24O2/jgbgY53jCzZdbTbQIDAQAB";
    public static final String pri_key="MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAIQOaZQjh6pcduFatx2A5q6gsu+8zP1cNbkht2imsDFLBb2aTJx9wNg7pBZCMCfH+lKVIoJ4atBzLh7+gAixL1jFtD4QyA+dCW0ardGI1zgKdQ0qiKdISRIg0L5HlpCt/FEjOPgj3WkHjtri/jE4/WALbg7b+OBuBjneMLNl1tNtAgMBAAECgYBnGlmUL6lceZTXndwaTOnOrekHBIGHztajFI/+RSaLVefFgRMPm59VNDuIpWSzqo7mq0mmVB4RYdxAlrOgFbbAkjIgsSaoahWTvH6+U9rrLyO6ENFpXpt7nwYy+L6SH2KzENOPa2yUBJEyqTIT+gcoq12sxYPxhG2iYXuKjY2QAQJBAOZFkRkczWWcfKAS2uKFqwReLxOHUNl4bu3HRR4PRmKpQPWh/EM1xnciF9OG+fR2ua4H4fULzeUWrZqVe9Cjgk0CQQCSz5oPKrsCH00/HaGLIqV4AYc2fM3JkURJmh0cJwQFQa4ZYk9Ldiq5OWnpqIp1inqQmXJemOeWEXR+X5mEv+WhAkEAl+sLiDrVU5VDL0RhPhMbubz9Z+/dKixnWt4dvnAraonpfmSJDjpbHlvSxk4FeAAgDi3LKCTKiLpGfHbkgcht/QJBAIfceRy6FZAUOZ9EI6An8utItsGU47g6p/BZH8FS1eSrcJtrICicdVMuL7adU20NNvmkF3Q1mnOFOcJNZDu2fqECQQDi3bDO8s2eqFMiFNxSElNP7NQQccpE8jk55OFgKb2MSWDJaGVMoWI+aiBETvv/prWFze0Odvj2YC2Ty5bx5LB6";

    
    /**
     * 初始化RSA密钥对
     * @return RSA密钥对
     * @throws Exception 抛出异常
     */
    private static Map<String, String> initKey() throws Exception {
        KeyPairGenerator keygen = KeyPairGenerator
                .getInstance(RSA_KEY_ALGORITHM);
        SecureRandom secrand = new SecureRandom();
        /*secrand.setSeed("sdadsadsawqdq".getBytes());// 初始化随机产生器
        keygen.initialize(KEY_SIZE, secrand);*/ // 初始化密钥生成器
        KeyPair keys = keygen.genKeyPair();
        //String pub_key = Base64.encodeBase64String(keys.getPublic().getEncoded());
       // String pri_key = Base64.encodeBase64String(keys.getPrivate().getEncoded());
        Map<String, String> keyMap = new HashMap<String, String>();
        keyMap.put(PUBLIC_KEY, pub_key);
        keyMap.put(PRIVATE_KEY, pri_key);
        System.out.println("公钥：" + pub_key);
        System.out.println("私钥：" + pri_key);
        return keyMap;
    }

    /**
     * 得到公钥
     * @param keyMap RSA密钥对
     * @return 公钥
     * @throws Exception 抛出异常
     */
    public static String getPublicKey(Map<String, String> keyMap) throws Exception{
        return keyMap.get(PUBLIC_KEY);
    }

    /**
     * 得到私钥
     * @param keyMap RSA密钥对
     * @return 私钥
     * @throws Exception 抛出异常
     */
    public static String getPrivateKey(Map<String, String> keyMap) throws Exception{
        return keyMap.get(PRIVATE_KEY);
    }

    /**
     * 数字签名
     * @param data 待签名数据
     * @param pri_key 私钥
     * @return 签名
     * @throws Exception 抛出异常
     */
    public static String sign(byte[] data, String pri_key) throws Exception {
        // 取得私钥
        byte[] pri_key_bytes = Base64.decodeBase64(pri_key);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(pri_key_bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        // 生成私钥
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 实例化Signature
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        // 初始化Signature
        signature.initSign(priKey);
        // 更新
        signature.update(data);

        return Base64.encodeBase64String(signature.sign());
    }

    /**
     * RSA校验数字签名
     * @param data 数据
     * @param sign 签名
     * @param pub_key 公钥
     * @return 校验结果，成功为true，失败为false
     * @throws Exception 抛出异常
     */
    public boolean verify(byte[] data, byte[] sign, String pub_key) throws Exception {
        // 转换公钥材料
        // 实例化密钥工厂
        byte[] pub_key_bytes = Base64.decodeBase64(pub_key);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        // 初始化公钥
        // 密钥材料转换
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pub_key_bytes);
        // 产生公钥
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        // 实例化Signature
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        // 初始化Signature
        signature.initVerify(pubKey);
        // 更新
        signature.update(data);
        // 验证
        return signature.verify(sign);
    }

    /**
     * 公钥加密
     * @param data 待加密数据
     * @param pub_key 公钥
     * @return 密文
     * @throws Exception 抛出异常
     */
    private static byte[] encryptByPubKey(byte[] data, byte[] pub_key) throws Exception {
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pub_key);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥加密
     * @param data 待加密数据
     * @param pub_key 公钥
     * @return 密文
     * @throws Exception 抛出异常
     */
    public static String encryptByPubKey(String data, String pub_key) throws Exception {
        // 私匙加密
        byte[] pub_key_bytes = Base64.decodeBase64(pub_key);
        byte[] enSign = encryptByPubKey(data.getBytes(), pub_key_bytes);
        return Base64.encodeBase64String(enSign);
    }

    /**
     * 私钥加密
     * @param data 待加密数据
     * @param pri_key 私钥
     * @return 密文
     *
     *
     *
     *
     * @throws Exception 抛出异常
     */
    private static byte[] encryptByPriKey(byte[] data, byte[] pri_key) throws Exception {
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(pri_key);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥加密
     * @param data 待加密数据
     * @param pri_key 私钥
     * @return 密文
     * @throws Exception 抛出异常
     */
    public static String encryptByPriKey(String data, String pri_key) throws Exception {
        // 私匙加密
        byte[] pri_key_bytes = Base64.decodeBase64(pri_key);
        byte[] enSign = encryptByPriKey(data.getBytes(), pri_key_bytes);
        return Base64.encodeBase64String(enSign);
    }

    /**
     * 公钥解密
     * @param data 待解密数据
     * @param pub_key 公钥
     * @return 明文
     * @throws Exception 抛出异常
     */
    private static byte[] decryptByPubKey(byte[] data, byte[] pub_key) throws Exception {
        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pub_key);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     * @param data 待解密数据
     * @param pub_key 公钥
     * @return 明文
     * @throws Exception 抛出异常
     */
    public static String decryptByPubKey(String data, String pub_key) throws Exception {
        // 公匙解密
        byte[] pub_key_bytes = Base64.decodeBase64(pub_key);
        byte[] design = decryptByPubKey(Base64.decodeBase64(data), pub_key_bytes);
        return new String(design);
    }

    /**
     * 私钥解密
     * @param data 待解密数据
     * @param pri_key 私钥
     * @return 明文
     * @throws Exception 抛出异常
     */
    private static byte[] decryptByPriKey(byte[] data, byte[] pri_key) throws Exception {
        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(pri_key);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     * @param data 待解密数据
     * @param pri_key 私钥
     * @return 明文
     * @throws Exception 抛出异常
     */
    public static String decryptByPriKey(String data, String pri_key) throws Exception {
    	System.out.println("data========================================:"+data);
    	System.out.println("pri_key=========================================:"+pri_key);
        // 私匙解密
        byte[] pri_key_bytes = Base64.decodeBase64(pri_key);
        
        byte[] design = decryptByPriKey(Base64.decodeBase64(data), pri_key_bytes);
        return new String(design);
    }

    /**
     * @param args
     */
    @SuppressWarnings("static-access")
    public static void main(String[] args) throws Exception {

        RsaUtil das = new RsaUtil();

        String datastr = "654321";
        System.out.println("待加密数据：\n" + datastr);
        //获取密钥对
        Map<String, String> keyMap = new HashMap<String, String>();
        keyMap = das.initKey();
       //  String pub_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCEDmmUI4eqXHbhWrcdgOauoLLvvMz9XDW5IbdoprAxSwW9mkycfcDYO6QWQjAnx/pSlSKCeGrQcy4e/oAIsS9YxbQ+EMgPnQltGq3RiNc4CnUNKoinSEkSINC+R5aQrfxRIzj4I91pB47a4v4xOP1gC24O2/jgbgY53jCzZdbTbQIDAQAB";//(String) keyMap.get(PUBLIC_KEY);
        //String pri_key = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAIQOaZQjh6pcduFatx2A5q6gsu+8zP1cNbkht2imsDFLBb2aTJx9wNg7pBZCMCfH+lKVIoJ4atBzLh7+gAixL1jFtD4QyA+dCW0ardGI1zgKdQ0qiKdISRIg0L5HlpCt/FEjOPgj3WkHjtri/jE4/WALbg7b+OBuBjneMLNl1tNtAgMBAAECgYBnGlmUL6lceZTXndwaTOnOrekHBIGHztajFI/+RSaLVefFgRMPm59VNDuIpWSzqo7mq0mmVB4RYdxAlrOgFbbAkjIgsSaoahWTvH6+U9rrLyO6ENFpXpt7nwYy+L6SH2KzENOPa2yUBJEyqTIT+gcoq12sxYPxhG2iYXuKjY2QAQJBAOZFkRkczWWcfKAS2uKFqwReLxOHUNl4bu3HRR4PRmKpQPWh/EM1xnciF9OG+fR2ua4H4fULzeUWrZqVe9Cjgk0CQQCSz5oPKrsCH00/HaGLIqV4AYc2fM3JkURJmh0cJwQFQa4ZYk9Ldiq5OWnpqIp1inqQmXJemOeWEXR+X5mEv+WhAkEAl+sLiDrVU5VDL0RhPhMbubz9Z+/dKixnWt4dvnAraonpfmSJDjpbHlvSxk4FeAAgDi3LKCTKiLpGfHbkgcht/QJBAIfceRy6FZAUOZ9EI6An8utItsGU47g6p/BZH8FS1eSrcJtrICicdVMuL7adU20NNvmkF3Q1mnOFOcJNZDu2fqECQQDi3bDO8s2eqFMiFNxSElNP7NQQccpE8jk55OFgKb2MSWDJaGVMoWI+aiBETvv/prWFze0Odvj2YC2Ty5bx5LB6";//(String) keyMap.get(PRIVATE_KEY);
//        String pub_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC3//sR2tXw0wrC2DySx8vNGlqt3Y7ldU9+LBLI6e1KS5lfc5jlTGF7KBTSkCHBM3ouEHWqp1ZJ85iJe59aF5gIB2klBd6h4wrbbHA2XE1sq21ykja/Gqx7/IRia3zQfxGv/qEkyGOx+XALVoOlZqDwh76o2n1vP1D+tD3amHsK7QIDAQAB";
////                        MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIS1VI9Foea34NzbI3e18xlTSZDjuhLUUBZH8se36uDIehIakuLVmirxOsNm/zZ6PwZZFz2WBPLwCN+aitlHf7R1bq1KgB8BA8wWE7wAIC3TNoPh5sat7ft8tRXKjb96/kJGFfRk3KR3okVzFWjcrgLM0A3p1wo836ZYoPD404CQIDAQAB
//        String pri_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOXlEEcPdps7J5gI4uRpaSkleAlB8ySU5Y+Z0kCcbV4PoJX5Q6lQozLku18YdAQztqjnHcGkzHgw1FRhXAECZTzg+HA2brGlMmuDnNkKWgWaxf4Tm6RYTlgVG7yLn7l3wu5BNNO/hd/NtMJM6E43y6P7KmSiYRO5U3CBEVUWMd3VAgMBAAECgYEAkqHVDQ4O35oOegmI9plJauYsNvNqqzWRalN5aJ6dn3YmPiI8Bt2ZClgcLt6A+UEmy3qGX0HG7Q5wD9X9geNOQB3ZiD/pGAGW08wS/wTxnWSnSBwdtZ03pUttfnFctkxULfDq4iG1ywdjqEk3F8QVFajQ0c76kWbt9LGAv2OGIi0CQQD2CmbVFXy4JeNHK3TDoLMjsUCiLa+qPnyyVDLDG9Ozb7wN2ydTrMhI+0udmjKvy/Lm1E2bKyp42iYuubEqvSAXAkEA7zNZsOgUe0q73sxXqrLQ7Fs7TNtIEXghrGmkVTHN0I7uMKzQ7KEbA6hfcBm4hPMoLa6Ag3m9tiMNBWtDWc/Y8wJAK0//dEl5EC3TSccTohCbGJBukV47i1u+teHuobw3U2I7F7FZxfgntflPAWqQu7PKieob01IRAv9cM2OLFbv/dwJBAIniXedeQMA5ekaaIEbjwQ8eH/bTyJ1ZVH/gfbwmc2+vlJo2ZFCjJcFcA3fJO9ZXnGeI2cfwG22sksr24+IXsAUCQG5yvVIleTDYqWuWVG1Rc8fk5UFjoZzJpp0nil0z+0fR5rogr4fxcH7vbWsE0id7gSvtV7KxPzkvJTpOK3yGDN0=";

        
        
        // 公匙加密
        String pubKeyStr = RsaUtil.encryptByPubKey(datastr, pub_key);
        
        System.out.println("pubKeyStr:"+pubKeyStr);
        //FZrx1XvmMXN%2BS0fTlbPP5sjRY5xJX1xHt6QeKn%2BMNK%2BV%2FzhqSn55zhBHONrnzRRMrgoKy2GwlJNBq6S3Rg44iDknpHtA5DGAGi24ab2pgqnkPPL6YEvViSsE7AqglzbABfjnT3asgbcLIuUKK4dokzqlN9SIF%2BYKJDBscwXdCJc%3D
       pubKeyStr="PUSSewxwmGlQUDJu%2FAcaDqhNTOpmcq4lcpgTJ1XhoChJyBCEqNRNxOFnQ80pMJkFYIX%2FLOlE5W8guH5USDu0Bw9Ah%2FYO7f%2FWJ86x2RsOtSVyPE9dC3LowW5Op4A4P4JkUn7p%2BCywIXHA%2FGc9mJgGwD0ksZpG7fVdDRomZe3hM0E%3D";
       //String elText="mOKKZ%2FwofsoEGlu%2BOqXBq7bJBA9YT5Vn0Lbk48hX2T%2BvddbUiBP%2Ffdy%2BPuxuWJhvfsApLt2%2Fn%2FKbu5eQs8Q1Ixrlr7xHPQhJPAqCyiB6S5eAqUYjhze7OX91S512BV8bKB%2F9pBsU1GxUWpOxAmnythMrx9yVs4I14rP0lTt4qxk%3D";
		  //String elText=("bViivpkje1R2nCXpgeJH+z6selmmo2legwb5k9BUpo2AhP56krEwMDaKEcsklEB+1duneWW2glB0PQ2rZYGWmRU/ONDcWRlvqO1N+0YDaL1310x6Az0VHOsQ8c5NoDXoK1NYw9i4yF64SH79LObkXNW6nzT4n6BiXqedcGm84mM=");
        pubKeyStr=pubKeyStr.replace("%2B", "+");
        pubKeyStr=pubKeyStr.replace("%2F", "/");
		pubKeyStr=pubKeyStr.replace("%3F", "?");
		pubKeyStr=pubKeyStr.replace("%23", "#");
		pubKeyStr=pubKeyStr.replace("%26", "&");
		pubKeyStr=pubKeyStr.replace("%3D", "=");
		pubKeyStr=pubKeyStr.replace("%20", " ");
		pubKeyStr=pubKeyStr.replace("%25", "%");
		
	
		System.out.println("pubKeyStr_new:"+pubKeyStr);

        System.out.println("公匙加密结果：\n" + pubKeyStr);
  
       	
       // 私匙解密
        String priKeyStr = RsaUtil.decryptByPriKey(pubKeyStr, pri_key);
        System.out.println("私匙解密结果：\n" + priKeyStr);

//        //换行
//        System.out.println();
//
//
//        // 数字签名
//        String str1 = "汉兵已略地";
//        String str2 = "四面楚歌声";
//        System.out.println("正确的签名：" + str1 + "\n错误的签名：" + str2);
//        String sign = RsaUtil.sign(str1.getBytes(), pri_key);
//        System.out.println("数字签名：\n" + sign);
//        boolean vflag1 = das.verify(str1.getBytes(), Base64.decodeBase64(sign), pub_key);
//        System.out.println("数字签名验证结果1：\n" + vflag1);
//        boolean vflag2 = das.verify(str2.getBytes(), Base64.decodeBase64(sign), pub_key);
//        System.out.println("数字签名验证结果2：\n" + vflag2);
    }
}