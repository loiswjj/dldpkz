package nju.software.baseframework.data.dataobject;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class PubLaAyPK implements Serializable {
    private Integer ajxh;
    private int laaybh;

    @Column(name = "AJXH")
    @Id
    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    @Column(name = "LAAYBH")
    @Id
    public int getLaaybh() {
        return laaybh;
    }

    public void setLaaybh(int laaybh) {
        this.laaybh = laaybh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PubLaAyPK pubLaAyPK = (PubLaAyPK) o;
        return laaybh == pubLaAyPK.laaybh &&
                Objects.equals(ajxh, pubLaAyPK.ajxh);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ajxh, laaybh);
    }
}
