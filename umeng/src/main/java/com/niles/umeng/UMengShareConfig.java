package com.niles.umeng;

import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Created by Niles
 * Date 2018/10/23 09:08
 * Email niulinguo@163.com
 */
public class UMengShareConfig {

    private final String mText;
    private final SHARE_MEDIA mPlatform;
    private final UMShareListener mShareCallback;

    private UMengShareConfig(String text, SHARE_MEDIA platform, UMShareListener shareCallback) {
        mText = text;
        mPlatform = platform;
        mShareCallback = shareCallback;
    }

    public String getText() {
        return mText;
    }

    public SHARE_MEDIA getPlatform() {
        return mPlatform;
    }

    public UMShareListener getShareCallback() {
        return mShareCallback;
    }

    public static final class Builder {

        private String mText;
        private SHARE_MEDIA mPlatform;
        private UMShareListener mShareCallback;

        public String getText() {
            return mText;
        }

        public Builder setText(String text) {
            mText = text;
            return this;
        }

        public SHARE_MEDIA getPlatform() {
            return mPlatform;
        }

        public Builder setPlatform(SHARE_MEDIA platform) {
            mPlatform = platform;
            return this;
        }

        public UMShareListener getShareCallback() {
            return mShareCallback;
        }

        public Builder setShareCallback(UMShareListener shareCallback) {
            mShareCallback = shareCallback;
            return this;
        }

        public UMengShareConfig build() {
            return new UMengShareConfig(
                    mText,
                    mPlatform,
                    mShareCallback
            );
        }
    }
}
