package cn.msz.skinchangedemo.skinattr;

import android.view.View;

import cn.msz.skinchangedemo.util.ResourceManager;
import cn.msz.skinchangedemo.util.SkinAttr;

public class BackgroundAttr extends SkinAttr {
    @Override
    public void setViewAttr(View view) {
        if (isColor()) {
            view.setBackgroundColor(ResourceManager.getInstance().getColor(attrValueId));
        } else if (isDrawable()) {
            view.setBackgroundDrawable(ResourceManager.getInstance().getDrawable(attrValueId));
        } else if (isMipmap()) {
            view.setBackgroundDrawable(ResourceManager.getInstance().getMipMap(attrValueId));
        }
    }
}
