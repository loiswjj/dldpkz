package nju.software.baseframework.util;

import java.util.StringTokenizer;

/**
 *
 * @author 13314
 * @date 2018/8/14
 */
public class DmUtil {
    /**
     * 获取受案件性质和审判程序影响的信息项的类别编号
     * @param sjxx
     * @param ajxzbh
     * @param spcxbh
     * @return
     */
    public static String getLbbh(String sjxx, String ajxzbh, String spcxbh) {
        String lbbh = "";
        StringTokenizer tokenizer = new StringTokenizer(sjxx, ",");
        String ajxzCh = "";
        String spcxCh = "";
        boolean xzMatched = false;
        boolean spcxMatched = false;
        // 情況 number+*
        if (StringUtil.equals("*", spcxbh)){
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                ajxzCh = token.substring(0, 1);
                if (StringUtil.equals("*", ajxzCh) || StringUtil.equals(ajxzbh,ajxzCh)) {
                    lbbh = token.substring(2);
                    break;
                }
            }
        } else {// 情況number+number
            while (tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                ajxzCh = token.substring(0, 1);
                xzMatched = StringUtil.equals("*", ajxzCh);
                spcxCh = token.substring(1, 2);
                spcxMatched = StringUtil.equals("*", spcxCh);
                String ajxzAndSpcx = spcxMatched ? ajxzCh
                        : xzMatched ? spcxCh : token.substring(0, 2);
                if (StringUtil.equals(spcxMatched ? xzMatched ? ajxzCh
                                : ajxzbh : xzMatched ? spcxbh : ajxzbh + spcxbh,
                        ajxzAndSpcx)) {
                    lbbh = token.substring(2);
                    break;
                }
            }
        }
        return lbbh;
    }
}
