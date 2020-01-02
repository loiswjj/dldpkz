package nju.software.baseframework.data.dataobject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PubXtglXxxgl entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PUB_XTGL_XXXGL")
public class PubXtglXxxgl implements java.io.Serializable {

	// Fields

	private Integer xxxbh;
	private Integer fybh;
	private String name;
	private String szb;
	private String szl;
	private String datatype;
	private String xxxjc;
	private String sjxx;
	private String sjxx2016;

	// Constructors

	/** default constructor */
	public PubXtglXxxgl() {
	}

	/** minimal constructor */
	public PubXtglXxxgl(Integer xxxbh) {
		this.xxxbh = xxxbh;
	}

	/** full constructor */
	public PubXtglXxxgl(Integer xxxbh, Integer fybh, String name, String szb,
			String szl, String datatype, String xxxjc, String sjxx,
			String sjxx2016) {
		this.xxxbh = xxxbh;
		this.fybh = fybh;
		this.name = name;
		this.szb = szb;
		this.szl = szl;
		this.datatype = datatype;
		this.xxxjc = xxxjc;
		this.sjxx = sjxx;
		this.sjxx2016 = sjxx2016;
	}

	// Property accessors
	@Id
	@Column(name = "XXXBH", unique = true, nullable = false)
	public Integer getXxxbh() {
		return this.xxxbh;
	}

	public void setXxxbh(Integer xxxbh) {
		this.xxxbh = xxxbh;
	}

	@Column(name = "FYBH")
	public Integer getFybh() {
		return this.fybh;
	}

	public void setFybh(Integer fybh) {
		this.fybh = fybh;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SZB", length = 30)
	public String getSzb() {
		return this.szb;
	}

	public void setSzb(String szb) {
		this.szb = szb;
	}

	@Column(name = "SZL", length = 50)
	public String getSzl() {
		return this.szl;
	}

	public void setSzl(String szl) {
		this.szl = szl;
	}

	@Column(name = "DATATYPE", length = 10)
	public String getDatatype() {
		return this.datatype;
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	@Column(name = "XXXJC", length = 20)
	public String getXxxjc() {
		return this.xxxjc;
	}

	public void setXxxjc(String xxxjc) {
		this.xxxjc = xxxjc;
	}

	@Column(name = "SJXX")
	public String getSjxx() {
		return this.sjxx;
	}

	public void setSjxx(String sjxx) {
		this.sjxx = sjxx;
	}

	@Column(name = "SJXX2016")
	public String getSjxx2016() {
		return this.sjxx2016;
	}

	public void setSjxx2016(String sjxx2016) {
		this.sjxx2016 = sjxx2016;
	}

}