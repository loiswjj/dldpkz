package nju.software.baseframework.util;

/**
 *
 * @author 13314
 * @date 2018/8/9
 */
public class NumberUtil {
    private static final String[]units={"千","百","十",""};
    private static String numZero= "零";
    /**
     * 直接转换，不可考虑个十百千万
     * @param intInput
     * @return
     */
    public static String numberDirectToCH(int intInput) {
        String si = String.valueOf(intInput);
        StringBuilder sb = new StringBuilder() ;
        for (char ch:si.toCharArray()){
            sb.append(getCH(ch)) ;
        }
        return sb.toString();
    }

    /**
     * 本值转换，保留各十百千万
     * @param num
     * @return
     */
    public static String numberToCH(int num){
        char[] numChars = (num +"").toCharArray();


        String tempStr ="";


        int inc =units.length- numChars.length;


        for(int i = 0; i < numChars.length; i++) {

            if(numChars[i] !='0') {

                tempStr +=getCH(numChars[i]) +units[i + inc];

            }else{

                tempStr +=getCH(numChars[i]);

            }

        }


//将连续的零保留一个

        tempStr = tempStr.replaceAll(numZero+"+",numZero+"");


//去掉未位的零

        tempStr = tempStr.replaceAll(numZero+"$","");

        //一十改为十
        if(StringUtil.equals("一十",tempStr)){
            tempStr = "十" ;
        }
        //一十一改为十一
        if (tempStr.length()==3 && StringUtil.equals(tempStr.substring(0,1),"一")){
            tempStr = tempStr.substring(1) ;
        }
        return tempStr;
    }

    private static String getCH(char input) {
        String sd = "";
        switch (input) {
            case '0':
                sd="零";
                break;
            case '1':
                sd = "一";
                break;
            case '2':
                sd = "二";
                break;
            case '3':
                sd = "三";
                break;
            case '4':
                sd = "四";
                break;
            case '5':
                sd = "五";
                break;
            case '6':
                sd = "六";
                break;
            case '7':
                sd = "七";
                break;
            case '8':
                sd = "八";
                break;
            case '9':
                sd = "九";
                break;
            default:
                break;
        }
        return sd;
    }

}
