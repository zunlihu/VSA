package com.magic.klaus.vsa;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.magic.klaus.vsa.presenter.PreprocessPresenter;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.OpenCVLoader;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "OpenCVLoader";
    private ImageView show_image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        TextView textView = (TextView)findViewById(R.id.sample_text);
        textView.setText(PreprocessPresenter.stringFromJNI());
        show_image = (ImageView) findViewById(R.id.show_image);
        show_image.setImageResource(R.drawable.cat);
        findViewById(R.id.pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //恢复
                backPic();
            }
        });
        findViewById(R.id.gray_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //变灰
                grayPic();
            }
        });
    }
    private void backPic(){
        show_image.setImageResource(R.drawable.cat);
    }
    private void grayPic(){
        Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.cat);
        int w = bmp.getWidth();
        int h = bmp.getHeight();
        int[] pixels = new int[w*h];
        bmp.getPixels(pixels, 0, w, 0, 0, w, h);
        //recall JNI
        int[] resultInt = PreprocessPresenter.getGrayImage(pixels, w, h);
        Bitmap resultImg = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        resultImg.setPixels(resultInt, 0, w, 0, 0, w, h);
        show_image.setImageBitmap(resultImg);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (OpenCVLoader.initDebug()) {
            Log.i(TAG, "OpenCV initialize success");
        } else {
            Log.i(TAG, "OpenCV initialize failed");
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
}
