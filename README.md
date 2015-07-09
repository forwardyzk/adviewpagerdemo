##自定义的广告条控件
- 支持本地图片资源和网络资源
- 自动播放和循环显示
- 自动填充底部指针按钮
- 有点击事件- 

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

   

