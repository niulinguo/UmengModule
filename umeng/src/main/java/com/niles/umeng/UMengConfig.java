package com.niles.umeng;

import com.umeng.commonsdk.UMConfigure;

import java.util.HashMap;

/**
 * Created by Niles
 * Date 2018/10/22 16:51
 * Email niulinguo@163.com
 */
public class UMengConfig {

    private final String mAppkey;
    private final String mChannel;
    private final int mDeviceType;
    private final String mPushSecret;
    private final String mSecret;
    private final HashMap<UMengSharePlatform, String[]> mShareInfo;

    private UMengConfig(String appkey, String channel, int deviceType, String pushSecret, String secret, HashMap<UMengSharePlatform, String[]> shareInfo) {
        mAppkey = appkey;
        mChannel = channel;
        mDeviceType = deviceType;
        mPushSecret = pushSecret;
        mSecret = secret;
        mShareInfo = shareInfo;
    }

    public HashMap<UMengSharePlatform, String[]> getShareInfo() {
        return mShareInfo;
    }

    public String getAppkey() {
        return mAppkey;
    }

    public String getSecret() {
        return mSecret;
    }

    public String getChannel() {
        return mChannel;
    }

    public int getDeviceType() {
        return mDeviceType;
    }

    public String getPushSecret() {
        return mPushSecret;
    }

    public static final class Builder {

        private String mAppkey;
        private String mChannel = "Default";
        private int mDeviceType = UMConfigure.DEVICE_TYPE_PHONE;
        private String mPushSecret = null;
        private String mSecret;
        private HashMap<UMengSharePlatform, String[]> mShareInfo = new HashMap<>();

        public String getAppkey() {
            return mAppkey;
        }

        public Builder setAppkey(String appkey) {
            mAppkey = appkey;
            return this;
        }

        public HashMap<UMengSharePlatform, String[]> getShareInfo() {
            return mShareInfo;
        }

        public Builder setShareInfo(UMengSharePlatform platform, String... keys) {
            mShareInfo.put(platform, keys);
            return this;
        }

        public String getSecret() {
            return mSecret;
        }

        public Builder setSecret(String secret) {
            mSecret = secret;
            return this;
        }

        public String getChannel() {
            return mChannel;
        }

        public Builder setChannel(String channel) {
            mChannel = channel;
            return this;
        }

        public int getDeviceType() {
            return mDeviceType;
        }

        public Builder setDeviceType(int deviceType) {
            mDeviceType = deviceType;
            return this;
        }

        public String getPushSecret() {
            return mPushSecret;
        }

        public Builder setPushSecret(String pushSecret) {
            mPushSecret = pushSecret;
            return this;
        }

        public UMengConfig build() {
            return new UMengConfig(
                    mAppkey,
                    mChannel,
                    mDeviceType,
                    mPushSecret,
                    mSecret,
                    mShareInfo
            );
        }
    }
}
