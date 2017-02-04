package edu.auburn.weagle.nasa;

import android.app.Application;

import org.xutils.x;

/**
 * Author: Gary
 * Time: 17/2/3
 */

public class NasaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }
}
