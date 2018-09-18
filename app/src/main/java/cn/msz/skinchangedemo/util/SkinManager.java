package cn.msz.skinchangedemo.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;

import cn.msz.skinchangedemo.config.SkinConfig;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SkinManager {
    private Context context;
    private static volatile SkinManager mInstance;
    private boolean isSystemResources = true;
    private List<SkinChangeListener> skinChangeListenerList = new ArrayList<>();
    private static final String SKINPATH = "skin_path";

    public void init(Context context, boolean reloadSkin) {
        this.context = context;
        ResourceManager.getInstance().setContext(context);
        try {
            String[] skinFiles = context.getAssets().list(SkinConfig.SKIN_DIR_NAME);
            for (String fileName : skinFiles) {
                File file = new File(SkinFileUtils.getSkinDir(context), fileName);
                if (!file.exists() || reloadSkin) {
                    SkinFileUtils.copySkinAssetsToDir(context, fileName, SkinFileUtils.getSkinDir(context));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String skinPkgPath = SkinPreferencesUtils.getString(context, SKINPATH);
        if (skinPkgPath != null) {
            loadSkin(skinPkgPath, null);
        }
    }

    public static SkinManager getInstance() {
        if (mInstance == null) {
            synchronized (SkinManager.class) {
                if (mInstance == null) {
                    mInstance = new SkinManager();
                }
            }
        }
        return mInstance;
    }

    public boolean isDefaultSkin() {
        return isSystemResources;
    }

    public void registerSkinChangeListener(SkinChangeListener skinChangeListener) {
        skinChangeListenerList.add(skinChangeListener);
    }

    public void unRegisterSkinChangeListener(SkinChangeListener skinChangeListener) {
        skinChangeListenerList.remove(skinChangeListener);
    }

    public void loadSkin(final String skinPath, final LoadSkinCallback loadSkinCallback) {
        new AsyncTask<String, Integer, Resources>() {
            @Override
            protected void onProgressUpdate(Integer... values) {
                loadSkinCallback.onProgress(values[0]);
            }

            @Override
            protected Resources doInBackground(String... strings) {
                try {
                    String skinPkgPath = strings[0];

                    File file = new File(skinPkgPath);
                    if (!file.exists()) {
                        return null;
                    }

                    PackageManager packageManager = context.getPackageManager();
                    PackageInfo packageInfo = packageManager.getPackageArchiveInfo(skinPkgPath, PackageManager.GET_ACTIVITIES);
                    ResourceManager.getInstance().setSkinPackageName(packageInfo.packageName);
                    AssetManager assetManager = AssetManager.class.newInstance();
                    Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
                    addAssetPath.invoke(assetManager, skinPkgPath);

                    Resources superRes = context.getResources();
                    Resources skinResource = new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
                    return skinResource;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Resources resources) {
                ResourceManager.getInstance().setmResources(resources);
                if (resources != null) {
                    SkinPreferencesUtils.putString(context, SKINPATH, skinPath);
                    isSystemResources = false;
                    ResourceManager.getInstance().setIsDefaultSkin(isSystemResources);
                    if (loadSkinCallback != null) {
                        loadSkinCallback.onSuccess();
                    }
                    notifySkinChange();
                } else {
                    isSystemResources = true;
                    ResourceManager.getInstance().setIsDefaultSkin(isSystemResources);
                    if (loadSkinCallback != null) {
                        loadSkinCallback.onFailed();
                    }
                }
            }
        }.execute(skinPath);
    }

    public void loadAssetSkin(final String assetSkin, final LoadSkinCallback loadSkinCallback) {
        new AsyncTask<String, Integer, Resources>() {
            @Override
            protected void onProgressUpdate(Integer... values) {
                loadSkinCallback.onProgress(values[0]);
            }

            @Override
            protected Resources doInBackground(String... strings) {
                try {
                    String skinPkgPath = SkinFileUtils.getSkinDir(context) + File.separator + strings[0];

                    File file = new File(skinPkgPath);
                    if (!file.exists()) {
                        return null;
                    }

                    PackageManager packageManager = context.getPackageManager();
                    PackageInfo packageInfo = packageManager.getPackageArchiveInfo(skinPkgPath, PackageManager.GET_ACTIVITIES);
                    ResourceManager.getInstance().setSkinPackageName(packageInfo.packageName);
                    AssetManager assetManager = AssetManager.class.newInstance();
                    Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
                    addAssetPath.invoke(assetManager, skinPkgPath);

                    Resources superRes = context.getResources();
                    return new Resources(assetManager, superRes.getDisplayMetrics(), superRes.getConfiguration());
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Resources resources) {
                ResourceManager.getInstance().setmResources(resources);
                if (resources != null) {
                    String skinPkgPath = SkinFileUtils.getSkinDir(context) + File.separator + assetSkin;
                    SkinPreferencesUtils.putString(context, SKINPATH, skinPkgPath);
                    isSystemResources = false;
                    ResourceManager.getInstance().setIsDefaultSkin(isSystemResources);
                    if (loadSkinCallback != null) {
                        loadSkinCallback.onSuccess();
                    }
                    notifySkinChange();
                } else {
                    isSystemResources = true;
                    ResourceManager.getInstance().setIsDefaultSkin(isSystemResources);
                    if (loadSkinCallback != null) {
                        loadSkinCallback.onFailed();
                    }
                }
            }
        }.execute(assetSkin);
    }

    public void resetDefualtSkin() {
        ResourceManager.getInstance().setmResources(context.getResources());
        SkinPreferencesUtils.clearPreferencesByKey(context, SKINPATH);
        notifySkinChange();
    }

    private void notifySkinChange() {
        if (skinChangeListenerList == null) return;
        for (SkinChangeListener skinChangeListener : skinChangeListenerList) {
            skinChangeListener.onSkinChanged();
        }
    }
}
