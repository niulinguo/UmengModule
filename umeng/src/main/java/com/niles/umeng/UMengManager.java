package com.niles.umeng;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.niles.umeng_share.UMengShareManager;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import java.util.HashMap;

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

        try {
            UMengShareManager.init(app, debug, config);
        } catch (NoClassDefFoundError error) {
            if (debug) {
                Log.e(TAG, "没有继承友盟分享");
            }
        }
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
}
