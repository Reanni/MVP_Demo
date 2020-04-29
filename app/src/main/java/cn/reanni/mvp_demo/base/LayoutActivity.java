package cn.reanni.mvp_demo.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.ViewStub;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import cn.reanni.mvp_demo.R;

public abstract class LayoutActivity<P extends LayoutPresenter> extends AppCompatActivity implements IView {

    public LayoutActivity(int contentLayoutId) {
        this(0, contentLayoutId);
    }

    public LayoutActivity(int titleLayoutId, int contentLayoutId) {
        super(titleLayoutId == 0 ? contentLayoutId : R.layout.common_base_content_layout_default);
        this.titleLayoutId = titleLayoutId;
        this.contentLayoutId = contentLayoutId;
    }

    private int titleLayoutId, contentLayoutId;

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**初始化布局*/
        if (titleLayoutId != 0) {
            ViewStub titleStub = findViewById(R.id.title_layout_stub);
            titleStub.setLayoutResource(titleLayoutId);
            titleStub.inflate();
            ViewStub contentStub = findViewById(R.id.content_layout_stub);
            contentStub.setLayoutResource(contentLayoutId);
            contentStub.inflate();
        }
        onInitLayout();
        /**绑定Presenter*/
        presenter = attachPresenter();
        if (presenter != null) presenter.onAttachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDetachView();
            presenter = null;
        }
    }

    abstract protected void onInitLayout();

    private P attachPresenter() {
        try {
            Type[] types = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
            for (int i = 0; i < types.length; i++) {
                Class type = (Class) types[i];
                if (LayoutPresenter.class.isAssignableFrom(type)) {
                    return ((P) type.newInstance());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /******************************************************* IView里的接口方法，Activity跟Fragment类保持方法一致  start *********************************************************************/

    ProgressDialog dialog;

    @Override
    public void onStartLoading() {
        dialog = ProgressDialog.show(this, null, "请求网络中~~~~");
    }

    @Override
    public void onStopLoading() {
        dialog.dismiss();
    }

    @Override
    public void onResultRight(Object data, String resultType) {
    }

    @Override
    public void onResultWrong(String errorMes, String resultType) {
        toast(errorMes);
    }

    @Override
    public void onNetworkError(Throwable exception) {
        System.out.println(exception.getMessage());
        toast("你的网络有问题,无法请求到服务器数据");
    }

    /******************************************************* IView里的接口方法，Activity跟Fragment类保持方法一致 end ************************************************************************/

    protected void toast(String s) {
        Toast.makeText(getApplication(), s, Toast.LENGTH_SHORT).show();
    }
}
