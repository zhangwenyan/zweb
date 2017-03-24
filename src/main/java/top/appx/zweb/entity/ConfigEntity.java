package top.appx.zweb.entity;

import top.appx.zutil.easysql.Table;

/**
 * Created by mrz on 2017/3/24.
 */
@Table("config")
public class ConfigEntity {
    private Integer id;
    private String name;
    private String value;
    private String comment;
    private Integer state;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
