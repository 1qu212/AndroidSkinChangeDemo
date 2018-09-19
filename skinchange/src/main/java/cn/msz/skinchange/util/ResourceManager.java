package cn.msz.skinchange.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class ResourceManager {
    private Context context;
    private static volatile ResourceManager mInstance;
    private Resources mResources;
    private String skinPackageName;
    private boolean isDefaultSkin;

    public static ResourceManager getInstance() {
        if (mInstance == null) {
            synchronized (ResourceManager.class) {
                if (mInstance == null) {
                    mInstance = new ResourceManager();
                }
            }
        }
        return mInstance;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setmResources(Resources mResources) {
        this.mResources = mResources;
    }

    public void setSkinPackageName(String skinPackageName) {
        this.skinPackageName = skinPackageName;
    }

    public void setIsDefaultSkin(boolean b) {
        isDefaultSkin = b;
    }

    public int getColor(int attrValueId) {
        int originColor = context.getResources().getColor(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originColor;
        }

        String resName = context.getResources().getResourceEntryName(attrValueId);

        int trueValueId = mResources.getIdentifier(resName, "color", skinPackageName);
        int trueColor;
        if (trueValueId == 0) {
            trueColor = originColor;
        } else {
            trueColor = mResources.getColor(trueValueId);
        }
        return trueColor;
    }

    public ColorStateList getColorStateList(int attrValueId) {
        ColorStateList originColorStateList = context.getResources().getColorStateList(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originColorStateList;
        }

        String resName = context.getResources().getResourceEntryName(attrValueId);

        int trueValueId = mResources.getIdentifier(resName, "color", skinPackageName);
        ColorStateList trueColorStateList;
        if (trueValueId == 0) {
            trueColorStateList = originColorStateList;
        } else {
            trueColorStateList = mResources.getColorStateList(trueValueId);
        }
        return trueColorStateList;
    }

    public float getDimen(int attrValueId) {
        float originDimen = context.getResources().getDimension(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originDimen;
        }

        String resName = context.getResources().getResourceEntryName(attrValueId);

        int trueValueId = mResources.getIdentifier(resName, "dimen", skinPackageName);
        float trueDimen;
        if (trueValueId == 0) {
            trueDimen = originDimen;
        } else {
            trueDimen = mResources.getDimension(trueValueId);
        }
        return trueDimen;
    }

    public Drawable getDrawable(int attrValueId) {
        Drawable originDrawable = context.getResources().getDrawable(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originDrawable;
        }
        String resName = context.getResources().getResourceEntryName(attrValueId);
        int trueValueId = mResources.getIdentifier(resName, "drawable", skinPackageName);
        Drawable trueDrawable;
        if (trueValueId == 0) {
            trueDrawable = originDrawable;
        } else {
            trueDrawable = mResources.getDrawable(trueValueId);
        }
        return trueDrawable;
    }

    public Drawable getMipMap(int attrValueId) {
        Drawable originDrawable = context.getResources().getDrawable(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originDrawable;
        }
        String resName = context.getResources().getResourceEntryName(attrValueId);
        int trueValueId = mResources.getIdentifier(resName, "mipmap", skinPackageName);
        Drawable trueDrawable;
        if (trueValueId == 0) {
            trueDrawable = originDrawable;
        } else {
            trueDrawable = mResources.getDrawable(trueValueId);
        }
        return trueDrawable;
    }
}
