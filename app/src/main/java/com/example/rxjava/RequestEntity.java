package com.example.rxjava;

import android.util.Log;

import com.alibaba.fastjson.JSONObject;


/**
 * @author david
 * @date 2018/6/26
 */
public class RequestEntity {
    private JSONObject filter = new JSONObject();
    private JSONObject data = new JSONObject();
    private JSONObject json = new JSONObject();

    public RequestEntity() {

    }

    public void putData(String key, Object value) {
        data.put(key, value);
    }

    public void putFilter(String key, Object value) {
        filter.put(key, value);
    }

    public JSONObject getFilter() {
        return filter;
    }

    public JSONObject getData() {
        return data;
    }

    public String toJsonString() {
        data.put("filter", filter);
        json.put("data", data);
        Log.d(TAG, "toJsonString: "+json.toString());
        return json.toJSONString();
    }

    private static final String TAG = "RequestEntity";
}
