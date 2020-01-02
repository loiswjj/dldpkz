package nju.software.baseframework.service.Enum;

import nju.software.baseframework.data.dataobject.PubDmb;

import java.util.ArrayList;
import java.util.List;

public enum  GgZTEnum {
    //ggzt0("0","未发布"),
    ggzt1("1","正在发布"),
    ggzt2("2","已发布"),
    ggzt3("3","已作废"),
    ggzt4("4","已过期"),
    ggzt5("5","审核成功"),
    ggzt6("6","审核中"),
    ggzt7("7","审核失败");

    private String number;
    private String explain;

    private String getNumber(){
        return this.number;
    }

    private String getExplain(){
        return this.explain;
    }

    GgZTEnum(String number,String explain){
        this.number = number;
        this.explain = explain;
    }

    public static String getExplainByNumber(String number){
        for(GgZTEnum ggzt : GgZTEnum.values()){
            if(ggzt.getNumber().equals(number)){
                return ggzt.getExplain();
            }
        }
        return null;
    }

    public static List<PubDmb> getGgzt(){
        List<PubDmb> _list = new ArrayList<>();
        for(GgZTEnum ggzt :GgZTEnum.values()){
            PubDmb pubDmb = new PubDmb();
            pubDmb.setDmbh(ggzt.getNumber());
            pubDmb.setDmms(ggzt.getExplain());
            _list.add(pubDmb);
        }
        return _list;
    }
}
