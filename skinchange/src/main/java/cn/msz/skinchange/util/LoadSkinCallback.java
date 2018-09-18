package cn.msz.skinchange.util;

public interface LoadSkinCallback {
    void onSuccess();

    void onFailed();

    void onProgress(int progress);
}
