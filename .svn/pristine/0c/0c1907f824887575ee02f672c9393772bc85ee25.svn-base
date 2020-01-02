package nju.software.baseframework.data.dataobject;

import javax.persistence.*;

@Entity
@Table(name = "DSR_DW")
@IdClass(value = DsrDwId.class)
public class DsrDw implements java.io.Serializable{
    private Integer ajxh;
    private String fddbrxm;
    private String dwmc;
    private Integer dsrbh;

    @Id
    @Column(name = "AJXH")
    public Integer getAjxh() {
        return ajxh;
    }

    @Column(name = "FDDBRXM")
    public String getFddbrxm() {
        return fddbrxm;
    }

    @Column(name = "DWMC")
    public String getDwmc() {
        return dwmc;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public void setFddbrxm(String fddbrxm) {
        this.fddbrxm = fddbrxm;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    @Id
    @Column(name = "DSRBH")
    public Integer getDsrbh() {
        return dsrbh;
    }

    public void setDsrbh(Integer dsrbh) {
        this.dsrbh = dsrbh;
    }
}
