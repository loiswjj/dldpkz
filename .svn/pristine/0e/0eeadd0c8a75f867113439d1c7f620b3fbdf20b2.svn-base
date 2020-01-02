package nju.software.baseframework.data.dataobject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "gnb", schema = "test")
public class GnbDO {
    private int bh;
    private String gnms;
    private Integer status;
    private Integer lbbh;

    @Id
    @Column(name = "bh")
    public int getBh() {
        return bh;
    }

    public void setBh(int bh) {
        this.bh = bh;
    }

    @Basic
    @Column(name = "gnms")
    public String getGnms() {
        return gnms;
    }

    public void setGnms(String gnms) {
        this.gnms = gnms;
    }

    @Basic
    @Column(name = "lbbh")
    public Integer getLbbh() {
        return lbbh;
    }

    public void setLbbh(Integer lbbh) {
        this.lbbh = lbbh;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GnbDO gnbDO = (GnbDO) o;
        return bh == gnbDO.bh &&
                Objects.equals(gnms, gnbDO.gnms) &&
                Objects.equals(status, gnbDO.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bh, gnms, status);
    }
}
