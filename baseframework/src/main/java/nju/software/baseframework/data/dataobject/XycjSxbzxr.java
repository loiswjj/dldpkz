package nju.software.baseframework.data.dataobject;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "XYCJ_SXBZXR")
public class XycjSxbzxr {
    private String id;
    private String ahdm;
    private String ah;
    private Date lasj;
    private String bzxrmc;
    private String ztmc;
    private String bzxrlxmc;
    private String xbmc;
    private Integer nl;
    private String zjlxmc;
    private String zjhm;
    private String zzjgdm;
    private String fddbr;
    private String zxyjzw;
    private String lxqkmc;
    private String ylxqk;
    private String wlxqk;
    private Short bszgy;
    private String zxfymc;
    private String zxyjzzdw;
    private String zxyjwh;
    private Date gbrq;
    private String zxay;
    private String sxxwqxmc;
    private String cpws;
    private Timestamp fbsj;
    private Timestamp jzsj;
    private Timestamp pbsj;
    private String pbyymc;
    private Timestamp cxsj;
    private String cxyymc;
    private Timestamp lastupdate;
    private String fydm;
    private String dz;

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "AHDM")
    public String getAhdm() {
        return ahdm;
    }

    public void setAhdm(String ahdm) {
        this.ahdm = ahdm;
    }

    @Basic
    @Column(name = "AH")
    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    @Basic
    @Column(name = "LASJ")
    public Date getLasj() {
        return lasj;
    }

    public void setLasj(Date lasj) {
        this.lasj = lasj;
    }

    @Basic
    @Column(name = "BZXRMC")
    public String getBzxrmc() {
        return bzxrmc;
    }

    public void setBzxrmc(String bzxrmc) {
        this.bzxrmc = bzxrmc;
    }

    @Basic
    @Column(name = "ZTMC")
    public String getZtmc() {
        return ztmc;
    }

    public void setZtmc(String ztmc) {
        this.ztmc = ztmc;
    }

    @Basic
    @Column(name = "BZXRLXMC")
    public String getBzxrlxmc() {
        return bzxrlxmc;
    }

    public void setBzxrlxmc(String bzxrlxmc) {
        this.bzxrlxmc = bzxrlxmc;
    }

    @Basic
    @Column(name = "XBMC")
    public String getXbmc() {
        return xbmc;
    }

    public void setXbmc(String xbmc) {
        this.xbmc = xbmc;
    }

    @Basic
    @Column(name = "NL")
    public Integer getNl() {
        return nl;
    }

    public void setNl(Integer nl) {
        this.nl = nl;
    }

    @Basic
    @Column(name = "ZJLXMC")
    public String getZjlxmc() {
        return zjlxmc;
    }

    public void setZjlxmc(String zjlxmc) {
        this.zjlxmc = zjlxmc;
    }

    @Basic
    @Column(name = "ZJHM")
    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    @Basic
    @Column(name = "ZZJGDM")
    public String getZzjgdm() {
        return zzjgdm;
    }

    public void setZzjgdm(String zzjgdm) {
        this.zzjgdm = zzjgdm;
    }

    @Basic
    @Column(name = "FDDBR")
    public String getFddbr() {
        return fddbr;
    }

    public void setFddbr(String fddbr) {
        this.fddbr = fddbr;
    }

    @Basic
    @Column(name = "ZXYJZW")
    public String getZxyjzw() {
        return zxyjzw;
    }

    public void setZxyjzw(String zxyjzw) {
        this.zxyjzw = zxyjzw;
    }

    @Basic
    @Column(name = "LXQKMC")
    public String getLxqkmc() {
        return lxqkmc;
    }

    public void setLxqkmc(String lxqkmc) {
        this.lxqkmc = lxqkmc;
    }

    @Basic
    @Column(name = "YLXQK")
    public String getYlxqk() {
        return ylxqk;
    }

    public void setYlxqk(String ylxqk) {
        this.ylxqk = ylxqk;
    }

    @Basic
    @Column(name = "WLXQK")
    public String getWlxqk() {
        return wlxqk;
    }

    public void setWlxqk(String wlxqk) {
        this.wlxqk = wlxqk;
    }

    @Basic
    @Column(name = "BSZGY")
    public Short getBszgy() {
        return bszgy;
    }

    public void setBszgy(Short bszgy) {
        this.bszgy = bszgy;
    }

    @Basic
    @Column(name = "ZXFYMC")
    public String getZxfymc() {
        return zxfymc;
    }

    public void setZxfymc(String zxfymc) {
        this.zxfymc = zxfymc;
    }

    @Basic
    @Column(name = "ZXYJZZDW")
    public String getZxyjzzdw() {
        return zxyjzzdw;
    }

    public void setZxyjzzdw(String zxyjzzdw) {
        this.zxyjzzdw = zxyjzzdw;
    }

    @Basic
    @Column(name = "ZXYJWH")
    public String getZxyjwh() {
        return zxyjwh;
    }

    public void setZxyjwh(String zxyjwh) {
        this.zxyjwh = zxyjwh;
    }

    @Basic
    @Column(name = "GBRQ")
    public Date getGbrq() {
        return gbrq;
    }

    public void setGbrq(Date gbrq) {
        this.gbrq = gbrq;
    }

    @Basic
    @Column(name = "ZXAY")
    public String getZxay() {
        return zxay;
    }

    public void setZxay(String zxay) {
        this.zxay = zxay;
    }

    @Basic
    @Column(name = "SXXWQXMC")
    public String getSxxwqxmc() {
        return sxxwqxmc;
    }

    public void setSxxwqxmc(String sxxwqxmc) {
        this.sxxwqxmc = sxxwqxmc;
    }

    @Basic
    @Column(name = "CPWS")
    public String getCpws() {
        return cpws;
    }

    public void setCpws(String cpws) {
        this.cpws = cpws;
    }

    @Basic
    @Column(name = "FBSJ")
    public Timestamp getFbsj() {
        return fbsj;
    }

    public void setFbsj(Timestamp fbsj) {
        this.fbsj = fbsj;
    }

    @Basic
    @Column(name = "JZSJ")
    public Timestamp getJzsj() {
        return jzsj;
    }

    public void setJzsj(Timestamp jzsj) {
        this.jzsj = jzsj;
    }

    @Basic
    @Column(name = "PBSJ")
    public Timestamp getPbsj() {
        return pbsj;
    }

    public void setPbsj(Timestamp pbsj) {
        this.pbsj = pbsj;
    }

    @Basic
    @Column(name = "PBYYMC")
    public String getPbyymc() {
        return pbyymc;
    }

    public void setPbyymc(String pbyymc) {
        this.pbyymc = pbyymc;
    }

    @Basic
    @Column(name = "CXSJ")
    public Timestamp getCxsj() {
        return cxsj;
    }

    public void setCxsj(Timestamp cxsj) {
        this.cxsj = cxsj;
    }

    @Basic
    @Column(name = "CXYYMC")
    public String getCxyymc() {
        return cxyymc;
    }

    public void setCxyymc(String cxyymc) {
        this.cxyymc = cxyymc;
    }

    @Basic
    @Column(name = "LASTUPDATE")
    public Timestamp getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Timestamp lastupdate) {
        this.lastupdate = lastupdate;
    }

    @Basic
    @Column(name = "FYDM")
    public String getFydm() {
        return fydm;
    }

    public void setFydm(String fydm) {
        this.fydm = fydm;
    }

    @Basic
    @Column(name = "DZ")
    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        XycjSxbzxr that = (XycjSxbzxr) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(ahdm, that.ahdm) &&
                Objects.equals(ah, that.ah) &&
                Objects.equals(lasj, that.lasj) &&
                Objects.equals(bzxrmc, that.bzxrmc) &&
                Objects.equals(ztmc, that.ztmc) &&
                Objects.equals(bzxrlxmc, that.bzxrlxmc) &&
                Objects.equals(xbmc, that.xbmc) &&
                Objects.equals(nl, that.nl) &&
                Objects.equals(zjlxmc, that.zjlxmc) &&
                Objects.equals(zjhm, that.zjhm) &&
                Objects.equals(zzjgdm, that.zzjgdm) &&
                Objects.equals(fddbr, that.fddbr) &&
                Objects.equals(zxyjzw, that.zxyjzw) &&
                Objects.equals(lxqkmc, that.lxqkmc) &&
                Objects.equals(ylxqk, that.ylxqk) &&
                Objects.equals(wlxqk, that.wlxqk) &&
                Objects.equals(bszgy, that.bszgy) &&
                Objects.equals(zxfymc, that.zxfymc) &&
                Objects.equals(zxyjzzdw, that.zxyjzzdw) &&
                Objects.equals(zxyjwh, that.zxyjwh) &&
                Objects.equals(gbrq, that.gbrq) &&
                Objects.equals(zxay, that.zxay) &&
                Objects.equals(sxxwqxmc, that.sxxwqxmc) &&
                Objects.equals(cpws, that.cpws) &&
                Objects.equals(fbsj, that.fbsj) &&
                Objects.equals(jzsj, that.jzsj) &&
                Objects.equals(pbsj, that.pbsj) &&
                Objects.equals(pbyymc, that.pbyymc) &&
                Objects.equals(cxsj, that.cxsj) &&
                Objects.equals(cxyymc, that.cxyymc) &&
                Objects.equals(lastupdate, that.lastupdate) &&
                Objects.equals(fydm, that.fydm) &&
                Objects.equals(dz, that.dz);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, ahdm, ah, lasj, bzxrmc, ztmc, bzxrlxmc, xbmc, nl, zjlxmc, zjhm, zzjgdm, fddbr, zxyjzw, lxqkmc, ylxqk, wlxqk, bszgy, zxfymc, zxyjzzdw, zxyjwh, gbrq, zxay, sxxwqxmc, cpws, fbsj, jzsj, pbsj, pbyymc, cxsj, cxyymc, lastupdate, fydm, dz);
    }
}
