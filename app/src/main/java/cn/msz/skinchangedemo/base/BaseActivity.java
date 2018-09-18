package cn.msz.skinchangedemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.msz.skinchange.util.SkinChangeListener;
import cn.msz.skinchange.util.SkinInflaterFactory;
import cn.msz.skinchange.util.SkinManager;

public class BaseActivity extends AppCompatActivity implements SkinChangeListener {
    public SkinInflaterFactory mSkinInflaterFactory;

    @Override
    public void onSkinChanged() {
        mSkinInflaterFactory.changeSkin();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mSkinInflaterFactory = new SkinInflaterFactory(this);
        getLayoutInflater().setFactory2(mSkinInflaterFactory);
        super.onCreate(savedInstanceState);
        SkinManager.getInstance().registerSkinChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().unRegisterSkinChangeListener(this);
    }
}
