package com.shanks.androidexplore;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanks.androidexplore.tip.PopCommander;
import com.shanks.androidexplore.tip.PopManager;

public class PopWindowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_window);
        final ViewGroup container = (ViewGroup) findViewById(R.id.container);

        final PopManager popManager = new PopManager("pootest",getBaseContext());
        popManager.append(new PopCommander(getBaseContext()) {


            @Override
            public View getContentView() {
                View view= LayoutInflater.from(getBaseContext()).inflate(R.layout.pop_item, container, false);
                view.findViewById(R.id.del).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
                return view;
            }

            @Override
            public View getAnchorView() {
                return findViewById(R.id.hi);
            }

            @Override
            public Point getLocation() {
                return new Point(0, 0);
            }

            @Override
            public int getAnimationStyle() {
                return R.style.pop_anim;
            }
        });
        popManager.append(new PopCommander(getBaseContext()) {


            @Override
            public View getContentView() {
                View view= LayoutInflater.from(getBaseContext()).inflate(R.layout.pop_item, container, false);
                view.findViewById(R.id.del).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
                return view;
            }

            @Override
            public View getAnchorView() {
                return findViewById(R.id.pa);
            }

            @Override
            public Point getLocation() {
                return new Point(0, 0);
            }

            @Override
            public int getAnimationStyle() {
                return R.style.pop_anim;
            }
        });
        container.post(new Runnable() {
            @Override
            public void run() {
                popManager.show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
