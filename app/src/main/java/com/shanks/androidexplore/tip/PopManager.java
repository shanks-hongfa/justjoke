package com.shanks.androidexplore.tip;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanksYao on 2/19/16.
 */
public class PopManager {
    private List<PopCommander> mList;

    private int count;

    /**
     * @param popName 用来标识pop的行为
     */
    public PopManager(String popName, Context context) {
        mList = new ArrayList<>();
        SharedPreferences settings = context.getSharedPreferences("PopManager", Context.MODE_PRIVATE);
        count = settings.getInt(popName, 0);
        count++;
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(popName, count);
        editor.commit();
    }

    public void reset() {
        count = 1;
    }


    public PopManager append(final PopCommander popCommander) {
        if (count > 1) return this;
        mList.add(popCommander);
        if (mList.size() > 1) {
            mList.get(mList.size() - 2).getPopupWindow().setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    Point point = popCommander.getLocation();
                    popCommander.getPopupWindow().showAsDropDown(popCommander.getAnchorView(), point.x, point.y);
                }
            });
        }
        return this;
    }

    public void show() {
        if (mList.size() > 0) {
            PopCommander popCommander = mList.get(0);
            Point point = popCommander.getLocation();
            popCommander.getPopupWindow().showAsDropDown(popCommander.getAnchorView(), point.x, point.y);
        }
    }
}
