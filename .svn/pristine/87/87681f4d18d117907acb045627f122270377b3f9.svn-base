package nju.software.baseframework.service.Enum;

import nju.software.baseframework.data.dataobject.PubDmb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 28643 on 2018/11/28.
 */
public enum YYZTEnum {
    yyzt1(1,"预约中"),
    yyzt2(2,"已完成"),
    yyzt3(3,"已过期"),
    yyzt4(4,"已作废"),
    yyzt5(5,"审核中"),
    yyzt6(6,"审核成功"),
    yyzt7(7,"审核失败"),
    yyzt8(10,"预约状态异常");

    private Integer code;
    private String msg;

    YYZTEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String getMsgByStatus(Integer code){
        for(YYZTEnum yyzt: YYZTEnum.values()){
            if(yyzt.getCode() == code){
                return yyzt.getMsg();
            }

        }
        return "预约状态异常";
    }

    public static Integer getStatusByMsg(String msg){
        if(msg == null){
            return 0;
        }
        msg = msg.trim();

        for(YYZTEnum yyzt: YYZTEnum.values()){
            if(yyzt.getMsg().equals(msg)){
                return yyzt.getCode();
            }
        }
        return 10;
    }

    public static List<PubDmb> getYyzt(){
        List<PubDmb> _list = new ArrayList<>();
        for(YYZTEnum yyzt:YYZTEnum.values()){
            PubDmb pubDmb = new PubDmb();
            pubDmb.setDmbh(yyzt.getCode().toString());
            pubDmb.setDmms(yyzt.getMsg());
            _list.add(pubDmb);
        }
        return _list;
    }
}
