package com.sfbm.store.bean;



/**
 * Created by smz on 2017/4/26.
 */

public class WeChatPayBean {


    /**
     * code : 0
     * msg : success
     * data : {"payOrderId":"P01201804160653121990002","sign":"13D7C6085E5837E76733D69D9EB981AE","payParams":"{\"timeStamp\":\"1523875992\",\"packageValue\":\"Sign=WXPay\",\"appId\":\"wxa833091477c207c2\",\"sign\":\"EF4D19540EE22091A50422C3692C8690\",\"prepayId\":\"wx161853124033734de98961ca1649857686\",\"partnerId\":\"1359014102\",\"nonceStr\":\"1523875992470\"}","retCode":"SUCCESS"}
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
         * payOrderId : P01201804160653121990002
         * sign : 13D7C6085E5837E76733D69D9EB981AE
         * payParams : {"timeStamp":"1523875992","packageValue":"Sign=WXPay","appId":"wxa833091477c207c2","sign":"EF4D19540EE22091A50422C3692C8690","prepayId":"wx161853124033734de98961ca1649857686","partnerId":"1359014102","nonceStr":"1523875992470"}
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
