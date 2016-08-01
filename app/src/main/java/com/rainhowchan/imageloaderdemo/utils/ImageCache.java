package com.rainhowchan.imageloaderdemo.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by RainhowChan on 2016/8/1.
 */
public class ImageCache {
    private LruCache<String,Bitmap> imageCache;

    public ImageCache() {
        initImageCache();
    }

    private void initImageCache() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize=maxMemory/4;
        imageCache = new LruCache<String,Bitmap>(cacheSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes()*bitmap.getHeight()/1024;
            }
        };

    }

    public void put(String url, Bitmap bitmap) {
        imageCache.put(url, bitmap);
    }

    public Bitmap get(String url) {
        return imageCache.get(url);
    }
}
