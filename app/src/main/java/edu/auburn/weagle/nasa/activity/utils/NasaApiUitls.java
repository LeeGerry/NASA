package edu.auburn.weagle.nasa.activity.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import edu.auburn.weagle.nasa.config.AppConfig;
import edu.auburn.weagle.nasa.model.Photo;

/**
 * API工具类
 * Author: Gary
 * Time: 17/2/4
 */

public class NasaApiUitls {
    public static List<Photo> getPhotoByRover(int roverId){
        final List<Photo> list = new ArrayList<>();
        RequestParams params = new RequestParams(AppConfig.sample);

        x.http().get(params, new Callback.CommonCallback<String>(){
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject jo = new JSONObject(result);
                    JSONArray photos = jo.getJSONArray("photos");
                    JSONObject model = (JSONObject) photos.get(0);
                    String img_src = model.getString("img_src");
//                   for (){
//                       list.add();
//                   }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        return list;
    }
}
