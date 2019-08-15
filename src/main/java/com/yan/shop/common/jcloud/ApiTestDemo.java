package com.yan.shop.common.jcloud;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.UUID;

public class ApiTestDemo {

    public static void main(String[] args) throws Exception {
        HttpClient client = new HttpClient();
        String uri = "https://commcso.jd.com/open/api/invoke.do";
        PostMethod post = new PostMethod(uri);
        post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        //管理中心-账户总览-开发参数-AppID、AppSecret(publickKey)、AccountCode、App_UserID
        String appId = "commec0bae107d1e47d5aee12fa76b1dxxxx";//TODO 需要修改
        String publickKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDbFKy9hGWLpnWeGUgPPCMEqB0wA0yUwtvqp+MyZBUatQsytkarHECuZ1DIkvI9UblcjXfEM/TR9rCZFni5+Qbn4lGvQvDTVHGfQwv8oNfy/e42dGP0Q1D7BC+O8ixWgiLkr+mgMa4fJhgNWjWYgjaT4fyOZG0MdFjUCn4qdHON5QIDAQAB";//TODO 需要修改
        String sign = null;
        String bizParam = null;

        HashMap<String, Object> header = new HashMap<String, Object>();
        String serviceName = "dailBackCall";
        header.put("serviceName", serviceName);
        header.put("version", "1.0");
        header.put("clientMessageId", serviceName + UUID.randomUUID().toString().replace("-", ""));
        header.put("timestamp", System.currentTimeMillis());

        HashMap<String, Object> body = new HashMap<String, Object>();
        body.put("accountCode", "1000099999");//TODO 需要修改
        body.put("userId", "a3c5cc8386e2472a9fe5e07ecea4xxxx");//TODO 需要修改
        body.put("callerNumber", "18555555555");//TODO 需要修改
        body.put("calledNumber", "18666666666");//TODO 需要修改
        body.put("isRecord", "1");//TODO 需要修改

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("header", header);
        map.put("body", body);

        //加密
        String jsonParam = JSON.toJSONString(map);
        byte[] encodeDate = RSAUtils.encryptByPublicKey(jsonParam.getBytes("utf-8"), publickKey);
        bizParam = Base64Utils.encode(encodeDate);

        System.out.println(URLEncoder.encode(bizParam, "utf-8"));
        //签名校验
        sign = Md5Utils.md5(jsonParam);

        //TODO 重点！！！此处为实际请求的内容
        System.out.println("post内容:" + "appId=" + appId + "&sign=" + sign + "&bizParam=" + bizParam);
        NameValuePair[] data = {
                new NameValuePair("appId", appId),
                new NameValuePair("sign", sign),
                new NameValuePair("bizParam", bizParam)
        };
        post.setRequestBody(data);
        client.executeMethod(post);
        String responseString = post.getResponseBodyAsString();
        System.out.println(responseString);
        JSONObject jsonObject = JSON.parseObject(responseString);
        System.out.println("header:" + jsonObject.getString("header"));

        //TODO 重点！！！响应内容
        String responseBody = jsonObject.getString("body");
        if (responseBody != null) {
            byte[] bytes = RSAUtils.decryptByPublicKey(Base64Utils.decode(responseBody), publickKey);
            System.out.println("body:" + new String(bytes));
        } else {
            System.out.println("body is null");
        }



    }

}
