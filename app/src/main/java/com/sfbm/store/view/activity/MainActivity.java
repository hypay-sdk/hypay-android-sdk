package com.sfbm.store.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.google.gson.Gson;
import com.sfbm.store.R;
import com.sfbm.store.bean.ALipayBean;
import com.sfbm.store.bean.WeChatPayBean;
import com.sfbm.store.util.ToastUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import static com.sfbm.store.wxapi.WXPayEntryActivity.APP_ID;

public class MainActivity extends AppCompatActivity {
    Context context = MainActivity.this;
    private String channelId;
    private boolean alichecked;
    private boolean wxchecked;
    private CheckBox cbAlipay;
    private CheckBox cbWxpay;
    private WeChatPayBean weChatPayBean;
    private String payParams;
    private ALipayBean aLipayBean;
    private static final int SDK_PAY_FLAG = 1;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(MainActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(MainActivity.this,PaymentSuccessActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Intent intent=new Intent(MainActivity.this,OrderTimeoutActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    break;
            }
        }
    };
    private String subject;
    private String mchId;
    private String appId;
    private String mchOrderNo;
    private String amount;
    private String paycode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        Log.i("wzccc", "onCreate: "+"确认过眼神");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("wzcccc", "onCreate: "+"遇见");

    }

    private void initData() {

        //标题
        subject = getIntent().getStringExtra("subject");
        //商户ID
        mchId = getIntent().getStringExtra("mchId");
        //appID
        appId = getIntent().getStringExtra("appId");
        //商户订单号
        mchOrderNo = getIntent().getStringExtra("mchOrderNo");
        //金额
        amount = getIntent().getStringExtra("amount");


        TextView tvCashierMoney = findViewById(R.id.tv_cashier_money);
        tvCashierMoney.setText("￥"+amount);

        TextView tvCashierMoney2 = findViewById(R.id.tv_cashier_money2);
        tvCashierMoney2.setText("充值"+amount+"元金币");

        TextView tvOrderNumber = findViewById(R.id.tv_order_number);
        tvOrderNumber.setText("订单号："+mchOrderNo);

        View llBack = findViewById(R.id.ll_back);
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView tvTitle = findViewById(R.id.tv_title);
        cbAlipay = findViewById(R.id.cb_alipay);
        cbWxpay = findViewById(R.id.cb_wxpay);
        tvTitle.setText("易支付");
        alichecked = cbAlipay.isChecked();
        wxchecked = cbWxpay.isChecked();
//        alichecked=true;//默认支付宝
//        cbAlipay.setChecked(true);

        TextView tv_pay_confirm = (TextView) findViewById(R.id.tv_pay_confirm);
        View rlAlipay = findViewById(R.id.rl_alipay);
        View rlWechatPay = findViewById(R.id.rl_wechat_pay);

        View.OnClickListener listener =  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取组件的资源id
                int id = v.getId();
                switch (id) {
                    case R.id.rl_alipay:
                        channelId ="alipay_mobile";

                        cbWxpay.setChecked(false);
                        if (alichecked) {
                            cbAlipay.setChecked(false);
                            alichecked = false;
                        } else {
                            cbAlipay.setChecked(true);
                            alichecked = true;
                            wxchecked = false;
                        }


                        break;
                    case R.id.rl_wechat_pay:
                        channelId ="wxpay_app";

                        cbAlipay.setChecked(false);
                        if (wxchecked) {
                            cbWxpay.setChecked(false);
                            wxchecked = false;
                        } else {
                            cbWxpay.setChecked(true);
                            wxchecked = true;
                            alichecked = false;
                        }


                        break;
                    case R.id.tv_pay_confirm:

                        if(channelId!=null) {
                            getHttpJson();
                            finish();
                        }else {
                            Toast.makeText(MainActivity.this,"请选择支付方式！",Toast.LENGTH_LONG).show();
                        }

                        break;

                    default:
                        break;
                }
            }
        };

        tv_pay_confirm.setOnClickListener(listener);
        rlAlipay.setOnClickListener(listener);
        rlWechatPay.setOnClickListener(listener);

    }

    public void getHttpJson() {

        //开启线程请求网络
        new Thread(new Runnable() {

            private HttpURLConnection connection;
            private BufferedReader reader;
            String extra;

            @Override
            public void run() {
                try {

//                    if (channelId.equals("alipay_mobile")) {
//                        extra = "";
//                    }

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("mchId", ""+mchId); //商户id
                    jsonObject.put("appId", ""+appId);// appId 83686ece228a49779d9b3d680d4e7b6c
                    jsonObject.put("mchOrderNo", ""+mchOrderNo);//商户订单号   9t9z9c9g1b67c8d0f
                    jsonObject.put("channelId", "" + channelId);
                    jsonObject.put("amount", ""+amount); //金额
                    jsonObject.put("currency", "cny");
                    jsonObject.put("subject", ""+subject);//标题
                    jsonObject.put("body", "shoujichongzhi");
                    jsonObject.put("extra", "");
                    String s = jsonObject.toString();
                    String encode = URLEncoder.encode(s, "utf-8");

                    URL url = new URL("http://pay.1080.com/cashier/pay?params= "+encode);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(8000);
                    conn.setConnectTimeout(8000);
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write("");
                    writer.flush();
                    writer.close();
                    os.close();
                    conn.connect();

                    if (conn.getResponseCode() >= 400) {
                        conn.getErrorStream();
                    } else {
                        InputStream inputStream = conn.getInputStream();
                        //   对获取到的输入流进行读取
                        reader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            stringBuilder.append(line);
                        }
                        Log.i("zzzz", "run: "+stringBuilder.toString());
                        Gson gson = new Gson();
                        //   ALiayBean aLiayBean = gson.fromJson(stringBuilder.toString(), ALiayBean.class);//支付宝

                        if(channelId != null && channelId.equals("wxpay_app") ) {
                            //微信
                            weChatPayBean = gson.fromJson(stringBuilder.toString(), WeChatPayBean.class);
                            //判断微信返回参数是否成功
                            if(weChatPayBean.getCode() == 0) {
                                payParams = weChatPayBean.getData().getPayParams();
                                startWxPay(payParams);
                            }else {
                                ToastUtils.showLong(context, "服务器异常");
                            }
                        }else if (channelId != null && channelId.equals("alipay_mobile") ) {
                            //支付宝
                            aLipayBean = gson.fromJson(stringBuilder.toString(), ALipayBean.class);
                            //判断支付宝返回参数是否成功
                        if (aLipayBean.getCode()==0) {
                                String AliorderInfo = aLipayBean.getData().getPayParams();
                                StartAliPay(AliorderInfo);
                                Log.i("xxxx", "run: "+AliorderInfo);
                        } else {
                            ToastUtils.showLong(context, "服务器异常");
                          }

                        }


                        //进行ui操作
                        showResponse(stringBuilder.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }



    private void StartAliPay(final String aliorderInfo) {
        Runnable authRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造AuthTask 对象
                AuthTask authTask = new AuthTask(MainActivity.this);
                // 调用授权接口，获取授权结果
                Map<String, String> result = authTask.authV2(aliorderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);

            }
        };

        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

    private void startWxPay(String wxpayBean) {


        try {
            if(wxpayBean!=null) {

                JSONObject  jsonObject = new JSONObject(wxpayBean);
                String timeStamp = jsonObject.getString("timeStamp");
                String packageValue = jsonObject.getString("packageValue");
                String sign = jsonObject.getString("sign");
                String prepayId = jsonObject.getString("prepayId");
                String partnerId = jsonObject.getString("partnerId");
                String nonceStr = jsonObject.getString("nonceStr");
                String appId = jsonObject.getString("appId");

                PayReq req = new PayReq();

                //判断是否安装微信
                IWXAPI api = WXAPIFactory.createWXAPI(this, null);
                api.registerApp(APP_ID);//appid

                req.appId = appId;
                req.partnerId = partnerId;
                req.prepayId = prepayId;
                req.packageValue = packageValue;
                req.nonceStr = nonceStr;
                req.timeStamp = timeStamp;
                req.sign = sign;
                api.sendReq(req);

            }else {


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }



    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //进行UI操作,将结果显示到界面


            }
        });


    }

}
