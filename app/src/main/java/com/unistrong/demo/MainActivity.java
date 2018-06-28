package com.unistrong.demo;

import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.auto_generate.local.PersonDao;
import com.greendao.GreenDaoUtils;
import com.unistrong.baselibs.style.BaseActivity;
import com.unistrong.baselibs.ui.ProgressView;
import com.unistrong.baselibs.ui.chart.BaseMeasure;
import com.unistrong.baselibs.ui.chart.PolyChartView;
import com.unistrong.demo.bean.Person;
import com.unistrong.requestlibs.request.MultiPartImpl;
import com.unistrong.requestlibs.response.ResponseBody;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private TextView viewById;
    private String path = Environment.getExternalStorageDirectory().getAbsolutePath();
    private PolyChartView iv;
    private ProgressView pb;

    @Override
    protected void initMvp() {
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.tv);
        iv.setData(92, getdata());
    }

    private List<BaseMeasure.BindData> getdata() {
        ArrayList<BaseMeasure.BindData> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            BaseMeasure.BindData e = new BaseMeasure.BindData();
            e.valueY = 4 * i;
            e.flagX = "" + i;
            datas.add(e);
        }
        return datas;
    }

    private void testGreenDao() {
        //增
        PersonDao personDao = GreenDaoUtils.newXXdao(Person.class);
        // personDao.insert(new Person(null, "王五", 0, "45"));
        personDao.insert(new Person(null, "张三", 0, "20"));
//        //查
//        WhereCondition equals = PersonDao.Properties.Name.eq("张三");
//        QueryBuilder<Person> builder = personDao.queryBuilder().where(equals);
//        Query<Person> build = builder.build();
//        List<Person> list = build.list();
//        //改
//        personDao.update(null);
//        //删
//        personDao.delete(null);
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
//        switch (requestCode) {
//            case PictureConfig.CHOOSE_REQUEST:
//                // 图片、视频、音频选择结果回调
//                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(resultCode, data);
//                if (!selectList.isEmpty()) {
//                    String imagePath = selectList.get(0).getPath();
//                    Glide.with(MainActivity.this).load(imagePath).into(iv);
//                }
//                break;
//        }
    }

}
