package top.appx.zweb.entity;

/**
 * Created by mrz on 2017/3/23.
 */
public class MenuEntity {
    private Integer id;
    private String text;
    private String url;
    private String iconCls;
    private String icon;
    private Integer sequence;
    private Integer pid;
  //  private boolean open;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

//    public boolean isOpen() {
//        return open;
 //   }

  //  public void setOpen(boolean open) {
  //      this.open = open;
  //  }
}
