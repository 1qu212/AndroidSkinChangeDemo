package cn.msz.skinchangedemo.util;

import java.util.HashMap;

public class AttrFactory {
    private static HashMap<String, SkinAttr> supportAttrs = new HashMap<>();

    //是否是支持重新赋值的属性
    public static boolean isSupported(String attrName) {
        return supportAttrs.containsKey(attrName);
    }

    //添加支持属性
    public static void addSupportAttr(String attrName, SkinAttr skinAttr) {
        supportAttrs.put(attrName, skinAttr);
    }

    public static SkinAttr createSkinAttr(String attrName, int attrValueId, String resourceTypeName) {
        SkinAttr skinAttr = supportAttrs.get(attrName).clone();
        if (skinAttr == null) {
            return null;
        }
        skinAttr.attrValueId = attrValueId;
        skinAttr.resourceTypeName = resourceTypeName;
        return skinAttr;
    }
}
