package nju.software.baseframework.data.dataobject;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * 侧边栏
 */
@Entity
@Table(name = "nav")
@DynamicInsert
@DynamicUpdate
public class Nav {
    private Integer id;
    private String name;
    private String href;
    private String iconUrl;
    private Integer level;
    private String iconNav;
    private String dqfy;
    private Integer fdh; //父级标签id 0-无父级标签

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Column(name="href")
    public String getHref() {
        if(href!=null && href.equals("")) href=null;
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    @Column(name = "iconurl")
    public String getIconUrl() {
        return iconUrl;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    @Column(name = "iconNav")
    public String getIconNav() {
        if (iconUrl!=null&&!iconUrl.equals(""))
            return "<span class="+iconUrl+"><span class='navPadding'>"+this.name+"</span></span>";
        return "<span class='navPadding'>"+name+"</span>";
    }

    public void setIconNav(String iconNav) {

        this.iconNav = iconNav;
    }
    @Column(name = "dqfy")
    public String getDqfy() {
        return dqfy;
    }

    public void setDqfy(String dqfy) {
        this.dqfy = dqfy;
    }

    @Column(name = "fdh")
    public Integer getFdh() {
        return fdh;
    }

    public void setFdh(Integer fdh) {
        this.fdh = fdh;
    }

    public Nav(){}

    public Nav(String name, String href, String iconUrl, Integer level, Integer fdh) {
        this.name = name;
        this.href = href;
        this.iconUrl = iconUrl;
        this.level = level;
        this.fdh = fdh;
    }
}
