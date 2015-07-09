##自定义的广告条控件
- 支持本地图片资源和网络资源
- 自动播放和循环显示
- 自动填充底部指针按钮
- 有点击事件
- 当触摸在广告条上,自动播放停止,当触摸离开后,继续循环播放

###效果图

###导入jar包

>项目中使用了ImagerLoader加载图片,所以要导入com.nostra13.universalimageloader:universal-image-loader包

###使用步骤

- **1.在布局文件中使用自定义的控件**
  ```
   <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@android:color/darker_gray">

    <com.ad.pager.view.AdViewPager
        android:id="@+id/adviewpager"
        android:layout_width="match_parent"
        android:layout_height="200dp">

    </com.ad.pager.view.AdViewPager>

</RelativeLayout>
```

- **2.设置显示资源**

   *设置本地资源*
   
   使用adViewPager.setImagerRescoures(ids)设置本地资源
   例如:
   
  ```
   List<Integer> ids = new ArrayList<Integer>();
        ids.add(R.mipmap.image_1);
        ids.add(R.mipmap.image_2);
        ids.add(R.mipmap.image_3);
        ids.add(R.mipmap.image_4);
        adViewPager.setImagerRescoures(ids);
  
  ```
  
  *设置网络资源*
  
  使用adViewPager.setImagerUrls(urls);方法设置网络资源
  
  例如:
  ```
  List<String> urls = new ArrayList<String>();
        final String url1 = "http://e.hiphotos.baidu.com/image/pic/item/a5c27d1ed21b0ef40f6f1042dec451da81cb3e22.jpg";
        final String url2 = "http://h.hiphotos.baidu.com/image/pic/item/8601a18b87d6277f59504b3a2b381f30e924fc76.jpg";
        final String url3 = "http://g.hiphotos.baidu.com/image/pic/item/e1fe9925bc315c60ba5154c68eb1cb13485477d6.jpg";
        final String url4 = "http://e.hiphotos.baidu.com/image/pic/item/6159252dd42a2834a082bf2858b5c9ea15cebfe9.jpg";
         urls.add(url1);
         urls.add(url2);
         urls.add(url3);
         urls.add(url4);
         adViewPager.setImagerUrls(urls);
  ```
  
 - **3.设置点击事件**
  
  使用setAdClickListener方法设置点击事件
  例如:
  
  
```
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
```


- **4.注意**
   当Activity销毁的时候,要调用stopTimer()方法
   
   ```
    @Override
    protected void onDestroy() {
        super.onDestroy();
        adViewPager.stopTimer();
    }
   ```
   
   要添加联网权限,因为有用到的网络请求图片
   
   ```
    <uses-permission android:name="android.permission.INTERNET" />

   ```
   不要同时使用setImagerUrls和setImagerRescoures方法.
  
   

