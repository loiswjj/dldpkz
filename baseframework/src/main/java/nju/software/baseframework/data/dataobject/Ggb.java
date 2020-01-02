package nju.software.baseframework.data.dataobject;

import nju.software.baseframework.data.vo.GgxxModel;
import nju.software.baseframework.data.vo.KtggModel;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 28643 on 2018/11/30.
 */
/*
* 公告表
* */
@Entity
@Table(name = "pub_ggb")
@DynamicInsert
@DynamicUpdate
public class Ggb {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BH")
    private Integer bh ;

    @Column(name = "AJXH")
    private Integer ajxh;
    @Column(name = "GGMC")
    private String ggmc ;

    /**
     * 0 -- 未发布
     * 1 -- 正在发布
     * 2 -- 已发布
     * 3 -- 已作废
     * 4 -- 已过期
     * 5 -- 审核成功
     * 6 -- 审核中
     * 7 -- 审核失败
     */
    @Column(name = "STATUS")
    private Integer status;

    /**
     * 1 -- 送达起诉
     * 2 -- 开庭排期公告
     * 3 -- 执行公告
     * 4 -- 失信被执行人公告
     * 5 -- 送达裁定书公告
     * 6 -- 自定义公告
     * 7 -- 信访公告
     */
    @Column(name = "GGLX")
    private Integer gglx ;

    @Column(name = "BFFS")
    private Integer bffs = 0 ;

    @Column(name = "BFKSSJ")
    private Date bfkssj ;

    @Column(name = "BFJSSJ")
    private Date bfjssj ;
    @Column(name = "BFKSSD")
    private Date bfkssd ;
    @Column(name = "SHSM")
    private String shsm ;
    @Column(name = "FBSJ")
    private Date fbsj;
    @Column(name = "KTSJ")
    private Date ktsj;
    @Column(name = "GGDQSJ")
    private Date dqsj;
    @Column(name = "GGSC")
    private Integer ggsc =30;
    @Column(name = "AH")
    private String ah ;
    @Column(name = "AJMC")
    private String ajmc ;
    @Column(name = "FBR")
    private String fbr ;
    @Column(name = "GGNR")
    private String ggnr ;
    @Column(name = "opers")
    private Integer opers=0;
    @Column(name = "SFBF")
    private Integer sfbf=1;

    public Integer getBh() {
        return bh;
    }

    public void setBh(Integer bh) {
        this.bh = bh;
    }

    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    public String getGgmc() {
        return ggmc;
    }

    public void setGgmc(String ggmc) {
        this.ggmc = ggmc;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getGglx() {
        return gglx;
    }

    public int getBffs() {
        return bffs;
    }

    public void setBffs(int bffs) {
        this.bffs = bffs;
    }

    public Date getBfkssj() {
        return bfkssj;
    }

    public void setBfkssj(Date bfkssj) {
        this.bfkssj = bfkssj;
    }

    public Date getBfjssj() {
        return bfjssj;
    }

    public void setBfjssj(Date bfjssj) {
        this.bfjssj = bfjssj;
    }

    public Date getBfkssd() {
        return bfkssd;
    }

    public void setBfkssd(Date bfkssd) {
        this.bfkssd = bfkssd;
    }

    public String getShsm() {
        return shsm;
    }

    public void setShsm(String shsm) {
        this.shsm = shsm;
    }

    public Date getFbsj() {
        return fbsj;
    }

    public void setFbsj(Date fbsj) {
        this.fbsj = fbsj;
    }

    public int getGgsc() {
        return ggsc;
    }

    public void setGgsc(int ggsc) {
        this.ggsc = ggsc;
    }

    public String getAh() {
        return ah;
    }

    public void setAh(String ah) {
        this.ah = ah;
    }

    public String getAjmc() {
        return ajmc;
    }

    public void setAjmc(String ajmc) {
        this.ajmc = ajmc;
    }

    public String getFbr() {
        return fbr;
    }

    public void setFbr(String fbr) {
        this.fbr = fbr;
    }

    public String getGgnr() {
        return ggnr;
    }

    public void setGgnr(String ggnr) {
        this.ggnr = ggnr;
    }

    public Integer getOpers() {
        return opers;
    }

    public void setOpers(Integer opers) {
        this.opers = opers;
    }

    public void setGglx(Integer gglx) {
        this.gglx = gglx;
    }

    public void setDqsj(Date dqsj) {
        this.dqsj = dqsj;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setBffs(Integer bffs) {
        this.bffs = bffs;
    }

    public void setGgsc(Integer ggsc) {
        this.ggsc = ggsc;
    }

    public void setSfbf(Integer sfbf) {
        this.sfbf = sfbf;
    }

    public Integer getSfbf() {
        return sfbf;
    }

    public Date getDqsj() {
        return dqsj;
    }

    public Date getKtsj() {
        return ktsj;
    }

    public void setKtsj(Date ktsj) {
        this.ktsj = ktsj;
    }

    public Ggb(){}

    public Ggb(KtggModel ktgg,String fymc){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        this.ajxh = ktgg.getAjxh();
        this.ggmc = "开庭排期公告";
        this.gglx = 2;
        if (fymc.equals("天津市宁河区人民法院")){
            this.status = 2;
        }else {
            this.status = 1;
        }
        this.fbsj = new Date();
        this.ktsj = ktgg.getKtrq();
        this.ah = ktgg.getAh();
        this.ajmc = ktgg.getAjmc();
        this.fbr = "系统管理员";
        this.ggnr = "本院定于"+sdf.format(ktgg.getKtrq())+"在"+ktgg.getFt()+"公开审理"+ktgg.getAjmc()+"一案。";
    }

    public Ggb(GgxxModel ggxx){
        this.ajxh = ggxx.getAjxh();
        this.ah = ggxx.getAh();
        this.fbr = ggxx.getLar();
        this.gglx = 8;
        this.status = 1;
        this.ggmc = ggxx.getGgmc();
        this.ggnr = ggxx.getGgnr();
        this.fbsj = ggxx.getFbsj();
    }

    public Ggb(String s){
        this.ggnr = s;
    }
}
