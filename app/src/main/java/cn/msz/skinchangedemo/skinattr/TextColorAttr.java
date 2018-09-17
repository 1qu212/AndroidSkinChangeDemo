package cn.msz.skinchangedemo.skinattr;

import android.view.View;
import android.widget.TextView;

import cn.msz.skinchangedemo.util.ResourceManager;
import cn.msz.skinchangedemo.util.SkinAttr;

public class TextColorAttr extends SkinAttr {
    @Override
    public void setViewAttr(View view) {
        if (view instanceof TextView && isColor()) {
            ((TextView) view).setTextColor(ResourceManager.getInstance().getColor(attrValueId));
        }
    }
}
