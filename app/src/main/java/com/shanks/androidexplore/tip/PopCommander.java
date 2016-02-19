package com.shanks.androidexplore.tip;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.PopupWindow;

/**
 * Created by shanksYao on 2/19/16.
 */
public abstract class PopCommander {
    /**
     * 设置展示的view
     */
    public abstract View getContentView();

    /**
     * 设置被挂载的view
     */
    public abstract View getAnchorView();

    /**
     * 设置popView的位置
     */
    public abstract Point getLocation();

    /**
     * 消失和出现时的动画
     */
    public abstract int getAnimationStyle();

    private PopupWindow popupWindow;

    public PopCommander(Context context) {
        popupWindow = new PopupWindow(context);
        popupWindow.setContentView(getContentView());
        popupWindow.setWidth(ActionBar.LayoutParams.WRAP_CONTENT);
        popupWindow.setHeight(ActionBar.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setAnimationStyle(getAnimationStyle());
    }

    public void dismiss() {
        popupWindow.dismiss();
    }

    PopupWindow getPopupWindow() {
        return popupWindow;
    }

}
