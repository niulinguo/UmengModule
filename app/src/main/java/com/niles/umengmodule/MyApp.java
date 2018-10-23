package com.niles.umengmodule;

import android.app.Application;

import com.niles.umeng.UMengConfig;
import com.niles.umeng.UMengManager;
import com.niles.umeng.UMengSharePlatform;

/**
 * Created by Niles
 * Date 2018/10/22 17:26
 * Email niulinguo@163.com
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UMengManager.init(this, BuildConfig.DEBUG, new UMengConfig.Builder()
                .setAppkey("5bcd8d68b465f5dab1000043")
                .setShareInfo(UMengSharePlatform.WEI_XIN, "", "")
                .build());
    }
}
