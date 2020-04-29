package cn.reanni.mvp_demo.bean;

public class BaseBean<T> {
    int state;
    String message;
    T data;

    public BaseBean(int state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public boolean isOk() {
        return state == 0;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
