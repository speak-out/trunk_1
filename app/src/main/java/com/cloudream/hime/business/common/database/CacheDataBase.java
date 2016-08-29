package com.cloudream.hime.business.common.database;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import com.cloudream.hime.business.base.BaseApplication;
import com.cloudream.hime.business.utils.DiskLruCache;
import com.google.gson.Gson;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import okhttp3.internal.io.FileSystem;

/**
 * Created by yuer on 2016/8/29.
 */
public class CacheDataBase {
    private static final String DATA_DATA_BASE = "himeBusiness.db";   //缓存名称
    private static CacheDataBase INSTANCE = null;
    private static Context mContext;
    private static DiskLruCache mDiskLruCache = null;
    Gson gson = new Gson();
    File dataFile = new File(BaseApplication.getInstance().getFilesDir(), DATA_DATA_BASE);

    public static CacheDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CacheDataBase();
            mContext = context;
            try {
                File cacheDir = getDiskCacheDir(context, "/hime/business/cache");
                if (!cacheDir.exists()) {
                    cacheDir.mkdirs();
                }
                mDiskLruCache = DiskLruCache.open(cacheDir, getAppVersion(context), 1, 10 * 1024 * 1024);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return INSTANCE;
    }

    public Object readCache(String CacheUrl) {
        String json = "";
        try {
            DiskLruCache.Snapshot snapshot = mDiskLruCache.get(hashKeyForDisk(CacheUrl));
            if (snapshot != null) {
                InputStream inputStream = snapshot.getInputStream(0);
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length = -1;
                while ((length = inputStream.read(buffer)) != -1) {
                    arrayOutputStream.write(buffer, 0, length);
                }
                json = arrayOutputStream.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    //将数据缓存起来
    public void saveCache(Object object, String CacheUrl) {
        String json = gson.toJson(object);
        try {
            if (!dataFile.exists()) {
                try {
                    DiskLruCache.Editor edit = mDiskLruCache.edit(hashKeyForDisk(CacheUrl));
                    OutputStream newOutputStream = edit.newOutputStream(0);
                    newOutputStream.write(json.getBytes());
                    newOutputStream.close();
                    newOutputStream.flush();
                    edit.commit();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Writer writer = new FileWriter(dataFile);
            writer.write(json);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //获取md5加密的路径
    public String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    //MD5加密
    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    //获取缓存文件的根路劲
    public static File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath = "";
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();
        } else {
            cachePath = context.getCacheDir().getPath();
        }
        return new File(cachePath + File.separator + uniqueName);
    }

    //获取版本号
    public static int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    //清空这个缓存文件，也就是将这个文件删掉
    public void delete() {
        dataFile.delete();

    }
}
