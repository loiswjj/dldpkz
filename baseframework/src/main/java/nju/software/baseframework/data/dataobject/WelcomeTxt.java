package nju.software.baseframework.data.dataobject;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "welcometxt")
@DynamicInsert
@DynamicUpdate
public class WelcomeTxt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nr")
    private String nr = "";

    @Column(name = "path")
    private String path;

    /**
     * 0 - 不是模板
     * 1 - 是模板
     */
    @Column(name = "lb")
    private Integer lb;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLb(Integer lb) {
        this.lb = lb;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public Integer getLb() {
        return lb;
    }

    public String getNr() {
        return nr;
    }

    public String getPath() {
        return path;
    }

    public WelcomeTxt(String path,Integer lb){
        this.path = path;
        this.lb = lb;
    }
}
