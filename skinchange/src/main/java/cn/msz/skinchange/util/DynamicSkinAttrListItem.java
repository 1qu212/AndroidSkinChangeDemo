package cn.msz.skinchange.util;

import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class DynamicSkinAttrListItem {
    public List<SkinAttr> skinAttrList = new ArrayList<>();
    public View view;

    public DynamicSkinAttrListItem(View view, List<SkinAttrItem> skinAttrItemList) {
        this.view = view;
        for (SkinAttrItem skinAttrItem : skinAttrItemList) {
            skinAttrList.add(skinAttrItem.getSkinAttr());
        }
    }

    public static class SkinAttrItem {
        private SkinAttr skinAttr;

        public SkinAttrItem(Context context, SkinAttr skinAttr, int attrValueId) {
            this.skinAttr = skinAttr.clone();
            this.skinAttr.attrValueId = attrValueId;
            this.skinAttr.resourceTypeName = context.getResources().getResourceTypeName(attrValueId);
        }

        SkinAttr getSkinAttr() {
            return skinAttr;
        }
    }
}
