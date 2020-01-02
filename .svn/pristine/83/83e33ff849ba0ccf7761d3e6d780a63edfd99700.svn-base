package nju.software.baseframework.data.vo;

import nju.software.baseframework.data.dataobject.AJjb;
import nju.software.baseframework.util.DateUtil;

import java.util.Map;

/**
 * Created by 28643 on 2018/12/6.
 */
public class AjxqModel {

    private Integer ajxh;
    private String ah    ;
    private String ajmc  ;
    private String sycx  ;
    private String larq  ;
    private String syslts;
    private String spz   ;
    private String spy   ;
    private String sjy ;
    private String fgzl  ;
    private String dsr   ;
    private String gllxr ;
    private String bm;

    public AjxqModel(AJjb aJjb, Map<String,String> spry,String dsr,String gllxr){
        this.ajxh = aJjb.getAjxh();
        this.ah = aJjb.getAh();
        this.ajmc = aJjb.getAjmc();
        this.sycx = SYCXEnum.getMcByBh(aJjb.getSycx());
        this.larq = DateUtil.format(aJjb.getLarq(),DateUtil.shortFormat);
        this.spz = spry.get("spz");
        this.sjy = spry.get("sjy");
        this.fgzl = spry.get("fgzl");
        this.spy = spry.get("spy");
        this.dsr = dsr;
        this.gllxr = gllxr;
        this.bm = aJjb.getBaspt();
    }

    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getAjmc() {
        return ajmc;
    }

    public void setAjmc(String ajmc) {
        this.ajmc = ajmc;
    }

    public String getSycx() {
        return sycx;
    }

    public void setSycx(String sycx) {
        this.sycx = sycx;
    }

    public String getLarq() {
        return larq;
    }

    public void setLarq(String larq) {
        this.larq = larq;
    }

    public String getSyslts() {
        return syslts;
    }

    public void setSyslts(String syslts) {
        this.syslts = syslts;
    }

    public String getSpz() {
        return spz;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    public String getSpy() {
        return spy;
    }

    public void setSpy(String spy) {
        this.spy = spy;
    }

    public String getFgzl() {
        return fgzl;
    }

    public void setFgzl(String fgzl) {
        this.fgzl = fgzl;
    }

    public String getDsr() {
        return dsr;
    }

    public void setDsr(String dsr) {
        this.dsr = dsr;
    }

    public String getGllxr() {
        return gllxr;
    }

    public void setGllxr(String gllxr) {
        this.gllxr = gllxr;
    }

    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    public String getSjy() {
        return sjy;
    }

    public void setSjy(String sjy) {
        this.sjy = sjy;
    }
}
