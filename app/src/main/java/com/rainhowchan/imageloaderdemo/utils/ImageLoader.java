package com.rainhowchan.imageloaderdemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by RainhowChan on 2016/8/1.
 */
public class ImageLoader {
    private ImageCache imageCache = new ImageCache();
    private  ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void displayImage(final String url, final ImageView iv) {
       Bitmap bitmap= imageCache.get(url);
        if (bitmap != null) {
            iv.setImageBitmap(bitmap);
            return;
        }
        iv.setTag(url);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap=downloadImage(url);
                if (bitmap == null) {
                    return;
                }
                if (iv.getTag().equals(url)) {
                    iv.setImageBitmap(bitmap);
                }
                imageCache.put(url,bitmap);
            }
        });
    }

    private Bitmap downloadImage(String imgUrl) {
        Bitmap bitmap=null;
        try {
            URL url = new URL(imgUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            bitmap = BitmapFactory.decodeStream(conn.getInputStream());
            conn.disconnect();
        } catch (Exception e) {
            throw new RuntimeException("出错了");
        }
        return bitmap;
    }

}
