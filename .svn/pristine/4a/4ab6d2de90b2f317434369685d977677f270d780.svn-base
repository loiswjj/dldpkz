package nju.software.baseframework.data.dataobject;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "PUB_LA_AY")
@IdClass(PubLaAyPK.class)
public class PubLaAy {
    private Integer ajxh;
    private String laay;
    private int laaybh;
    private String ay;

    public void setAjxh(int ajxh) {
        this.ajxh = ajxh;
    }

    @Id
    @Column(name = "AJXH")
    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    @Basic
    @Column(name = "LAAY")
    public String getLaay() {
        return laay;
    }

    public void setLaay(String laay) {
        this.laay = laay;
    }

    @Id
    @Column(name = "LAAYBH")
    public int getLaaybh() {
        return laaybh;
    }

    public void setLaaybh(int laaybh) {
        this.laaybh = laaybh;
    }

    @Basic
    @Column(name = "AY")
    public String getAy() {
        return ay;
    }

    public void setAy(String ay) {
        this.ay = ay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PubLaAy pubLaAy = (PubLaAy) o;
        return laaybh == pubLaAy.laaybh &&
                Objects.equals(ajxh, pubLaAy.ajxh) &&
                Objects.equals(laay, pubLaAy.laay) &&
                Objects.equals(ay, pubLaAy.ay);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ajxh, laaybh, ay, laay);
    }
}
