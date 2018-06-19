package com.sfbm.store.bean;

/**
 * Created by huhansan on 2018/4/19.
 */

public class ALipayBean {


    /**
     * code : 0
     * msg : success
     * data : {"payOrderId":"P01201804160341280830003","sign":"8E3D336A1D2F600AD545FBBC7707CEE7","payParams":"alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2016062301547751&biz_content=%7B%22body%22%3A%22shoujichongzhi%22%2C%22out_trade_no%22%3A%22P01201804160341280830003%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22chongzhibiaoti%22%2C%22total_amount%22%3A%220.10%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fpay.1080.com%2Fnotify%2Fpay%2FaliPayNotifyRes.htm&return_url=http%3A%2F%2Fpay.1080.com%2Fcashier%2Fali_wap_result&sign=lm5DgbjCuhaKceI2HYPhsdfmOp0MckvgUHzECk5qaai9rki7vvcggeS48BfPqTVQmfWNX%2B%2BHBbNApI0bdh59N1OnbnENYI6rn7MzJh6iV3gmhQbMTVEH4oAjEMwKUOOJ4m6JDzttPnjnAoAC4a2gK5u%2Fht%2F93rfRHsacTIuqBi9Qk9iU70DZeqSP%2B0A446qIprmW9Fyv40d0uyPhnlUpC4W0w2aYbttw0wM6t%2FevOxmDGIta9NbawQFQcezNiFINdQokhWkai2h4yMzL5Ui5JYXW3tVDsEScR7DIBBJaizIk1wnsbHtUbRtiKA4uYY0KoDaZj79TCTrdNpOvKcsLDQ%3D%3D&sign_type=RSA2&timestamp=2018-04-16+15%3A41%3A28&version=1.0","retCode":"SUCCESS"}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * payOrderId : P01201804160341280830003
         * sign : 8E3D336A1D2F600AD545FBBC7707CEE7
         * payParams : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2016062301547751&biz_content=%7B%22body%22%3A%22shoujichongzhi%22%2C%22out_trade_no%22%3A%22P01201804160341280830003%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22chongzhibiaoti%22%2C%22total_amount%22%3A%220.10%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fpay.1080.com%2Fnotify%2Fpay%2FaliPayNotifyRes.htm&return_url=http%3A%2F%2Fpay.1080.com%2Fcashier%2Fali_wap_result&sign=lm5DgbjCuhaKceI2HYPhsdfmOp0MckvgUHzECk5qaai9rki7vvcggeS48BfPqTVQmfWNX%2B%2BHBbNApI0bdh59N1OnbnENYI6rn7MzJh6iV3gmhQbMTVEH4oAjEMwKUOOJ4m6JDzttPnjnAoAC4a2gK5u%2Fht%2F93rfRHsacTIuqBi9Qk9iU70DZeqSP%2B0A446qIprmW9Fyv40d0uyPhnlUpC4W0w2aYbttw0wM6t%2FevOxmDGIta9NbawQFQcezNiFINdQokhWkai2h4yMzL5Ui5JYXW3tVDsEScR7DIBBJaizIk1wnsbHtUbRtiKA4uYY0KoDaZj79TCTrdNpOvKcsLDQ%3D%3D&sign_type=RSA2&timestamp=2018-04-16+15%3A41%3A28&version=1.0
         * retCode : SUCCESS
         */

        private String payOrderId;
        private String sign;
        private String payParams;
        private String retCode;

        public String getPayOrderId() {
            return payOrderId;
        }

        public void setPayOrderId(String payOrderId) {
            this.payOrderId = payOrderId;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPayParams() {
            return payParams;
        }

        public void setPayParams(String payParams) {
            this.payParams = payParams;
        }

        public String getRetCode() {
            return retCode;
        }

        public void setRetCode(String retCode) {
            this.retCode = retCode;
        }
    }
}
