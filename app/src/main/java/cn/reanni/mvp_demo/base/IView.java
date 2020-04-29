package cn.reanni.mvp_demo.base;

public interface IView<T> {
    /**
     * 开始加载
     */
    void onStartLoading();

    /**
     * 停止加载
     */
    void onStopLoading();

    /**
     * 网络有返回,获取到正确的数据
     */
    void onResultRight(T data, String resultType);

    /**
     * 网络有返回,由于请求参数错误等问题,获取到错误提示
     */
    void onResultWrong(String errorMes, String resultType);

    /**
     * 请求网络发生错误
     */
    void onNetworkError(Throwable exception);
}
