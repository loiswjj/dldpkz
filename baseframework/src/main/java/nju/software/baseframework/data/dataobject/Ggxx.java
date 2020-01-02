package nju.software.baseframework.data.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * @description 信访库中的公告
 * Created by johnl on 2019/1/17.
 */
@Entity
@Table(name = "PUB_GGXX")
public class Ggxx {
    @Id
    @Column(name = "AJXH")
    private int ajxh;
    @Column(name = "AH")
    private String ah;
    @Column(name = "FYBH")
    private String fybh;
    @Column(name = "FYMC")
    private String fymc;
    @Column(name = "LAR")
    private String lar;
    @Column(name = "GGNR")
    private String ggnr;
    @Column(name = "GGLBID")
    private int gglbid;
    @Column(name = "GYTIME")
    private Date fbsj; //高院审核时间
    /** 0未审核，1审核，3导出*/
    @Column(name = "GYSTATUS")
    private int gyStatus;

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

    public void setFbsj(Date fbsj) {
        this.fbsj = fbsj;
    }

    public int getGglbid() {
        return gglbid;
    }

    public void setGglbid(int gglbid) {
        this.gglbid = gglbid;
    }

    public int getGyStatus() {
        return gyStatus;
    }

    public void setGyStatus(int gyStatus) {
        this.gyStatus = gyStatus;
    }

    public String getFybh() {
        return fybh;
    }

    public void setFybh(String fybh) {
        this.fybh = fybh;
    }
}
