package nju.software.baseframework.data.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @description 信访库公告的代码表，用于识别公告类型
 * Created by johnl on 2019/1/17.
 */
@Entity
@Table(name = "PUB_GGLB_DMB")
public class GgxxDmb {
    @Id
    @Column(name = "GGLBID")
    private int gglbid;
    @Column(name = "GGMC")
    private String ggmc;

    public int getGglbid() {
        return gglbid;
    }

    public String getGgmc() {
        return ggmc;
    }

    public void setGglbid(int gglbid) {
        this.gglbid = gglbid;
    }

    public void setGgmc(String ggmc) {
        this.ggmc = ggmc;
    }
}
