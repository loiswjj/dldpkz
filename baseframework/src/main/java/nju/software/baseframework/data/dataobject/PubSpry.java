package nju.software.baseframework.data.dataobject;

import javax.persistence.*;
import java.util.Date;

/**
 * PubSpry entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_SPRY")
@IdClass(value=PubSpryId.class)
public class PubSpry implements java.io.Serializable {

	// Fields
    private Integer ajxh;
    private Integer sprybh;
	private String fg;
	private String sfcbr;
	private String sfspz;
	private String sfdlspy;
	private String sfrmpsy;
	private String xm;
	private String gh;
	private String bgyy;
	private Date bgsj;
	private String bgfs;

	// Constructors

	/** default constructor */
	public PubSpry() {
	}

	/** minimal constructor */
    public PubSpry(Integer ajxh, Integer sprybh) {
        this.ajxh = ajxh;
        this.sprybh = sprybh;
    }

    /** full constructor */
	public PubSpry(Integer ajxh, Integer sprybh, String fg, String sfcbr, String sfspz,
			String sfdlspy, String sfrmpsy, String xm, String gh, String bgyy,
			Date bgsj, String bgfs) {
        this.ajxh = ajxh;
        this.sprybh = sprybh;
		this.fg = fg;
		this.sfcbr = sfcbr;
		this.sfspz = sfspz;
		this.sfdlspy = sfdlspy;
		this.sfrmpsy = sfrmpsy;
		this.xm = xm;
		this.gh = gh;
		this.bgyy = bgyy;
		this.bgsj = bgsj;
		this.bgfs = bgfs;
	}
    @Id
    @Column(name = "AJXH")
    public Integer getAjxh() {
        return ajxh;
    }

    public void setAjxh(Integer ajxh) {
        this.ajxh = ajxh;
    }

    @Id
    @Column(name = "SPRYBH")
    public Integer getSprybh() {
        return sprybh;
    }

    public void setSprybh(Integer sprybh) {
        this.sprybh = sprybh;
    }


	@Column(name = "FG", length = 10)
	public String getFg() {
		return this.fg;
	}

	public void setFg(String fg) {
		this.fg = fg;
	}

	@Column(name = "SFCBR", length = 1)
	public String getSfcbr() {
		return this.sfcbr;
	}

	public void setSfcbr(String sfcbr) {
		this.sfcbr = sfcbr;
	}

	@Column(name = "SFSPZ", length = 1)
	public String getSfspz() {
		return this.sfspz;
	}

	public void setSfspz(String sfspz) {
		this.sfspz = sfspz;
	}

	@Column(name = "SFDLSPY", length = 1)
	public String getSfdlspy() {
		return this.sfdlspy;
	}

	public void setSfdlspy(String sfdlspy) {
		this.sfdlspy = sfdlspy;
	}

	@Column(name = "SFRMPSY", length = 1)
	public String getSfrmpsy() {
		return this.sfrmpsy;
	}

	public void setSfrmpsy(String sfrmpsy) {
		this.sfrmpsy = sfrmpsy;
	}

	@Column(name = "XM", length = 50)
	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column(name = "GH", length = 50)
	public String getGh() {
		return this.gh;
	}

	public void setGh(String gh) {
		this.gh = gh;
	}

	@Column(name = "BGYY", length = 50)
	public String getBgyy() {
		return this.bgyy;
	}

	public void setBgyy(String bgyy) {
		this.bgyy = bgyy;
	}

	@Column(name = "BGSJ", length = 23)
	public Date getBgsj() {
		return this.bgsj;
	}

	public void setBgsj(Date bgsj) {
		this.bgsj = bgsj;
	}

	@Column(name = "BGFS", length = 10)
	public String getBgfs() {
		return this.bgfs;
	}

	public void setBgfs(String bgfs) {
		this.bgfs = bgfs;
	}

}