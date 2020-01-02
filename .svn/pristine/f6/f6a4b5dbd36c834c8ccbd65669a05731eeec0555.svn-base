package nju.software.baseframework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by 13314 on 2018/8/9.
 */
public class DateUtil {
    public final static String chineseDtFormat = "yyyy年MM月dd日";
    public final static String shortFormat = "yyyy-MM-dd";
    public final static String WebFormat = "yyyy-MM-dd hh:mm:ss";

    /**
     * 标准化输出
     *
     * @param date
     *            日期对象
     * @param format
     *            日期输出格式
     * @return 按照指定格式输出的字符串
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(format).format(date);
    }

    public static String formateDateToCH(Date date){
        Calendar calendar = new GregorianCalendar() ;
        calendar.setTime(date);
        StringBuilder sb = new StringBuilder() ;
        sb.append(NumberUtil.numberDirectToCH(calendar.get(Calendar.YEAR)))
                .append("年")
                .append(NumberUtil.numberToCH(calendar.get(Calendar.MONTH)+1))
                .append("月")
                .append(NumberUtil.numberToCH(calendar.get(Calendar.DAY_OF_MONTH)))
                .append("日");
        return sb.toString() ;
    }

    public static Date createDate(int year,int month,int day){
        Calendar calendar = new GregorianCalendar(year,month,day) ;
        return calendar.getTime() ;
    }
    /**
     * 比较两个日期的先后关系
     *
     * @param one
     *            日期1
     * @param two
     *            日期2
     * @return  0 ： 表示相等    1 ： one大于two     -1 ： two大于one
     */
    public static int compareDate(Date one, Date two) {
        // 合法性判断
        if (one == null && two != null) {
            return -1;
        } else if (one != null && two == null) {
            return 1;
        } else if (one == null && two == null) {
            return 0;
        } else {
            if (one.getTime() > two.getTime()) {
                return 1;
            }
            if (one.getTime() == two.getTime()) {
                return 0;
            }
            return -1;
        }
    }

    public static Date parseDate(String date) throws ParseException {
        if (date == null || date.equals("")) {
            return null;
        }

        return new SimpleDateFormat().parse(date);

    }

    public static String getSysj(Date fbsj,int period){
        Date current = new Date();
        long days = (current.getTime()-fbsj.getTime())/(1000*3600*24);
        long sysj = period - days;
        return String.valueOf(sysj);
    }

    public static String getDqsj(Date fbsj,int period){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fbsj);
        calendar.add(Calendar.DATE,period);
        return format(calendar.getTime(),DateUtil.shortFormat);
    }

    /**
     * 计算某一日期之后的period时间后日期
     * @param fbsj
     * @param period
     * @return
     */
    public static Date getGgDqsj(Date fbsj,int period){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fbsj);
        calendar.add(Calendar.DATE,period);
        return calendar.getTime();
    }

    public static Date getDayStart(Date date,int interval){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE,interval);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND,0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    public static Date getDayEnd (Date date,int interval){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE,interval);
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND,59);
        c.set(Calendar.MILLISECOND, 999);
        return c.getTime();
    }

    public static void main(String[] args){
        System.out.println(getGgDqsj(createDate(2018,12,1),30));
    }
}
