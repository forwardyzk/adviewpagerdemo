package com.ad.pager;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.ad.pager.view.AdViewPager;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private AdViewPager adViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adViewPager = (AdViewPager) findViewById(R.id.adviewpager);
        List<Integer> ids = new ArrayList<Integer>();
        ids.add(R.mipmap.image_1);
        ids.add(R.mipmap.image_2);
        ids.add(R.mipmap.image_3);
        ids.add(R.mipmap.image_4);
        ids.add(R.mipmap.image_5);
        ids.add(R.mipmap.image_6);
        ids.add(R.mipmap.image_7);
        adViewPager.setImagerRescoures(ids);

        List<String> urls = new ArrayList<String>();
        final String url1 = "http://e.hiphotos.baidu.com/image/pic/item/a5c27d1ed21b0ef40f6f1042dec451da81cb3e22.jpg";
        final String url2 = "http://h.hiphotos.baidu.com/image/pic/item/8601a18b87d6277f59504b3a2b381f30e924fc76.jpg";
        final String url3 = "http://g.hiphotos.baidu.com/image/pic/item/e1fe9925bc315c60ba5154c68eb1cb13485477d6.jpg";
        final String url4 = "http://e.hiphotos.baidu.com/image/pic/item/6159252dd42a2834a082bf2858b5c9ea15cebfe9.jpg";
        final String url5 = "http://b.hiphotos.baidu.com/image/pic/item/314e251f95cad1c85e57743b7c3e6709c93d513a.jpg";
        final String url6 = "http://g.hiphotos.baidu.com/image/pic/item/2f738bd4b31c8701df8414e6257f9e2f0708ff84.jpg";
        final String url7 = "http://f.hiphotos.baidu.com/image/pic/item/43a7d933c895d143c880555471f082025baf07d6.jpg";
        final String url8 = "http://a.hiphotos.baidu.com/image/w%3D2048/sign=14e5df7de9f81a4c2632ebc9e3126159/b3b7d0a20cf431add3392e304936acaf2fdd98cb.jpg";
        final String url9 = "http://h.hiphotos.baidu.com/image/w%3D2048/sign=fd51fe27aad3fd1f3609a53a0476241f/ac6eddc451da81cb4601eec45066d016082431d7.jpg";
        final String url10 = "http://g.hiphotos.baidu.com/image/w%3D2048/sign=d1cae12bcafcc3ceb4c0ce33a67dd788/d01373f082025aaf32d513c2f9edab64024f1ad7.jpg";
        urls.add(url1);
        urls.add(url2);
        urls.add(url3);
        urls.add(url4);
        urls.add(url5);
        urls.add(url6);
        urls.add(url7);
        urls.add(url8);
        urls.add(url9);
        urls.add(url10);

//        adViewPager.setImagerUrls(urls);
        adViewPager.setAdClickListener(new AdViewPager.AdClickListener() {
            @Override
            public void clickAd(int position) {
                switch (position) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "点击了第一个广告条", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(), "点击了第二个广告条", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(), "点击了第三个广告条", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(), "点击了第四个广告条", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(getApplicationContext(), "点击了第五个广告条", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        Toast.makeText(getApplicationContext(), "点击了第六个广告条", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adViewPager.stopTimer();
    }
}
