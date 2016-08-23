package com.vierbom.gooddoc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xiaofei.library.hermeseventbus.HermesEventBus;
import xiaofei.library.hermeseventbustest.Dragon;

public class MainActivity extends AppCompatActivity {
    private TextView viewById;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HermesEventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);
        viewById = (TextView) findViewById(R.id.tv);
        /**请求主项目事件*/
        HermesEventBus.getDefault().post(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HermesEventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showText(Dragon dragon) {
        /**获取从主项目返回的Pokemon的名字*/
        viewById.setText(dragon.getName());
    }
}
