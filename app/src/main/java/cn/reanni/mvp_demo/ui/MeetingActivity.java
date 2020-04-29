package cn.reanni.mvp_demo.ui;

import android.view.View;
import android.widget.TextView;

import java.util.Random;

import cn.reanni.mvp_demo.R;
import cn.reanni.mvp_demo.base.LayoutActivity;
import cn.reanni.mvp_demo.bean.Guest;
import cn.reanni.mvp_demo.bean.Host;

import static cn.reanni.mvp_demo.ui.MeetPresenter.GET_GUEST;
import static cn.reanni.mvp_demo.ui.MeetPresenter.GET_HOST;

public class MeetingActivity extends LayoutActivity<MeetPresenter> {

    public MeetingActivity() {
        super(R.layout.activity_meeting);
    }

    TextView tv_1, tv_2;
    Random r = new Random();

    @Override
    protected void onInitLayout() {
        tv_1 = findViewById(R.id.tv_1);
        tv_2 = findViewById(R.id.tv_2);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_1:
               /* int i = r.nextInt(10);
                String sex = (i % 2 == 1) ? "男" : "女";
                presenter.getGuest(sex);*/
                presenter.getGuest(r.nextInt(10) % 2 == 0 ? "男" : "女");
                break;
            case R.id.bt_2:
                presenter.getHost();
                break;
            case R.id.bt_3:
                presenter.getJury();
                break;
            case R.id.bt_4:
                presenter.getStar();
                break;
        }
    }

    @Override
    public void onResultRight(Object data, String resultType) {
        switch (resultType) {
            case GET_GUEST:
                Guest guest = (Guest) data;
                tv_1.setText(guest.getNumb() + "号" + guest.getSex() + "嘉宾上场");
                break;
            case GET_HOST:
                Host host = (Host) data;
                tv_2.setText("主持人" + host.getName() + "闪亮登场");
                break;
        }
    }
}
