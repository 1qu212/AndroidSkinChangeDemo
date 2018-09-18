package cn.msz.skinchangedemo.base;

import android.app.Application;

import cn.msz.skinchange.util.AttrFactory;
import cn.msz.skinchange.util.SkinManager;
import cn.msz.skinchangedemo.skinattr.BackgroundAttr;
import cn.msz.skinchangedemo.skinattr.TextColorAttr;
import cn.msz.skinchangedemo.skinattr.TextSizeAttr;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SkinManager.getInstance().init(this, true);
        AttrFactory.addSupportAttr("textColor", new TextColorAttr());
        AttrFactory.addSupportAttr("textSize", new TextSizeAttr());
        AttrFactory.addSupportAttr("background", new BackgroundAttr());
    }
}
