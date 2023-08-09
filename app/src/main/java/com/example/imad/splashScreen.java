package com.example.imad;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class splashScreen extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        else{
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
        stockCrawler stlk = new stockCrawler();
        stlk.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"https://groww.in/stocks/filter?closePriceHigh=100000&closePriceLow=0&marketCapHigh=2000000&marketCapLow=0&page=0&size=50&sortBy=CLOSE_PRICE&sortType=DESC");
        WebCrawlerExample webc = new WebCrawlerExample();
        webc.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"https://www.imdb.com/calendar/?ref_=rlm&region=IN&type=MOVIE");
        jobCrawler jobc = new jobCrawler();
        jobc.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"https://in.indeed.com/jobs?q=","sde");
        newsCrawler newc = new newsCrawler();
        newc.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"https://news.google.com/search?q=","caa");
        tv = findViewById(R.id.textView);
        YoYo.with(Techniques.Flash)
                .duration(2000)
                .repeat(2)
                .playOn(tv);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(), loginActivity.class));
                finish();
            }
        }, 3500);
    }
}