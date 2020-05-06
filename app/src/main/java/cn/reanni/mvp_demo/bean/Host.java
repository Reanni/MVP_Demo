package cn.reanni.mvp_demo.bean;

/**
 * 主持人
 */
@SuppressWarnings("unused")
public class Host {
    private String name;

    public Host(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
