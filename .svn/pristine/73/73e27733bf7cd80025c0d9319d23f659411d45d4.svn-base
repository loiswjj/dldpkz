package nju.software.baseframework.data.dataobject;

import javax.persistence.*;

/**
 * PubDmb entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_DMB")
@IdClass(value=PubDmbId.class)
public class PubDmb implements java.io.Serializable {

	// Fields
    private String lbbh;
    private String dmbh;
	private String dmms;
	private String bz;

	// Constructors

	/** default constructor */
	public PubDmb() {
	}

	/** minimal constructor */
    public PubDmb(String dmbh, String lbbh) {
        this.dmbh = dmbh;
        this.lbbh = lbbh;
    }

    @Id
    @Column(name = "DMBH")
    public String getDmbh() {
        return dmbh;
    }

    public void setDmbh(String dmbh) {
        this.dmbh = dmbh;
    }
    @Id
    @Column(name = "LBBH")
    public String getLbbh() {
        return lbbh;
    }

    public void setLbbh(String lbbh) {
        this.lbbh = lbbh;
    }

    @Column(name = "DMMS", length = 250)
	public String getDmms() {
		return this.dmms;
	}

	public void setDmms(String dmms) {
		this.dmms = dmms;
	}

	@Column(name = "BZ")
    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}