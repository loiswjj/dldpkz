package nju.software.baseframework.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * Created by 13314 on 2018/9/11.
 */
public class DESUtil {
    private static final String PASSWORD="znwszzps" ;
    /**
 * 加密
 * @param datasource byte[]
 * @return byte[]
 */
public static  byte[] encrypt(byte[] datasource) {
    try{
        SecureRandom random = new SecureRandom();
        DESKeySpec desKey = new DESKeySpec(PASSWORD.getBytes());
        //创建一个密匙工厂，然后用它把DESKeySpec转换成
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(desKey);
        //Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");
        //用密匙初始化Cipher对象,ENCRYPT_MODE用于将 Cipher 初始化为加密模式的常量
        cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
        //现在，获取数据并加密
        //正式执行加密操作
        //按单部分操作加密或解密数据，或者结束一个多部分操作
        return cipher.doFinal(datasource);
    }catch(Throwable e){
        e.printStackTrace();
    }
    return null;
}
    /**
     * 解密
     * @param src byte[]
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(PASSWORD.getBytes());
        // 创建一个密匙工厂
        //返回实现指定转换的 Cipher 对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }
}