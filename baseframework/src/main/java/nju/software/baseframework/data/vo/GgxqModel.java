package nju.software.baseframework.data.vo;

import nju.software.baseframework.data.dataobject.Ggb;
import nju.software.baseframework.service.Enum.GgZTEnum;
import nju.software.baseframework.util.DateUtil;

import java.util.Date;

public class GgxqModel {
    private int id;
    private String ggzt;  //公告状态 - explain
    private String gglx;  //公告类型
    private Integer gglx0; //状态number
    private String shsm;  //审核说明
    private String fbsj;  //公告发布时间
    private String ggsc;
    private String ggsysj;  //公告剩余时间
    private String dqsj;
    private String ah;
    private String ajmc;
    private String fbr;
    private String ggnr; //公告内容

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGgzt() {
        return ggzt;
    }

    public void setGgzt(String ggzt) {
        this.ggzt = ggzt;
    }

    public String getGglx() {
        return gglx;
    }

    public void setGglx(String gglx) {
        this.gglx = gglx;
    }

    public String getFbsj() {
        return fbsj;
    }

    public void setFbsj(String fbsj) {
        this.fbsj = fbsj;
    }

    public String getGgsysj() {
        return ggsysj;
    }

    public void setGgsysj(String ggsysj) {
        this.ggsysj = ggsysj;
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

    public void setGgsc(String ggsc) {
        this.ggsc = ggsc;
    }

    public String getGgsc() {
        return ggsc;
    }

    public String getShsm() {
        return shsm;
    }

    public void setShsm(String shsm) {
        this.shsm = shsm;
    }

    public String getDqsj() {
        return dqsj;
    }

    public void setDqsj(String dqsj) {
        this.dqsj = dqsj;
    }

    public String getFbr() {
        return fbr;
    }

    public void setFbr(String fbr) {
        this.fbr = fbr;
    }

    public void setGglx0(Integer gglx0) {
        this.gglx0 = gglx0;
    }

    public Integer getGglx0() {
        return gglx0;
    }

    public String getGgnr() {
        return ggnr;
    }

    public void setGgnr(String ggnr) {
        this.ggnr = ggnr;
    }

    public GgxqModel(Ggb ggb){
        this.id = ggb.getBh();
        this.gglx0 = ggb.getStatus();
        this.ggzt = GgZTEnum.getExplainByNumber(Integer.toString(ggb.getStatus()));
        this.gglx = GGLXEnum.getExplainByNumber(Integer.toString(ggb.getGglx()));
        this.fbsj = DateUtil.format(ggb.getFbsj(),DateUtil.shortFormat);
        this.ggsc = ggb.getGgsc()+"天";
        this.ggsysj = DateUtil.getSysj(ggb.getFbsj(),ggb.getGgsc())+"天";
        this.dqsj = DateUtil.getDqsj(ggb.getFbsj(),ggb.getGgsc());
        this.shsm = ggb.getShsm();
        this.ah = ggb.getAh();
        this.ajmc = ggb.getAjmc();
        this.fbr = ggb.getFbr();
        this.ggnr = ggb.getGgnr();
    }
}
