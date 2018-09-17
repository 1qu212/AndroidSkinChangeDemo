package cn.msz.skinchangedemo.util;

import android.view.View;

public abstract class SkinAttr implements Cloneable {
    protected static final String RES_TYPE_NAME_COLOR = "color";
    protected static final String RES_TYPE_NAME_DRAWABLE = "drawable";
    protected static final String RES_TYPE_NAME_MIPMAP = "mipmap";
    protected static final String RES_TYPE_NAME_DIMEN = "dimen";
    //属性值引用id
    public int attrValueId;
    //属性类型
    public String resourceTypeName;

    //给view设置对应属性值
    public abstract void setViewAttr(View view);

    @Override
    public SkinAttr clone() {
        SkinAttr skinAttr = null;
        try {
            skinAttr = (SkinAttr) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return skinAttr;
    }

    protected boolean isDrawable() {
        return RES_TYPE_NAME_DRAWABLE.equals(resourceTypeName);
    }

    protected boolean isMipmap() {
        return RES_TYPE_NAME_MIPMAP.equals(resourceTypeName);
    }

    protected boolean isColor() {
        return RES_TYPE_NAME_COLOR.equals(resourceTypeName);
    }

    protected boolean isDimen() {
        return RES_TYPE_NAME_DIMEN.equals(resourceTypeName);
    }
}
