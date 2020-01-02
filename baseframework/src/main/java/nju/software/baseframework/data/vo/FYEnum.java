package nju.software.baseframework.data.vo;

import nju.software.baseframework.util.StringUtil;

/**
 * 法院的Enum
 * @author byron
 *
 */
public enum FYEnum {
    //TEST("天津市高级人民法院","市高级法院","0000","130.1.1.111","50","50"),
    TJGY("天津市高级人民法院","市高级法院","120000 200","130.1.1.111","51","51"),
    TJYZY("天津市第一中级人民法院","第一中级法院","120100 210","130.2.0.110","52","52"),//,有问题
    TJEZY("天津市第二中级人民法院","第二中级法院","120200 220","130.3.100.36","62","62"),
    TJHS("天津海事法院","海事法院","960200 230","130.4.1.196","72","72"),
    TJHP("天津市和平区人民法院","和平区法院","120101 211","130.6.0.200","53","53"),
    TJNK("天津市南开区人民法院","南开区法院","120104 212","130.5.0.14","54","54"),
    TJHX("天津市河西区人民法院","河西区法院","120203 222","130.10.0.167","64","64"),
    TJHD("天津市河东区人民法院","河东区法院","120202 221","130.9.40.13","63","63"),
    TJHB("天津市河北区人民法院","河北区法院","120105 213","130.7.0.7","55","55"),
    TJHQ("天津市红桥区人民法院","红桥区法院","120106 214","130.8.0.73","56","56"),//有问题
    TJBH("天津市滨海新区人民法院","滨海新区法院","120242 22A","130.25.1.13","74","740"),
    TJTG("天津市塘沽区人民法院","塘沽审判区","120204 223","130.15.0.21","65","741"),
    TJHG("天津市汉沽区人民法院","汉沽审判区","120205 224","130.16.0.18","66","742"),
    TJDG("天津市大港区人民法院","大港审判区","120206 225","130.17.0.12","67","743"),
    TJKFQ("天津经济技术开发区人民法院","功能区审判区","120241 229","130.23.0.15","71","744"),
    TJDL("天津市东丽区人民法院","东丽区法院","120207 226","130.13.0.13","68","68"),
    TJJN("天津市津南区人民法院","津南区法院","120208 227","130.14.0.22","69","69"),
    TJXQ("天津市西青区人民法院","西青区法院","120107 215","130.11.1.5","57","57"),
    TJBC("天津市北辰区人民法院","北辰区法院","120108 216","130.12.0.2","58","58"),
    TJWQ("天津市武清区人民法院","武清区法院","120222 217","130.19.0.12","59","59"),
    TJBD("天津市宝坻区人民法院","宝坻区法院","120224 219","130.21.0.5","61","61"),
    TJJH("天津市静海区人民法院","静海县法院","120223 218","130.20.1.8","60","60"),
    TJNH("天津市宁河区人民法院","宁河县法院","120221 228","130.18.0.11","70","70"),
    TJJX("蓟县人民法院","蓟县法院","120225 21A","130.22.0.8","73","73"),
    TJTL("天津铁路运输法院","铁路法院","920103 132","130.26.0.7","24","24");

    private String name;

    private String jc;

    private String fydm;

    private String fydz;

    private String fybh;//最高院法院编号

    private String bsbh;//报送编号

    private FYEnum(String name, String jc, String fydm) {
        this.name = name;
        this.jc = jc;
        this.fydm = fydm;
    }

    private FYEnum(String name, String jc, String fydm, String fydz, String fybh, String bsbh) {
        this.name = name;
        this.jc = jc;
        this.fydm = fydm;
        this.fydz = fydz;
        this.fybh = fybh;
        this.bsbh = bsbh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJc() {
        return jc;
    }

    public void setJc(String jc) {
        this.jc = jc;
    }

    public String getFydm() {
        return fydm;
    }

    public void setFydm(String fydm) {
        this.fydm = fydm;
    }

    public String getFydz() {
        return fydz;
    }

    public void setFydz(String fydz) {
        this.fydz = fydz;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }

    public void setBsbh(String bsbh) {
        this.bsbh = bsbh;
    }

    public String getBsbh() {
        return bsbh;
    }

    public static String getFYDMByName(String name) {
        for(FYEnum fy:FYEnum.values()){
            if(fy.getName().equals(name))
                return fy.getFydm();
        }
        return null;
    }

    public static String getNameByFYDM(String fydm) {
        for(FYEnum fy:FYEnum.values()){
            if(fy.getFydm().equals(fydm))
                return fy.getName();
        }
        return null;
    }

    public static FYEnum getFYByName(String fymc){
        for(FYEnum fy:FYEnum.values()){
            if(fymc.contains(fy.getName()))
                return fy;
        }
        return null;
    }

    public static String getBsbhByFydm(String fydm){
        for(FYEnum fy:FYEnum.values()){
            fydm=fydm.trim();
            if(fydm.startsWith(fy.getBsbh()))
                return fy.getBsbh();
        }
        return null;
    }

    public static String getBsbhByAjbs(String ajbs){
        for(FYEnum fy:FYEnum.values()){
            ajbs=ajbs.trim();
            if(ajbs.startsWith(fy.getBsbh()))
                return fy.getBsbh();
        }
        return "";
    }

    public static FYEnum getFyByBsbh(String bsbh){
        for(FYEnum fy:FYEnum.values()){
            if(fy.getBsbh().equals(bsbh))
                return fy;
            if(bsbh.equals(fy.getBsbh()+"0"))
                return fy;
        }
        return null;
    }

    /**
     * 根据案件标识获取所属法院
     * @param ajbs
     * @return
     */
    public static FYEnum getFyByAJbs(String ajbs){
        if(!StringUtil.isBlank(ajbs)){
            ajbs=""+Long.parseLong(ajbs);
            String prefix=ajbs.substring(0,3);
            for(FYEnum fy:FYEnum.values()){
                if(prefix.equals(fy.getFybh()+"0"))
                    return fy;
                else if(prefix.startsWith(fy.getBsbh()))
                    return fy;
            }
        }
        return null;
    }

    public static String getFybhByFydm(String fydm){
        for(FYEnum fy:FYEnum.values()){
            if(fy.getFydm().equals(fydm)){
                return fy.getFybh();
            }
        }
        return "";
    }

    /**
     * 根据案件标识获取所属法院
     * @return
     */
    public static FYEnum getFyByFydm(String fydm){
        if(!StringUtil.isBlank(fydm)){
            for(FYEnum fy:FYEnum.values()){
                if(fy.getFydm().equals(fydm.trim()))
                    return fy;
            }
        }
        return null;
    }

    /**
     * 根据法院编号获取法院代码
     * @param fybh
     * @return
     */
    public static FYEnum getFyByFybh(String fybh){
        if(!StringUtil.isBlank(fybh)){
            for(FYEnum fy:FYEnum.values()){
                if(fy.getFybh().equals(fybh.trim()))
                    return fy;
            }
        }
        return null;
    }
}
