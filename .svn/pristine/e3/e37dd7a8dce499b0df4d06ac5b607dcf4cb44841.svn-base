package nju.software.baseframework.util;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author 13314
 * @date 2018/8/11
 */
public class UrlUtil {
    /**
     * 编码url，并转换特殊字符
     * @param url
     * @return
     */
    public static String encode(String url){
        try {
            return URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                    .replaceAll("\\+", "%20").replaceAll("%28", "\\(")
                    .replaceAll("%29", "\\)").replaceAll("%3B", ";")
                    .replaceAll("%40", "@").replaceAll("%23", "\\#")
                    .replaceAll("%26", "\\&") ;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null ;
    }
    /***
     * 获取本机ip
     * @return 本机ip
     * @throws UnknownHostException
     */
    public static String getHostAddress() throws UnknownHostException {
        String userAddress = InetAddress.getLocalHost().getHostAddress();
//        return userAddress.replaceAll("[^0-9a-zA-Z.=]", "_");
        return "130.1.67.36" ;
    }
}
