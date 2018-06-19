package com.sfbm.store.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.util.Log;

import com.sfbm.store.util.ToastUtils;
import com.sfbm.store.view.activity.MainActivity;
import com.sfbm.store.view.activity.OrderTimeoutActivity;
import com.sfbm.store.view.activity.PaymentSuccessActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by huhansan on 2018/6/4.
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    public static final String APP_ID = "wxa833091477c207c2";
    private IWXAPI api;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

//        api = WXAPIFactory.createWXAPI(this, APP_ID);
//        try {
//            api.handleIntent(getIntent(), this);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Log.i("wzcccccc", "onCreate: "+"第一次。。。");
    }

    @Override
    protected void onStart() {
        super.onStart();
        //这两个方法放在onCreate中 不响应
        api = WXAPIFactory.createWXAPI(this, APP_ID);
        try {
            api.handleIntent(getIntent(), this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("wzcccccc", "onResume: "+"天啊。。。");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        try {
            api.handleIntent(intent,this);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void onReq(BaseReq baseReq) {
        Log.i("wzcccccccccccc", "onCreate: "+"onReq。。。");
    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.i("wzcccccccccc", "onCreate: "+"onResp!!!");

        //		支付结果的回调
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            Log.i("zzzzzz", "onCreate: "+baseResp.errCode);
            if(baseResp.errCode==0){    //支付成功

                setResult(Activity.RESULT_OK);
                ToastUtils.showLong(this,"支付成功");
                Intent intent=new Intent(WXPayEntryActivity.this,PaymentSuccessActivity.class);
              //  intent.putExtra("PAYCODE",baseResp.errCode+"");
                startActivity(intent);
                finish();

            } else if(baseResp.errCode == -1 ) {    //支付失败
                ToastUtils.showLong(this,"支付失败");
                setResult(Activity.RESULT_CANCELED);
                Intent intent=new Intent(WXPayEntryActivity.this,OrderTimeoutActivity.class);
                //  intent.putExtra("PAYCODE",baseResp.errCode+"");
                startActivity(intent);
                finish();

            }else if(baseResp.errCode == -2 ) {    //支付取消
                ToastUtils.showLong(this,"支付取消");
                setResult(Activity.RESULT_CANCELED);
                Intent intent=new Intent(WXPayEntryActivity.this,OrderTimeoutActivity.class);
                //  intent.putExtra("PAYCODE",baseResp.errCode+"");
                startActivity(intent);
                finish();

            }
            finish();
        } else {

            setResult(Activity.RESULT_CANCELED);
            finish();
        }
    }
}
