package nju.software.baseframework.data.vo;


/**
 * 用于控制大屏显示内容的枚举类
 */
public enum DPNREnum {
    ZUYGG("showggList",0),
    HYC("showggList",1),
    SXBZXR("showsxbzxr",2),
    KTGG("showggList",3),
    ZDYGG("showggList",4);

    private String url;
    private Integer lx;

    DPNREnum(String url,Integer lx){
        this.url = url;
        this.lx = lx;
    }

    public Integer getLx() {
        return lx;
    }

    public String getUrl() {
        return url;
    }

    public static String GetUrlByLx(Integer lx){
        for (DPNREnum dpnrEnum:DPNREnum.values()){
            if(dpnrEnum.getLx()==lx){
                return dpnrEnum.getUrl();
            }
        }
        return null;
    }
}
