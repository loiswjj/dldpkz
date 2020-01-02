package nju.software.baseframework.data.dataobject;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by johnl on 2019/1/21.
 */
@Entity
@Table(name = "screen")
@DynamicInsert
@DynamicUpdate
public class Screen {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ip")
    private String ip;
    @Column(name = "width")
    private int width = 0;
    @Column(name = "height")
    private int height = 0;
    /**
     * 屏幕类型
     * 0 - 外屏
     * 1 - 内屏
     */
    @Column(name = "lx")
    private Integer lx = 0;

    /**
     * 屏幕播放内容的类别
     * 0 - 公告
     * 1 - 欢迎词ID
     * 2 - 只播视频
     */
    @Column(name = "nrlb")
    private Integer nrlb;

    /**
     * 是否需要刷新
     * 0 - 不需要
     * 1 - 需要
     */
    @Column(name = "status")
    private Integer status = 0;

    @Column(name = "selectarr")
    private String selectarr ="";

    public int getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLx(Integer lx) {
        this.lx = lx;
    }

    public Integer getLx() {
        return lx;
    }

    public void setNrlb(Integer nrlb) {
        this.nrlb = nrlb;
    }

    public Integer getNrlb() {
        return nrlb;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public String getSelectarr() {
        return selectarr;
    }

    public void setSelectarr(String selectarr) {
        this.selectarr = selectarr;
    }
}
