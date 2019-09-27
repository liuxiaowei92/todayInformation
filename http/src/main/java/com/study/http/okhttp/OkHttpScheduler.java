package com.study.http.okhttp;

import com.study.http.HttpSchedule;
import com.study.http.annotation.RequestMethod;
import com.study.http.https.Https;
import com.study.http.request.call.ICall;
import com.study.http.request.IRequest;

import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @authour lxw
 * @function
 * @date 2019/9/25
 */
public class OkHttpScheduler extends HttpSchedule {
    private OkHttpClient client;

    @Override
    public ICall newCall(IRequest request) {
        Map<String, Object> params = request.getParams();
        int requestMethod = request.getRequestMethod();
        Request.Builder requestBuilder=new Request.Builder();
        switch (requestMethod){
            case RequestMethod.Get:
                //拼接get请求的url host+path
                StringBuilder urlStrBuilder=new StringBuilder(request.getHost().getHost());
                urlStrBuilder.append(request.getPath());
                HttpUrl.Builder urlBuilder = HttpUrl.parse(urlStrBuilder.toString()).newBuilder();

                if(params!=null&&params.size()>0){
                    Iterator<Map.Entry<String,Object>> iterator=params.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry<String,Object> next=iterator.next();
                        // TODO: 2019/9/25  对象转String
                        urlBuilder.addQueryParameter(next.getKey(),String.valueOf(next.getValue()));
                    }
                }
                requestBuilder.get().url(urlBuilder.build());
                break;
            case RequestMethod.Post:
                // TODO: 2019/9/25
                break;
                default:
        }
        Request okHttpRequest = requestBuilder.build();
        Call call = getClient().newCall(okHttpRequest);
        OkHttpCall okHttpCall=new OkHttpCall(request,call);

        return okHttpCall;
    }

    private OkHttpClient getClient() {
        if(client==null){
//            client=new OkHttpClient();
            OkHttpClient.Builder builder=new OkHttpClient.Builder();
            //https信任所有证书
            builder.sslSocketFactory(Https.getSSLSocketFactory());
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            client=builder.build();
        }
        return client;
    }
}
