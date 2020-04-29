package cn.reanni.mvp_demo.base;

import java.lang.ref.WeakReference;

import cn.reanni.mvp_demo.bean.BaseBean;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LayoutPresenter<V extends IView> {

    private final static String DEFAULT = "default";

    private WeakReference<V> mWeakView;

    public void onAttachView(V view) {
        mWeakView = new WeakReference<>(view);
    }

    public void onDetachView() {
        mWeakView.clear();
    }

    protected V view() {
        return mWeakView.get();
    }

    public <T extends BaseBean> void doApi(Observable<T> apiResult) {
        doApi(apiResult, DEFAULT);
    }

    public <T extends BaseBean> void doApi(Observable<T> apiResult, final String resultType) {
        apiResult
                .subscribeOn(Schedulers.io())
                .doOnNext(new Consumer<T>() {
                    @Override
                    public void accept(T t) throws Exception {
                        Thread.sleep(2000); //休眠模拟请求网络
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        V view = view();
                        if (view != null) {
                            view.onStartLoading();
                        }
                    }

                    @Override
                    public void onNext(T t) {
                        V view = view();
                        if (view != null) {
                            if (t.isOk()) {
                                view.onResultRight(t.getData(), resultType);
                            } else {
                                view.onResultWrong(t.getMessage(), resultType);
                            }
                        }
                    }

                    @Override
                    public void onComplete() {
                        V view = view();
                        if (view != null) {
                            view.onStopLoading();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        V view = view();
                        if (view != null) {
                            view.onStopLoading();
                            view.onNetworkError(e);
                        }
                    }

                });
    }
}
