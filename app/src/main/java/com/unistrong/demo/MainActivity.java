package com.unistrong.demo;

import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.requestlibs.request.HttpRequestImpl;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.HashMap;

public class MainActivity extends BaseActivity {

    private TextView viewById;
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    private ImageView iv;

    @Override
    protected void initMvp() {
        setContentView(R.layout.activity_main);
        viewById = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);
    }

    public void request(View view) {
//        String imagePath = "http://www.xinhuanet.com/world/2015-12/03/128496492_14491425659751n.jpg";
//        Picasso.get().load(imagePath).into(iv);
//        String serverUrl = "http://issuecdn.baidupcs.com/issue/netdisk/yunguanjia/BaiduNetdisk_6.2.0.exe";
//        MultiPartImpl.init().downloadFile(serverUrl, path, new Handler(), new ResponseBody(Object.class) {
//            @Override
//            public void onSuccess(Object json) {
//            }
//
//            @Override
//            public void onProgress(int progress) {
//                viewById.setText("进度" + progress + "%");
//            }
//
//            @Override
//            public void onSuccess(String json) {
//                viewById.setText(json);
//            }
//
//            @Override
//            public void onFailure(String message) {
//                viewById.setText(message);
//            }
//        });

        String url = "https://www.sojson.com/open/api/weather/json.shtml";
        HashMap<String, String> map = new HashMap<>();
        map.put("city", "北京");
        HttpRequestImpl.getInstance().requestGet(url, map, new ResponseBody<Weather>(Weather.class) {
            @Override
            public void onSuccess(Weather weather) {
                //     viewById.setText(weather.toString());
            }

            @Override
            public void onSuccess(String json) {
                viewById.setText(json);
            }

            @Override
            public void onFailure(String message) {
                viewById.setText("null!");
            }
        });
    }
}
