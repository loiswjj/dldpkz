package nju.software.baseframework.data.vo;

import nju.software.baseframework.data.dataobject.Yyb;
import nju.software.baseframework.util.DateUtil;

public class Yyxx {
    private String xm;
    private String rq;
    private String sfzhm;
    private String dz;
    private String fgxm;

    public void setDz(String dz) {
        this.dz = dz;
    }

    public void setFgxm(String fgxm) {
        this.fgxm = fgxm;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public void setSfzhm(String sfzhm) {
        this.sfzhm = sfzhm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getRq() {
        return rq;
    }

    public String getDz() {
        return dz;
    }

    public String getFgxm() {
        return fgxm;
    }

    public String getSfzhm() {
        return sfzhm;
    }

    public String getXm() {
        return xm;
    }

    public Yyxx(Yyb yyb){
        this.xm = yyb.getLfr();
        this.dz = yyb.getJfdd();
        this.fgxm = yyb.getJfr();
        this.rq = DateUtil.format(yyb.getLfsj(),DateUtil.WebFormat);
        this.sfzhm = yyb.getLfrsfzh();
    }
}
