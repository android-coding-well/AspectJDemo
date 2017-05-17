package com.gosuncn.ajd;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.gosuncn.ajd.databinding.ActivityMainBinding;
import com.gosuncn.ajd.domain.DemoInfo;
import com.gosuncn.ajd.domain.TestInfo;
import com.gosuncn.ajd.domain.WithincodeInfo;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    DemoInfo demoInfo=new DemoInfo();
    TestInfo testInfo=new TestInfo();
    WithincodeInfo withincodeInfo=new WithincodeInfo();
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        test();
        test2();
        test3();
    }

    /**
     * 测试异常捕获
     * @param v
     */
    public void onClickCrash(View v){
        throw new RuntimeException("模拟产生异常");
    }

    /**
     * 测试@DemoJointPoint,可以发现注解过的被成功织入了内容
     */
    public void  test(){
        Log.i(TAG,"@DemoJointPoint:"+demoInfo.getMessage());
        Log.i(TAG,"@DemoJointPoint:"+demoInfo.getMessageWithAspect());//内容发生变化
    }

    /**
     * 测试execution和call
     */
    public void  test2() {
        testInfo.test1();
        testInfo.test2("abc",123,null);
    }

    /**
     * 测试withincode
     * withincodeInfo.test4()和withincodeInfo.test4()同时调用了withincodeInfo.test1()
     * 只有withincodeInfo.test4()中的withincodeInfo.test1()被切入
     */
    public void  test3(){
        Log.i(TAG,"test withincode:"+withincodeInfo.test3());//正常输出hello
        Log.i(TAG,"test withincode:"+withincodeInfo.test4());//由于被切入，输出内容已经改变
    }

}
