package cn.msz.skinchange.util;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import cn.msz.skinchange.config.SkinConfig;

public class SkinInflaterFactory implements LayoutInflater.Factory {
    private Activity mActivity;
    private List<SkinAttrViewItem> mSkinAttrViewItemList = new ArrayList<>();
    //见com.android.internal.policy.PhoneLayoutInflater
    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.webkit.",
            "android.app."
    };

    public SkinInflaterFactory(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        boolean isSkinEnable = attrs.getAttributeBooleanValue(SkinConfig.NAMESPACE, SkinConfig.ATTR_SKIN_ENABLE, false);
        View view = null;
        if (-1 == name.indexOf('.')) {
            for (String prefix : sClassPrefixList) {
                try {
                    view = LayoutInflater.from(context).createView(name, prefix, attrs);
                    if (view != null) {
                        break;
                    }
                } catch (ClassNotFoundException e) {
                }
            }
        } else {
            try {
                view = LayoutInflater.from(context).createView(name, null, attrs);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
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

    public void addItem(DynamicSkinAttrListItem item) {
        SkinAttrViewItem skinAttrViewItem = new SkinAttrViewItem(item.view, item.skinAttrList);
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
