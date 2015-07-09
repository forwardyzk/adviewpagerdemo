package com.ad.pager.view;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ad.pager.R;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by yang on 15/7/8.
 */
public class AdViewPager extends RelativeLayout {
    private final String TAG = AdViewPager.class.getSimpleName();
    private ViewPager mAdViewpager;
    private LinearLayout mAdBottomPointRl;
    private LinearLayout.LayoutParams mPointParams;
    private LayoutParams mAdViewParams;
    private List<ImageView> mAdPointList;
    private Context mContext;
    private int ADVIEWCOUNT = 0;//广告条的个数
    private int ADAPTERCOUNT;//适配器加载的个数
    private List<Integer> mImageIds;//本地资源的ids
    private List<String> mImageUrls;//网络Url
    private boolean mIsTouchingViewpager = false;
    private int auto_trun_postion = 0;
    private int type = -1;//0:表示网络url 1:表示本地资源 -1表示没有设置资源
    private TimerTask mTimerTask = new TimerTask() {
        @Override
        public void run() {
            if (!mIsTouchingViewpager) {
                auto_trun_postion = (mAdViewpager.getCurrentItem() + 1) % ADAPTERCOUNT;
                ((Activity) mContext).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdViewpager.setCurrentItem(auto_trun_postion);
                    }
                });
            }
        }
    };
    private Timer mTimer = new Timer();
    private AdClickListener mAdClickListener;

    public AdViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.ad_viewpager_rl, this, true);
        mAdViewpager = (ViewPager) findViewById(R.id.ad_viewpager);
        mAdBottomPointRl = (LinearLayout) findViewById(R.id.ad_bottom_point);
        mAdPointList = new ArrayList<ImageView>();
        initPointParams();
        initImageLoader();
    }

    private void initImageLoader() {
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(mContext)
                .diskCacheSize(10 * 1024 * 1024).memoryCacheSize(5 * 1024 * 11024).memoryCache(new LruMemoryCache(5 * 1024 * 1024))
                .build();
        ImageLoader.getInstance().init(configuration);

    }

    /**
     * 初始化Point的
     */
    private void initPointParams() {
        mPointParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mPointParams.setMargins(5, 0, 5, 0);

        mAdViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    /**
     * 设置广告条展示Url集合
     *
     * @param urls
     */
    public void setImagerUrls(List<String> urls) {
        type = 0;
        ADVIEWCOUNT = urls == null ? 0 : urls.size();
        ADAPTERCOUNT = ADVIEWCOUNT * 5;
        mImageUrls = urls;
        initPoint(ADVIEWCOUNT);
        initViewPager();
    }

    /**
     * 设置本地资源的文件
     *
     * @param imageIds 资源文件的id集合
     */
    public void setImagerRescoures(List<Integer> imageIds) {
        type = 1;
        ADVIEWCOUNT = imageIds == null ? 0 : imageIds.size();
        ADAPTERCOUNT = ADVIEWCOUNT * 5;
        mImageIds = imageIds;
        initPoint(ADVIEWCOUNT);
        initViewPager();
    }

    /**
     * 初始化Viewpager
     */
    private void initViewPager() {
        mAdViewpager.setAdapter(new AdAdapter());
        mAdViewpager.setCurrentItem(ADVIEWCOUNT * 2 - 1);
        setSecltedPoint(ADVIEWCOUNT * 2 - 1);
        mAdViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setSecltedPoint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mAdViewpager.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_MOVE:
                        mIsTouchingViewpager = true;
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_OUTSIDE:
                        mIsTouchingViewpager = false;
                        break;
                }

                return false;
            }
        });
        mTimer.schedule(mTimerTask, 100l, 4000l);
    }

    /**
     * 初始化底部的点
     *
     * @param size
     */
    private void initPoint(int size) {
        ImageView point;
        for (int i = 0; i < size; i++) {
            point = new ImageView(mContext);
            point.setLayoutParams(mPointParams);
            //默认状态下不选中
            point.setImageResource(R.mipmap.icon_ad_normal);
            //添加到文件中
            mAdBottomPointRl.addView(point);
            mAdPointList.add(point);
        }
    }

    /**
     * 更新底部Point的状态
     *
     * @param position
     */
    private void setSecltedPoint(int position) {
        position %= ADVIEWCOUNT;
        for (int i = 0; i < ADVIEWCOUNT; i++) {
            if (position == i) {
                mAdPointList.get(i).setImageResource(R.mipmap.icon_ad_selected);
            } else {
                mAdPointList.get(i).setImageResource(R.mipmap.icon_ad_normal);
            }

        }
    }

    /**
     * 暂停轮训,当界面销毁的时候
     */
    public void stopTimer() {
        mTimer.cancel();
    }

    /**
     * ViewPager的适配器
     */
    private class AdAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return ADAPTERCOUNT;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            position %= ADVIEWCOUNT;

            ImageView view = new ImageView(mContext);
            if (type == 0) {//网络
                ImageLoader.getInstance().displayImage(mImageUrls.get(position), view);
            } else if (type == 1) {//本地资源
                view.setImageResource(mImageIds.get(position));
            }
            view.setLayoutParams(mAdViewParams);
            if (mAdClickListener != null) {
                final int currentPosition = position;
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAdClickListener.clickAd(currentPosition);
                    }
                });
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((ImageView) object);
        }

        //在这里实现循环
        @Override
        public void finishUpdate(ViewGroup container) {
            super.finishUpdate(container);
            int position = mAdViewpager.getCurrentItem();//获取当前的显示的位置
            if (position == 0) {//表示当前展示的位置是0
                position = ADVIEWCOUNT;
                mAdViewpager.setCurrentItem(position, false);
            } else if (ADAPTERCOUNT - 1 == position) {//最后的位置
                position = ADVIEWCOUNT - 1;
                mAdViewpager.setCurrentItem(position, false);
            }
        }
    }

    /**
     * 设置点击广告条的点击事件
     *
     * @param adClickListener
     */
    public void setAdClickListener(AdClickListener adClickListener) {
        this.mAdClickListener = adClickListener;
    }

    /**
     * 点击监听
     */
    public interface AdClickListener {
        void clickAd(int position);
    }

}
