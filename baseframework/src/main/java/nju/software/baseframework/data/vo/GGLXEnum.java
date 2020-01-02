package nju.software.baseframework.data.vo;

import nju.software.baseframework.data.dataobject.PubDmb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum GGLXEnum {
    lx1("1","送达起诉状副本及开庭传票公告","ggmodel/sdqs"),
    lx2("2","开庭公告","ggmodel/ktpq"),
    lx3("3","送达判决书公告","ggmodel/sdpjs"),
    lx4("4","执行公告","ggmodel/zxgg"),
    lx5("5","失信被执行人公告","gmodel/sxbzxr"),
    lx6("6","送达裁定书公告","ggmodel/sdcds"),
    lx7("7","自定义公告","ggmodel/zdy");
    /**
     * 1 -- 送达起诉状副本及开庭传票公告
     * 2 -- 开庭排期公告
     * 3 -- 送达判决书公告
     * 4 -- 执行公告
     * 5 -- 失信被执行人公告
     * 6 -- 送达裁定书公告
     * 7 -- 自定义公告
     * 8 -- 最高院公告
     */

    private String number;
    private String explain;
    private String path;

    GGLXEnum(String number,String explain,String path){
        this.number = number;
        this.explain = explain;
        this.path = path;
    }

    private String getNumber() {
        return number;
    }

    private String getExplain() {
        return explain;
    }

    private String getPath() {
        return path;
    }

    public static String getExplainByNumber(String number){
        for (GGLXEnum gglx: GGLXEnum.values()){
            if(gglx.getNumber().equals(number)){
                return gglx.getExplain();
            }
        }
        return null;
    }

    public static Map<String,String> getGglxMap(){
        Map<String,String> ggmap = new HashMap<>();
        for (GGLXEnum gglx: GGLXEnum.values()){
            if(!gglx.getNumber().equals("2")){
                ggmap.put(gglx.getNumber(),gglx.getExplain());
            }
        }
        return ggmap;
    }

    public static String getPathByNumber(String number){
        for(GGLXEnum gglx:GGLXEnum.values()){
            if(gglx.getNumber().equals(number)){
                return gglx.getPath();
            }
        }
        return null;
    }

    public static List<PubDmb> getGglx(){
        List<PubDmb> _list = new ArrayList<>();
        for (GGLXEnum gglx: GGLXEnum.values()){
            PubDmb pubDmb = new PubDmb();
            pubDmb.setDmbh(gglx.getNumber());
            pubDmb.setDmms(gglx.getExplain());
            _list.add(pubDmb);
        }
        return _list;
    }
}
