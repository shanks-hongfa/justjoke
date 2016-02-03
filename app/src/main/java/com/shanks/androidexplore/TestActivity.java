package com.shanks.androidexplore;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;



/**
 * Created by shanksYao on 10/14/15.
 */
public class TestActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    boolean hide;
    boolean first = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        fragmentManager = getFragmentManager();
        findViewById(R.id.hook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE);
                fragmentTransaction.setCustomAnimations(R.animator.in_, R.animator.out_, R.animator.in_, R.animator.out_);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.container, new TestFragment());
                fragmentTransaction.commit();

                // findViewById(R.id.hook).startAnimation(AnimationUtils.loadAnimation(getBaseContext(), R.anim.in));
                Animator animator = AnimatorInflater.loadAnimator(getBaseContext(), R.animator.in_);
                v.setPivotY(0);
                v.setPivotX(0);
                animator.setTarget(v);
                animator.start();


            }
        });
        findViewById(R.id.hook1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE);
                fragmentTransaction.setCustomAnimations(R.animator.in_, R.animator.out_, R.animator.in_, R.animator.out_);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.container,new TestFragment1());

                fragmentTransaction.commit();
                Animator animator = AnimatorInflater.loadAnimator(getBaseContext(), R.animator.in_);
                animator.setTarget(v);
                animator.start();


            }
        });

    }

    @Override
    public void onBackPressed() {
      //  super.onBackPressed();
        if(fragmentManager.getBackStackEntryCount()<=1){
            super.onBackPressed();
            return;
        }
       fragmentManager.popBackStack();
        ///会执行到pop的动画
    }
}
