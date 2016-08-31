package com.vierbom.gooddoc;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import xiaofei.library.hermeseventbus.HermesEventBus;
import xiaofei.library.hermeseventbustest.Dragon;

public class MainActivity extends AppCompatActivity {
    private TextView viewById;
    private Handler ttsHandler;
    private Runnable runnable = new Runnable() {
        public void run() {
            HermesEventBus.getDefault().post(1);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HandlerThread mHandlerThread = new HandlerThread("MainActivity");
        mHandlerThread.start();
        ttsHandler = new Handler(mHandlerThread.getLooper());
        HermesEventBus.getDefault().register(this);
        setContentView(R.layout.activity_main);
        viewById = (TextView) findViewById(R.id.tv);
        /**请求主项目事件*/
//        ttsHandler.postDelayed(runnable, 500);
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showText(String dragon) {
        /**获取从主项目返回的Pokemon的名字*/
        viewById.setText(dragon);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showText(Integer dragon) {
        /**获取从主项目返回的Pokemon的名字*/
        viewById.setText(String.valueOf(dragon));
    }
}
