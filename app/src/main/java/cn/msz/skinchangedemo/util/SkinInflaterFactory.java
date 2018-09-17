package cn.msz.skinchangedemo.util;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import cn.msz.skinchangedemo.config.SkinConfig;

import java.util.ArrayList;
import java.util.List;

public class SkinInflaterFactory implements LayoutInflater.Factory2 {
    private AppCompatActivity mAppCompatActivity;
    private List<SkinAttrViewItem> mSkinAttrViewItemList = new ArrayList<>();

    public SkinInflaterFactory(AppCompatActivity appCompatActivity) {
        this.mAppCompatActivity = appCompatActivity;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return null;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        boolean isSkinEnable = attrs.getAttributeBooleanValue(SkinConfig.NAMESPACE, SkinConfig.ATTR_SKIN_ENABLE, false);
        AppCompatDelegate delegate = mAppCompatActivity.getDelegate();
        View view = delegate.createView(parent, name, context, attrs);
        if (view == null) {
            return null;
        }
        //如果是需要重新设置属性的view，重新对其属性赋值
        if (isSkinEnable) {
            setViewSkinAttr(context, attrs, view);
        }
        return view;
    }

    private void setViewSkinAttr(Context context, AttributeSet attrs, View view) {
        List<SkinAttr> skinAttrList = new ArrayList<>();
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            String attrName = attrs.getAttributeName(i);
            //不支持的属性则continue
            if (!AttrFactory.isSupported(attrName)) {
                continue;
            }
            String attrValue = attrs.getAttributeValue(i);
            //需要重新赋值的属性值必须是引用值，也是为了方便引用外部资源
            if (attrValue.startsWith("@")) {
                int attrValueId = Integer.parseInt(attrValue.substring(1));
                String resourceTypeName = context.getResources().getResourceTypeName(attrValueId);
                SkinAttr skinAttr = AttrFactory.createSkinAttr(attrName, attrValueId, resourceTypeName);
                skinAttrList.add(skinAttr);
            }
        }
        if (!skinAttrList.isEmpty()) {
            SkinAttrViewItem item = new SkinAttrViewItem(view, skinAttrList);
            //系统默认的资源则不需要重新设置属性
            if (!SkinManager.getInstance().isDefaultSkin()) {
                //重新设置view的属性
                item.setViewAttrs();
            }
            //保存需要重新设置属性的view及其属性
            mSkinAttrViewItemList.add(item);
        }
    }

    public void addItem(DynamicSkinAttrItem item) {
        List<SkinAttr> skinAttrList = new ArrayList<>();
        skinAttrList.add(item.skinAttr);
        SkinAttrViewItem skinAttrViewItem = new SkinAttrViewItem(item.view, skinAttrList);
        skinAttrViewItem.setViewAttrs();
        mSkinAttrViewItemList.add(skinAttrViewItem);
    }

    private class SkinAttrViewItem {
        private View view;
        private List<SkinAttr> attrList;

        SkinAttrViewItem(View view, List<SkinAttr> attrList) {
            this.view = view;
            this.attrList = attrList;
        }

        void setViewAttrs() {
            if (attrList == null || attrList.isEmpty()) {
                return;
            }
            for (SkinAttr skinAttr : attrList) {
                skinAttr.setViewAttr(view);
            }
        }
    }

    public void changeSkin() {
        if (mSkinAttrViewItemList == null || mSkinAttrViewItemList.isEmpty()) {
            return;
        }
        for (SkinAttrViewItem skinAttrViewItem : mSkinAttrViewItemList) {
            if (skinAttrViewItem.view == null) {
                continue;
            }
            skinAttrViewItem.setViewAttrs();
        }
    }
}
