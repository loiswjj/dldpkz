package nju.software.baseframework.data.vo;

import nju.software.baseframework.data.dataobject.Yyb;
import nju.software.baseframework.service.Enum.YYZTEnum;
import nju.software.baseframework.util.DateUtil;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by 28643 on 2018/12/6.
 */

public class YyxqModel {
    /* 可以由YYB 中的字段转化得到*/
    private Integer zt;
    private String status;//    当前预约状态
    private String lfsjStr; //    来访时间
    private String yysjStr;
    private String jfdd  ;//     接访地点
    private String lfrxm;//来访人姓名
    private String lfrlxfs; //来访人联系方式
    private String lfsy;//来访事由
    private String yajgl;  //    与案件关联 案件的结案方式；
    private String jfdh  ;//     接访人电话
    private String ah;
    private String ajmc;
    private String glr ;//     本案关联人
    private String jfr;

    /*上面的部分不属于 YYB 中的字段*/
    private Integer bh;
    private String bz ;//备注
    private String dsr   ;//   案件当事人

    public String getYajgl() {
        return yajgl;
    }

    public String getAjmc() {
        return ajmc;
    }

    public void setAjmc(String ajmc) {
        this.ajmc = ajmc;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getLfsy() {
        return lfsy;
    }

    public void setLfsy(String lfsy) {
        this.lfsy = lfsy;
    }

    public String getLfsjStr() {
        return lfsjStr;
    }

    public void setLfsjStr(String lfsj) {
        this.lfsjStr = lfsj;
    }

    public Integer getBh() {
        return bh;
    }

    public void setBh(Integer bh) {
        this.bh = bh;
    }

    public void setYysjStr(String yysjStr) {
        this.yysjStr = yysjStr;
    }

    public String getYysjStr() {
        return yysjStr;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }

    public Integer getZt() {
        return zt;
    }

    public YyxqModel(Yyb yyb){
        this.bh = yyb.getBh();
        this.zt = yyb.getYyzt();
        this.status = YYZTEnum.getMsgByStatus(yyb.getYyzt());
        this.lfsjStr = DateUtil.format(yyb.getLfsj(),DateUtil.shortFormat);
        this.yysjStr = DateUtil.format(yyb.getYysj(),DateUtil.shortFormat);
        this.jfdd = yyb.getJfdd();
        this.jfdh =yyb.getJfrlxfs();
        this.lfrxm =yyb.getLfr();
        this.bz =yyb.getBz();
        this.lfsy = yyb.getLfsy();
        this.ah = yyb.getAh();
        this.ajmc = yyb.getAjmc();
        if(ajmc==null){
            ajmc = "[快速预约]无案件名称";
        }
        this.glr = yyb.getGldsr();
        this.jfr = yyb.getJfr();
        this.lfrlxfs = yyb.getLfrlxfs();
    }

    public YyxqModel(Yyb yyb,String yajgl,String dsr,String glr){
        this.bh = yyb.getBh();
        this.zt = yyb.getYyzt();
        this.status = YYZTEnum.getMsgByStatus(yyb.getYyzt());
        this.lfsjStr = DateUtil.format(yyb.getLfsj(),DateUtil.shortFormat);
        this.yysjStr = DateUtil.format(yyb.getYysj(),DateUtil.shortFormat);
        this.jfdd = yyb.getJfdd();
        this.jfdh =yyb.getJfrlxfs();
        this.lfrxm =yyb.getLfr();
        this.bz =yyb.getBz();
        this.lfsy = yyb.getLfsy();
        this.ah = yyb.getAh();
        this.ajmc = yyb.getAjmc();
        this.yajgl = yajgl;
        this.dsr = dsr;
        this.glr = glr;
    }

    public String getLfrxm() {
        return lfrxm;
    }

    public void setLfrxm(String lfrxm) {
        this.lfrxm = lfrxm;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJfdd() {
        return jfdd;
    }

    public void setJfdd(String jfdd) {
        this.jfdd = jfdd;
    }

    public String getDsr() {
        return dsr;
    }

    public void setDsr(String dsr) {
        this.dsr = dsr;
    }

    public String getJfdh() {
        return jfdh;
    }

    public void setJfdh(String jfdh) {
        this.jfdh = jfdh;
    }

    public String getGlr() {
        return glr;
    }

    public void setGlr(String glr) {
        this.glr = glr;
    }

    public void setYajgl(String yajgl) {
        this.yajgl = yajgl;
    }

    public String getJfr() {
        return jfr;
    }

    public void setJfr(String jfr) {
        this.jfr = jfr;
    }

    public String getLfrlxfs() {
        return lfrlxfs;
    }
}
