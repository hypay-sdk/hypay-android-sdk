package com.sfbm.store.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sfbm.mylibrary.util.RandomNumber;
import com.sfbm.store.R;

public class ParameterActivity extends AppCompatActivity {

    private EditText etParameter;
    private EditText etParameter2;
    private EditText etParameter3;
    private EditText etParameter4;
    private EditText etParameter5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter);



    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
    }

    public void initView() {
       Button btIntent = findViewById(R.id.bt_intent);
       etParameter = findViewById(R.id.et_parameter);
       etParameter2 = findViewById(R.id.et_parameter2);
       etParameter3 = findViewById(R.id.et_parameter3);
       etParameter4 = findViewById(R.id.et_parameter4);
       etParameter5 = findViewById(R.id.et_parameter5);

       etParameter.setText("手机充值");
       etParameter2.setText("8");
       etParameter3.setText("6cd0f63f172d45018c0ef301ea2a4ebf");
        String s = RandomNumber.newGuid20();
       etParameter4.setText(""+s);
       etParameter5.setText("1");

       View.OnClickListener listener =   new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //获取组件的资源id
               int id = view.getId();
               switch (id) {
                   case R.id.bt_intent:
                       String parameter = etParameter.getText().toString();
                       String parameter2 = etParameter2.getText().toString();
                       String parameter3 = etParameter3.getText().toString();
                       String parameter4 = etParameter4.getText().toString();
                       String parameter5 = etParameter5.getText().toString();
                       String markstr = "";
                       boolean flag = true;

                       if (parameter.equals("")) {
                           flag = false;
                           markstr = "标题不能为空！";
                       }
                       if (parameter2.equals("")) {
                           flag = false;
                           markstr = "商户ID不能为空！";
                       }
                       if (parameter3.equals("")) {
                           flag = false;
                           markstr = "appID不能为空！";
                       }
                       if (parameter4.equals("")) {
                           flag = false;
                           markstr = "商户订单号不能为空！";
                       }
                       if (parameter5.equals("")) {
                           flag = false;
                           markstr = "金额不能为空！";
                       }
                       if (flag) {
                           Intent intent = new Intent(ParameterActivity.this,MainActivity.class);
                           intent.putExtra("subject",parameter); //标题
                           intent.putExtra("mchId",parameter2);  //商户ID
                           intent.putExtra("appId",parameter3);  //appID
                           intent.putExtra("mchOrderNo",parameter4); //商户订单号
                           intent.putExtra("amount",parameter5); //金额
                           startActivity(intent);

                       } else {// 输入错误提示
                           Toast toast = Toast.makeText(ParameterActivity.this, markstr, Toast.LENGTH_SHORT);

                           toast.setGravity(Gravity.CENTER, 0, 0);
                           toast.show();
                       }

                       break;
               }
           }
       };

       btIntent.setOnClickListener(listener);
       etParameter.setOnClickListener(listener);
       etParameter2.setOnClickListener(listener);
       etParameter3.setOnClickListener(listener);
       etParameter4.setOnClickListener(listener);
       etParameter5.setOnClickListener(listener);

   }



}
