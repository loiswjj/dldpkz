package nju.software.baseframework.data.dataobject;

import javax.persistence.*;
import java.util.Date;

/**
 * DsrJb entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "DSR_JB")
@IdClass(value=DsrJbId.class)
public class DsrJb implements java.io.Serializable {

	// Fields
    private Integer ajxh;
    private Integer dsrbh;
	private String dsrssdw;
	private String dsrlb;
	private String dsrjc;
	private String sfssdbr;
	private String dsrbgfs;
	private String dsrbgyy;
	private Date dsrbgsj;
	private String sfsa;
	private String sfsg;
	private String sfsq;
	private String sfst;
	private String sfsw;
	private String scbc;
	private String sfqxsp;
	private String dsrqxyy;
	private String ssdr;
	private String sddz;


	// Constructors

	/** default constructor */
	public DsrJb() {
	}

	/** minimal constructor */
    public DsrJb(Integer ajxh, Integer dsrbh) {
        this.ajxh = ajxh;
        this.dsrbh = dsrbh;
    }

    /** full constructor */
	public DsrJb(Integer ajxh, Integer dsrbh, String dsrssdw, String dsrlb, String dsrjc,
			String sfssdbr, String dsrbgfs, String dsrbgyy, Date dsrbgsj,
			String sfsa, String sfsg, String sfsq, String sfst, String sfsw, String scbc, String sfqxsp,
			String dsrqxyy, String ssdr, String sddz) {
        this.ajxh = ajxh;
        this.dsrbh = dsrbh;
		this.dsrssdw = dsrssdw;
		this.dsrlb = dsrlb;
		this.dsrjc = dsrjc;
		this.sfssdbr = sfssdbr;
		this.dsrbgfs = dsrbgfs;
		this.dsrbgyy = dsrbgyy;
		this.dsrbgsj = dsrbgsj;
		this.sfsa = sfsa;
		this.sfsg = sfsg;
		this.sfsq = sfsq;
		this.sfst = sfst;
		this.sfsw = sfsw;
		this.scbc = scbc;
		this.sfqxsp = sfqxsp;
		this.dsrqxyy = dsrqxyy;
		this.ssdr = ssdr;
		this.sddz = sddz;

	}

	// Property accessors
    @Id
    @Column(name = "AJXH")
    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    @Id
	@Column(name = "DSRBH")
    public Integer getDsrbh() {
        return dsrbh;
    }

    public void setDsrbh(Integer dsrbh) {
        this.dsrbh = dsrbh;
    }

    @Column(name = "DSRSSDW", length = 10)
	public String getDsrssdw() {
		return this.dsrssdw;
	}

	public void setDsrssdw(String dsrssdw) {
		this.dsrssdw = dsrssdw;
	}

	@Column(name = "DSRLB", length = 10)
	public String getDsrlb() {
		return this.dsrlb;
	}

	public void setDsrlb(String dsrlb) {
		this.dsrlb = dsrlb;
	}

	@Column(name = "DSRJC", length = 200)
	public String getDsrjc() {
		return this.dsrjc;
	}

	public void setDsrjc(String dsrjc) {
		this.dsrjc = dsrjc;
	}

	@Column(name = "SFSSDBR", length = 1)
	public String getSfssdbr() {
		return this.sfssdbr;
	}

	public void setSfssdbr(String sfssdbr) {
		this.sfssdbr = sfssdbr;
	}

	@Column(name = "DSRBGFS", length = 10)
	public String getDsrbgfs() {
		return this.dsrbgfs;
	}

	public void setDsrbgfs(String dsrbgfs) {
		this.dsrbgfs = dsrbgfs;
	}

	@Column(name = "DSRBGYY", length = 10)
	public String getDsrbgyy() {
		return this.dsrbgyy;
	}

	public void setDsrbgyy(String dsrbgyy) {
		this.dsrbgyy = dsrbgyy;
	}

	@Column(name = "DSRBGSJ", length = 23)
	public Date getDsrbgsj() {
		return this.dsrbgsj;
	}

	public void setDsrbgsj(Date dsrbgsj) {
		this.dsrbgsj = dsrbgsj;
	}

	@Column(name = "SFSA", length = 1)
	public String getSfsa() {
		return this.sfsa;
	}

	public void setSfsa(String sfsa) {
		this.sfsa = sfsa;
	}

	@Column(name = "SFSG", length = 1)
	public String getSfsg() {
		return this.sfsg;
	}

	public void setSfsg(String sfsg) {
		this.sfsg = sfsg;
	}

	@Column(name = "SFSQ", length = 1)
	public String getSfsq() {
		return this.sfsq;
	}

	public void setSfsq(String sfsq) {
		this.sfsq = sfsq;
	}

	@Column(name = "SFST", length = 1)
	public String getSfst() {
		return this.sfst;
	}

	public void setSfst(String sfst) {
		this.sfst = sfst;
	}

	@Column(name = "SFSW", length = 1)
	public String getSfsw() {
		return this.sfsw;
	}

	public void setSfsw(String sfsw) {
		this.sfsw = sfsw;
	}

	@Column(name = "SCBC")
	public String getScbc() {
		return this.scbc;
	}

	public void setScbc(String scbc) {
		this.scbc = scbc;
	}

	@Column(name = "SFQXSP", length = 10)
	public String getSfqxsp() {
		return this.sfqxsp;
	}

	public void setSfqxsp(String sfqxsp) {
		this.sfqxsp = sfqxsp;
	}

	@Column(name = "DSRQXYY", length = 200)
	public String getDsrqxyy() {
		return this.dsrqxyy;
	}

	public void setDsrqxyy(String dsrqxyy) {
		this.dsrqxyy = dsrqxyy;
	}

	@Column(name = "SSDR", length = 200)
	public String getSsdr() {
		return this.ssdr;
	}

	public void setSsdr(String ssdr) {
		this.ssdr = ssdr;
	}

	@Column(name = "SDDZ", length = 200)
	public String getSddz() {
		return this.sddz;
	}

	public void setSddz(String sddz) {
		this.sddz = sddz;
	}
}