package nju.software.baseframework.data.dataobject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "pub_xtgl_yhjsgxb", schema = "test", catalog = "")
public class PubXtglYhjsgxbDO {
    private Integer yhbh;
    private Integer jsbh;
    private String sffa;

    @Id
    @Column(name = "YHBH")
    public Integer getYhbh() {
        return yhbh;
    }

    public void setYhbh(Integer yhbh) {
        this.yhbh = yhbh;
    }

    @Basic
    @Column(name = "JSBH")
    public Integer getJsbh() {
        return jsbh;
    }

    public void setJsbh(Integer jsbh) {
        this.jsbh = jsbh;
    }

    @Basic
    @Column(name = "SFFA")
    public String getSffa() {
        return sffa;
    }

    public void setSffa(String sffa) {
        this.sffa = sffa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PubXtglYhjsgxbDO that = (PubXtglYhjsgxbDO) o;
        return Objects.equals(yhbh, that.yhbh) &&
                Objects.equals(jsbh, that.jsbh) &&
                Objects.equals(sffa, that.sffa);
    }

    @Override
    public int hashCode() {

        return Objects.hash(yhbh, jsbh, sffa);
    }
}
