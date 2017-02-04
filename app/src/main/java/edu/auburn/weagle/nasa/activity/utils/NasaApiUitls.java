package edu.auburn.weagle.nasa.activity.utils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;

import edu.auburn.weagle.nasa.config.AppConfig;
import edu.auburn.weagle.nasa.model.Photo;

/**
 * API工具类
 * Author: Gary
 * Time: 17/2/4
 */

public class NasaApiUitls {
    static List<Photo> list ;
    public static List<Photo> getPhotoByRover(int roverId){

        RequestParams params = new RequestParams(AppConfig.sample);

        x.http().get(params, new Callback.CommonCallback<String>(){
            @Override
            public void onSuccess(String result) {
                list = ParserUtils.purser(result);

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
