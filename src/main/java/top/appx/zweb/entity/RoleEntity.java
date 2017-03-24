package top.appx.zweb.entity;

import top.appx.zutil.easysql.Table;

/**
 * Created by mrz on 2017/3/24.
 */
@Table("role")
public class RoleEntity {
    private Integer id;
    private String name;
    private String icon;
    private String pid;
    private String comment;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
