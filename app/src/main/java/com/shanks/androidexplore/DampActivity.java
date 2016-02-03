package com.shanks.androidexplore;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by shanksYao on 1/29/16.
 */
public class DampActivity extends Activity {


    private ViewPager viewPager;
    private int[] colors = {R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark};
    float distance = 0;
    float x;
    float factor = 0.3f;
    boolean kaka;
     ValueAnimator animator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.damp_layout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.item, null);
                Log.e("color", position + "-" + colors[position]);
                view.setBackgroundColor(getResources().getColor(colors[position]));
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                //   super.destroyItem(container, position, object);
            }
        });
        viewPager.setOverScrollMode(View.OVER_SCROLL_NEVER);

        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("x", event.getX() + "--");
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if(animator!=null)
                        animator.cancel();
                    distance = 0;
                    x = event.getX();
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    distance = -event.getX() + x;
                    x = event.getX();
                } else {
                    x = 0;
                    distance = 0;
                    if (kaka) {
                        animator = ValueAnimator.ofFloat(-viewPager.getTranslationX());
                        animator.setDuration(200);
                       // animator.setInterpolator(new AnticipateInterpolator());
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) findViewById(R.id.tt).getLayoutParams();
                        final int rightMargin = params.rightMargin;
                        final float translationX = viewPager.getTranslationX();
                        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(final ValueAnimator animation) {

                                float kk = (float) animation.getAnimatedValue();
                                Log.e("ValueAnimator", kk + "-----");
                                int ii = (int) kk;
                                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) findViewById(R.id.tt).getLayoutParams();
                                params.rightMargin = rightMargin - ii;
                                findViewById(R.id.tt).setLayoutParams(params);
                                viewPager.setTranslationX(translationX + ii);
                                if(viewPager.getTranslationX()>=0){
                                  //  animation.cancel();
                                    viewPager.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            animator.cancel();
                                        }
                                    });

                                }
                            }
                        });
                        animator.start();
                        kaka = false;
                    }

                }

                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int ii = (int) (distance * factor);
                Log.e("onPageScrolled", position + "-" + positionOffset + "-" + positionOffsetPixels + "==" + ii);
                if (position == 2 && positionOffset == 0) {
                    kaka = true;
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) findViewById(R.id.tt).getLayoutParams();
                    if ((params.rightMargin + ii) > 0) {
                        return;
                    }
                    if ((params.rightMargin + ii) > -50) {
                        factor = 0.2f;
                    }
                    x += -ii;
                    params.rightMargin = params.rightMargin + ii;
                    findViewById(R.id.tt).setLayoutParams(params);
                    viewPager.setTranslationX(viewPager.getTranslationX() - ii);
                    Log.e("TRA", "--" + viewPager.getTranslationX());
                } else {
                    kaka = false;
                }
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("onPageSelected", "" + position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("Changed", "" + state);
            }
        });
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.beginFakeDrag();
                viewPager.fakeDragBy(200);
                //  viewPager.endFakeDrag();
            }
        });
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  viewPager.beginFakeDrag();
                viewPager.fakeDragBy(200);*/
                viewPager.endFakeDrag();
            }
        });

    }
}
