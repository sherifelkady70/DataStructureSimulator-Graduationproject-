package com.must.datastructuressimulator;

import android.content.Context;
import android.util.TypedValue;
import android.view.Window;
import android.view.WindowManager;

public class UIHelper {

    public static void hideActivityStatusBar(Window window)
    {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static float dpToPx(Context context, int dp){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static float pixelsToSp(Context context, float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px/scaledDensity;
    }

}
