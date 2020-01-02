package nju.software.baseframework.data.vo;

import nju.software.baseframework.data.dataobject.PubDmb;

import java.util.ArrayList;
import java.util.List;

public enum AJZTEnum {
    zt1("0","正在办理"," and ajjb.ajzt='0'"),
    zt2("1","中止"," and ajjb.ajzt='1'"),
    zt3("2","中断"," and ajjb.ajzt='2'"),
    zt4("3","已结案"," and ajjb.jarq!=null");

    private String number;
    private String explain;
    private String sql;

    AJZTEnum(String number,String explain,String sql){
        this.number = number;
        this.explain = explain;
        this.sql = sql;
    }

    public String getNumber() {
        return number;
    }

    public String getExplain() {
        return explain;
    }

    public String getSql() {
        return sql;
    }

    public static String getNumberByExplain(String explain){
        for(AJZTEnum ajztEnum: AJZTEnum.values()){
            if(ajztEnum.getExplain().equals(explain)){
                return ajztEnum.getNumber();
            }
        }
        return null;
    }

    public static String getExplainByNumber(String number){
        for(AJZTEnum ajztEnum : AJZTEnum.values()){
            if(ajztEnum.getNumber().equals(number)){
                return ajztEnum.getExplain();
            }
        }
        return null;
    }

    public static String getSqlByNumber(String number){
        for(AJZTEnum ajztEnum: AJZTEnum.values()){
            if(ajztEnum.getNumber().equals(number)){
                return ajztEnum.getSql();
            }
        }
        return null;
    }

    public static List<PubDmb> getAjzt(){
        List<PubDmb> _list = new ArrayList<>();
        for (AJZTEnum ajztEnum: AJZTEnum.values()){
            PubDmb pubDmb = new PubDmb();
            pubDmb.setDmbh(ajztEnum.getNumber());
            pubDmb.setDmms(ajztEnum.getExplain());
            _list.add(pubDmb);
        }
        return _list;
    }
}
