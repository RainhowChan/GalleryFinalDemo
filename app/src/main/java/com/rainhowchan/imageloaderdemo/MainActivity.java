package com.rainhowchan.imageloaderdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.rainhowchan.imageloaderdemo.utils.ImageLoader;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private ImageView iv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        iv2 = (ImageView) findViewById(R.id.iv2);
        int img = getResources().getIdentifier("ic", "drawable", getPackageName());
//        iv.setImageResource(img);
        ImageLoader imageLoader = new ImageLoader();
        imageLoader.displayImage("http://192.168.1.112/test/ic.png",iv2);
    }
}
