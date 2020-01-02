package nju.software.baseframework.data.vo;

import java.util.List;

/**
 * 用于新增公告时的公告模板信息生成
 */
public class GgModel {

    private List<String> yg;  //原告
    private List<String> bg;  //被告
    private String ah;  //案号
    private String ay;  //案由
    private String bgdb;  //被告代表
    private String ajmc;  //案件名称
    private Integer ajxh; //案件序号

    public List<String> getYg() {
        return yg;
    }

    public void setYg(List<String> yg) {
        this.yg = yg;
    }

    public List<String> getBg() {
        return bg;
    }

    public void setBg(List<String> bg) {
        this.bg = bg;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getAy() {
        return ay;
    }

    public void setAy(String ay) {
        this.ay = ay;
    }

    public String getBgdb() {
        return bgdb;
    }

    public void setBgdb(String bgdb) {
        this.bgdb = bgdb;
    }

    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public String getAjmc() {
        return ajmc;
    }

    public void setAjmc(String ajmc) {
        this.ajmc = ajmc;
    }

    public GgModel(List<String> yg, List<String> bg, String ah, String ay,
                   String bgbd, String ajmc,Integer ajxh){
        this.yg = yg;
        this.bg = bg;
        this.ah = ah;
        this.ay = ay;
        this.bgdb = bgbd+"（被告代表）";
        this.ajxh = ajxh;
        this.ajmc = ajmc;
    }
}
