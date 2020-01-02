package nju.software.baseframework.data.vo;

import java.util.Date;

/**
 * @description 信访库的公告
 * Created by johnl on 2019/1/17.
 */
public class GgxxModel {
    private int ajxh;
    private String ah;
    private String fymc;
    private String lar;
    private String ggnr;
    private String ggmc;
    private Date fbsj;

    public GgxxModel(int ajxh, String ah, String fymc, String lar, String ggnr, String ggmc, Date fbsj) {
        this.ajxh = ajxh;
        this.ah = ah;
        this.fymc = fymc;
        this.lar = lar;
        this.ggnr = ggnr;
        this.ggmc = ggmc;
        this.fbsj = fbsj;
    }

    public int getAjxh() {
        return ajxh;
    }

    public String getAh() {
        return ah;
    }

    public String getFymc() {
        return fymc;
    }

    public String getLar() {
        return lar;
    }

    public String getGgnr() {
        return ggnr;
    }

    public String getGgmc() {
        return ggmc;
    }

    public Date getFbsj() {
        return fbsj;
    }

    public void setAjxh(int ajxh) {
        this.ajxh = ajxh;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public void setFymc(String fymc) {
        this.fymc = fymc;
    }

    public void setLar(String lar) {
        this.lar = lar;
    }

    public void setGgnr(String ggnr) {
        this.ggnr = ggnr;
    }

    public void setGgmc(String ggmc) {
        this.ggmc = ggmc;
    }

    public void setFbsj(Date fbsj) {
        this.fbsj = fbsj;
    }
}
