package nju.software.baseframework.data.dataobject;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "pub_xtgl_jsb", schema = "test", catalog = "")
public class PubXtglJsbDO {
    private Integer jsbh;
    private Integer zxtbh;
    private String js;
    private String bm;
    private Integer fybh;
    private Timestamp cjsj;

    @Id
    @Column(name = "JSBH")
    public Integer getJsbh() {
        return jsbh;
    }

    public void setJsbh(Integer jsbh) {
        this.jsbh = jsbh;
    }

    @Basic
    @Column(name = "ZXTBH")
    public Integer getZxtbh() {
        return zxtbh;
    }

    public void setZxtbh(Integer zxtbh) {
        this.zxtbh = zxtbh;
    }

    @Basic
    @Column(name = "JS")
    public String getJs() {
        return js;
    }

    public void setJs(String js) {
        this.js = js;
    }

    @Basic
    @Column(name = "BM")
    public String getBm() {
        return bm;
    }

    public void setBm(String bm) {
        this.bm = bm;
    }

    @Basic
    @Column(name = "FYBH")
    public Integer getFybh() {
        return fybh;
    }

    public void setFybh(Integer fybh) {
        this.fybh = fybh;
    }

    @Basic
    @Column(name = "CJSJ")
    public Timestamp getCjsj() {
        return cjsj;
    }

    public void setCjsj(Timestamp cjsj) {
        this.cjsj = cjsj;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PubXtglJsbDO that = (PubXtglJsbDO) o;
        return Objects.equals(jsbh, that.jsbh) &&
                Objects.equals(zxtbh, that.zxtbh) &&
                Objects.equals(js, that.js) &&
                Objects.equals(bm, that.bm) &&
                Objects.equals(fybh, that.fybh) &&
                Objects.equals(cjsj, that.cjsj);
    }

    @Override
    public int hashCode() {

        return Objects.hash(jsbh, zxtbh, js, bm, fybh, cjsj);
    }
}
