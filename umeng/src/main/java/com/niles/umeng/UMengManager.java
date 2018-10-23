package com.niles.umeng;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Niles
 * Date 2018/10/22 16:48
 * Email niulinguo@163.com
 */
public class UMengManager {

    private static final String TAG = "Niles_UMeng";

    private static Application sApp;
    private static boolean sIsDebug;
    private static UMengConfig sConfig;

    public static void init(Application app, boolean debug, UMengConfig config) {
        sApp = app;
        sConfig = config;
        sIsDebug = debug;

        UMConfigure.init(app, config.getAppkey(), config.getChannel(), config.getDeviceType(), config.getPushSecret());
        UMConfigure.setLogEnabled(debug);
        MobclickAgent.setScenarioType(app, MobclickAgent.EScenarioType.E_UM_NORMAL);
        if (config.getSecret() != null) {
            MobclickAgent.setSecret(app, config.getSecret());
        }
        MobclickAgent.openActivityDurationTrack(false);

        HashMap<UMengSharePlatform, String[]> shareInfo = config.getShareInfo();
        for (Map.Entry<UMengSharePlatform, String[]> entry : shareInfo.entrySet()) {
            UMengSharePlatform platform = entry.getKey();
            String[] value = entry.getValue();
            switch (platform) {
                case WEI_XIN: {
                    PlatformConfig.setWeixin(value[0], value[1]);
                    break;
                }
                default: {
                    Log.e(TAG, "暂不支持" + platform.name() + "平台");
                }
            }
        }
    }

    public static void share(Activity activity, UMengShareConfig config) {
        new ShareAction(activity)
                .setPlatform(config.getPlatform())
                .withText(config.getText())
                .setCallback(config.getShareCallback())
                .share();
    }

    /**
     * 统计页面使用时长
     */
    public static void onActivityResume(Activity activity) {
        MobclickAgent.onResume(activity);
    }

    public static void onActivityPause(Activity activity) {
        MobclickAgent.onPause(activity);
    }

    /**
     * 统计页面跳转
     */
    public static void onPageStart(String name) {
        MobclickAgent.onPageStart(name);
    }

    public static void onPageEnd(String name) {
        MobclickAgent.onPageEnd(name);
    }

    /**
     * 用户登录
     */
    public static void onUserSignIn(String provider, String uid) {
        MobclickAgent.onProfileSignIn(provider, uid);
    }

    public static void onUserSignOff() {
        MobclickAgent.onProfileSignOff();
    }

    public static void onEvent(String eventID, String label) {
        if (label == null) {
            MobclickAgent.onEvent(sApp, eventID);
        } else {
            MobclickAgent.onEvent(sApp, eventID, label);
        }
    }

    public static void onEvent(String eventID, HashMap<String, String> attrs) {
        MobclickAgent.onEvent(sApp, eventID, attrs);
    }

    public static void onEventValue(String eventID, HashMap<String, String> attrs, int duration) {
        MobclickAgent.onEventValue(sApp, eventID, attrs, duration);
    }

    public static void reportError(String error) {
        MobclickAgent.reportError(sApp, error);
    }

    public static void reportError(Throwable e) {
        MobclickAgent.reportError(sApp, e);
    }

    /**
     * 友盟分享回调结果
     */
    public static void onActivityResult(Context context, int requestCode, int resultCode, @Nullable Intent data) {
        UMShareAPI.get(context).onActivityResult(requestCode, resultCode, data);
    }

}
