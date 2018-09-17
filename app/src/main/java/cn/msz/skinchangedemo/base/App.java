package cn.msz.skinchangedemo.base;

import android.app.Application;

import cn.msz.skinchangedemo.skinattr.BackgroundAttr;
import cn.msz.skinchangedemo.skinattr.TextColorAttr;
import cn.msz.skinchangedemo.skinattr.TextSizeAttr;
import cn.msz.skinchangedemo.util.AttrFactory;
import cn.msz.skinchangedemo.util.SkinManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this);
        AttrFactory.addSupportAttr("textColor", new TextColorAttr());
        AttrFactory.addSupportAttr("textSize", new TextSizeAttr());
        AttrFactory.addSupportAttr("background", new BackgroundAttr());
    }
}
