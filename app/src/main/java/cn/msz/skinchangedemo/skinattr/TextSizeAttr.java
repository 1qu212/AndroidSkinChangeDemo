package cn.msz.skinchangedemo.skinattr;

import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import cn.msz.skinchange.util.ResourceManager;
import cn.msz.skinchange.util.SkinAttr;

public class TextSizeAttr extends SkinAttr {
    @Override
    public void setViewAttr(View view) {
        if (view instanceof TextView && isDimen()) {
            ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, ResourceManager.getInstance().getDimen(attrValueId));
        }
    }
}
