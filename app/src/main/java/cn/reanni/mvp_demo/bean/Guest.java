package cn.reanni.mvp_demo.bean;

/**
 * 嘉宾
 */
@SuppressWarnings("unused")
public class Guest {
    private  int numb;
    private  String sex;

    public Guest(int numb, String sex) {
        this.numb = numb;
        this.sex = sex;
    }

    public int getNumb() {
        return numb;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
