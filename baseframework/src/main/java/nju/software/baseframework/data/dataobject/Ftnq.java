package nju.software.baseframework.data.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @description 法庭内勤表，获取开庭公告信息
 * Created by johnl on 2019/1/17.
 */
@Entity
@Table(name = "FTNQ_FTSYSQDJ")
public class Ftnq {
    @Id
    @Column(name = "AJXH")
    private Integer ajxh;
    @Column(name = "SJFT")
    private String  ft; //法庭
    @Column(name = "RQ")
    private Date ktrq; //开庭日期
    @Column(name = "SFKTSL")
    private String sfktsl; //是否开庭审理

    public int getAjxh() {
        return ajxh;
    }

    public String getFt() {
        return ft;
    }

    public Date getKtrq() {
        return ktrq;
    }

    public void setAjxh(int ajxh) {
        this.ajxh = ajxh;
    }

    public void setFt(String ft) {
        this.ft = ft;
    }

    public void setKtrq(Date ktrq) {
        this.ktrq = ktrq;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public String getSfktsl() {
        return sfktsl;
    }

    public void setSfktsl(String sfktsl) {
        this.sfktsl = sfktsl;
    }
}
