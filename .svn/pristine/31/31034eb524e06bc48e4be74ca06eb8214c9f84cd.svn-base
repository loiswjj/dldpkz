package nju.software.baseframework.data.vo;

import nju.software.baseframework.data.dataobject.AJjb;
import nju.software.baseframework.data.dataobject.PubSpry;
import nju.software.baseframework.util.DateUtil;

import java.util.List;
import java.util.Map;

public class AjlbModel {
    private String ah;
    private String ajmc;
    private String spz;
    private String spy;
    private String fgzl;//法官助理
    private String sjy;//书记员
    private String larq;
    private String sycx;//适用程序
    private String yyzt;//预约状态
    private String cpggzt="";//承办公告状态
    private String pqggzt="";//排期公告状态
    private String sdpjszt=""; //送达裁定书状态
    private String zzgg = "";//执行公关稿
    private String sxgg = "";//失信公告
    private String cdsggzt="";//送达书公告状态
    private String zdyggzt="";//自定义公告状态


    public AjlbModel(AJjb aJjb, Map<String,String> map,String yyzt, String cpggzt, String pqggzt,
                     String zzgg, String sxgg, String cdsggzt, String zdyggzt,String sdcdszt) {
        this.ah = aJjb.getAh();
        this.ajmc = aJjb.getAjmc();
        this.spz = map.get("spz");
        this.spy = map.get("spy");
        this.fgzl = map.get("fgzl");
        this.sjy = map.get("sjy");
        this.larq = DateUtil.format(aJjb.getLarq(),DateUtil.shortFormat);
        this.sycx = SYCXEnum.getMcByBh(aJjb.getSycx());
        this.yyzt = yyzt;
        this.cpggzt = cpggzt;
        this.pqggzt = pqggzt;
        this.zzgg = zzgg;
        this.sxgg = sxgg;
        this.cdsggzt = cdsggzt;
        this.zdyggzt = zdyggzt;
        this.sdpjszt = sdcdszt;
    }

    public void setAjmc(String ajmc) {
        this.ajmc = ajmc;
    }

    public String getAjmc() {
        return ajmc;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getAh() {
        return ah;
    }

    public void setSpy(String spy) {
        this.spy = spy;
    }

    public String getSpy() {
        return spy;
    }

    public void setFgzl(String fgzl) {
        this.fgzl = fgzl;
    }

    public String getFgzl() {
        return fgzl;
    }

    public void setLarq(String larq) {
        this.larq = larq;
    }

    public String getLarq() {
        return larq;
    }

    public void setSjy(String sjy) {
        this.sjy = sjy;
    }

    public String getSjy() {
        return sjy;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    public String getSpz() {
        return spz;
    }

    public void setCdsggzt(String cdsggzt) {
        this.cdsggzt = cdsggzt;
    }

    public String getCdsggzt() {
        return cdsggzt;
    }

    public void setCpggzt(String cpggzt) {
        this.cpggzt = cpggzt;
    }

    public String getCpggzt() {
        return cpggzt;
    }

    public void setPqggzt(String pqggzt) {
        this.pqggzt = pqggzt;
    }

    public String getPqggzt() {
        return pqggzt;
    }

    public void setSxgg(String sxgg) {
        this.sxgg = sxgg;
    }

    public String getYyzt() {
        return yyzt;
    }

    public void setSycx(String sycx) {
        this.sycx = sycx;
    }

    public String getZdyggzt() {
        return zdyggzt;
    }

    public void setYyzt(String yyzt) {
        this.yyzt = yyzt;
    }

    public String getSxgg() {
        return sxgg;
    }

    public void setZdyggzt(String zdyggzt) {
        this.zdyggzt = zdyggzt;
    }

    public String getSycx() {
        return sycx;
    }

    public void setZzgg(String zzgg) {
        this.zzgg = zzgg;
    }

    public String getZzgg() {
        return zzgg;
    }

    public void setSdpjszt(String sdpjszt) {
        this.sdpjszt = sdpjszt;
    }

    public String getSdpjszt() {
        return sdpjszt;
    }
}
