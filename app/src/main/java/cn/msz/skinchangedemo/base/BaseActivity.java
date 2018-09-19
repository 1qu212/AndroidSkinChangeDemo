package cn.msz.skinchangedemo.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import cn.msz.skinchange.util.SkinChangeListener;
import cn.msz.skinchange.util.SkinInflaterFactory;
import cn.msz.skinchange.util.SkinManager;

public class BaseActivity extends Activity implements SkinChangeListener {
    public SkinInflaterFactory mSkinInflaterFactory;

    @Override
    public void onSkinChanged() {
        mSkinInflaterFactory.changeSkin();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mSkinInflaterFactory = new SkinInflaterFactory(this);
        getLayoutInflater().setFactory(mSkinInflaterFactory);
        super.onCreate(savedInstanceState);
        SkinManager.getInstance().registerSkinChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().unRegisterSkinChangeListener(this);
    }
}
