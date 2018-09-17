package cn.msz.skinchangedemo.util;

public interface LoadSkinCallback {
    void onSuccess();

    void onFailed();

    void onProgress(int progress);
}
