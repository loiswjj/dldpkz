package nju.software.baseframework.data.dynamicdDatabases;


import nju.software.baseframework.util.StringUtil;

/**
 * 数据源枚举
 * @author 13314
 * @date 2018/7/26
 */
public enum DataSourceEnum {
    /**
     * 信访库
     */
    XF("0001","xf"),

    /**
     * 预约公告库
     */
    LOCAL("50","local"),
    DLGG("68","dlgg"),
    /**
     * 天津东丽法院
     */
    TJDLFY("120207 226","Tjdlfy");

    private String key ;
    private String fydm ;

    DataSourceEnum(String fydm,String key){
        this.fydm = fydm ;
        this.key = key ;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFydm() {
        return fydm;
    }

    public void setFydm(String fydm) {
        this.fydm = fydm;
    }

    public static String getSourceByFydm(String fydm){
        if(StringUtil.isEmpty(fydm)) {
            return null;
        }
        for (DataSourceEnum src : DataSourceEnum.values()) {
            if (StringUtil.equals(fydm, src.getFydm())) {
                return src.getKey();
            }
        }
        return null;
    }
}
