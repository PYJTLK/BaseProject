package baseproject.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2017/12/16.
 */

public class MD5Util {
    /**
     * 将字符串进行md5加密
     * @param inputString
     * @return
     */
    public static String toMD5(String inputString){
        String md5String = null;
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(inputString.getBytes());
            md5String = bytesToString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }finally {
            return md5String;
        }
    }

    private static String bytesToString(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0;i < bytes.length;i++){
            String hex = Integer.toHexString(bytes[i]);
            if(hex.length() == 1){
                stringBuilder.append('0');
            }
            stringBuilder.append(hex);
        }
        return stringBuilder.toString();
    }
}
