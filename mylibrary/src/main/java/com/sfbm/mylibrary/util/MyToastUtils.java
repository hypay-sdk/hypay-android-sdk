package com.sfbm.mylibrary.util;

import android.content.Context;
import android.widget.Toast;

/**
 * 主要是保证context不为空
 * Created by Li on 2016/7/6.
 */
public class MyToastUtils {
    static Toast toast = null;  //用于判断是否已有Toast执行
    public static void showLong(Context context, String msg){

        if (context == null){
            return;
        }
        if (toast==null) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);  //正常执行
        }  else {
            toast.setText(msg);  //用于覆盖前面未消失的提示信息
        }
        toast.show();
    }

    public static void showShort(Context context, String msg){
        if (context == null){
            return;
        }
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show();
    }

    public static void showError(Context context, Throwable throwable){
        if (context == null || throwable == null){
            return;
        }
        Toast.makeText(context,throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
