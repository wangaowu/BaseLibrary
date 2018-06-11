package com.unistrong.demo;

import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.auto_generate.local.PersonDao;
import com.auto_generate.local.UserDao;
import com.bumptech.glide.Glide;
import com.greendao.GreenDaoUtils;
import com.picture.lib.PictureSelector;
import com.picture.lib.config.PictureConfig;
import com.picture.lib.entity.LocalMedia;
import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.baselibs.ui.ProgressView;
import com.unistrong.demo.bean.Person;
import com.unistrong.demo.bean.User;
import com.unistrong.requestlibs.request.MultiPartImpl;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.List;

public class MainActivity extends BaseActivity {

    private TextView viewById;
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    private ImageView iv;
    private ProgressView pb;

    @Override
    protected void initMvp() {
        setContentView(R.layout.activity_main);
        viewById = findViewById(R.id.tv);
        iv = findViewById(R.id.iv);
        pb = findViewById(R.id.pb);
        testGreenDao();
        PersonDao personDao = GreenDaoUtils.newXXdao(Person.class);
        UserDao abstractDao = GreenDaoUtils.newXXdao(User.class);
    }

    private void testGreenDao() {
        //  PersonDao personDao = GreenDaoUtils.getDaoSession().getPersonDao();

//        Person person1 = new Person(1, "张三", 1, "20");
//        personDao.insert(person1);

        PersonDao personDao = GreenDaoUtils.newXXdao(Person.class);
        personDao.insert(new Person(System.currentTimeMillis(), "王五", 0, "45"));
    }

    public void request(View view) {
        //请求网址
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

        //请求
//        String url = "https://www.sojson.com/open/api/weather/json.shtml";
//        HashMap<String, String> map = new HashMap<>();
//        map.put("city", "北京");
//        HttpRequestImpl.getInstance().requestGet(url, map, new ResponseBody<Weather>(Weather.class) {
//            @Override
//            public void onSuccess(Weather weather) {
//                //     viewById.setText(weather.toString());
//            }
//
//            @Override
//            public void onSuccess(String json) {
//                viewById.setText(json);
//            }
//
//            @Override
//            public void onFailure(String message) {
//                viewById.setText("null!");
//            }
//        });

//        //选择照片
//        PictureSelector.use(this).openGallery(PictureMimeType.TYPE_IMAGE())
//                .forResult(PictureConfig.CHOOSE_REQUEST);

        String serverPath = "https://dl.google.com/dl/android/studio/install/3.1.3.0/android-studio-ide-173.4819257-windows.exe";
        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        MultiPartImpl.getInstance().downloadFile(serverPath, absolutePath, new Handler(), new ResponseBody(Object.class) {
            @Override
            public void onFailure(String message) {

            }

            @Override
            public void onProgress(int progress) {
                super.onProgress(progress);
                pb.setProgress(progress);
            }

            @Override
            public void onSuccess(Object json) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                // 图片、视频、音频选择结果回调
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(resultCode, data);
                if (!selectList.isEmpty()) {
                    String imagePath = selectList.get(0).getPath();
                    Glide.with(MainActivity.this).load(imagePath).into(iv);
                }
                break;
        }
    }

}
