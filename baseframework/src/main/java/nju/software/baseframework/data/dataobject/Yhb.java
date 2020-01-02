package nju.software.baseframework.data.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PUB_XTGL_YHB")
public class Yhb {
    private Integer yhbh;
    private String yhmc;
    private String yhdm;
    private String yhkl;
    private String yhbm;
    private Integer sfyx = 1;
    private Integer yhqx = 1;

    @Column(name = "YHKL")
    public String getYhkl() {
        return yhkl;
    }

    @Id
    @Column(name = "YHBH")
    public Integer getYhbh() {
        return yhbh;
    }

    @Column(name = "YHDM")
    public String getYhdm() {
        return yhdm;
    }

    @Column(name = "YHMC")
    public String getYhmc() {
        return yhmc;
    }

    @Column(name = "YHBM")
    public String getYhbm() {
        return yhbm;
    }

    @Column(name = "SFYX")
    public Integer getSfyx() {
        return sfyx;
    }

    @Column(name = "YHQX")
    public Integer getYhqx() {
        return yhqx;
    }

    public void setSfyx(Integer sfyx) {
        this.sfyx = sfyx;
    }

    public void setYhbh(Integer yhbh) {
        this.yhbh = yhbh;
    }

    public void setYhdm(String yhdm) {
        this.yhdm = yhdm;
    }

    public void setYhkl(String yhkl) {
        this.yhkl = yhkl;
    }

    public void setYhmc(String yhmc) {
        this.yhmc = yhmc;
    }

    public void setYhbm(String yhbm) {
        this.yhbm = yhbm;
    }

    public void setYhqx(Integer yhqx) {
        this.yhqx = yhqx;
    }
}
