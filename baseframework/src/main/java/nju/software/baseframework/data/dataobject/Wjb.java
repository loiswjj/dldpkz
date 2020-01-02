package nju.software.baseframework.data.dataobject;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wjb")
@DynamicInsert
@DynamicUpdate
public class Wjb {
    private Integer id;
    private Date scsj;
    private String wjm;
    private String bcwz;  //保存位置
    private String type; //文件类型 video -视频 image -图片
    private Integer zt; //用于控制是否播放 1 - 播放 0 - 不播放
    private String docstr;
    private Integer lx; //LED大屏id

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    @Column(name = "scsj")
    public Date getScsj() {
        return scsj;
    }

    @Column(name = "bcwz")
    public String getBcwz() {
        return bcwz;
    }

    @Column(name = "wjm")
    public String getWjm() {
        return wjm;
    }

    @Column(name = "type")
    public String getType() {
        return type;
    }

    @Column(name = "zt")
    public Integer getZt() {
        return zt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBcwz(String bcwz) {
        this.bcwz = bcwz;
    }

    public void setScsj(Date scsj) {
        this.scsj = scsj;
    }

    public void setWjm(String wjm) {
        this.wjm = wjm;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "docstr")
    public String getDocstr() {
        return docstr;
    }

    public void setDocstr(String docstr) {
        this.docstr = docstr;
    }

    @Column(name ="lx")
    public Integer getLx() {
        return lx;
    }

    public void setLx(Integer lx) {
        this.lx = lx;
    }

    public Wjb(){}

    public void setZt(Integer zt) {
        this.zt = zt;
    }

    public Wjb(String wjm, String bcwz, Date scsj, String type,Integer lx){
        this.wjm = wjm;
        this.bcwz = bcwz;
        this.scsj = scsj;
        this.type = type;
        this.lx = lx;
    }
}
