package cn.reanni.mvp_demo.ui;

import android.content.Intent;
import android.view.View;

import cn.reanni.mvp_demo.R;
import cn.reanni.mvp_demo.base.LayoutActivity;

public class MainActivity extends LayoutActivity implements View.OnClickListener {

    // 使用公共的标题栏
    public MainActivity() {
        super(R.layout.common_title_default, R.layout.activity_main);
    }

    @Override
    protected void onInit() {
        findViewById(R.id.tv).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, MeetingActivity.class));
    }
}
