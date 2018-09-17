package cn.msz.skinchangedemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.msz.skinchangedemo.util.SkinChangeListener;
import cn.msz.skinchangedemo.util.SkinInflaterFactory;
import cn.msz.skinchangedemo.util.SkinManager;

public class BaseActivity extends AppCompatActivity implements SkinChangeListener {
    private SkinInflaterFactory skinInflaterFactory;

    @Override
    public void onSkinChanged() {
        skinInflaterFactory.changeSkin();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        skinInflaterFactory = new SkinInflaterFactory(this);
        getLayoutInflater().setFactory2(skinInflaterFactory);
        super.onCreate(savedInstanceState);
        SkinManager.getInstance().registerSkinChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinManager.getInstance().unRegisterSkinChangeListener(this);
    }
}
