package cn.msz.skinchange.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Movie;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;

import java.io.InputStream;

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

    public String getString(int attrValueId) {
        String originString = context.getResources().getString(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originString;
        }
        String resName = context.getResources().getResourceEntryName(attrValueId);
        int trueValueId = mResources.getIdentifier(resName, "string", skinPackageName);
        String trueString;
        if (trueValueId == 0) {
            trueString = originString;
        } else {
            trueString = mResources.getString(attrValueId);
        }
        return trueString;
    }

    public String[] getStringArray(int attrValueId) {
        String[] originStringArray = context.getResources().getStringArray(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originStringArray;
        }
        String resName = context.getResources().getResourceEntryName(attrValueId);
        int trueValueId = mResources.getIdentifier(resName, "array", skinPackageName);
        String[] trueStringArray;
        if (trueValueId == 0) {
            trueStringArray = originStringArray;
        } else {
            trueStringArray = mResources.getStringArray(attrValueId);
        }
        return trueStringArray;
    }

    public CharSequence getText(int attrValueId) {
        CharSequence originCharSequence = context.getResources().getText(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originCharSequence;
        }
        String resName = context.getResources().getResourceEntryName(attrValueId);
        int trueValueId = mResources.getIdentifier(resName, "string", skinPackageName);
        CharSequence trueCharSequence;
        if (trueValueId == 0) {
            trueCharSequence = originCharSequence;
        } else {
            trueCharSequence = mResources.getText(attrValueId);
        }
        return trueCharSequence;
    }

    public CharSequence[] getTextArray(int attrValueId) {
        CharSequence[] originCharSequenceArray = context.getResources().getTextArray(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originCharSequenceArray;
        }
        String resName = context.getResources().getResourceEntryName(attrValueId);
        int trueValueId = mResources.getIdentifier(resName, "array", skinPackageName);
        CharSequence[] trueCharSequenceArray;
        if (trueValueId == 0) {
            trueCharSequenceArray = originCharSequenceArray;
        } else {
            trueCharSequenceArray = mResources.getTextArray(attrValueId);
        }
        return trueCharSequenceArray;
    }

    public InputStream openRawResource(int attrValueId) {
        InputStream originInputStream = context.getResources().openRawResource(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originInputStream;
        }
        String resName = context.getResources().getResourceEntryName(attrValueId);
        int trueValueId = mResources.getIdentifier(resName, "raw", skinPackageName);
        InputStream trueInputStream;
        if (trueValueId == 0) {
            trueInputStream = originInputStream;
        } else {
            trueInputStream = mResources.openRawResource(attrValueId);
        }
        return trueInputStream;
    }

    public InputStream openDrawableResource(int attrValueId) {
        InputStream originInputStream = context.getResources().openRawResource(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originInputStream;
        }
        String resName = context.getResources().getResourceEntryName(attrValueId);
        int trueValueId = mResources.getIdentifier(resName, "drawable", skinPackageName);
        InputStream trueInputStream;
        if (trueValueId == 0) {
            trueInputStream = originInputStream;
        } else {
            trueInputStream = mResources.openRawResource(attrValueId);
        }
        return trueInputStream;
    }

    public boolean getBoolean(int attrValueId) {
        boolean originBoolean = context.getResources().getBoolean(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originBoolean;
        }
        String resName = context.getResources().getResourceEntryName(attrValueId);
        int trueValueId = mResources.getIdentifier(resName, "bool", skinPackageName);
        boolean trueBoolean;
        if (trueValueId == 0) {
            trueBoolean = originBoolean;
        } else {
            trueBoolean = mResources.getBoolean(attrValueId);
        }
        return trueBoolean;
    }

    public int getInteger(int attrValueId) {
        int originInteger = context.getResources().getInteger(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originInteger;
        }
        String resName = context.getResources().getResourceEntryName(attrValueId);
        int trueValueId = mResources.getIdentifier(resName, "integer", skinPackageName);
        int trueInteger;
        if (trueValueId == 0) {
            trueInteger = originInteger;
        } else {
            trueInteger = mResources.getInteger(attrValueId);
        }
        return trueInteger;
    }

    public int[] getIntArray(int attrValueId) {
        int[] originIntArray = context.getResources().getIntArray(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originIntArray;
        }
        String resName = context.getResources().getResourceEntryName(attrValueId);
        int trueValueId = mResources.getIdentifier(resName, "array", skinPackageName);
        int[] trueIntArray;
        if (trueValueId == 0) {
            trueIntArray = originIntArray;
        } else {
            trueIntArray = mResources.getIntArray(attrValueId);
        }
        return trueIntArray;
    }

    public XmlResourceParser getLayout(int attrValueId) {
        XmlResourceParser originXmlResourceParser = context.getResources().getLayout(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originXmlResourceParser;
        }
        String resName = context.getResources().getResourceEntryName(attrValueId);
        int trueValueId = mResources.getIdentifier(resName, "layout", skinPackageName);
        XmlResourceParser trueXmlResourceParser;
        if (trueValueId == 0) {
            trueXmlResourceParser = originXmlResourceParser;
        } else {
            trueXmlResourceParser = mResources.getLayout(attrValueId);
        }
        return trueXmlResourceParser;
    }

    public XmlResourceParser getAnimation(int attrValueId) {
        XmlResourceParser originXmlResourceParser = context.getResources().getAnimation(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originXmlResourceParser;
        }
        String resName = context.getResources().getResourceEntryName(attrValueId);
        int trueValueId = mResources.getIdentifier(resName, "anim", skinPackageName);
        XmlResourceParser trueXmlResourceParser;
        if (trueValueId == 0) {
            trueXmlResourceParser = originXmlResourceParser;
        } else {
            trueXmlResourceParser = mResources.getAnimation(attrValueId);
        }
        return trueXmlResourceParser;
    }

    public XmlResourceParser getAnimator(int attrValueId) {
        XmlResourceParser originXmlResourceParser = context.getResources().getAnimation(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originXmlResourceParser;
        }
        String resName = context.getResources().getResourceEntryName(attrValueId);
        int trueValueId = mResources.getIdentifier(resName, "animator", skinPackageName);
        XmlResourceParser trueXmlResourceParser;
        if (trueValueId == 0) {
            trueXmlResourceParser = originXmlResourceParser;
        } else {
            trueXmlResourceParser = mResources.getAnimation(attrValueId);
        }
        return trueXmlResourceParser;
    }

    public XmlResourceParser getXml(int attrValueId) {
        XmlResourceParser originXmlResourceParser = context.getResources().getXml(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originXmlResourceParser;
        }
        String resName = context.getResources().getResourceEntryName(attrValueId);
        int trueValueId = mResources.getIdentifier(resName, "xml", skinPackageName);
        XmlResourceParser trueXmlResourceParser;
        if (trueValueId == 0) {
            trueXmlResourceParser = originXmlResourceParser;
        } else {
            trueXmlResourceParser = mResources.getXml(attrValueId);
        }
        return trueXmlResourceParser;
    }

    @TargetApi(Build.VERSION_CODES.O)
    public Typeface getFont(int attrValueId) {
        Typeface originTypeface = context.getResources().getFont(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originTypeface;
        }
        String resName = context.getResources().getResourceEntryName(attrValueId);
        int trueValueId = mResources.getIdentifier(resName, "font", skinPackageName);
        Typeface trueTypeface;
        if (trueValueId == 0) {
            trueTypeface = originTypeface;
        } else {
            trueTypeface = mResources.getFont(attrValueId);
        }
        return trueTypeface;
    }

    public Movie getMovie(int attrValueId) {
        Movie originMovie = context.getResources().getMovie(attrValueId);
        if (mResources == null || isDefaultSkin) {
            return originMovie;
        }
        String resName = context.getResources().getResourceEntryName(attrValueId);
        int trueValueId = mResources.getIdentifier(resName, "raw", skinPackageName);
        Movie trueMovie;
        if (trueValueId == 0) {
            trueMovie = originMovie;
        } else {
            trueMovie = mResources.getMovie(attrValueId);
        }
        return trueMovie;
    }
}
