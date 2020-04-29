package cn.reanni.mvp_demo.ui;

import android.accounts.NetworkErrorException;
import android.annotation.SuppressLint;

import java.util.Random;

import cn.reanni.mvp_demo.base.LayoutPresenter;
import cn.reanni.mvp_demo.bean.BaseBean;
import cn.reanni.mvp_demo.bean.Guest;
import cn.reanni.mvp_demo.bean.Host;
import cn.reanni.mvp_demo.bean.Jury;
import io.reactivex.Observable;

@SuppressLint("SetTextI18n")
public class MeetPresenter extends LayoutPresenter<MainActivity> {

    Random r = new Random();

    final static String GET_GUEST = "GET_GUEST";

    public void getGuest(String sex) { //模拟网络请求成功
        doApi(Observable.fromArray(new BaseBean(0, "请求数据成功", new Guest(r.nextInt(10), sex))), GET_GUEST);
    }

    final static String GET_HOST = "GET_HOST";

    public void getHost() { //模拟网络请求成功
        doApi(Observable.fromArray(new BaseBean(0, "请求数据成功", new Host("孟飞"))), GET_HOST);
    }

    final static String GET_JURY = "GET_JURY";

    public void getJury() { //模拟网络请求成功，但由于参数不对没有拿到数据
        doApi(Observable.fromArray(new BaseBean(1, "请求参数有误(通告费太少,评委不来)", new Jury())), GET_JURY);
    }

    final static String GET_STAR = "GET_STAR";

    public void getStar() { //模拟网络连接失败
        doApi(Observable.<BaseBean>error(new NetworkErrorException("网络请求失败(明星看不上你这个破节目,不想跟你们沟通)")), GET_STAR);
    }
}
