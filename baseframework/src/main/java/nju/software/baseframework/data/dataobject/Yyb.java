package nju.software.baseframework.data.dataobject;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.Date;

/**
 * Yyb entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pub_yyb")
@DynamicInsert
@DynamicUpdate
public class Yyb{

	// Fields
	private Integer bh;
	private Integer fybh;
	private String ah;
	private String ajmc;

	private String lfr;
	private String lfrsfzh;
	private String lfrlxfs;
	private Date lfsj; //来访时间
    private String lfsy;

    private Date yysj = new Date(); //预约时间
    private Integer yyzt = 5;//预约状态

	private String bz;
	private String jfdd; //接访地点
	private String jfbm;
	private String jfr;
	private String jfrlxfs;

	private String gldsr;  //关联当事人
	/**
	 * 1 父母
	 * 2 子女
	 * 3 配偶
	 * 4 代理律师
	 */
	private String ybagx; //与本案关系


	// Constructors

	/** default constructor */
	public Yyb() {
	}

    public Yyb(Integer bh,String lfr, String lfrsfzh, String lfrlxfs, Date lfsj, String lfsy, Date yysj,
               Integer yyzt, String bz, String jfdd, String jfbm, String jfr, String jfrlxfs) {
        this.bh = bh;
        this.lfr = lfr;
        this.lfrsfzh = lfrsfzh;
        this.lfrlxfs = lfrlxfs;
        this.lfsj = lfsj;
        this.lfsy = lfsy;
        this.yysj = yysj;
        this.yyzt = yyzt;
        this.bz = bz;
        this.jfdd = jfdd;
        this.jfbm = jfbm;
        this.jfr = jfr;
        this.jfrlxfs = jfrlxfs;
    }


    // Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BH", unique = true, nullable = false)
	public Integer getBh() {
		return this.bh;
	}

	public void setBh(Integer bh) {
		this.bh = bh;
	}

	public Integer getFybh() {
		return fybh;
	}

	public void setFybh(Integer fybh) {
		this.fybh = fybh;
	}

    @Column(name = "LFR", length = 20)
	public String getLfr() {
		return this.lfr;
	}

	public void setLfr(String lfr) {
		this.lfr = lfr;
	}

    @Column(name = "LFRSFZH", length = 20)
    public String getLfrsfzh() {
        return lfrsfzh;
    }

    public void setLfrsfzh(String lfrsfzh) {
        this.lfrsfzh = lfrsfzh;
    }

    @Column(name = "LFRLXFS", length = 20)
    public String getLfrlxfs() {
        return lfrlxfs;
    }

    public void setLfrlxfs(String lfrlxfs) {
        this.lfrlxfs = lfrlxfs;
    }
    @Column(name = "YYSJ", length = 10)
    public Date getYysj() {
        return yysj;
    }

    public void setYysj(Date yysj) {
        this.yysj = yysj;
    }
    @Column(name = "JFDD", length = 100)
    public String getJfdd() {
        return jfdd;
    }

    public void setJfdd(String jfdd) {
        this.jfdd = jfdd;
    }

    @Query(name = "ah")
	public String getAh() {
		return ah;
	}

	public void setAh(String ah) {
		this.ah = ah;
	}

	@Column(name = "ajmc")
	public String getAjmc() {
		return ajmc;
	}

	public void setAjmc(String ajmc) {
		this.ajmc = ajmc;
	}

	@Column(name = "LFSJ", length = 10)
	public Date getLfsj() {
		return this.lfsj;
	}

	public void setLfsj(Date lfsj) {
		this.lfsj = lfsj;
	}

	@Column(name = "BZ", length = 100)
	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

    @Column(name = "JFBM", length = 10)
	public String getJfbm() {
		return this.jfbm;
	}

	public void setJfbm(String jfbm) {
		this.jfbm = jfbm;
	}

	@Column(name = "JFR", length = 10)
	public String getJfr() {
		return this.jfr;
	}

	public void setJfr(String jfr) {
		this.jfr = jfr;
	}

	@Column(name = "JFRLXFS", length = 20)
	public String getJfrlxfs() {
		return this.jfrlxfs;
	}

	public void setJfrlxfs(String jfrlxfs) {
		this.jfrlxfs = jfrlxfs;
	}

	@Column(name = "YYZT")
	public Integer getYyzt() {
		return this.yyzt;
	}

	public void setYyzt(Integer yyzt) {
		this.yyzt = yyzt;
	}

	@Column(name = "LFSY", length = 200)
	public String getLfsy() {
		return this.lfsy;
	}

	public void setLfsy(String lfsy) {
		this.lfsy = lfsy;
	}

	@Column(name = "YBAGX")
	public String getYbagx() {
		return ybagx;
	}

	public void setYbagx(String ybagx) {
		this.ybagx = ybagx;
	}

	@Column(name = "GLDSR")
	public String getGldsr() {
		return gldsr;
	}

	public void setGldsr(String gldsr) {
		this.gldsr = gldsr;
	}
}