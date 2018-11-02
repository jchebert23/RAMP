package com.example.gabrielsaruhashi.ramp.Clients;

import android.util.Log;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

import com.loopj.android.http.*;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


/**
 * Created by Masayuki on 9/28/18.
 * https://guides.codepath.com/android/Using-Android-Async-Http-Client
 */

public class Two11Client{
   //String taxurl = "https://api.211ct.org/api/taxonomies";
   String searchurl = "https://api.211ct.org/api/search?page=1&service_area=enfield&term=Long_Term_Care_Counseling";
   String contentType = "application/json";
   String accept = "application/json";
   String key = "ZaYa7pBS6P79v_vdHAWgzw";
   String cacheControl = "no-cache";
   String charset = "UTF-8";

    public void search(String service_area, AsyncHttpResponseHandler handler){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.addHeader("Content-Type", this.contentType);
        client.addHeader("Accept", this.accept);
        //client.addHeader("cache-control", "no-cache");
        client.addHeader("X-UW211-KEY", this.key);
//        params.put("page", 1);
//        params.put("service_area", "enfield");
//        params.put("term", "Long Term Care Counseling");

        client.post(this.searchurl, params, handler);
    }


}
